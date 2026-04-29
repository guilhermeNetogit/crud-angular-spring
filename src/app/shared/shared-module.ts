import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { AppMaterialModule } from './app-material/app-material-module';
import { ErrorDialog } from './components/error-dialog/error-dialog';

@NgModule({
  imports: [CommonModule, ErrorDialog, AppMaterialModule],
  exports: [ErrorDialog]
})
export class SharedModule {}
