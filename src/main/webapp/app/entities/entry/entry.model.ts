import * as dayjs from 'dayjs';
import { IBlog } from 'app/entities/blog/blog.model';
import { ITag } from 'app/entities/tag/tag.model';

export interface IEntry {
  id?: number;
  title?: string;
  content?: string;
  date?: dayjs.Dayjs;
  blog?: IBlog | null;
  tags?: ITag[] | null;
}

export class Entry implements IEntry {
  constructor(
    public id?: number,
    public title?: string,
    public content?: string,
    public date?: dayjs.Dayjs,
    public blog?: IBlog | null,
    public tags?: ITag[] | null
  ) {}
}

export function getEntryIdentifier(entry: IEntry): number | undefined {
  return entry.id;
}
