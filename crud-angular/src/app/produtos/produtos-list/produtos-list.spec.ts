import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdutosList } from './produtos-list';

describe('ProdutosList', () => {
  let component: ProdutosList;
  let fixture: ComponentFixture<ProdutosList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProdutosList],
    }).compileComponents();

    fixture = TestBed.createComponent(ProdutosList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
