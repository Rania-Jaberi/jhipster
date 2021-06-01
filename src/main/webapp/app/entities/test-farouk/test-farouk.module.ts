import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TestFaroukComponent } from './list/test-farouk.component';
import { TestFaroukDetailComponent } from './detail/test-farouk-detail.component';
import { TestFaroukUpdateComponent } from './update/test-farouk-update.component';
import { TestFaroukDeleteDialogComponent } from './delete/test-farouk-delete-dialog.component';
import { TestFaroukRoutingModule } from './route/test-farouk-routing.module';

@NgModule({
  imports: [SharedModule, TestFaroukRoutingModule],
  declarations: [TestFaroukComponent, TestFaroukDetailComponent, TestFaroukUpdateComponent, TestFaroukDeleteDialogComponent],
  entryComponents: [TestFaroukDeleteDialogComponent],
})
export class TestFaroukModule {}
