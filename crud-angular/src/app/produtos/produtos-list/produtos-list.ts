import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';

import { MatFormFieldModule, MatLabel } from "@angular/material/form-field";
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from "@angular/material/menu";
import { MatToolbarModule } from "@angular/material/toolbar";
import { Produto } from '../models/produto';

@Component({
  selector: 'app-produtos-list',
  standalone: true,
  imports: [MatTableModule, MatIconModule, MatInputModule, MatButtonModule, MatSortModule, MatToolbarModule, MatFormFieldModule, MatLabel, MatMenuModule],
  templateUrl: './produtos-list.html',
  styleUrl: './produtos-list.scss'
})
export class ProdutosList {
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.filter.emit(filterValue.trim().toLowerCase());
  }

  clearFilter() {
      this.clear.emit();
    }

  @ViewChild(MatSort) set MatSort(sort: MatSort) {
    this.dataSource.sort = sort;
  }

  @Input() filterValue: string = '';
  @Input() dataSource = new MatTableDataSource<Produto>([]);

  @Input() readonly displayedColumns: string[] = [
    'codprod',
    'descrprod',
    'compldesc',
    'codvol',
    'referencia',
    'eangtin',
    'actions'
  ];
  columnsToDisplay: string[] = this.displayedColumns.slice();

  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter<number>();
  @Output() delete = new EventEmitter<number>();
  @Output() filter = new EventEmitter<string>();
  @Output() clear = new EventEmitter<void>();

  onAdd() {
    this.add.emit(true);
  }

  onEdit(produto: Produto) {
    this.edit.emit(produto.codprod);
  }

  onDelete(produto: Produto) {
    this.delete.emit(produto.codprod);
  }
}
