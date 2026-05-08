import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from '@angular/router';
import { of } from 'rxjs';
import { Parceiro } from '../models/parceiro';
import { ParceirosService } from '../services/parceiros';

export const ParceirosResolver: ResolveFn<Parceiro> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const service = inject(ParceirosService);
  const id = route.params['id'];

  if (route.params && id) {
    return service.loadById(id);
  }
  return of({ id: null as any, position: 1, name: '', weight: 1, symbol: '', contatos:[] });
};
