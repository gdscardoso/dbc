package br.com.dbc.projeto.service;

import br.com.dbc.projeto.model.Cliente;
import br.com.dbc.projeto.repository.ClienteRepository;
import br.com.dbc.projeto.repository.RiscoRepositort;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RiscoRepositort riscoRepositort;

    public List<Cliente> listAll() {
        return this.clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        Cliente cliente = this.clienteRepository.findById(id).get();
        Assert.notNull(cliente, "cliente não encontrado.");
        Hibernate.initialize(cliente.getRisco());
        return cliente;
    }

    @Transactional()
    public Cliente create(Cliente cliente) throws IllegalArgumentException {
        Assert.notNull(cliente, "cliente não pode ser nulo.");
        String risco = buscarRisco(cliente.getRenda());
        this.riscoRepositort.findById(risco).ifPresent(cliente::setRisco);
        return this.clienteRepository.save(cliente);
    }

    @Transactional()
    public Cliente update(Cliente cliente) throws IllegalArgumentException {
        Cliente c = this.clienteRepository.findById(cliente.getId()).get();
        Assert.notNull(c, "Cliente não encontrado");
        String risco = buscarRisco(cliente.getRenda());
        this.riscoRepositort.findById(risco).ifPresent(cliente::setRisco);

        return this.clienteRepository.save(cliente);
    }

    @Transactional()
    public void delete(@NotNull Integer id) throws IllegalArgumentException {
        this.clienteRepository.findById(id).ifPresent(this.clienteRepository::delete);
    }


    public SimulacaoResponseDTO simular(SimulacaoRequestDTO request) {
        Cliente cliente = this.clienteRepository.findById(request.clienteId).get();
        Assert.notNull(cliente, "cliente não encontrado.");
        Hibernate.initialize(cliente.getRisco());
        Assert.notNull(cliente.getRisco(), "não foi possivel localizar taxa de juros para o cliente");

        BigDecimal valorJuros = request.getValorOperacao().multiply(cliente.getRisco().getTaxa());
        BigDecimal valorTotal = request.valorOperacao.add(valorJuros);
        BigDecimal valorParcela = BigDecimal.valueOf(valorTotal.doubleValue() / request.prazo);

        SimulacaoResponseDTO response = new SimulacaoResponseDTO();
        response.setValorOperacao(request.getValorOperacao());
        response.setPrazo(request.getPrazo());
        response.setValorTotal(valorTotal);
        response.setValorParcela(valorParcela);

        return response;
    }

    private String buscarRisco(@NotNull BigDecimal renda) {
        if (renda == null || renda.compareTo(BigDecimal.valueOf(2000)) <= 0) return "C";
        if (renda.compareTo(BigDecimal.valueOf(8000)) <= 0) return "B";
        if (renda.compareTo(BigDecimal.valueOf(8000)) > 0) return "A";
        return null;
    }

    public static class SimulacaoRequestDTO {
        @NotNull
        private Integer clienteId;
        @NotNull
        private BigDecimal valorOperacao;
        @NotNull
        private Integer prazo;

        public Integer getClienteId() {
            return clienteId;
        }

        public void setClienteId(Integer clienteId) {
            this.clienteId = clienteId;
        }

        public BigDecimal getValorOperacao() {
            return valorOperacao;
        }

        public void setValorOperacao(BigDecimal valorOperacao) {
            this.valorOperacao = valorOperacao;
        }

        public Integer getPrazo() {
            return prazo;
        }

        public void setPrazo(Integer prazo) {
            this.prazo = prazo;
        }
    }

    public static class SimulacaoResponseDTO {
        @NotNull
        private BigDecimal valorOperacao;
        @NotNull
        private Integer prazo;
        @NotNull
        private BigDecimal valorTotal;
        @NotNull
        private BigDecimal valorParcela;

        public BigDecimal getValorOperacao() {
            return valorOperacao;
        }

        public void setValorOperacao(BigDecimal valorOperacao) {
            this.valorOperacao = valorOperacao;
        }

        public Integer getPrazo() {
            return prazo;
        }

        public void setPrazo(Integer prazo) {
            this.prazo = prazo;
        }

        public BigDecimal getValorTotal() {
            return valorTotal;
        }

        public void setValorTotal(BigDecimal valorTotal) {
            this.valorTotal = valorTotal;
        }

        public BigDecimal getValorParcela() {
            return valorParcela;
        }

        public void setValorParcela(BigDecimal valorParcela) {
            this.valorParcela = valorParcela;
        }
    }
}
