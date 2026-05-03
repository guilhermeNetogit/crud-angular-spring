import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParceirosForm } from './parceiros-form';

describe('ParceirosForm', () => {
  let component: ParceirosForm;
  let fixture: ComponentFixture<ParceirosForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ParceirosForm],
    }).compileComponents();

    fixture = TestBed.createComponent(ParceirosForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
