import { Location } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormArray, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { firstValueFrom, } from 'rxjs';
import { FormUtilsService } from './../../../shared/form/form-utils';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Contato } from '../../models/contato';
import { Parceiro } from '../../models/parceiro';
import { ParceirosService } from '../../services/parceiros';

@Component({
  selector: 'app-parceiros-form',
  imports: [ReactiveFormsModule,
      MatFormFieldModule,
      MatIconModule,
      MatInputModule,
      MatButtonModule,
      MatCardModule, MatToolbarModule],
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
    public formUtils: FormUtilsService,
  ) {
    this.form = this.formBuilder.group({
      id: [''],
      position: [1],
      name: ['', [Validators.required, Validators.minLength(2)]],
      weight: [1],
      symbol: ['', [Validators.required, Validators.maxLength(2)]],
      contatos: this.formBuilder.array([]),
    });
  }

  onInput(event: Event) {
    const input = event.target as HTMLInputElement;
    this.value.set(input.value);
  }

  ngOnInit(): void {
    const parceiro: Parceiro = this.route.snapshot.data['parceiro'];

    if (parceiro && parceiro.id) {
      this.form.patchValue(parceiro);
      console.log('Formulário preenchido com:', parceiro);
      this.value.set(parceiro.name || '');
    }

    const contatosFormArray = this.getContatosFormArray();
    parceiro.contatos?.forEach((contato) => {
      contatosFormArray.push(this.createContato(contato));
    });
  }

  getContatosFormArray() {
    return this.form.get('contatos') as FormArray;
  }

  addContato() {
    this.getContatosFormArray().push(this.createContato());
  }

  removeContato(index: number) {
    const contatoArray = this.getContatosFormArray();
    const contato = contatoArray.at(index).value;

  // Se tem codcontato (existente no banco), chama API de delete
  if (contato.codcontato && contato.codcontato > 0) {
    const parceiroId = this.form.get('id')?.value;

    if (!parceiroId) {
          // Segurança: não deveria acontecer, mas protege
          contatoArray.removeAt(index);
          return;
        }

    this.service.deleteContato(parceiroId, contato.codcontato).subscribe({
      next: () => {
        contatoArray.removeAt(index);
        this.snackBar.open('Contato removido', 'Ok', { duration: 3000 });
      },
      error: () => {
        this.snackBar.open('Erro ao remover contato', 'Ok', { duration: 3000 });
      }
    });
  } else {
    // Contato novo (sem ID) — remove apenas do formulário
    contatoArray.removeAt(index);
  }

  }

  private obterContato(parceiro: Parceiro) {
    const contatos = [];
    if (parceiro?.contatos) {
      parceiro.contatos.forEach((contato) => contatos.push(this.createContato(contato)));
    } else {
      contatos.push(this.createContato());
    }
    return contatos;
  }

  private createContato(
    contato: Contato = { nomecontato: '', telefone: '', email: '', siteurl: '' },
  ) {
    return this.formBuilder.group({
      codcontato: [contato.codcontato || null],
      nomecontato: [contato.nomecontato, [Validators.required]],
      telefone: [contato.telefone],
      email: [contato.email, Validators.email],
      siteurl: [contato.siteurl],
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
    const contatosArray = this.getContatosFormArray();

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      this.snackBar.open('Verifique os campos obrigatórios!', 'Ok', { duration: 3000 });
      return;
    }

    for (let i = contatosArray.length - 1; i >= 0; i--) {
      const contato = contatosArray.at(i).value;
      if (!contato.nomecontato && !contato.telefone && !contato.email) {
        contatosArray.removeAt(i);
      }
    }

    if (this.form.valid) {
      const parceiroParaSalvar = { ...this.form.value };

      if (!parceiroParaSalvar.id || parceiroParaSalvar.id === '') {
        parceiroParaSalvar.id = null;
      }

      if (parceiroParaSalvar.symbol === '') {
        parceiroParaSalvar.symbol = null;
      }

      parceiroParaSalvar.position = Number(parceiroParaSalvar.position);
      parceiroParaSalvar.weight = Number(parceiroParaSalvar.weight);

      // Limpa codcontato null/0 para não enviar para API
      if (parceiroParaSalvar.contatos) {
            parceiroParaSalvar.contatos = parceiroParaSalvar.contatos.map((c: any) => ({
              ...c,
              codcontato: c.codcontato && c.codcontato > 0 ? c.codcontato : null
            }));
      }

      try {
        await firstValueFrom(this.service.save(parceiroParaSalvar));
        this.onSuccess();
      } catch (error) {
        this.onError();
      }
    } else {
      Object.keys(this.form.controls).forEach((key) => {
        const controlErros = this.form.get(key)?.errors;
        if (controlErros) console.log('Campos inválidos:' + key, controlErros);
      });
    }
  }

  onCancel() {
    this.location.back();
  }
}
