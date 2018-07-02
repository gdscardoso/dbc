export interface SimulacaoRequest {
  clienteId: number;
  valorOperacao: number;
  prazo: number;
}

export interface SimulacaoResponse {
  clienteId: number;
  valorOperacao: number;
  prazo: number;
  valorTotal: number;
  valorParcela: number;
}

