import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component/dashboard.component';

export const APP_ROUTES: Routes = [
  {path: '', component: DashboardComponent},
  {path: '', pathMatch: 'full', redirectTo: 'parceiros'},
  {path: 'parceiros',
    loadChildren: () => import('./parceiros/parceiros.routes').then(m => m.PARCEIROS_ROUTES)
  },
  {path: 'produtos',
    loadChildren: () => import('./produtos/produtos.routes').then(m => m.PRODUTOS_ROUTES)
    }
];
