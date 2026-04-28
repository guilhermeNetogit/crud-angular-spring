import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableModule } from '@angular/material/table';

@NgModule({
  exports: [MatFormFieldModule, MatInputModule, MatTableModule,
  MatCardModule, MatProgressSpinnerModule
],
})
export class AppMaterialModule {}
