import {Component, OnInit} from '@angular/core';
import {ClienteService} from '../cliente.service';
import {Router} from '@angular/router';
import {Cliente} from '../../model/cliente';
import {MatDialog} from '@angular/material';
import {SimulacaoComponent} from '../simulacao/simulacao.component';
import {ModalConfirmacaoComponent} from '../../modal-confirmacao/modal-confirmacao.component';
import {switchMap} from 'rxjs/internal/operators';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  clientes: Cliente[] = [];

  constructor(private service: ClienteService,
              private router: Router,
              private confirm: MatDialog) {
  }

  ngOnInit() {
    this._list();
  }

  _list() {
    this.service.list()
      .subscribe(
        clientes => {
          this.clientes = clientes;
        },
        error => console.log(error));
  }

  novo(event: Event) {
    event.preventDefault();
    this.router.navigate(['novo']);
  }

  simular(cliente: Cliente) {
    this.router.navigate(['simular', cliente.id]);
  }

  editar(cliente: Cliente) {
    this.router.navigate(['editar', cliente.id]);
  }

  excluir(cliente: Cliente) {
    const modal = this.confirm
      .open(ModalConfirmacaoComponent, {
        data: 'Você tem certeza que deseja excluir o cliente?',
        hasBackdrop: true
      });

    modal.beforeClose()
      .subscribe(result => {
        if (result) {
          this.service.remove(cliente)
            .subscribe(() => {
              this._list();
            });
        }
      });

  }
}
