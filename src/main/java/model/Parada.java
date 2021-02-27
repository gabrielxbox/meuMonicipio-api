package model;

import javax.persistence.*;

@Entity
@Table(name = "PARADA")
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "HORARIO_IDA_MANHA")
    private String horarioIdaManha;

    @Column(name = "HORARIO_VOLTA_MANHA")
    private String horarioVoltaManha;

    @Column(name = "HORARIO_IDA_TARDE")
    private String horarioIdaTarde;

    @Column(name = "HORARIO_VOLTA_TARDE")
    private String horarioVoltaTarde;

    @Column(name = "ID_ROTEIRO")
    private Long idRoteiro;

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getHorarioIdaManha() {
        return horarioIdaManha;
    }

    public void setHorarioIdaManha(String horarioIdaManha) {
        this.horarioIdaManha = horarioIdaManha;
    }

    public String getHorarioVoltaManha() {
        return horarioVoltaManha;
    }

    public void setHorarioVoltaManha(String horarioVoltaManha) {
        this.horarioVoltaManha = horarioVoltaManha;
    }

    public String getHorarioIdaTarde() {
        return horarioIdaTarde;
    }

    public void setHorarioIdaTarde(String horarioIdaTarde) {
        this.horarioIdaTarde = horarioIdaTarde;
    }

    public String getHorarioVoltaTarde() {
        return horarioVoltaTarde;
    }

    public void setHorarioVoltaTarde(String horarioVoltaTarde) {
        this.horarioVoltaTarde = horarioVoltaTarde;
    }

    public Long getIdRoteiro() {
        return idRoteiro;
    }

    public void setIdRoteiro(Long idRoteiro) {
        this.idRoteiro = idRoteiro;
    }
}
