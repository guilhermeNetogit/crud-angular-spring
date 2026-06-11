import { Component } from '@angular/core';
import { MatCard } from "@angular/material/card";
import { MatProgressSpinner } from "@angular/material/progress-spinner";
import { MatIcon } from "@angular/material/icon";
import { MatPaginator } from "@angular/material/paginator";

export interface Produto {
  codprod: number;
  descrprod: string;
  compldesc?: string;
  codvol: string;
  eangtin?: string;
  referencia?: string;
}

export interface ProdutoPage {
  produtos: Produto[];
  totalProdutos: number;
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
