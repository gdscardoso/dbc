package br.com.dbc.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "risco")
public class Risco implements Serializable {

    @Id
    private String id;

    @Column(name = "taxa", nullable = false, precision = 20, scale = 4)
    private BigDecimal taxa;

    public String getId() {
        return id;
    }

    public Risco setId(String id) {
        this.id = id;
        return this;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public Risco setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
        return this;
    }
}
