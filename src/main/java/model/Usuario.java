package model;

import javax.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CPF")
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENDERECO_ID")
    private Endereco endereco;

    @Column(name = "IMEI")
    private String imei;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "FABRICANTE")
    private String fabricante;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "VERSAO_ANDROID")
    private String versaoAndroid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVersaoAndroid() {
        return versaoAndroid;
    }

    public void setVersaoAndroid(String versaoAndroid) {
        this.versaoAndroid = versaoAndroid;
    }
}