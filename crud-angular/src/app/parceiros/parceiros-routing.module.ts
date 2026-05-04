import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Parceiros } from './containers/parceiros/parceiros';
import { ParceirosForm } from './containers/parceiros-form/parceiros-form';
import { ParceirosResolver } from './guards/parceiros-resolver';

const routes: Routes = [
  { path: '', component: Parceiros },
  { path: 'new', component: ParceirosForm,  resolve: { parceiro: ParceirosResolver }  },
  { path: 'edit/:id', component: ParceirosForm, resolve: { parceiro: ParceirosResolver } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ParceirosRoutingModule {}
