import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestFaroukDetailComponent } from './test-farouk-detail.component';

describe('Component Tests', () => {
  describe('TestFarouk Management Detail Component', () => {
    let comp: TestFaroukDetailComponent;
    let fixture: ComponentFixture<TestFaroukDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TestFaroukDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ testFarouk: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TestFaroukDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TestFaroukDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load testFarouk on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.testFarouk).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
