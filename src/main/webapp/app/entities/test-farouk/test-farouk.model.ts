export interface ITestFarouk {
  id?: number;
  nom?: string | null;
  adresse?: string | null;
  history?: string | null;
}

export class TestFarouk implements ITestFarouk {
  constructor(public id?: number, public nom?: string | null, public adresse?: string | null, public history?: string | null) {}
}

export function getTestFaroukIdentifier(testFarouk: ITestFarouk): number | undefined {
  return testFarouk.id;
}
