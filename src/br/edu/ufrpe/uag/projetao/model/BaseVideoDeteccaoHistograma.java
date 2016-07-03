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
	@NamedNativeQuery(query = "select * from base_video_deteccao_histograma", name = "baseVideoDeteccaoHistograma", resultClass = BaseVideoDeteccaoHistograma.class),
	@NamedNativeQuery(query = "select * from base_video_deteccao_histograma where base = :base", name = "baseVideoDeteccaoHistogramaPorBase", resultClass = BaseVideoDeteccaoHistograma.class) })
public class BaseVideoDeteccaoHistograma implements InterfaceEntity {

    private int id;
    private byte[] objeto;
    private int total;
    private int base;

    /**
     * 
     */
    public BaseVideoDeteccaoHistograma() {
	// TODO Auto-generated constructor stub
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
     * @return the objeto
     */
    public byte[] getObjeto() {
	return objeto;
    }

    /**
     * @param objeto
     *            the objeto to set
     */
    public void setObjeto(byte[] objeto) {
	this.objeto = objeto;
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
    public int getBase() {
	return base;
    }

    /**
     * @param base
     *            the base to set
     */
    public void setBase(int base) {
	this.base = base;
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
	result = prime * result + base;
	result = prime * result + id;
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
	if (!(obj instanceof BaseVideoDeteccaoHistograma)) {
	    return false;
	}
	BaseVideoDeteccaoHistograma other = (BaseVideoDeteccaoHistograma) obj;
	if (base != other.base) {
	    return false;
	}
	if (id != other.id) {
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
	return "BaseVideoDeteccaoHistograma [id=" + id + ", total=" + total + ", base=" + base + "]";
    }

}
