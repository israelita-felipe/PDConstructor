/**
 * 
 */
package br.edu.ufrpe.uag.projetao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;

/**
 * @author israel
 *
 */
@Entity
@NamedNativeQuery(query = "select * from BASE_TEXTO_HISTOGRAMA", resultClass = BaseTextoHistograma.class, name = "totalClassificacaoPorBaseTexto")
public class BaseTextoHistograma implements InterfaceEntity {

    private int id;
    private String titulo;
    private String descricao;
    private int total;

    public BaseTextoHistograma() {
    }

    /**
     * @return the id
     */
    @Id
    public int getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
	return titulo;
    }

    /**
     * @param titulo
     *            the titulo to set
     */
    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
	return descricao;
    }

    /**
     * @param descricao
     *            the descricao to set
     */
    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    /**
     * @return the total
     */
    public int getTotal() {
	return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(int total) {
	this.total = total;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    @Transient
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
	result = prime * result + id;
	result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
	result = prime * result + total;
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    @Transient
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof BaseTextoHistograma)) {
	    return false;
	}
	BaseTextoHistograma other = (BaseTextoHistograma) obj;
	if (descricao == null) {
	    if (other.descricao != null) {
		return false;
	    }
	} else if (!descricao.equals(other.descricao)) {
	    return false;
	}
	if (id != other.id) {
	    return false;
	}
	if (titulo == null) {
	    if (other.titulo != null) {
		return false;
	    }
	} else if (!titulo.equals(other.titulo)) {
	    return false;
	}
	if (total != other.total) {
	    return false;
	}
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    @Transient
    public String toString() {
	return "BaseTextoHistograma [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", total=" + total
		+ "]";
    }

}
