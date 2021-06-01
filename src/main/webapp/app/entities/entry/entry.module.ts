import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EntryComponent } from './list/entry.component';
import { EntryDetailComponent } from './detail/entry-detail.component';
import { EntryUpdateComponent } from './update/entry-update.component';
import { EntryDeleteDialogComponent } from './delete/entry-delete-dialog.component';
import { EntryRoutingModule } from './route/entry-routing.module';

@NgModule({
  imports: [SharedModule, EntryRoutingModule],
  declarations: [EntryComponent, EntryDetailComponent, EntryUpdateComponent, EntryDeleteDialogComponent],
  entryComponents: [EntryDeleteDialogComponent],
})
export class EntryModule {}
