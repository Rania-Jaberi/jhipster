jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ITestFarouk, TestFarouk } from '../test-farouk.model';
import { TestFaroukService } from '../service/test-farouk.service';

import { TestFaroukRoutingResolveService } from './test-farouk-routing-resolve.service';

describe('Service Tests', () => {
  describe('TestFarouk routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TestFaroukRoutingResolveService;
    let service: TestFaroukService;
    let resultTestFarouk: ITestFarouk | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TestFaroukRoutingResolveService);
      service = TestBed.inject(TestFaroukService);
      resultTestFarouk = undefined;
    });

    describe('resolve', () => {
      it('should return ITestFarouk returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTestFarouk = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTestFarouk).toEqual({ id: 123 });
      });

      it('should return new ITestFarouk if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTestFarouk = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTestFarouk).toEqual(new TestFarouk());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultTestFarouk = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTestFarouk).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});