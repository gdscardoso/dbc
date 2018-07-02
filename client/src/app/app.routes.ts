import {Routes, RouterModule} from '@angular/router';
import {ListaComponent} from './cliente/lista/lista.component';
import {CadastroComponent} from './cliente/cadastro/cadastro.component';
import {SimulacaoComponent} from './cliente/simulacao/simulacao.component';
import {EditarComponent} from './cliente/editar/editar.component';

const routes: Routes = [
  {
    path: '',
    component: ListaComponent
  },
  {
    path: 'novo',
    component: CadastroComponent
  },
  {
    path: 'simular/:id',
    component: SimulacaoComponent
  },
  {
    path: 'editar/:id',
    component: EditarComponent
  },
  {
    path: '**',
    component: ListaComponent
  }
];

export const appRoutes: any = RouterModule.forRoot(routes);
