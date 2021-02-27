package model;

import javax.persistence.*;

@Entity
@Table(name = "ACADEMICO")
public class Academico extends Pessoa {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IS_CADASTRADO", columnDefinition = "boolean default false")
    private Boolean isCadastrado;

    @Column(name = "ROTEIRO")
    private Integer roteiro;

    private String instituicao;
    private String campus;

    @Column(name = "CURSO")
    private String curso;

    @Column(name = "TURNO")
    private int turno;

    @Column(name = "NIVEL_CURSO")
    private int nivelCurso;

    @Column(name = "SEMESTRE_ATUAL")
    private int semestreAtual;

    @Column(name = "IS_BOLSISTA")
    private boolean bolsista;

    @Column(name = "PROGRAMA_BOLSA")
    private String programaBolsa;

    @Column(name = "PERCENTUAL_BOLSA")
    private int percentualBolsa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column(name = "ID_INSTITUICAO")
    private Integer idInstituicao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getCadastrado() {
        return isCadastrado;
    }

    public void setCadastrado(Boolean cadastrado) {
        isCadastrado = cadastrado;
    }

    public Integer getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(Integer roteiro) {
        this.roteiro = roteiro;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getNivelCurso() {
        return nivelCurso;
    }

    public void setNivelCurso(int nivelCurso) {
        this.nivelCurso = nivelCurso;
    }

    public int getSemestreAtual() {
        return semestreAtual;
    }

    public void setSemestreAtual(int semestreAtual) {
        this.semestreAtual = semestreAtual;
    }

    public boolean isBolsista() {
        return bolsista;
    }

    public void setBolsista(boolean bolsista) {
        this.bolsista = bolsista;
    }

    public String getProgramaBolsa() {
        return programaBolsa;
    }

    public void setProgramaBolsa(String programaBolsa) {
        this.programaBolsa = programaBolsa;
    }

    public int getPercentualBolsa() {
        return percentualBolsa;
    }

    public void setPercentualBolsa(int percentualBolsa) {
        this.percentualBolsa = percentualBolsa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
