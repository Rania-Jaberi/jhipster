import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EntryComponent } from '../list/entry.component';
import { EntryDetailComponent } from '../detail/entry-detail.component';
import { EntryUpdateComponent } from '../update/entry-update.component';
import { EntryRoutingResolveService } from './entry-routing-resolve.service';

const entryRoute: Routes = [
  {
    path: '',
    component: EntryComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EntryDetailComponent,
    resolve: {
      entry: EntryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EntryUpdateComponent,
    resolve: {
      entry: EntryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EntryUpdateComponent,
    resolve: {
      entry: EntryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(entryRoute)],
  exports: [RouterModule],
})
export class EntryRoutingModule {}
