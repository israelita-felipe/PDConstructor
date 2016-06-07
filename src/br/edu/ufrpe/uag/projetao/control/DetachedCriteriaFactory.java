/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.ClasssificacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.DeteccaoImagem;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaImagemClasse;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.view.util.SHA256;

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
	    todosEscravos = getDetachedCriteriaUsuarioPorPerfil("CLASSIFICADOR");
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
		.forClass(AlocacaoImagemClasse.class)
		.add(Restrictions.eq("baseImagemClasse.id", liberacao.getBaseImagemClasse().getId()));

	return alocacoesImagemPorLiberacao;
    }

    public static DetachedCriteria getClassificacaoImagemClassePorEscravoEAlocacao(Usuario escravo,
	    AlocacaoImagemClasse alocacao) {
	DetachedCriteria classificacaoImagemClassePorEscravoEAlocacao = DetachedCriteria
		.forClass(ClasssificacaoImagemClasse.class).add(Restrictions.eq("usuario.id", escravo.getId()))
		.add(Restrictions.eq("alocacaoImagemClasse.id", alocacao.getId()));

	return classificacaoImagemClassePorEscravoEAlocacao;
    }

    public static DetachedCriteria getLiberacoesBaseImagemClasseDoEscravo(Usuario usuario) {
	DetachedCriteria liberacaoBasesImagemClasseDoEscravo = getDetachedCriteriaLiberacaoBasePorEscravo(
		LiberacaoBaseImagemClasse.class, usuario);

	return liberacaoBasesImagemClasseDoEscravo;
    }

    public static DetachedCriteria getLiberacoesPorBaseDeTexto(BaseTexto base) {
	DetachedCriteria usuariosComLiberacoesPorBaseDeTexto = DetachedCriteria.forClass(LiberacaoBaseTexto.class)
		.add(Restrictions.eq("baseTexto.id", base.getId()));

	return usuariosComLiberacoesPorBaseDeTexto;
    }

    /**
     * Seleciona o usuário dado uma senha e um email
     * 
     * @param email
     * @param senha
     * @return lista de usuários com senha e email passados como parâmetro
     */
    public static DetachedCriteria getUsuario(String email, String senha, Perfil perfil) {
	try {
	    return DetachedCriteria.forClass(Usuario.class).add(Restrictions.eq("email", email))
		    .add(Restrictions.eq("senha", SHA256.encode(senha)))
		    .add(Restrictions.eq("perfil.nome", perfil.getNome()));
	} catch (NoSuchAlgorithmException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    public static DetachedCriteria getLiberacoesPorBaseDeImagemClasse(BaseImagemClasse base) {
	DetachedCriteria usuariosComLiberacoesPorBaseDeImagemClasse = DetachedCriteria
		.forClass(LiberacaoBaseImagemClasse.class).add(Restrictions.eq("baseImagemClasse.id", base.getId()));

	return usuariosComLiberacoesPorBaseDeImagemClasse;
    }

    public static DetachedCriteria getBasesImagemDeteccaoDoUsuario(Usuario usuario) {
	DetachedCriteria basesImagemClasseDoUsuario = getDetachedCriteriaBase(BaseImagemDeteccao.class, usuario);

	return basesImagemClasseDoUsuario;
    }

    public static DetachedCriteria getLiberacoesPorBaseDeImagemDeteccao(BaseImagemDeteccao base) {
	DetachedCriteria usuariosComLiberacoesPorBaseDeImagemDeteccao = DetachedCriteria
		.forClass(LiberacaoBaseImagemDeteccao.class)
		.add(Restrictions.eq("baseImagemDeteccao.id", base.getId()));

	return usuariosComLiberacoesPorBaseDeImagemDeteccao;
    }

    public static DetachedCriteria getLiberacoesBaseImagemDeteccaoDoEscravo(Usuario usuario) {
	DetachedCriteria liberacaoBasesImagemDeteccaoDoEscravo = getDetachedCriteriaLiberacaoBasePorEscravo(
		LiberacaoBaseImagemDeteccao.class, usuario);

	return liberacaoBasesImagemDeteccaoDoEscravo;
    }

    public static DetachedCriteria getAlocacoesImagemDeteccaoPorLiberacao(LiberacaoBaseImagemDeteccao liberacao) {
	DetachedCriteria alocacoesImagemDeteccaoPorLiberacao = DetachedCriteria
		.forClass(LiberacaoBaseImagemDeteccao.class)
		.add(Restrictions.eq("usuarioByEscravo.id", liberacao.getUsuarioByEscravo().getId()))
		.forClass(AlocacaoImagemDeteccao.class)
		.add(Restrictions.eq("baseImagemDeteccao.id", liberacao.getBaseImagemDeteccao().getId()));

	return alocacoesImagemDeteccaoPorLiberacao;
    }

    public static DetachedCriteria getDeteccaoImagemPorEscravoEAlocacao(Usuario escravo,
	    AlocacaoImagemDeteccao alocacao) {
	DetachedCriteria deteccaoImagemClassePorEscravoEAlocacao = DetachedCriteria.forClass(DeteccaoImagem.class)
		.add(Restrictions.eq("usuario.id", escravo.getId()))
		.add(Restrictions.eq("alocacaoImagemDeteccao.id", alocacao.getId()));

	return deteccaoImagemClassePorEscravoEAlocacao;
    }

    public static DetachedCriteria getDeteccaoImagemPorAlocacao(AlocacaoImagemDeteccao alocacaoImagemDeteccao) {
	DetachedCriteria deteccaoImagemPorAlocacao = DetachedCriteria.forClass(DeteccaoImagem.class)
		.add(Restrictions.eq("alocacaoImagemDeteccao.id", alocacaoImagemDeteccao.getId()));
	return deteccaoImagemPorAlocacao;
    }

    public static DetachedCriteria getEscolhaImagemClassePorLiberacaoEAlocacao(LiberacaoBaseImagemClasse liberacao,
	    AlocacaoImagemClasse alocacao) {
	return DetachedCriteria.forClass(LiberacaoBaseImagemClasse.class)
		.add(Restrictions.eq("usuarioByEscravo.id", liberacao.getUsuarioByEscravo().getId()))
		.add(Restrictions.eq("baseImagemClasse.id", alocacao.getBaseImagemClasse().getId()))
		.forClass(EscolhaImagemClasse.class).add(Restrictions.eq("alocacaoImagemClasse.id", alocacao.getId()));
    }

    public static DetachedCriteria getEscolhaImagemClassePorUsuarioEAlocacao(Usuario usuario,
	    AlocacaoImagemClasse alocacao) {
	return DetachedCriteria.forClass(ClasssificacaoImagemClasse.class)
		.add(Restrictions.eq("usuario.id", usuario.getId()))
		.add(Restrictions.eq("alocacaoImagemClasse.id", alocacao.getId())).forClass(EscolhaImagemClasse.class)
		.add(Restrictions.eq("alocacaoImagemClasse.id", alocacao.getId()));
    }

}
