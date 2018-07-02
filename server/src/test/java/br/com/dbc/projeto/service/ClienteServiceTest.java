package br.com.dbc.projeto.service;

import br.com.dbc.projeto.model.Cliente;
import br.com.dbc.projeto.repository.ClienteRepository;
import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;


    @Test
    public void createCliente() {
        Cliente c1 = new Cliente();
        c1.setNome("Teste 1");
        c1.setEndereco("Teste 1");
        c1.setTipo(Cliente.ClienteTipo.COMUM);
        c1.setRenda(BigDecimal.valueOf(1000));
        c1.setEmpregado(false);

        Cliente r1 = this.clienteService.create(c1);
        Assert.notNull(r1.getId(), "erro ao criar cliente c1");

        Cliente c2 = new Cliente();
        c2.setNome("Teste 2");
        c2.setEndereco("Teste 2");
        c2.setTipo(Cliente.ClienteTipo.COMUM);
        c2.setRenda(BigDecimal.valueOf(2500));
        c2.setEmpregado(false);

        Cliente r2 = this.clienteService.create(c2);
        Assert.notNull(r2.getId(), "erro ao criar cliente c2");


        Cliente c3 = new Cliente();
        c3.setNome("Teste 3");
        c3.setEndereco("Teste 3");
        c3.setTipo(Cliente.ClienteTipo.COMUM);
        c3.setRenda(BigDecimal.valueOf(8500));
        c3.setEmpregado(false);

        Cliente r3 = this.clienteService.create(c3);
        Assert.notNull(r3.getId(), "erro ao criar cliente c3");

        Assert.isTrue(r1.getRisco().getId().equalsIgnoreCase("C"), "risco diferente do esperado");
        Assert.isTrue(r2.getRisco().getId().equalsIgnoreCase("B"), "risco diferente do esperado");
        Assert.isTrue(r3.getRisco().getId().equalsIgnoreCase("A"), "risco diferente do esperado");
    }

    @Test
    public void simulacaoEmprestimo() {
        this.clienteRepository.findAll().forEach(c -> {
            Hibernate.initialize(c.getRisco());
            switch (c.getRisco().getId()) {
                case "C":
                    ClienteService.SimulacaoRequestDTO r1 = new ClienteService.SimulacaoRequestDTO();
                    r1.setClienteId(c.getId());
                    r1.setValorOperacao(BigDecimal.valueOf(1000));
                    r1.setPrazo(1);

                    ClienteService.SimulacaoResponseDTO res1 = this.clienteService.simular(r1);
                    Assert.isTrue(res1.getValorTotal().compareTo(BigDecimal.valueOf(1100)) == 0, "simulacao invalida para risco C");

                    break;
                case "B":
                    ClienteService.SimulacaoRequestDTO r2 = new ClienteService.SimulacaoRequestDTO();
                    r2.setClienteId(c.getId());
                    r2.setValorOperacao(BigDecimal.valueOf(1000));
                    r2.setPrazo(1);

                    ClienteService.SimulacaoResponseDTO res2 = this.clienteService.simular(r2);
                    Assert.isTrue(res2.getValorTotal().compareTo(BigDecimal.valueOf(1050)) == 0, "simulacao invalida para risco B");

                    break;

                case "A":
                    ClienteService.SimulacaoRequestDTO r3 = new ClienteService.SimulacaoRequestDTO();
                    r3.setClienteId(c.getId());
                    r3.setValorOperacao(BigDecimal.valueOf(1000));
                    r3.setPrazo(1);

                    ClienteService.SimulacaoResponseDTO res3 = this.clienteService.simular(r3);
                    Assert.isTrue(res3.getValorTotal().compareTo(BigDecimal.valueOf(1019)) == 0, "simulacao invalida para risco A");
                    break;
            }


        });


    }
}
