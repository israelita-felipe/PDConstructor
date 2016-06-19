package br.edu.ufrpe.uag.projetao.model;
// Generated 27/05/2016 19:57:54 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;

/**
 * AlocacaoImagemDeteccao generated by hbm2java
 */
@Entity
@Table(name = "alocacao_imagem_deteccao", schema = "public")
public class AlocacaoImagemDeteccao implements InterfaceEntity {

    private int id;
    private BaseImagemDeteccao baseImagemDeteccao;
    private ImagemDeteccao imagemDeteccao;
    private Usuario usuario;
    private Set<DeteccaoImagem> deteccaoImagems = new HashSet<DeteccaoImagem>(0);

    public AlocacaoImagemDeteccao() {
    }

    public AlocacaoImagemDeteccao(int id, BaseImagemDeteccao baseImagemDeteccao, ImagemDeteccao imagemDeteccao,
	    Usuario usuario) {
	this.id = id;
	this.baseImagemDeteccao = baseImagemDeteccao;
	this.imagemDeteccao = imagemDeteccao;
	this.usuario = usuario;
    }

    public AlocacaoImagemDeteccao(int id, BaseImagemDeteccao baseImagemDeteccao, ImagemDeteccao imagemDeteccao,
	    Usuario usuario, Set<DeteccaoImagem> deteccaoImagems) {
	this.id = id;
	this.baseImagemDeteccao = baseImagemDeteccao;
	this.imagemDeteccao = imagemDeteccao;
	this.usuario = usuario;
	this.deteccaoImagems = deteccaoImagems;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_imagem_deteccao", nullable = false)
    public BaseImagemDeteccao getBaseImagemDeteccao() {
	return this.baseImagemDeteccao;
    }

    public void setBaseImagemDeteccao(BaseImagemDeteccao baseImagemDeteccao) {
	this.baseImagemDeteccao = baseImagemDeteccao;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imagem_deteccao", nullable = false)
    public ImagemDeteccao getImagemDeteccao() {
	return this.imagemDeteccao;
    }

    public void setImagemDeteccao(ImagemDeteccao imagemDeteccao) {
	this.imagemDeteccao = imagemDeteccao;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor", nullable = false)
    public Usuario getUsuario() {
	return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "alocacaoImagemDeteccao")
    public Set<DeteccaoImagem> getDeteccaoImagems() {
	return this.deteccaoImagems;
    }

    public void setDeteccaoImagems(Set<DeteccaoImagem> deteccaoImagems) {
	this.deteccaoImagems = deteccaoImagems;
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
	result = prime * result + ((baseImagemDeteccao == null) ? 0 : baseImagemDeteccao.hashCode());
	result = prime * result + id;
	result = prime * result + ((imagemDeteccao == null) ? 0 : imagemDeteccao.hashCode());
	result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
	if (!(obj instanceof AlocacaoImagemDeteccao)) {
	    return false;
	}
	AlocacaoImagemDeteccao other = (AlocacaoImagemDeteccao) obj;
	if (baseImagemDeteccao == null) {
	    if (other.baseImagemDeteccao != null) {
		return false;
	    }
	} else if (!baseImagemDeteccao.equals(other.baseImagemDeteccao)) {
	    return false;
	}
	if (id != other.id) {
	    return false;
	}
	if (imagemDeteccao == null) {
	    if (other.imagemDeteccao != null) {
		return false;
	    }
	} else if (!imagemDeteccao.equals(other.imagemDeteccao)) {
	    return false;
	}
	if (usuario == null) {
	    if (other.usuario != null) {
		return false;
	    }
	} else if (!usuario.equals(other.usuario)) {
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
	return "Base: " + baseImagemDeteccao + ", Imagem: " + imagemDeteccao.getId() + "]";
    }

}
