import { TestBed } from '@angular/core/testing';

import { Parceiro } from './parceiros';

describe('Parceiro', () => {
  let service: Parceiro;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Parceiro);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
