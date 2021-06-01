import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITestFarouk, getTestFaroukIdentifier } from '../test-farouk.model';

export type EntityResponseType = HttpResponse<ITestFarouk>;
export type EntityArrayResponseType = HttpResponse<ITestFarouk[]>;

@Injectable({ providedIn: 'root' })
export class TestFaroukService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/test-farouks');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(testFarouk: ITestFarouk): Observable<EntityResponseType> {
    return this.http.post<ITestFarouk>(this.resourceUrl, testFarouk, { observe: 'response' });
  }

  update(testFarouk: ITestFarouk): Observable<EntityResponseType> {
    return this.http.put<ITestFarouk>(`${this.resourceUrl}/${getTestFaroukIdentifier(testFarouk) as number}`, testFarouk, {
      observe: 'response',
    });
  }

  partialUpdate(testFarouk: ITestFarouk): Observable<EntityResponseType> {
    return this.http.patch<ITestFarouk>(`${this.resourceUrl}/${getTestFaroukIdentifier(testFarouk) as number}`, testFarouk, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITestFarouk>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITestFarouk[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTestFaroukToCollectionIfMissing(
    testFaroukCollection: ITestFarouk[],
    ...testFarouksToCheck: (ITestFarouk | null | undefined)[]
  ): ITestFarouk[] {
    const testFarouks: ITestFarouk[] = testFarouksToCheck.filter(isPresent);
    if (testFarouks.length > 0) {
      const testFaroukCollectionIdentifiers = testFaroukCollection.map(testFaroukItem => getTestFaroukIdentifier(testFaroukItem)!);
      const testFarouksToAdd = testFarouks.filter(testFaroukItem => {
        const testFaroukIdentifier = getTestFaroukIdentifier(testFaroukItem);
        if (testFaroukIdentifier == null || testFaroukCollectionIdentifiers.includes(testFaroukIdentifier)) {
          return false;
        }
        testFaroukCollectionIdentifiers.push(testFaroukIdentifier);
        return true;
      });
      return [...testFarouksToAdd, ...testFaroukCollection];
    }
    return testFaroukCollection;
  }
}
