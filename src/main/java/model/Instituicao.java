package model;

import javax.persistence.*;

@Entity
@Table(name = "INSTITUICAO")
public class Instituicao {

    @Id
    @Column(name = "ID", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", insertable = false, updatable = false)
    private String nome;

    @Column(name = "CAMPUS", insertable = false, updatable = false)
    private String campus;

    @Column(name = "ID_ROTEIRO", insertable = false, updatable = false)
    private Integer idRoteiro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Integer getIdRoteiro() {
        return idRoteiro;
    }

    public void setIdRoteiro(Integer idRoteiro) {
        this.idRoteiro = idRoteiro;
    }
}
