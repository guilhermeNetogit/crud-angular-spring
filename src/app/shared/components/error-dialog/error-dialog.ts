import { Component, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';

@Component({
  selector: 'app-error-dialog',
  imports: [MatDialogModule],
  templateUrl: './error-dialog.html',
  styleUrl: './error-dialog.scss',
})

export class ErrorDialog {

  public data = inject(MAT_DIALOG_DATA);

  constructor() {
    console.log('Dados recebidos:', this.data);
  }
}
