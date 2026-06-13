import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { first, tap } from 'rxjs/operators';
import { ProdutoPage } from '../models/produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {
  // Ajuste o caminho da URL de acordo com o seu Controller do Spring
  private readonly API = 'api/produtos';

  constructor(private httpClient: HttpClient) { }

  findAll(page = 0, pageSize = 10, name = '') {
    return this.httpClient.get<ProdutoPage>(this.API, {
      params: { page: page.toString(), size: pageSize.toString(), name }
    }).pipe(
      first(),
      tap(data => console.log(data))
    );
  }

  delete(id: number) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }
}
