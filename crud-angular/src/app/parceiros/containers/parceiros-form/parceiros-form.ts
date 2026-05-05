import { Location } from '@angular/common';
import { Component, OnInit, signal, Input } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { firstValueFrom, min } from 'rxjs';
import { AppMaterialModule } from '../../../shared/app-material/app-material-module';
import { Parceiro, ParceirosService } from '../../services/parceiros';

@Component({
  selector: 'app-parceiros-form',
  imports: [AppMaterialModule],
  templateUrl: './parceiros-form.html',
  styleUrl: './parceiros-form.scss',
})
export class ParceirosForm implements OnInit {
  form: FormGroup;

  value = signal('');

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: ParceirosService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute,
  ) {
    this.form = this.formBuilder.group({
      id: [''],
      position: [''],
      name: ['', [Validators.required, Validators.minLength(2)]],
      weight: [''],
      symbol: [''],
    });
  }

  onInput(event: Event) {
    const input = event.target as HTMLInputElement;
    this.value.set(input.value);
  }

  ngOnInit(): void {
    const parceiro: Parceiro = this.route.snapshot.data['parceiro'];

    if (parceiro) {
      this.form.patchValue(parceiro);
      console.log('Formulário preenchido com:', parceiro);
      this.value.set(parceiro.name || '');
    }
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

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);
    if (field?.hasError('required')) {
      return 'O campo é obrigatório';
    }
    if (field?.hasError('minlength')) {
      const requiredLength = field.errors ? field.errors['minlength']['requiredLength'] : 2;
      return `Tamanho mín. ${requiredLength} caracteres.`;
    }
    return 'Campo inválido!';
  }
}
