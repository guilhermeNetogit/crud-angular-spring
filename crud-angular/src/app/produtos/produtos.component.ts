import { AsyncPipe, CommonModule } from '@angular/common';
import { Component, inject, signal, ViewChild } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError, switchMap, tap } from 'rxjs/operators';

import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { ErrorDialog } from '../shared/components/error-dialog/error-dialog';
import { Produto, ProdutoPage } from './models/produto';
import { ProdutosService } from './services/produtos.service';
import { ConfirmationDialogComponent } from '../shared/components/confirmation-dialog/confirmation-dialog';
import { ProdutosList } from './produtos-list/produtos-list';

export interface ProdutosTable {
  codprod: number;
  descrprod: string;
  compldesc: string;
  codvol: string;
  referencia: string;
  eangtin: string;
}

@Component({
  selector: 'app-produtos',
  standalone: true,
  imports: [AsyncPipe, CommonModule,
    MatCardModule, MatIconModule,
    MatProgressSpinnerModule, MatPaginatorModule,
    ProdutosList, MatSortModule
  ],
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent {
  loadingError = signal(false);
  private dialog = inject(MatDialog);
  dataSource = new MatTableDataSource<Produto>([]);
  input: any;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  pageIndex = 0;
  pageSize = 10;
  filterValue = '';

  private readonly refresh$ = new BehaviorSubject<void>(undefined);

  constructor(
    private produtosService: ProdutosService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    console.log('Iniciando busca de produtos...');
  }

  refresh() {
    this.refresh$.next();
  }

  readonly produtos$: Observable<ProdutoPage> = this.refresh$.pipe(
      tap(() => this.loadingError.set(false)),
      switchMap(() =>
        this.produtosService.findAll(this.pageIndex, this.pageSize, this.filterValue).pipe(
          catchError((error) => {
            console.error('ERRO NO SERVIDOR DE BANCO DE DADOS:', error);
            this.loadingError.set(true);
            this.openError('Não foi possível carregar os dados!');
            this.dataSource.data = [];
            return of({ produtos: [], totalProdutos: 0, totalPages: 0 });
          }),
          tap((dados) => {
            console.log('Dados chegaram:', dados);
            this.dataSource.data = [...(dados.produtos || [])];
          }),
        ),
      ),
  );

  applyFilter(texto: string) {
    this.filterValue = texto.trim();
    this.pageIndex = 0;
    this.refresh();
  }

  clearFilter() {
    this.filterValue = '';
    this.pageIndex = 0;
    this.refresh();
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(id: number) {
    this.router.navigate(['edit', id], { relativeTo: this.route });
  }

  onDelete(id: number) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '350px',
      data: { name: `Produto Código ${id}` },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.produtosService.delete(id).subscribe({
          next: () => {
            this.refresh();
            this.snackBar.open('Produto deletado com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center',
            });
          },
          error: () => this.openError('Erro ao tentar deletar o produto.'),
        });
      }
    });
  }

  openError(errorMsg: string) {
      this.dialog.open(ErrorDialog, {
        data: errorMsg,
      });
  }

  onPageChange(pageEvent: PageEvent) {
    this.pageIndex = pageEvent.pageIndex;
    this.pageSize = pageEvent.pageSize;
    this.refresh();
  }
}
