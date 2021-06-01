import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITestFarouk, TestFarouk } from '../test-farouk.model';

import { TestFaroukService } from './test-farouk.service';

describe('Service Tests', () => {
  describe('TestFarouk Service', () => {
    let service: TestFaroukService;
    let httpMock: HttpTestingController;
    let elemDefault: ITestFarouk;
    let expectedResult: ITestFarouk | ITestFarouk[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TestFaroukService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        nom: 'AAAAAAA',
        adresse: 'AAAAAAA',
        history: 'AAAAAAA',
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TestFarouk', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TestFarouk()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TestFarouk', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            nom: 'BBBBBB',
            adresse: 'BBBBBB',
            history: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TestFarouk', () => {
        const patchObject = Object.assign(
          {
            adresse: 'BBBBBB',
            history: 'BBBBBB',
          },
          new TestFarouk()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TestFarouk', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            nom: 'BBBBBB',
            adresse: 'BBBBBB',
            history: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TestFarouk', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTestFaroukToCollectionIfMissing', () => {
        it('should add a TestFarouk to an empty array', () => {
          const testFarouk: ITestFarouk = { id: 123 };
          expectedResult = service.addTestFaroukToCollectionIfMissing([], testFarouk);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(testFarouk);
        });

        it('should not add a TestFarouk to an array that contains it', () => {
          const testFarouk: ITestFarouk = { id: 123 };
          const testFaroukCollection: ITestFarouk[] = [
            {
              ...testFarouk,
            },
            { id: 456 },
          ];
          expectedResult = service.addTestFaroukToCollectionIfMissing(testFaroukCollection, testFarouk);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TestFarouk to an array that doesn't contain it", () => {
          const testFarouk: ITestFarouk = { id: 123 };
          const testFaroukCollection: ITestFarouk[] = [{ id: 456 }];
          expectedResult = service.addTestFaroukToCollectionIfMissing(testFaroukCollection, testFarouk);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(testFarouk);
        });

        it('should add only unique TestFarouk to an array', () => {
          const testFaroukArray: ITestFarouk[] = [{ id: 123 }, { id: 456 }, { id: 58922 }];
          const testFaroukCollection: ITestFarouk[] = [{ id: 123 }];
          expectedResult = service.addTestFaroukToCollectionIfMissing(testFaroukCollection, ...testFaroukArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const testFarouk: ITestFarouk = { id: 123 };
          const testFarouk2: ITestFarouk = { id: 456 };
          expectedResult = service.addTestFaroukToCollectionIfMissing([], testFarouk, testFarouk2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(testFarouk);
          expect(expectedResult).toContain(testFarouk2);
        });

        it('should accept null and undefined values', () => {
          const testFarouk: ITestFarouk = { id: 123 };
          expectedResult = service.addTestFaroukToCollectionIfMissing([], null, testFarouk, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(testFarouk);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
