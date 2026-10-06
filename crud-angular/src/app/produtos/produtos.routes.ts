import { Routes } from "@angular/router";
import { ProdutosComponent } from "./containers/produtos/produtos.component";
import { ProdutosFormComponent } from "./containers/produtos-form/produtos-form.component";


export const PRODUTOS_ROUTES: Routes = [
  { path: '', component: ProdutosComponent },
  { path: 'new', component: ProdutosFormComponent },
  { path: 'edit/:id', component: ProdutosFormComponent }
];
