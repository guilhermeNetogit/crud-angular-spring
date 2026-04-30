import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs';

// Idealmente, mova esta interface para um arquivo separado depois
export interface Parceiro {
  id: number;
  position: number;
  name: string;
  weight: number;
  symbol: string;
}

@Injectable({
  providedIn: 'root',
})
export class ParceirosService {

  private readonly API = 'api/parceiros';

  constructor(private httpClient: HttpClient) {}

  findAll() {
    return this.httpClient.get<Parceiro[]>(this.API)
    .pipe(
      first(),
      delay(1500),
      tap(parceiros => console.log(parceiros))
    );
  }
}
