/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.ClasssificacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.Usuario;

/**
 * @author israel
 * @author Juan Augusto
 *
 */
public class DetachedCriteriaFactory {

    private static DetachedCriteria todosEscravos;
    private static DetachedCriteria todosSupervisores;

    /**
     * Faz uma busca de toda as classificações de texto feitas por um usuário e
     * pertencentes a uma alocação
     * 
     * @param escravo
     * @param alocacao
     * @return
     */
    public static DetachedCriteria getClassificacaoTextoPorEscravoEAlocacao(Usuario escravo, AlocacaoTexto alocacao) {
	DetachedCriteria classificacaoTextoPorEscravoEAlocacao = DetachedCriteria.forClass(ClassificacaoTexto.class)
		.add(Restrictions.eq("usuario.id", escravo.getId()))
		.add(Restrictions.eq("alocacaoTexto.id", alocacao.getId()));

	return classificacaoTextoPorEscravoEAlocacao;
    }

    public static DetachedCriteria getAlocacoesTextoPorLiberacao(LiberacaoBaseTexto liberacao) {

	DetachedCriteria alocacoesTextoPorLiberacao = DetachedCriteria.forClass(LiberacaoBaseTexto.class)
		.add(Restrictions.eq("usuarioByEscravo.id", liberacao.getUsuarioByEscravo().getId()))
		.forClass(AlocacaoTexto.class).add(Restrictions.eq("baseTexto.id", liberacao.getBaseTexto().getId()));

	return alocacoesTextoPorLiberacao;
    }

    public static DetachedCriteria getClassesTextoPorAlocacao(AlocacaoTexto alocacao) {

	DetachedCriteria classesTextoPorAlocacao = DetachedCriteria.forClass(EscolhaClasseTexto.class)
		.add(Restrictions.eq("alocacaoTexto.id", alocacao.getId()));

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

	DetachedCriteria basesImagemClasseDoUsuario = getDetachedCriteriaBase(BaseImagemClasse.class, usuario);

	return basesImagemClasseDoUsuario;

    }

    public static DetachedCriteria getBasesTextoDoUsuario(Usuario usuario) {

	DetachedCriteria basesTextoDoUsuario = getDetachedCriteriaBase(BaseTexto.class, usuario);

	return basesTextoDoUsuario;

    }

    public static DetachedCriteria getLiberacoesBaseTextoDoEscravo(Usuario usuario) {

	DetachedCriteria liberacaoBasesTextoDoEscravo = getDetachedCriteriaLiberacaoBasePorEscravo(
		LiberacaoBaseTexto.class, usuario);

	return liberacaoBasesTextoDoEscravo;
    }

    public static DetachedCriteria getLiberacoesBaseTextoDoSupervisor(Usuario usuario) {

	DetachedCriteria liberacaoBasesTextoDoSupervisor = getDetachedCriteriaLiberacaoBasePorSupervisor(
		LiberacaoBaseTexto.class, usuario);

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
	return DetachedCriteria.forClass(classe).add(Restrictions.eq("usuarioByEscravo.id",
		usuario.getId()))/*
				  * .add(Restrictions.eq("status",
				  * StatusDeLiberacao.LIBERADO))
				  */;
    }

    private static DetachedCriteria getDetachedCriteriaLiberacaoBasePorSupervisor(Class<?> classe, Usuario usuario) {
	return DetachedCriteria.forClass(classe).add(Restrictions.eq("usuarioBySupervisor.id", usuario.getId()));
    }

    private static DetachedCriteria getDetachedCriteriaUsuarioPorPerfil(String perfil) {
	return DetachedCriteria.forClass(Usuario.class).add(Restrictions.eq("perfil.nome", perfil));
    }

	public static DetachedCriteria getAlocacoesImagemPorLiberacao(LiberacaoBaseImagemClasse liberacao) {
		DetachedCriteria alocacoesImagemPorLiberacao = DetachedCriteria.forClass(LiberacaoBaseImagemClasse.class)
				.add(Restrictions.eq("usuarioByEscravo.id", liberacao.getUsuarioByEscravo().getId()))
				.forClass(AlocacaoImagemClasse.class).add(Restrictions.eq("baseImagemClasse.id", liberacao.getBaseImagemClasse().getId()));

			return alocacoesImagemPorLiberacao;
	}

	public static DetachedCriteria getClassificacaoImagemClassePorEscravoEAlocacao(Usuario escravo,
			AlocacaoImagemClasse alocacao) {
		DetachedCriteria classificacaoImagemClassePorEscravoEAlocacao = DetachedCriteria.forClass(ClasssificacaoImagemClasse.class)
				.add(Restrictions.eq("usuario.id", escravo.getId()))
				.add(Restrictions.eq("alocacaoImagemClasse.id", alocacao.getId()));

			return classificacaoImagemClassePorEscravoEAlocacao;
	}

	public static DetachedCriteria getLiberacoesBaseImagemClasseDoEscravo(Usuario usuario) {
		DetachedCriteria liberacaoBasesImagemClasseDoEscravo = getDetachedCriteriaLiberacaoBasePorEscravo(
				LiberacaoBaseImagemClasse.class, usuario);

			return liberacaoBasesImagemClasseDoEscravo;
	}



}
