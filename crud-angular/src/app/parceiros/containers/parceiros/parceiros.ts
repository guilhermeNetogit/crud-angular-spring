import { AsyncPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject, catchError, Observable, of, switchMap, tap } from 'rxjs';
import { AppMaterialModule } from '../../../shared/app-material/app-material-module';
import { ErrorDialog } from '../../../shared/components/error-dialog/error-dialog';
import { ParceirosList } from "../../components/parceiros-list/parceiros-list";
import { ParceirosService } from '../../services/parceiros';
import { ConfirmationDialogComponent } from '../../../shared/components/confirmation-dialog/confirmation-dialog';

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
  imports: [AppMaterialModule, AsyncPipe, MatSortModule, ParceirosList],
  templateUrl: './parceiros.html',
  styleUrl: './parceiros.scss',
})

export class Parceiros {
edit($event: number) {
throw new Error('Method not implemented.');
}

private readonly refresh$ = new BehaviorSubject<void>(undefined);

readonly parceiros$: Observable<PeriodicElement[]> = this.refresh$.pipe(
    switchMap(() => this.parceirosService.findAll().pipe(
      catchError((error) => {
        console.error('ERRO REAL AQUI:', error);
        this.openError('Não foi possível carregar os dados!');
        return of([]);
      }),
      tap((dados) => {
        console.log('Dados chegaram:', dados); // <--- Teste 2
        this.dataSource.data = dados;
      })
    ))
  );

private dialog = inject(MatDialog);  /*dataSource = new MatTableDataSource(ELEMENT_DATA);*/
dataSource = new MatTableDataSource<PeriodicElement>([]);input: any;

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

  openError(errorMsg: string) {
    this.dialog.open(ErrorDialog, {
      data: errorMsg,
    });
  }

}
