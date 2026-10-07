import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap } from 'rxjs/operators';
import { Produto, ProdutoPage } from '../models/produto';

@Injectable({
  providedIn: 'root',
})
export class ProdutosService {
  // Ajuste o caminho da URL de acordo com o seu Controller do Spring
  private readonly API = 'api/produtos';

  constructor(private httpClient: HttpClient) {}

  findAll(page = 0, pageSize = 10, name = '') {
    return this.httpClient
      .get<ProdutoPage>(this.API, {
        params: { page: page.toString(), size: pageSize.toString(), name },
      })
      .pipe(
        first(),
        tap((data) => console.log(data)),
      );
  }

  buscarPorId(id: number) {
    return this.httpClient.get<Produto>(`${this.API}/${id}`);
  }

  salvar(record: Produto) {
    if (record.codprod) {
      return this.httpClient.put<Produto>(`${this.API}/${record.codprod}`, record);
    }
    return this.httpClient.post<Produto>(this.API, record);
  }

  softDelete(id: number) {
    return this.httpClient.delete(`${this.API}/${id}`);
  }

  delete(id: number) {
    return this.httpClient.delete(`${this.API}/${id}/hard`);
  }
}
