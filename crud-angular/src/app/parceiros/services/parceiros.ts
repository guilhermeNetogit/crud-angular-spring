import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first } from 'rxjs';
import { Parceiro } from '../models/parceiro';
import { ParceiroPage } from '../models/parceiro-page';

@Injectable({
  providedIn: 'root',
})

export class ParceirosService {

  private readonly API = 'api/parceiros';
  private create(record: Partial<Parceiro>) {
    return this.httpClient.post<Parceiro>(this.API, record);
  }
  private update(record: Partial<Parceiro>) {
    return this.httpClient.put<Parceiro>(`${this.API}/${record.id}`, record);
  }

  constructor(private httpClient: HttpClient) {}

  findAll(page = 0, pageSize = 10, name = '') {
    let params = new HttpParams()
          .set('page', page.toString())
          .set('size', pageSize.toString());

    if (name && name.trim() !== '') {
        params = params.set('name', name);
      }

    return this.httpClient.get<ParceiroPage>(this.API, { params })
    .pipe(
      first()
      //delay(1200),
      //tap(parceiros => console.log(parceiros))
    );
  }

  loadById(id: number) {
    return this.httpClient.get<Parceiro>(`${this.API}/${id}`);
  }

  save(record: Parceiro) {
    console.log(record);
    if (record.id) {
      console.log('update')
    return this.update(record);
    }
    console.log('create');
    return this.create(record);
  }

  delete(id: number) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }
}
