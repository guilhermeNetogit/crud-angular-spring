import { Component, inject } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ParceirosService } from '../../../../services/parceiros';
import { AppMaterialModule } from '../../shared/app-material/app-material-module';
import { catchError, Observable, of, tap } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { ErrorDialog } from '../../shared/components/error-dialog/error-dialog';

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
  imports: [AppMaterialModule, AsyncPipe],
  templateUrl: './parceiros.html',
  styleUrl: './parceiros.scss',
})

export class Parceiros {
  displayedColumns: string[] = ['position', 'name', 'symbol', 'weight'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  /*dataSource = new MatTableDataSource(ELEMENT_DATA);*/
  dataSource = new MatTableDataSource<PeriodicElement>([]);

  parceiros$: Observable<PeriodicElement[]>;
  private dialog = inject(MatDialog);

  constructor(private parceirosService: ParceirosService) {
    console.log('Iniciando busca...'); // <--- Teste 1
    this.parceiros$ = this.parceirosService.findAll().pipe(
      catchError(error => {
        this.openError('Não foi possível carregar dados!');
        return of([])
      }),
      tap(dados => {
        console.log('Dados chegaram:', dados); // <--- Teste 2
        this.dataSource.data = dados;
      })
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

