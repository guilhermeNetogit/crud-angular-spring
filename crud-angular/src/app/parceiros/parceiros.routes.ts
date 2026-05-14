import { Routes } from "@angular/router";
import { ParceirosForm } from "./containers/parceiros-form/parceiros-form";
import { Parceiros } from "./containers/parceiros/parceiros";
import { ParceirosResolver } from "./guards/parceiros-resolver";

export const PARCEIROS_ROUTES: Routes = [
  { path: '', component: Parceiros },
  { path: 'new', component: ParceirosForm,  resolve: { parceiro: ParceirosResolver }  },
  { path: 'edit/:id', component: ParceirosForm, resolve: { parceiro: ParceirosResolver } }
];
