import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {map} from 'rxjs/internal/operators';
import {ClienteService} from '../cliente.service';
import {SimulacaoRequest, SimulacaoResponse} from '../../model/simulacao';

@Component({
  selector: 'app-simulacao',
  templateUrl: './simulacao.component.html',
  styleUrls: ['./simulacao.component.css']
})
export class SimulacaoComponent implements OnInit {

  form: FormGroup;
  clienteId: number;
  response: SimulacaoResponse;

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              private service: ClienteService) {
    this.createForm();
  }

  createForm() {
    this.form = this.fb.group({
      valor: ['', Validators.required],
      prazo: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.route.params
      .pipe(
        map(params => params['id'])
      )
      .subscribe(id => {
        this.clienteId = id;
        console.log(this.clienteId);
      });
  }

  simular() {
    if (this.clienteId) {
      const request: SimulacaoRequest = {
        clienteId: this.clienteId as number,
        valorOperacao: this.form.value.valor as number,
        prazo: this.form.value.prazo as number,
      };

      this.service.simular(request)
        .subscribe(
          result => {
            this.response = result;
          },
          error => {
            console.log(error);
          });
    }
  }

}
