import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITestFarouk, TestFarouk } from '../test-farouk.model';
import { TestFaroukService } from '../service/test-farouk.service';

@Component({
  selector: 'jhi-test-farouk-update',
  templateUrl: './test-farouk-update.component.html',
})
export class TestFaroukUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    adresse: [],
    history: [],
  });

  constructor(protected testFaroukService: TestFaroukService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ testFarouk }) => {
      this.updateForm(testFarouk);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const testFarouk = this.createFromForm();
    if (testFarouk.id !== undefined) {
      this.subscribeToSaveResponse(this.testFaroukService.update(testFarouk));
    } else {
      this.subscribeToSaveResponse(this.testFaroukService.create(testFarouk));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITestFarouk>>): void {
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

  protected updateForm(testFarouk: ITestFarouk): void {
    this.editForm.patchValue({
      id: testFarouk.id,
      nom: testFarouk.nom,
      adresse: testFarouk.adresse,
      history: testFarouk.history,
    });
  }

  protected createFromForm(): ITestFarouk {
    return {
      ...new TestFarouk(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      history: this.editForm.get(['history'])!.value,
    };
  }
}
