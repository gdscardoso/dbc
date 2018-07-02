import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {ClienteService} from '../cliente.service';
import {Cliente} from '../../model/cliente';
import {switchMap} from 'rxjs/internal/operators';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.css']
})
export class EditarComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder,
              private service: ClienteService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this._createForm();

    this.route.params
      .pipe(switchMap((params: Params) => this.service.find(params['id'])))
      .subscribe((cliente: Cliente) => {
        console.log(cliente);

        this.form.patchValue({
          id: cliente.id,
          nome: cliente.nome,
          endereco: cliente.endereco,
          renda: cliente.renda,
          tipo: cliente.tipo,
          valorPatrimonio: cliente.valorPatrimonio,
          valorDividas: cliente.valorDividas,
          empregado: cliente.empregado
        });


      });
  }

  _createForm() {
    this.form = this.fb
      .group({
        id: [null, null],
        nome: ['', Validators.required],
        endereco: ['', Validators.required],
        renda: ['', Validators.required],
        tipo: ['COMUM', Validators.required],
        valorPatrimonio: ['', null],
        valorDividas: ['', null],
        empregado: ['', null]
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

  salvar() {
    this.service.update(this.form.value)
      .subscribe(
        (result) => {
          this.router.navigate(['/']);
        },
        error => console.log(error)
      );
  }


}
