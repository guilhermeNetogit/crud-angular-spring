import { Component } from '@angular/core';
import { MatCard } from "@angular/material/card";
import { MatProgressSpinner } from "@angular/material/progress-spinner";
import { MatIcon } from "@angular/material/icon";
import { MatPaginator } from "@angular/material/paginator";

export interface Produto {
  CODPROD: number;
  DESCPROD: string;
  COMPLDESC: string;
  CODVOL: string;
  EANGTIN: string;
  REFERENCIA: string;
  DTCREATED: string;
  DTALTER: string;
}

export interface ProdutoPage {
  produtos: Produto[];
  totalElements: number;
  totalPages: number;
}
/*
@Component({
  selector: 'app-produtos.component',
  imports: [MatCard, MatProgressSpinner, MatIcon, MatPaginator],
  templateUrl: '../produtos.component.html',
  styleUrl: '../produtos.component.scss',
})

export class ProdutosComponent {
produtos$: any;
loadingError() {
throw new Error('Method not implemented.');
}
refresh() {
throw new Error('Method not implemented.');
}
pageIndex: any;
pageSize: any;
onPageChange($event: Event) {
throw new Error('Method not implemented.');
}
clearFilter() {
throw new Error('Method not implemented.');
}
}*/
