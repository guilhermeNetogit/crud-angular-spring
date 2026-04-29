import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { AppMaterialModule } from './app-material/app-material-module';
import { ErrorDialog } from './components/error-dialog/error-dialog';
import { CategoryPipe } from './pipes/category-pipe';

@NgModule({
  imports: [CommonModule, ErrorDialog, AppMaterialModule, CategoryPipe],
  exports: [ErrorDialog, CategoryPipe, AppMaterialModule]
})
export class SharedModule {}
