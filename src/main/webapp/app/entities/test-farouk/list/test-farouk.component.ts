import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITestFarouk } from '../test-farouk.model';
import { TestFaroukService } from '../service/test-farouk.service';
import { TestFaroukDeleteDialogComponent } from '../delete/test-farouk-delete-dialog.component';

@Component({
  selector: 'jhi-test-farouk',
  templateUrl: './test-farouk.component.html',
})
export class TestFaroukComponent implements OnInit {
  testFarouks?: ITestFarouk[];
  isLoading = false;

  constructor(protected testFaroukService: TestFaroukService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.testFaroukService.query().subscribe(
      (res: HttpResponse<ITestFarouk[]>) => {
        this.isLoading = false;
        this.testFarouks = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: ITestFarouk): number {
    return item.id!;
  }

  delete(testFarouk: ITestFarouk): void {
    const modalRef = this.modalService.open(TestFaroukDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.testFarouk = testFarouk;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
