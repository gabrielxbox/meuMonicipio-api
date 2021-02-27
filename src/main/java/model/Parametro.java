package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARAMETRO")
public class Parametro {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "IS_PERIODO_MATRICULA")
    private Integer isPeriodoMatricula;

    @Column(name = "IS_PERIODO_RENOVACAO")
    private Integer isPeriodoRenovacao;

    @Column(name = "IS_ELEICAO")
    private Integer isEleicao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsPeriodoMatricula() {
        return isPeriodoMatricula;
    }

    public void setIsPeriodoMatricula(Integer isPeriodoMatricula) {
        this.isPeriodoMatricula = isPeriodoMatricula;
    }

    public Integer getIsPeriodoRenovacao() {
        return isPeriodoRenovacao;
    }

    public void setIsPeriodoRenovacao(Integer isPeriodoRenovacao) {
        this.isPeriodoRenovacao = isPeriodoRenovacao;
    }

    public Integer getIsEleicao() {
        return isEleicao;
    }

    public void setIsEleicao(Integer isEleicao) {
        this.isEleicao = isEleicao;
    }
}
