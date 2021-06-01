import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TestFaroukComponent } from '../list/test-farouk.component';
import { TestFaroukDetailComponent } from '../detail/test-farouk-detail.component';
import { TestFaroukUpdateComponent } from '../update/test-farouk-update.component';
import { TestFaroukRoutingResolveService } from './test-farouk-routing-resolve.service';

const testFaroukRoute: Routes = [
  {
    path: '',
    component: TestFaroukComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TestFaroukDetailComponent,
    resolve: {
      testFarouk: TestFaroukRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TestFaroukUpdateComponent,
    resolve: {
      testFarouk: TestFaroukRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TestFaroukUpdateComponent,
    resolve: {
      testFarouk: TestFaroukRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(testFaroukRoute)],
  exports: [RouterModule],
})
export class TestFaroukRoutingModule {}
