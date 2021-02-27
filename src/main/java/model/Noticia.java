package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NOTICIA")
public class Noticia {

    @Id
    @Column(name = "ID_NOTICIA")
    private Long idNoticia;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "RESUMO")
    private String resumo;

    @Column(name = "AUTOR")
    private String autor;

    @Column(name = "NOTICIA")
    private String noticia;

    private String[] imagens;

    @Column(name = "DATA")
    private String data;

    public Long getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(Long idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
