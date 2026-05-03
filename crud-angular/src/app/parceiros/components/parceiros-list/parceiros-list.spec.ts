import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParceirosList } from './parceiros-list';

describe('ParceirosList', () => {
  let component: ParceirosList;
  let fixture: ComponentFixture<ParceirosList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ParceirosList],
    }).compileComponents();

    fixture = TestBed.createComponent(ParceirosList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
