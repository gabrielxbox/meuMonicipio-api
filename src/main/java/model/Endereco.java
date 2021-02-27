package model;

import javax.persistence.*;

@Entity
@Table(name = "ENDERECO")
public class Endereco {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "MUNICIPIO")
    private String municipio;

    @Column(name = "CEP")
    private String cep;

    @OneToOne(mappedBy = "endereco")
    private Academico academico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Academico getAcademico() {
        return academico;
    }

    public void setAcademico(Academico academico) {
        this.academico = academico;
    }
}
