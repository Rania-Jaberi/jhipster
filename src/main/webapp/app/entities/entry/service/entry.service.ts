import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEntry, getEntryIdentifier } from '../entry.model';

export type EntityResponseType = HttpResponse<IEntry>;
export type EntityArrayResponseType = HttpResponse<IEntry[]>;

@Injectable({ providedIn: 'root' })
export class EntryService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/entries');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(entry: IEntry): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entry);
    return this.http
      .post<IEntry>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(entry: IEntry): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entry);
    return this.http
      .put<IEntry>(`${this.resourceUrl}/${getEntryIdentifier(entry) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(entry: IEntry): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entry);
    return this.http
      .patch<IEntry>(`${this.resourceUrl}/${getEntryIdentifier(entry) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEntry>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEntry[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEntryToCollectionIfMissing(entryCollection: IEntry[], ...entriesToCheck: (IEntry | null | undefined)[]): IEntry[] {
    const entries: IEntry[] = entriesToCheck.filter(isPresent);
    if (entries.length > 0) {
      const entryCollectionIdentifiers = entryCollection.map(entryItem => getEntryIdentifier(entryItem)!);
      const entriesToAdd = entries.filter(entryItem => {
        const entryIdentifier = getEntryIdentifier(entryItem);
        if (entryIdentifier == null || entryCollectionIdentifiers.includes(entryIdentifier)) {
          return false;
        }
        entryCollectionIdentifiers.push(entryIdentifier);
        return true;
      });
      return [...entriesToAdd, ...entryCollection];
    }
    return entryCollection;
  }

  protected convertDateFromClient(entry: IEntry): IEntry {
    return Object.assign({}, entry, {
      date: entry.date?.isValid() ? entry.date.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? dayjs(res.body.date) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((entry: IEntry) => {
        entry.date = entry.date ? dayjs(entry.date) : undefined;
      });
    }
    return res;
  }
}
