import { ParceirosService } from '../../../../services/parceiros';
import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { AppMaterialModule } from '../../shared/app-material/app-material-module';

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
  imports: [AppMaterialModule
  ],
  templateUrl: './parceiros.html',
  styleUrl: './parceiros.scss',
})

export class Parceiros {
  displayedColumns: string[] = ['position', 'name', 'symbol', 'weight'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  /*dataSource = new MatTableDataSource(ELEMENT_DATA);*/
  dataSource = new MatTableDataSource<PeriodicElement>([]);

  constructor(private parceirosService: ParceirosService){
    this.parceirosService.findAll().subscribe(dados => {console.log('Dados recebidos:', dados); this.dataSource.data = dados;

    });

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
