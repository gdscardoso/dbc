import {Risco} from './risco';

export interface Cliente {
  id: number;
  nome: string;
  renda: number;
  endereco: string;
  valorPatrimonio?: number;
  valorDividas?: number;
  empregado?: boolean;
  risco: Risco;
  tipo: string;
}
