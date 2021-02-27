package model;

import javax.persistence.*;

@Entity
@Table(name = "SOLICITACAO")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SOLICITACAO")
    private Long idSolicitacao;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "CAMINHO_IMAGEM")
    private String caminhoImagem;

    @Column(name = "CATEGORIA")
    private int categoria;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "NOTIFICADO")
    private int notificado;

    @Column(name = "RESPOSTA")
    private String resposta;

    @Column(name = "RETORNO_USUARIO")
    private Integer retornoUsuario;

    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNotificado() {
        return notificado;
    }

    public void setNotificado(int notificado) {
        this.notificado = notificado;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Integer getRetornoUsuario() {
        return retornoUsuario;
    }

    public void setRetornoUsuario(Integer retornoUsuario) {
        this.retornoUsuario = retornoUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}