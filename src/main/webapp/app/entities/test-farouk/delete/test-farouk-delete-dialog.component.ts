import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITestFarouk } from '../test-farouk.model';
import { TestFaroukService } from '../service/test-farouk.service';

@Component({
  templateUrl: './test-farouk-delete-dialog.component.html',
})
export class TestFaroukDeleteDialogComponent {
  testFarouk?: ITestFarouk;

  constructor(protected testFaroukService: TestFaroukService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.testFaroukService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
