import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITestFarouk, TestFarouk } from '../test-farouk.model';
import { TestFaroukService } from '../service/test-farouk.service';

@Injectable({ providedIn: 'root' })
export class TestFaroukRoutingResolveService implements Resolve<ITestFarouk> {
  constructor(protected service: TestFaroukService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITestFarouk> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((testFarouk: HttpResponse<TestFarouk>) => {
          if (testFarouk.body) {
            return of(testFarouk.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TestFarouk());
  }
}
