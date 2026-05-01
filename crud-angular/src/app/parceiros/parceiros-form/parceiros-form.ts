import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AppMaterialModule } from "../../shared/app-material/app-material-module";
import { ParceirosService } from '../../../../services/parceiros';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-parceiros-form',
  imports: [AppMaterialModule],
  templateUrl: './parceiros-form.html',
  styleUrl: './parceiros-form.scss',
})
export class ParceirosForm {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder, private service: ParceirosService,
  private snackBar: MatSnackBar
  ) {
    this.form = this.formBuilder.group( {
      position:[null],
      name: [null],
      weight: [null],
      symbol: [null]
    });
   }

  private onError() {
    {this.snackBar.open('Erro ao salvar curso','Ok', {duration: 5000})}
  }

  onSave() {
    this.service.save(this.form.value).subscribe(result => console.log(result), error => this.onError());
  }

  onCancel() {
    console.log('onCancel');
  }
}
