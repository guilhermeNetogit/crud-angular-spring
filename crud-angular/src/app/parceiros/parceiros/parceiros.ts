import { Component, inject } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ParceirosService } from '../../../../services/parceiros';
import { AppMaterialModule } from '../../shared/app-material/app-material-module';
import { catchError, Observable, of, tap } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { ErrorDialog } from '../../shared/components/error-dialog/error-dialog';
import { CategoryPipe } from '../../shared/pipes/category-pipe';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute, Router } from '@angular/router';

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
  imports: [AppMaterialModule, AsyncPipe, CategoryPipe, MatIconModule],
  templateUrl: './parceiros.html',
  styleUrl: './parceiros.scss',
})
export class Parceiros {
  displayedColumns: string[] = ['position', 'name', 'symbol', 'weight', 'actions'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  /*dataSource = new MatTableDataSource(ELEMENT_DATA);*/
  dataSource = new MatTableDataSource<PeriodicElement>([]);

  onAdd() {
    console.log('Abrindo formulário de criação...');
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  editar(id: number) {
    console.log('Editando registro: ', id);
  }

  deletar(id: number) {
    console.log('Excluindo registro: ', id);
  }

  parceiros$: Observable<PeriodicElement[]>;
  private dialog = inject(MatDialog);

  constructor(
    private parceirosService: ParceirosService,
    private router: Router,
    private route: ActivatedRoute) {
    console.log('Iniciando busca...'); // <--- Teste 1
    this.parceiros$ = this.parceirosService.findAll().pipe(
      catchError((error) => {
        console.error('ERRO REAL AQUI:', error);
        this.openError('Não foi possível carregar dados!');
        return of([]);
      }),
      tap((dados) => {
        console.log('Dados chegaram:', dados); // <--- Teste 2
        this.dataSource.data = dados;
      }),
    );
  }

  openError(errorMsg: string) {
    this.dialog.open(ErrorDialog, {
      data: errorMsg,
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
