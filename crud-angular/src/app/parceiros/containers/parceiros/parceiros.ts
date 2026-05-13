import { AsyncPipe } from '@angular/common';
import { Component, inject, signal, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject, catchError, Observable, of, switchMap, tap } from 'rxjs';
import { AppMaterialModule } from '../../../shared/app-material/app-material-module';
import { ErrorDialog } from '../../../shared/components/error-dialog/error-dialog';
import { ParceirosList } from "../../components/parceiros-list/parceiros-list";
import { ParceirosService } from '../../services/parceiros';
import { ConfirmationDialogComponent } from '../../../shared/components/confirmation-dialog/confirmation-dialog';
import { ParceiroPage } from '../../models/parceiro-page';
import { Parceiro } from '../../models/parceiro';
import { MatPaginator, MatPaginatorModule, PageEvent} from '@angular/material/paginator';

export interface PeriodicElement {
  id: number;
  position: number;
  name: string;
  weight: number;
  symbol: string;
}

@Component({
  selector: 'app-parceiros',
  standalone: true,
  imports: [AppMaterialModule, AsyncPipe, MatSortModule, ParceirosList, MatPaginatorModule],
  templateUrl: './parceiros.html',
  styleUrl: './parceiros.scss',
})

export class Parceiros {

  loadingError = signal(false);
  private dialog = inject(MatDialog);  /*dataSource = new MatTableDataSource(ELEMENT_DATA);*/
  dataSource = new MatTableDataSource<Parceiro>([]);
  input: any;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  pageIndex = 0;
  pageSize = 10;

  private readonly refresh$ = new BehaviorSubject<void>(undefined);

  constructor(
    private parceirosService: ParceirosService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar) {
    console.log('Iniciando busca...');
  }

  refresh() {
      this.refresh$.next();
  }

  filterValue = '';

  readonly parceiros$: Observable<ParceiroPage> = this.refresh$.pipe(
    tap(() => this.loadingError.set(false)),
    switchMap(() => this.parceirosService.findAll(this.pageIndex, this.pageSize, this.filterValue).pipe(
      catchError((error) => {
        console.error('ERRO NO SERVIDOR DE BANCO DE DADOS:', error);
        this.loadingError.set(true);
        this.openError('Não foi possível carregar os dados!');
        return of({parceiros: [], totalElements: 0, totalPages: 0});
      }),
      tap((dados) => {
        console.log('Dados chegaram:', dados);
        this.dataSource.data = dados.parceiros || [];
      })
    ))
  );

  applyFilter(texto: string) {
    this.filterValue = texto;
    this.pageIndex = 0;
    this.refresh();
  }

  edit($event: number) {
      throw new Error('Method not implemented.');
  }

  onAdd() {
    console.log('Abrindo formulário de criação...');
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  onEdit(id: number) {
    console.log('Editando registro: ', id);
    this.router.navigate(['edit', id], {relativeTo: this.route});
  }

  onDelete(id: number) {
    const parceiroName = this.dataSource.data.find(p => p.id === id)?.name;
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
          width: '350px',
          data: { name: parceiroName }
        });
    console.log('Excluindo registro: ', id);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.parceirosService.delete(id).subscribe({
          next: () => {
            this.refresh();
            this.snackBar.open('Registro deletado com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center'
            });
          },
          error: () => this.openError('Erro ao tentar deletar registro.')
        });
      }
    });
  }

  openError(errorMsg: string) {
    this.dialog.open(ErrorDialog, {
      data: errorMsg,
    });
  }

  onPageChange(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.refresh();
  }
}
