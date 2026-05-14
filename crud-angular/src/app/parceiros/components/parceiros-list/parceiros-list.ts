import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { CategoryPipe } from '../../../shared/pipes/category-pipe';
import { PeriodicElement } from '../../containers/parceiros/parceiros';

@Component({
  selector: 'app-parceiros-list',
  standalone: true,
  imports: [MatToolbarModule, MatFormFieldModule,
  MatInputModule,
  MatMenuModule,
  MatButtonModule,MatTableModule, MatSortModule, MatIconModule, CategoryPipe],
  templateUrl: './parceiros-list.html',
  styleUrl: './parceiros-list.scss',
})
export class ParceirosList {
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
  @Input() dataSource = new MatTableDataSource<PeriodicElement>([]);

  @Input() readonly displayedColumns: string[] = [
    'name',
    'symbol',
    'position',
    'weight',
    'actions',
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

  onEdit(id: number) {
    this.edit.emit(id);
  }

  onDelete(id: number) {
    this.delete.emit(id);
  }
}
