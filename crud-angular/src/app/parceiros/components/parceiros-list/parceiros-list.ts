import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { PeriodicElement } from '../../containers/parceiros/parceiros';
import { AppMaterialModule } from '../../../shared/app-material/app-material-module';
import { MatTableDataSource } from '@angular/material/table';
import { CategoryPipe } from '../../../shared/pipes/category-pipe';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-parceiros-list',
  standalone: true,
  imports: [AppMaterialModule, CategoryPipe],
  templateUrl: './parceiros-list.html',
  styleUrl: './parceiros-list.scss',
})

export class ParceirosList {

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  @ViewChild(MatSort) set MatSort(sort: MatSort) {
    this.dataSource.sort = sort;
  };

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

  onAdd() {
    this.add.emit(true);
  }

  editar(id: number) {
    this.edit.emit(id);
  }

  deletar(id: number) {
    this.delete.emit(id);
  }
}
