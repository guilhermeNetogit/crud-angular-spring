import { Routes } from '@angular/router';

export const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'parceiros'},
  {path: 'parceiros',
    loadChildren: () => import('./parceiros/parceiros.module').then(m => m.ParceirosModule)
  }
];
