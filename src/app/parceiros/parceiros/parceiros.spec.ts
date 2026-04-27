import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Parceiros } from './parceiros';

describe('Parceiros', () => {
  let component: Parceiros;
  let fixture: ComponentFixture<Parceiros>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Parceiros],
    }).compileComponents();

    fixture = TestBed.createComponent(Parceiros);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
