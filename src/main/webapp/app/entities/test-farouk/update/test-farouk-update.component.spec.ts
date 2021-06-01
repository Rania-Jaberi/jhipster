jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TestFaroukService } from '../service/test-farouk.service';
import { ITestFarouk, TestFarouk } from '../test-farouk.model';

import { TestFaroukUpdateComponent } from './test-farouk-update.component';

describe('Component Tests', () => {
  describe('TestFarouk Management Update Component', () => {
    let comp: TestFaroukUpdateComponent;
    let fixture: ComponentFixture<TestFaroukUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let testFaroukService: TestFaroukService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TestFaroukUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TestFaroukUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TestFaroukUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      testFaroukService = TestBed.inject(TestFaroukService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const testFarouk: ITestFarouk = { id: 456 };

        activatedRoute.data = of({ testFarouk });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(testFarouk));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const testFarouk = { id: 123 };
        spyOn(testFaroukService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ testFarouk });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: testFarouk }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(testFaroukService.update).toHaveBeenCalledWith(testFarouk);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const testFarouk = new TestFarouk();
        spyOn(testFaroukService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ testFarouk });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: testFarouk }));
        saveSubject.complete();

        // THEN
        expect(testFaroukService.create).toHaveBeenCalledWith(testFarouk);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const testFarouk = { id: 123 };
        spyOn(testFaroukService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ testFarouk });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(testFaroukService.update).toHaveBeenCalledWith(testFarouk);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
