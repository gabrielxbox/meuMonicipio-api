package model;

import javax.persistence.*;

@Entity
@Table(name = "COLEGIO")
public class Colegio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COLEGIO")
    private Long idColegio;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "DIRETOR")
    private String diretor;

    @Column(name = "SEGMENTO")
    private String segmento;

    @Column(name = "TURNO")
    private String turno;

    @Column(name = "INTEGRAL")
    private Integer integral;

    @Column(name = "MULTI_FUNCIONAL")
    private Integer multiFuncional;

    public Long getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Long idColegio) {
        this.idColegio = idColegio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getMultiFuncional() {
        return multiFuncional;
    }

    public void setMultiFuncional(Integer multiFuncional) {
        this.multiFuncional = multiFuncional;
    }
}
