import { ActivatedRoute, Router } from '@angular/router';
import { Component, EventEmitter, input, Input, Output } from '@angular/core';
import { PeriodicElement } from '../parceiros/parceiros';
import { AppMaterialModule } from '../../shared/app-material/app-material-module';
import { MatTableDataSource } from '@angular/material/table';
import { CategoryPipe } from '../../shared/pipes/category-pipe';

@Component({
  selector: 'app-parceiros-list',
  standalone: true,
  imports: [AppMaterialModule, CategoryPipe],
  templateUrl: './parceiros-list.html',
  styleUrl: './parceiros-list.scss',
})
export class ParceirosList {

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

  constructor(
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  editar(id: number) {
    this.edit.emit(id);
  }

  deletar(id: number) {
    this.delete.emit(id);
  }
}
