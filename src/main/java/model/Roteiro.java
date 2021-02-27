package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROTEIRO")
public class Roteiro {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "HORA_SAIDA_MANHA")
    private String horaSaidaManha;

    @Column(name = "HORA_VOLTA_MANHA")
    private String horaVoltaManha;

    @Column(name = "HORA_SAIDA_TARDE")
    private String horaSaidaTarde;

    @Column(name = "HORA_VOLTA_TARDE")
    private String horaVoltaTarde;

    @Column(name = "QUANTIDADE_ASSENTOS")
    private int quantidadeAssentos;

    private List<Parada> paradas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoraSaidaManha() {
        return horaSaidaManha;
    }

    public void setHoraSaidaManha(String horaSaidaManha) {
        this.horaSaidaManha = horaSaidaManha;
    }

    public String getHoraVoltaManha() {
        return horaVoltaManha;
    }

    public void setHoraVoltaManha(String horaVoltaManha) {
        this.horaVoltaManha = horaVoltaManha;
    }

    public String getHoraSaidaTarde() {
        return horaSaidaTarde;
    }

    public void setHoraSaidaTarde(String horaSaidaTarde) {
        this.horaSaidaTarde = horaSaidaTarde;
    }

    public String getHoraVoltaTarde() {
        return horaVoltaTarde;
    }

    public void setHoraVoltaTarde(String horaVoltaTarde) {
        this.horaVoltaTarde = horaVoltaTarde;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    public int getQuantidadeAssentos() {
        return quantidadeAssentos;
    }

    public void setQuantidadeAssentos(int quantidadeAssentos) {
        this.quantidadeAssentos = quantidadeAssentos;
    }
}
