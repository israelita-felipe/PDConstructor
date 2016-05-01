/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.model.enumerate.StatusDeLiberacao;

/**
 * @author israel
 *
 */
public class DetachedCriteriaFactory {

    private static DetachedCriteria todosEscravos;
    private static DetachedCriteria todosSupervisores;
    private static DetachedCriteria basesTextoDoUsuario;
    private static DetachedCriteria basesImagemClasseDoUsuario;
    private static DetachedCriteria liberacaoBasesTextoDoEscravo;
    private static DetachedCriteria liberacaoBasesTextoDoSupervisor;
    private static DetachedCriteria alocacoesTextoPorLiberacao;
    private static DetachedCriteria classesTextoPorAlocacao;

    public static DetachedCriteria getAlocacoesTextoPorLiberacao(LiberacaoBaseTexto liberacao) {
	if (alocacoesTextoPorLiberacao == null) {
	    alocacoesTextoPorLiberacao = DetachedCriteria.forClass(LiberacaoBaseTexto.class)
		    .add(Restrictions.eq("usuarioByEscravo.id", liberacao.getUsuarioByEscravo().getId()))
		    .forClass(AlocacaoTexto.class)
		    .add(Restrictions.eq("baseTexto.id", liberacao.getBaseTexto().getId()));
	}	
	return alocacoesTextoPorLiberacao;
    }

    public static DetachedCriteria getClassesTextoPorAlocacao(AlocacaoTexto alocacao) {
	if (classesTextoPorAlocacao == null) {
	    classesTextoPorAlocacao = DetachedCriteria.forClass(EscolhaClasseTexto.class)
		    .add(Restrictions.eq("alocacaoTexto.id", alocacao.getId()));
	}
	return classesTextoPorAlocacao;
    }

    public static DetachedCriteria getTodosEscravos() {
	if (todosEscravos == null) {
	    todosEscravos = getDetachedCriteriaUsuarioPorPerfil("ESCRAVO");
	}
	return todosEscravos;
    }

    public static DetachedCriteria getTodosSupervisores() {
	if (todosSupervisores == null) {
	    todosSupervisores = getDetachedCriteriaUsuarioPorPerfil("SUPERVISOR");
	}
	return todosSupervisores;
    }

    public static DetachedCriteria getBasesImagemClasseDoUsuario(Usuario usuario) {
	if (basesImagemClasseDoUsuario == null) {
	    basesImagemClasseDoUsuario = getDetachedCriteriaBase(BaseImagemClasse.class, usuario);
	}
	return basesImagemClasseDoUsuario;

    }

    public static DetachedCriteria getBasesTextoDoUsuario(Usuario usuario) {
	if (basesTextoDoUsuario == null) {
	    basesTextoDoUsuario = getDetachedCriteriaBase(BaseTexto.class, usuario);
	}
	return basesTextoDoUsuario;

    }

    public static DetachedCriteria getLiberacoesBaseTextoDoEscravo(Usuario usuario) {
	if (liberacaoBasesTextoDoEscravo == null) {
	    liberacaoBasesTextoDoEscravo = getDetachedCriteriaLiberacaoBasePorEscravo(LiberacaoBaseTexto.class,
		    usuario);
	}
	return liberacaoBasesTextoDoEscravo;
    }

    public static DetachedCriteria getLiberacoesBaseTextoDoSupervisor(Usuario usuario) {
	if (liberacaoBasesTextoDoSupervisor == null) {
	    liberacaoBasesTextoDoSupervisor = getDetachedCriteriaLiberacaoBasePorSupervisor(LiberacaoBaseTexto.class,
		    usuario);
	}
	return liberacaoBasesTextoDoSupervisor;
    }

    /**
     * Cria uma criteia de pesquisa de base
     * 
     * @param classe
     *            da base a ser pesquisada
     * @param usuario
     *            dono da base
     * @return DetachedCriteria de uma base e um usuario
     */
    private static DetachedCriteria getDetachedCriteriaBase(Class<?> classe, Usuario usuario) {
	return DetachedCriteria.forClass(classe).add(Restrictions.eq("usuario.id", usuario.getId()));
    }

    private static DetachedCriteria getDetachedCriteriaLiberacaoBasePorEscravo(Class<?> classe, Usuario usuario) {
	return DetachedCriteria.forClass(classe).add(Restrictions.eq("usuarioByEscravo.id", usuario.getId()))
		.add(Restrictions.eq("status", StatusDeLiberacao.LIBERADO));
    }

    private static DetachedCriteria getDetachedCriteriaLiberacaoBasePorSupervisor(Class<?> classe, Usuario usuario) {
	return DetachedCriteria.forClass(classe).add(Restrictions.eq("usuarioBySupervisor.id", usuario.getId()));
    }

    private static DetachedCriteria getDetachedCriteriaUsuarioPorPerfil(String perfil) {
	return DetachedCriteria.forClass(Usuario.class).add(Restrictions.eq("perfil.nome", perfil));
    }

}
