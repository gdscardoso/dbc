package br.com.dbc.projeto.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "renda", nullable = false)
    private BigDecimal renda;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "valorPatrimonio", nullable = true)
    private BigDecimal valorPatrimonio;

    @Column(name = "valorDividas", nullable = true)
    private BigDecimal valorDividas;

    @Type(type = "numeric_boolean")
    @Column(name = "empregado", nullable = true)
    private Boolean empregado;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClienteTipo tipo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Risco risco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getValorPatrimonio() {
        return valorPatrimonio;
    }

    public void setValorPatrimonio(BigDecimal valorPatrimonio) {
        this.valorPatrimonio = valorPatrimonio;
    }

    public BigDecimal getValorDividas() {
        return valorDividas;
    }

    public void setValorDividas(BigDecimal valorDividas) {
        this.valorDividas = valorDividas;
    }

    public Boolean getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Boolean empregado) {
        this.empregado = empregado;
    }

    public ClienteTipo getTipo() {
        return tipo;
    }

    public void setTipo(ClienteTipo tipo) {
        this.tipo = tipo;
    }

    public Risco getRisco() {
        return risco;
    }

    public void setRisco(Risco risco) {
        this.risco = risco;
    }

    public enum ClienteTipo {
        COMUM, POTENCIAL, ESPECIAL
    }
}
