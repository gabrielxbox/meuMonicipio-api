package model;

import javax.persistence.*;

@Entity
@Table(name = "MOTORISTA")
public class Motorista extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "ID")
    private Long id;

    @Column(name = "ID_ROTEIRO")
    private Long idRoteiro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRoteiro() {
        return idRoteiro;
    }

    public void setIdRoteiro(Long idRoteiro) {
        this.idRoteiro = idRoteiro;
    }
}
