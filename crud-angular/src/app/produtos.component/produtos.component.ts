import { AsyncPipe, CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { ProdutoPage } from './models/produto';
import { ProdutosService } from './services/produtos.service';

@Component({
  selector: 'app-produtos',
  standalone: true,
  imports: [
    CommonModule, AsyncPipe, MatCardModule, MatIconModule,
    MatProgressSpinnerModule, MatPaginatorModule, MatButtonModule
  ],
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent {

  produtos$: Observable<ProdutoPage> | null = null;
  loadingError = signal(false);

  pageIndex = 0;
  pageSize = 10;
  filterValue = '';

  constructor(private produtosService: ProdutosService) {
    this.refresh();
  }

  refresh() {
    this.loadingError.set(false);
    this.produtos$ = this.produtosService.list(this.pageIndex, this.pageSize, this.filterValue)
      .pipe(
        catchError(error => {
          this.loadingError.set(true);
          return of({ produtos: [], totalElements: 0, totalPages: 0 });
        })
      );
  }

  onPageChange(pageEvent: PageEvent) {
    this.pageIndex = pageEvent.pageIndex;
    this.pageSize = pageEvent.pageSize;
    this.refresh();
  }

  clearFilter() {
    this.filterValue = '';
    this.pageIndex = 0;
    this.refresh();
  }
}
