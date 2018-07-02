import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ClienteService} from '../cliente.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {switchMap} from 'rxjs/internal/operators';
import {Cliente} from '../../model/cliente';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  form: FormGroup;
  title: string = 'Cadastro de Clientes';

  constructor(private fb: FormBuilder,
              private service: ClienteService,
              private router: Router,
              private route: ActivatedRoute) {

    this._createForm();
  }

  ngOnInit() {
  }

  _createForm() {
    this.form = this.fb
      .group({
        nome: ['', Validators.required],
        endereco: ['', Validators.required],
        renda: ['', Validators.required],
        tipo: ['COMUM', Validators.required],
        valorPatrimonio: '',
        valorDividas: '',
        empregado: ''
      });
    this._validate();
  }

  _validate() {
    this.form.get('tipo').valueChanges
      .subscribe((tipo) => {
        if (tipo === 'COMUM') {
          this.form.get('empregado').setValidators(Validators.required);
        } else {
          this.form.get('empregado').setValidators(null);
        }

        if (tipo === 'POTENCIAL') {
          this.form.get('valorDividas').setValidators(Validators.required);
        } else {
          this.form.get('valorDividas').setValidators(null);
        }

        if (tipo === 'ESPECIAL') {
          this.form.get('valorPatrimonio').setValidators(Validators.required);
        } else {
          this.form.get('valorPatrimonio').setValidators(null);
        }
      });
  }

  submit() {
    this.service.create(this.form.value)
      .subscribe(
        (result) => {
          this.router.navigate(['/']);
        },
        error => console.log(error)
      );
  }


}
