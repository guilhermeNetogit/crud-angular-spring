import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { parceirosResolver } from './parceiros-resolver';

describe('parceirosResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) =>
    TestBed.runInInjectionContext(() => parceirosResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
