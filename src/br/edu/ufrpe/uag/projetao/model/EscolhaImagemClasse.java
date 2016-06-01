package br.edu.ufrpe.uag.projetao.model;
// Generated 26/04/2016 23:17:23 by Hibernate Tools 4.3.1

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

import br.edu.ufrpe.uag.projetao.annotations.Coluna;
import br.edu.ufrpe.uag.projetao.annotations.Tabela;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;

/**
 * EscolhaImagemClasse generated by hbm2java
 */
@Tabela
@Entity
@Table(name = "escolha_imagem_classe", schema = "public")
public class EscolhaImagemClasse implements java.io.Serializable, InterfaceEntity {

    private int id;
    @Coluna(colunaNome = "Alocação de imagem", colunaPosicao = 0)
    private AlocacaoImagemClasse alocacaoImagemClasse;
    @Coluna(colunaNome = "Descrição", colunaPosicao = 1)
    private String descricao;
    private Set<ClasssificacaoImagemClasse> classsificacaoImagemClasses = new HashSet<>(0);

    public EscolhaImagemClasse() {
    }

    public EscolhaImagemClasse(int id, AlocacaoImagemClasse alocacaoImagemClasse, String descricao) {
	this.id = id;
	this.alocacaoImagemClasse = alocacaoImagemClasse;
	this.descricao = descricao;
    }

    public EscolhaImagemClasse(int id, AlocacaoImagemClasse alocacaoImagemClasse, String descricao,
	    Set<ClasssificacaoImagemClasse> classsificacaoImagemClasses) {
	this.id = id;
	this.alocacaoImagemClasse = alocacaoImagemClasse;
	this.descricao = descricao;
	this.classsificacaoImagemClasses = classsificacaoImagemClasses;
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
    @JoinColumn(name = "alocacao_imagem_classe", nullable = false)
    public AlocacaoImagemClasse getAlocacaoImagemClasse() {
	return this.alocacaoImagemClasse;
    }

    public void setAlocacaoImagemClasse(AlocacaoImagemClasse alocacaoImagemClasse) {
	this.alocacaoImagemClasse = alocacaoImagemClasse;
    }

    @Column(name = "descricao", nullable = false)
    public String getDescricao() {
	return this.descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "escolhaImagemClasse")
    public Set<ClasssificacaoImagemClasse> getClasssificacaoImagemClasses() {
	return this.classsificacaoImagemClasses;
    }

    public void setClasssificacaoImagemClasses(Set<ClasssificacaoImagemClasse> classsificacaoImagemClasses) {
	this.classsificacaoImagemClasses = classsificacaoImagemClasses;
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
	result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
	if (!(obj instanceof EscolhaImagemClasse)) {
	    return false;
	}
	EscolhaImagemClasse other = (EscolhaImagemClasse) obj;
	if (descricao == null) {
	    if (other.descricao != null) {
		return false;
	    }
	} else if (!descricao.equals(other.descricao)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return this.descricao;
    }

}
