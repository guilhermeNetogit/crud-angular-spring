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

const ELEMENT_DATA: PeriodicElement[] = [
  {id: 1, position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {id: 2, position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {id: 3, position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {id: 4, position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {id: 5, position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {id: 6, position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {id: 7, position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {id: 8, position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {id: 9 ,position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {id: 10 ,position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
  {id: 11, position: 11, name: 'Sodium', weight: 22.9898, symbol: 'Na'},
  {id: 12, position: 12, name: 'Magnesium', weight: 24.305, symbol: 'Mg'},
  {id: 13, position: 13, name: 'Aluminum', weight: 26.9815, symbol: 'Al'},
  {id: 14, position: 14, name: 'Silicon', weight: 28.0855, symbol: 'Si'},
  {id: 15, position: 15, name: 'Phosphorus', weight: 30.9738, symbol: 'P'},
  {id: 16, position: 16, name: 'Sulfur', weight: 32.065, symbol: 'S'},
  {id: 17, position: 17, name: 'Chlorine', weight: 35.453, symbol: 'Cl'},
  {id: 18, position: 18, name: 'Argon', weight: 39.948, symbol: 'Ar'},
  {id: 19, position: 19, name: 'Potassium', weight: 39.0983, symbol: 'K'},
  {id: 20, position: 20, name: 'Calcium', weight: 40.078, symbol: 'Ca'},
  {id: 21, position: 21, name: 'Scandium', weight: 44.9559, symbol: 'Sc'},
  {id: 22, position: 22, name: 'Titanium', weight: 47.867, symbol: 'Ti'},
  {id: 23, position: 23, name: 'Vanadium', weight: 50.9415, symbol: 'V'},
  {id: 24, position: 24, name: 'Chromium', weight: 51.9961, symbol: 'Cr'},
  {id: 25, position: 25, name: 'Manganese', weight: 54.938, symbol: 'Mn'},
  {id: 26, position: 26, name: 'Iron', weight: 55.845, symbol: 'Fe'},
  {id: 27, position: 27, name: 'Cobalt', weight: 58.9332, symbol: 'Co'},
  {id: 28, position: 28, name: 'Nickel', weight: 58.6934, symbol: 'Ni'},
  {id: 29, position: 29, name: 'Copper', weight: 63.546, symbol: 'Cu'},
  {id: 30, position: 30, name: 'Zinc', weight: 65.38, symbol: 'Zn'},
  {id: 31, position: 31, name: 'Gallium', weight: 69.723, symbol: 'Ga'},
  {id: 32, position: 32, name: 'Germanium', weight: 72.63, symbol: 'Ge'},
  {id: 33, position: 33, name: 'Arsenic', weight: 74.9216, symbol: 'As'},
  {id: 34, position: 34, name: 'Selenium', weight: 78.96, symbol: 'Se'},
  {id: 35, position: 35, name: 'Bromine', weight: 79.904, symbol: 'Br'},
  {id: 36, position: 36, name: 'Krypton', weight: 83.798, symbol: 'Kr'},
  {id: 37, position: 37, name: 'Rubidium', weight: 85.4678, symbol: 'Rb'},
  {id: 38, position: 38, name: 'Strontium', weight: 87.62, symbol: 'Sr'},
  {id: 39, position: 39, name: 'Yttrium', weight: 88.9059, symbol: 'Y'},
  {id: 40, position: 40, name: 'Zirconium', weight: 91.224, symbol: 'Zr'},
  {id: 41, position: 41, name: 'Niobium', weight: 92.9064, symbol: 'Nb'},
  {id: 42, position: 42, name: 'Molybdenum', weight: 95.94, symbol: 'Mo'},
  {id: 43, position: 43, name: 'Technetium', weight: 98, symbol: 'Tc'},
  {id: 44, position: 44, name: 'Ruthenium', weight: 101.07, symbol: 'Ru'},
  {id: 45, position: 45, name: 'Rhodium', weight: 102.9055, symbol: 'Rh'},
  {id: 46, position: 46, name: 'Palladium', weight: 106.42, symbol: 'Pd'},
  {id: 47, position: 47, name: 'Silver', weight: 107.8682, symbol: 'Ag'},
  {id: 48, position: 48, name: 'Cadmium', weight: 112.411, symbol: 'Cd'},
  {id: 49, position: 49, name: 'Indium', weight: 114.818, symbol: 'In'},
  {id: 50, position: 50, name: 'Tin', weight: 118.71, symbol: 'Sn'},
  {id: 51, position: 51, name: 'Antimony', weight: 121.76, symbol: 'Sb'},
  {id: 52, position: 52, name: 'Tellurium', weight: 127.6, symbol: 'Te'},
  {id: 53, position: 53, name: 'Iodine', weight: 126.9045, symbol: 'I'},
  {id: 54, position: 54, name: 'Xenon', weight: 131.293, symbol: 'Xe'},
  {id: 55, position: 55, name: 'Cesium', weight: 132.9055, symbol: 'Cs'},
  {id: 56, position: 56, name: 'Barium', weight: 137.327, symbol: 'Ba'},
  {id: 57, position: 57, name: 'Lanthanum', weight: 138.9055, symbol: 'La'},
  {id: 58, position: 58, name: 'Cerium', weight: 140.116, symbol: 'Ce'},
  {id: 59, position: 59, name: 'Praseodymium', weight: 140.9077, symbol: 'Pr'},
  {id: 60, position: 60, name: 'Neodymium', weight: 144.242, symbol: 'Nd'},
  {id: 61, position: 61, name: 'Promethium', weight: 145, symbol: 'Pm'},
  {id: 62, position: 62, name: 'Samarium', weight: 150.36, symbol: 'Sm'},
  {id: 63, position: 63, name: 'Europium', weight: 151.964, symbol: 'Eu'},
  {id: 64, position: 64, name: 'Gadolinium', weight: 157.25, symbol: 'Gd'},
  {id: 65, position: 65, name: 'Terbium', weight: 158.9254, symbol: 'Tb'},
  {id: 66, position: 66, name: 'Dysprosium', weight: 162.5, symbol: 'Dy'},
  {id: 67, position: 67, name: 'Holmium', weight: 164.9303, symbol: 'Ho'},
  {id: 68, position: 68, name: 'Erbium', weight: 167.259, symbol: 'Er'},
  {id: 69, position: 69, name: 'Thulium', weight: 168.9342, symbol: 'Tm'},
  {id: 70, position: 70, name: 'Ytterbium', weight: 173.04, symbol: 'Yb'},
  {id: 71, position: 71, name: 'Lutetium', weight: 174.967, symbol: 'Lu'},
  {id: 72, position: 72, name: 'Hafnium', weight: 178.49, symbol: 'Hf'},
  {id: 73, position: 73, name: 'Tantalum', weight: 180.9479, symbol: 'Ta'},
  {id: 74, position: 74, name: 'Tungsten', weight: 183.84, symbol: 'W'},
  {id: 75, position: 75, name: 'Rhenium', weight: 186.207, symbol: 'Re'},
  {id: 76, position: 76, name: 'Osmium', weight: 190.23, symbol: 'Os'},
  {id: 77, position: 77, name: 'Iridium', weight: 192.217, symbol: 'Ir'},
  {id: 78, position: 78, name: 'Platinum', weight: 195.084, symbol: 'Pt'},
  {id: 79, position: 79, name: 'Gold', weight: 196.9666, symbol: 'Au'},
  {id: 80, position: 80, name: 'Mercury', weight: 200.59, symbol: 'Hg'},
  {id: 81, position: 81, name: 'Thallium', weight: 204.3833, symbol: 'Tl'},
  {id: 82, position: 82, name: 'Lead', weight: 207.2, symbol: 'Pb'},
  {id: 83, position: 83, name: 'Bismuth', weight: 208.9804, symbol: 'Bi'},
  {id: 84, position: 84, name: 'Polonium', weight: 209, symbol: 'Po'},
  {id: 85, position: 85, name: 'Astatine', weight: 210, symbol: 'At'},
  {id: 86, position: 86, name: 'Radon', weight: 222, symbol: 'Rn'},
  {id: 87, position: 87, name: 'Francium', weight: 223, symbol: 'Fr'},
  {id: 88, position: 88, name: 'Radium', weight: 226, symbol: 'Ra'},
  {id: 89, position: 89, name: 'Actinium', weight: 227, symbol: 'Ac'},
  {id: 90, position: 90, name: 'Thorium', weight: 232.0381, symbol: 'Th'},
  {id: 91, position: 91, name: 'Protactinium', weight: 231.0359, symbol: 'Pa'},
  {id: 92, position: 92, name: 'Uranium', weight: 238.0289, symbol: 'U'},
  {id: 93, position: 93, name: 'Neptunium', weight: 237, symbol: 'Np'},
  {id: 94, position: 94, name: 'Plutonium', weight: 244, symbol: 'Pu'},
  {id: 95, position: 95, name: 'Americium', weight: 243, symbol: 'Am'},
  {id: 96, position: 96, name: 'Curium', weight: 247, symbol: 'Cm'},
  {id: 97, position: 97, name: 'Berkelium', weight: 247, symbol: 'Bk'},
  {id: 98, position: 98, name: 'Californium', weight: 251, symbol: 'Cf'},
  {id: 99, position: 99, name: 'Einsteinium', weight: 252, symbol: 'Es'},
  {id: 100, position: 100, name: 'Fermium', weight: 257, symbol: 'Fm'},
  {id: 101, position: 101, name: 'Mendelevium', weight: 258, symbol: 'Md'},
  {id: 102, position: 102, name: 'Nobelium', weight: 259, symbol: 'No'},
  {id: 103, position: 103, name: 'Lawrencium', weight: 262, symbol: 'Lr'},
  {id: 104, position: 104, name: 'Rutherfordium', weight: 261, symbol: 'Rf'},
  {id: 105, position: 105, name: 'Dubnium', weight: 262, symbol: 'Db'},
  {id: 106, position: 106, name: 'Seaborgium', weight: 266, symbol: 'Sg'},
  {id: 107, position: 107, name: 'Bohrium', weight: 264, symbol: 'Bh'},
  {id: 108, position: 108, name: 'Hassium', weight: 277, symbol: 'Hs'},
  {id: 109, position: 109, name: 'Meitnerium', weight: 268, symbol: 'Mt'},
  {id: 110, position: 110, name: 'Darmstadtium', weight: 271, symbol: 'Ds'},
  {id: 111, position: 111, name: 'Roentgenium', weight: 272, symbol: 'Rg'},
  {id: 112, position: 112, name: 'Copernicium', weight: 285, symbol: 'Cn'},
  {id: 113, position: 113, name: 'Nihonium', weight: 284, symbol: 'Nh'},
  {id: 114, position: 114, name: 'Flerovium', weight: 289, symbol: 'Fl'},
  {id: 115, position: 115, name: 'Moscovium', weight: 288, symbol: 'Mc'},
  {id: 116, position: 116, name: 'Livermorium', weight: 292, symbol: 'Lv'},
  {id: 117, position: 117, name: 'Tennessine', weight: 294, symbol: 'Ts'},
  {id: 118, position: 118, name: 'Oganesson', weight: 294, symbol: 'Og'}


];

@Component({
  selector: 'app-parceiros',
  standalone: true,
  imports: [AppMaterialModule
  ],
  templateUrl: './parceiros.html',
  styleUrl: './parceiros.scss',
})

export class Parceiros {
  displayedColumns: string[] = ['id', 'position', 'name', 'symbol', 'weight'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
