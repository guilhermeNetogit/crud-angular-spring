import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableModule } from '@angular/material/table';

@NgModule({
  exports: [MatFormFieldModule, MatInputModule, MatTableModule,
  MatCardModule, MatProgressSpinnerModule,
  MatDialogModule, MatIconModule
],
})
export class AppMaterialModule {}
