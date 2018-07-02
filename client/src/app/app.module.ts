import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './app.material.module';
import {CadastroComponent} from './cliente/cadastro/cadastro.component';
import {ListaComponent} from './cliente/lista/lista.component';
import {appRoutes} from './app.routes';
import {ClienteService} from './cliente/cliente.service';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import {SimulacaoComponent} from './cliente/simulacao/simulacao.component';
import { EditarComponent } from './cliente/editar/editar.component';
import { ModalConfirmacaoComponent } from './modal-confirmacao/modal-confirmacao.component';

@NgModule({
  declarations: [
    AppComponent,
    CadastroComponent,
    ListaComponent,
    SimulacaoComponent,
    EditarComponent,
    ModalConfirmacaoComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    appRoutes
  ],
  entryComponents: [
    ModalConfirmacaoComponent
  ],
  providers: [ClienteService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
