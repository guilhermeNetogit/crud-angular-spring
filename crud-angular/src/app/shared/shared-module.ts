import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { AppMaterialModule } from './app-material/app-material-module';
import { ErrorDialog } from './components/error-dialog/error-dialog';
import { CategoryPipe } from './pipes/category-pipe';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog';

@NgModule({
  declarations: [],
  imports: [CommonModule, ConfirmationDialogComponent, ErrorDialog, AppMaterialModule, CategoryPipe],
  exports: [ErrorDialog, ConfirmationDialogComponent,
  CategoryPipe, AppMaterialModule]
})
export class SharedModule {}
