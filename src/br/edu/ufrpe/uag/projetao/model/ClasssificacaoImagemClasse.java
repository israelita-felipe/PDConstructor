package br.edu.ufrpe.uag.projetao.model;
// Generated 26/04/2016 23:17:23 by Hibernate Tools 4.3.1

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ufrpe.uag.projetao.annotations.Coluna;
import br.edu.ufrpe.uag.projetao.annotations.Tabela;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;

/**
 * ClasssificacaoImagemClasse generated by hbm2java
 */
@Tabela
@Entity
@Table(name = "classsificacao_imagem_classe", schema = "public")
public class ClasssificacaoImagemClasse implements java.io.Serializable, InterfaceEntity {

    @Coluna(colunaNome = "Alocação de Imagem", colunaPosicao = 0)
    private AlocacaoImagemClasse alocacaoImagemClasse;
    @Coluna(colunaNome = "Classe", colunaPosicao = 1)
    private EscolhaImagemClasse escolhaImagemClasse;
    @Coluna(colunaNome = "Escravo", colunaPosicao = 2)
    private Usuario usuario;

    public ClasssificacaoImagemClasse() {
    }

    public ClasssificacaoImagemClasse(AlocacaoImagemClasse alocacaoImagemClasse,
	    EscolhaImagemClasse escolhaImagemClasse, Usuario usuario) {

	this.alocacaoImagemClasse = alocacaoImagemClasse;
	this.escolhaImagemClasse = escolhaImagemClasse;
	this.usuario = usuario;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alocacao_imagem_classe", nullable = false, insertable = false, updatable = false)
    public AlocacaoImagemClasse getAlocacaoImagemClasse() {
	return this.alocacaoImagemClasse;
    }

    public void setAlocacaoImagemClasse(AlocacaoImagemClasse alocacaoImagemClasse) {
	this.alocacaoImagemClasse = alocacaoImagemClasse;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escolha_imagem_classe", nullable = false)
    public EscolhaImagemClasse getEscolhaImagemClasse() {
	return this.escolhaImagemClasse;
    }

    public void setEscolhaImagemClasse(EscolhaImagemClasse escolhaImagemClasse) {
	this.escolhaImagemClasse = escolhaImagemClasse;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escravo", nullable = false, insertable = false, updatable = false)
    public Usuario getUsuario() {
	return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((alocacaoImagemClasse == null) ? 0 : alocacaoImagemClasse.hashCode());
	result = prime * result + ((escolhaImagemClasse == null) ? 0 : escolhaImagemClasse.hashCode());
	result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ClasssificacaoImagemClasse other = (ClasssificacaoImagemClasse) obj;
	if (alocacaoImagemClasse == null) {
	    if (other.alocacaoImagemClasse != null)
		return false;
	} else if (!alocacaoImagemClasse.equals(other.alocacaoImagemClasse))
	    return false;
	if (escolhaImagemClasse == null) {
	    if (other.escolhaImagemClasse != null)
		return false;
	} else if (!escolhaImagemClasse.equals(other.escolhaImagemClasse))
	    return false;
	if (usuario == null) {
	    if (other.usuario != null)
		return false;
	} else if (!usuario.equals(other.usuario))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return alocacaoImagemClasse.getId() + "." + escolhaImagemClasse.getId() + "." + usuario.getId();
    }

}
