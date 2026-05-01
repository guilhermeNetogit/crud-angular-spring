import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { firstValueFrom } from 'rxjs';
import { ParceirosService } from '../../../../services/parceiros';
import { AppMaterialModule } from '../../shared/app-material/app-material-module';

@Component({
  selector: 'app-parceiros-form',
  imports: [AppMaterialModule],
  templateUrl: './parceiros-form.html',
  styleUrl: './parceiros-form.scss',
})
export class ParceirosForm {
  form: FormGroup;

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: ParceirosService,
    private snackBar: MatSnackBar,
    private location: Location,
  ) {
    this.form = this.formBuilder.group({
      position: [0],
      name: ['', [Validators.required]],
      weight: [0],
      symbol: [''],
    });
  }

  private onSuccess() {
    this.snackBar.open('Registro salvo com suceero!', 'Ok', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar curso!', 'Ok', { duration: 5000 });
  }

  async onSave() {
    if (this.form.valid) {
      try {
        await firstValueFrom(this.service.save(this.form.value));
        this.onSuccess();
      } catch (error) {
        this.onError();
      }
    } else {
      this.snackBar.open('O campo Nome é obrigatório!', 'Ok', { duration: 3000 });
    }
  }

  onCancel() {
    this.location.back();
  }
}
