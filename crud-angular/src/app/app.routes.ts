import { Routes } from '@angular/router';

export const APP_ROUTES: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'parceiros'},
  {path: 'parceiros',
    loadChildren: () => import('./parceiros/parceiros.routes').then(m => m.PARCEIROS_ROUTES)
  }
];
