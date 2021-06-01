import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TestFaroukService } from '../service/test-farouk.service';

import { TestFaroukComponent } from './test-farouk.component';

describe('Component Tests', () => {
  describe('TestFarouk Management Component', () => {
    let comp: TestFaroukComponent;
    let fixture: ComponentFixture<TestFaroukComponent>;
    let service: TestFaroukService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TestFaroukComponent],
      })
        .overrideTemplate(TestFaroukComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TestFaroukComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TestFaroukService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ id: 123 }],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.testFarouks?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
