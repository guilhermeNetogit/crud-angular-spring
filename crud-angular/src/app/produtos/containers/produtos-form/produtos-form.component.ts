import { Component, ChangeDetectionStrategy, OnInit, inject, signal } from "@angular/core";
import { ReactiveFormsModule, NonNullableFormBuilder, Validators } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatDialog } from "@angular/material/dialog";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatSnackBar } from "@angular/material/snack-bar";
import { MatToolbarModule } from "@angular/material/toolbar";
import { Router, ActivatedRoute } from "@angular/router";
import { ErrorDialog } from "../../../shared/components/error-dialog/error-dialog";
import { Produto } from "../../models/produto";
import { ProdutosService } from "../../services/produtos.service";

@Component({
  selector: 'app-produtos-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
  ],
  templateUrl: './produtos-form.component.html',
  styleUrl: './produtos-form.component.scss',
  changeDetection: ChangeDetectionStrategy.Eager,
})
export class ProdutosFormComponent implements OnInit {
  private readonly fb = inject(NonNullableFormBuilder);
  private readonly service = inject(ProdutosService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);
  private readonly snackBar = inject(MatSnackBar);
  private readonly dialog = inject(MatDialog);

  readonly loading = signal(false);
  readonly saving = signal(false);
  readonly isEdit = signal(false);

  readonly form = this.fb.group({
    codprod: this.fb.control<number | null>({ value: null, disabled: true }),
    descrprod: this.fb.control('', [Validators.required, Validators.maxLength(100)]),
    compldesc: this.fb.control('', [Validators.maxLength(255)]),
    codvol: this.fb.control('', [Validators.required, Validators.maxLength(10)]),
    referencia: this.fb.control('', [Validators.maxLength(30)]),
    eangtin: this.fb.control('', [Validators.pattern(/^(\d{8}|\d{12}|\d{13}|\d{14})?$/)]),
  });

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (!id) return;

    this.isEdit.set(true);
    this.loading.set(true);

    this.service.buscarPorId(Number(id)).subscribe({
      next: (response) => {
        const produto = response as Produto;
        this.form.patchValue({
          codprod: produto.codprod,
          descrprod: produto.descrprod ?? '',
          compldesc: produto.compldesc ?? '',
          codvol: produto.codvol ?? '',
          referencia: produto.referencia ?? '',
          eangtin: produto.eangtin ?? '',
        });
        this.loading.set(false);
      },
      error: () => {
        this.loading.set(false);
        this.openError('Não foi possível carregar o produto.');
        this.onCancel();
      },
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    // getRawValue inclui o codprod, que fica desabilitado
    const raw = this.form.getRawValue();
    const produto = {
      ...raw,
      referencia: raw.referencia || null,
      eangtin: raw.eangtin || null,
    } as unknown as Produto;

    this.saving.set(true);

    this.service.salvar(produto).subscribe({
      next: () => {
        this.saving.set(false);
        this.snackBar.open(
          this.isEdit() ? 'Produto atualizado com sucesso!' : 'Produto cadastrado com sucesso!',
          'X',
          { duration: 5000, verticalPosition: 'top', horizontalPosition: 'center' },
        );
        this.onCancel();
      },
      error: () => {
        this.saving.set(false);
        this.openError('Erro ao salvar o produto.');
      },
    });
  }

  onCancel(): void {
    this.router.navigate(['/produtos']);
  }

  errorMessage(name: keyof typeof this.form.controls): string {
    const errors = this.form.controls[name].errors;
    if (!errors) return '';
    if (errors['required']) return 'Campo obrigatório';
    if (errors['maxlength']) {
      return `Máximo de ${errors['maxlength'].requiredLength} caracteres`;
    }
    if (errors['pattern']) return 'Informe 8, 12, 13 ou 14 dígitos';
    return 'Valor inválido';
  }

  private openError(msg: string): void {
    this.dialog.open(ErrorDialog, { data: msg });
  }
}