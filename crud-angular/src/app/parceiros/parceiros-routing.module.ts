import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Parceiros } from './containers/parceiros/parceiros';
import { ParceirosForm } from './containers/parceiros-form/parceiros-form';

const routes: Routes = [
  { path: '', component: Parceiros },
  { path: 'new', component: ParceirosForm },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ParceirosRoutingModule {}
