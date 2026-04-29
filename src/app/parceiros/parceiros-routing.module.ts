import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Parceiros } from './parceiros/parceiros';

const routes: Routes = [
  {path: '', component: Parceiros}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ParceirosRoutingModule {}
