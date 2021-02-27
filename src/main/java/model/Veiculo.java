package model;

import javax.persistence.*;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @Column(name = "ROTEIRO")
    private Long roteiro;

   /* @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumns(name = "motorista_id")
    private Motorista motorista;*/

    @Column(name = "PLACA")
    private String placa;

    @Column(name = "EMPRESA")
    private String empresa;

    @Column(name = "NUMERACAO")
    private Integer numeracao;

    @Column(name = "ASSENTOS")
    private Integer assentos;

    @Column(name = "ASSENTOS_OCUPADOS")
    private Integer assentosOcupados;

    public Long getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(Long roteiro) {
        this.roteiro = roteiro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Integer getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(Integer numeracao) {
        this.numeracao = numeracao;
    }

    public Integer getAssentos() {
        return assentos;
    }

    public void setAssentos(Integer assentos) {
        this.assentos = assentos;
    }

    public Integer getAssentosOcupados() {
        return assentosOcupados;
    }

    public void setAssentosOcupados(Integer assentosOcupados) {
        this.assentosOcupados = assentosOcupados;
    }
}
