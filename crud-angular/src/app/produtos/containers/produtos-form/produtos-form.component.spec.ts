import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdutosFormComponent } from './produtos-form.component';

describe('ProdutosFormComponent', () => {
  let component: ProdutosFormComponent;
  let fixture: ComponentFixture<ProdutosFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProdutosFormComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ProdutosFormComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
