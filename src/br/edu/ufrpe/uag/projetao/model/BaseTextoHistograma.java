/**
 * 
 */
package br.edu.ufrpe.uag.projetao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;

/**
 * @author israel
 *
 */
@Entity
@NamedNativeQueries(value = {
	@NamedNativeQuery(query = "select * from base_texto_histograma", name = "baseTextoHistograma", resultClass = BaseTextoHistograma.class),
	@NamedNativeQuery(query = "select * from base_texto_histograma where id = :base", name = "baseTextoHistogramaPorBase", resultClass = BaseTextoHistograma.class) })
public class BaseTextoHistograma implements InterfaceEntity {

    private int id;
    private String titulo;
    private String descricao;
    private String classe;
    private int total;
    private int supervisor;

    public BaseTextoHistograma() {
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

    /**
     * @return the base
     */
    @Id
    public int getId() {
	return id;
    }

    /**
     * @param base
     *            the base to set
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * @return the classe
     */
    @Id
    public String getClasse() {
	return classe;
    }

    /**
     * @param classe
     *            the classe to set
     */
    public void setClasse(String classe) {
	this.classe = classe;
    }

    /**
     * @return the supervisor
     */
    public int getSupervisor() {
	return supervisor;
    }

    /**
     * @param supervisor
     *            the supervisor to set
     */
    public void setSupervisor(int supervisor) {
	this.supervisor = supervisor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((classe == null) ? 0 : classe.hashCode());
	result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
	result = prime * result + id;
	result = prime * result + supervisor;
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
	if (classe == null) {
	    if (other.classe != null) {
		return false;
	    }
	} else if (!classe.equals(other.classe)) {
	    return false;
	}
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
	if (supervisor != other.supervisor) {
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
    public String toString() {
	return "BaseTextoHistograma [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", classe="
		+ classe + ", total=" + total + ", supervisor=" + supervisor + "]";
    }

}
