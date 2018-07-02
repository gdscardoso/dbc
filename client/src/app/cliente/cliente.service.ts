import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Cliente} from '../model/cliente';
import {SimulacaoRequest, SimulacaoResponse} from '../model/simulacao';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) {
  }

  list(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>('rest/clientes');
  }

  find(id: number): Observable<Cliente> {
    return this.http.get<Cliente>('rest/clientes/' + id);
  }

  create(cliente: Cliente): Observable<Cliente> {
    return this.http
      .post<Cliente>('rest/clientes/create', cliente, httpOptions);
  }

  update(cliente: Cliente): Observable<Cliente> {
    return this.http
      .put<Cliente>('rest/clientes/update', cliente, httpOptions);
  }

  remove(cliente: Cliente): Observable<Cliente> {
    return this.http
      .delete<Cliente>('rest/clientes/delete/' + cliente.id, httpOptions);
  }

  simular(simulacao: SimulacaoRequest) {
    return this.http.post<SimulacaoResponse>('rest/clientes/simular', simulacao, httpOptions);
  }

}
