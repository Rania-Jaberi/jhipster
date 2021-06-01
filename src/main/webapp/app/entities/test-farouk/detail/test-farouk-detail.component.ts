import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITestFarouk } from '../test-farouk.model';

@Component({
  selector: 'jhi-test-farouk-detail',
  templateUrl: './test-farouk-detail.component.html',
})
export class TestFaroukDetailComponent implements OnInit {
  testFarouk: ITestFarouk | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ testFarouk }) => {
      this.testFarouk = testFarouk;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
