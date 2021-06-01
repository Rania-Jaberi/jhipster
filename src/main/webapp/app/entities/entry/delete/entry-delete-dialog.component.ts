import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntry } from '../entry.model';
import { EntryService } from '../service/entry.service';

@Component({
  templateUrl: './entry-delete-dialog.component.html',
})
export class EntryDeleteDialogComponent {
  entry?: IEntry;

  constructor(protected entryService: EntryService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.entryService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
