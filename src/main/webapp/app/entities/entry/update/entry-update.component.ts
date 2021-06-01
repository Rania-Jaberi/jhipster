import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IEntry, Entry } from '../entry.model';
import { EntryService } from '../service/entry.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { IBlog } from 'app/entities/blog/blog.model';
import { BlogService } from 'app/entities/blog/service/blog.service';
import { ITag } from 'app/entities/tag/tag.model';
import { TagService } from 'app/entities/tag/service/tag.service';

@Component({
  selector: 'jhi-entry-update',
  templateUrl: './entry-update.component.html',
})
export class EntryUpdateComponent implements OnInit {
  isSaving = false;

  blogsSharedCollection: IBlog[] = [];
  tagsSharedCollection: ITag[] = [];

  editForm = this.fb.group({
    id: [],
    title: [null, [Validators.required]],
    content: [null, [Validators.required]],
    date: [null, [Validators.required]],
    blog: [],
    tags: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected entryService: EntryService,
    protected blogService: BlogService,
    protected tagService: TagService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entry }) => {
      if (entry.id === undefined) {
        const today = dayjs().startOf('day');
        entry.date = today;
      }

      this.updateForm(entry);

      this.loadRelationshipsOptions();
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(
          new EventWithContent<AlertError>('constatApp.error', { ...err, key: 'error.file.' + err.key })
        ),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entry = this.createFromForm();
    if (entry.id !== undefined) {
      this.subscribeToSaveResponse(this.entryService.update(entry));
    } else {
      this.subscribeToSaveResponse(this.entryService.create(entry));
    }
  }

  trackBlogById(index: number, item: IBlog): number {
    return item.id!;
  }

  trackTagById(index: number, item: ITag): number {
    return item.id!;
  }

  getSelectedTag(option: ITag, selectedVals?: ITag[]): ITag {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntry>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(entry: IEntry): void {
    this.editForm.patchValue({
      id: entry.id,
      title: entry.title,
      content: entry.content,
      date: entry.date ? entry.date.format(DATE_TIME_FORMAT) : null,
      blog: entry.blog,
      tags: entry.tags,
    });

    this.blogsSharedCollection = this.blogService.addBlogToCollectionIfMissing(this.blogsSharedCollection, entry.blog);
    this.tagsSharedCollection = this.tagService.addTagToCollectionIfMissing(this.tagsSharedCollection, ...(entry.tags ?? []));
  }

  protected loadRelationshipsOptions(): void {
    this.blogService
      .query()
      .pipe(map((res: HttpResponse<IBlog[]>) => res.body ?? []))
      .pipe(map((blogs: IBlog[]) => this.blogService.addBlogToCollectionIfMissing(blogs, this.editForm.get('blog')!.value)))
      .subscribe((blogs: IBlog[]) => (this.blogsSharedCollection = blogs));

    this.tagService
      .query()
      .pipe(map((res: HttpResponse<ITag[]>) => res.body ?? []))
      .pipe(map((tags: ITag[]) => this.tagService.addTagToCollectionIfMissing(tags, ...(this.editForm.get('tags')!.value ?? []))))
      .subscribe((tags: ITag[]) => (this.tagsSharedCollection = tags));
  }

  protected createFromForm(): IEntry {
    return {
      ...new Entry(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      content: this.editForm.get(['content'])!.value,
      date: this.editForm.get(['date'])!.value ? dayjs(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined,
      blog: this.editForm.get(['blog'])!.value,
      tags: this.editForm.get(['tags'])!.value,
    };
  }
}
