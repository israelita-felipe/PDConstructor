package br.edu.ufrpe.uag.projetao.control.util.imagem;

import java.util.LinkedList;
import java.util.List;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractPaginador;
import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.ClasssificacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.EscolhaImagemClasse;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.Usuario;

/**
 * 
 * @author Juan Augusto
 *
 */
public class ClassificacaoBaseImagemPaginator extends
	AbstractPaginador<LiberacaoBaseImagemClasse, ClasssificacaoImagemClasse, EscolhaImagemClasse, AlocacaoImagemClasse> {

    private ClassificacaoBaseImagemPaginator(InterfaceController<ClasssificacaoImagemClasse> controller,
	    LiberacaoBaseImagemClasse liberacao, List<EscolhaImagemClasse> listaDeClasses,
	    List<AlocacaoImagemClasse> listaDeAlocacao, List<ClasssificacaoImagemClasse> listaDeClassificacao) {
	super(controller, liberacao, listaDeClasses, listaDeAlocacao, listaDeClassificacao);
    }

    /**
     * Inicia uma inst�ncia de Paginador dado uma liberação de imagem
     * 
     * @param liberacao
     * @return
     */
    public static ClassificacaoBaseImagemPaginator getInstance(LiberacaoBaseImagemClasse liberacao) {

	// instanciando os controladores
	InterfaceController<LiberacaoBaseImagemClasse> liberacaoImagemClasseController = ControllerFactory
		.getLiberacaoBaseImagemClasseController();
	InterfaceController<AlocacaoImagemClasse> alocacaoImagemClasseController = ControllerFactory
		.getAlocacaoImagemClasseController();
	InterfaceController<ClasssificacaoImagemClasse> classificacaoImagemClasseController = ControllerFactory
		.getClassificacaoImagemClasseController();

	liberacaoImagemClasseController.prepareView(liberacaoImagemClasseController.getItems().indexOf(liberacao));

	// preparando listas para passagem da nova instância
	alocacaoImagemClasseController
		.getItemsFromCriteria(DetachedCriteriaFactory.getAlocacoesImagemPorLiberacao(liberacao));

	// criação de lista de opções de classificação
	List<EscolhaImagemClasse> opcoesDeClassificacao = new LinkedList<EscolhaImagemClasse>(
		alocacaoImagemClasseController.getItems().get(0).getEscolhaImagemClasses());

	// criação de lista de classificação
	List<ClasssificacaoImagemClasse> listaDeClassificacao = new LinkedList<>();
	Usuario escravo = UsuarioController.currentEscravo;

	for (AlocacaoImagemClasse alocacao : alocacaoImagemClasseController.getItems()) {
	    List<ClasssificacaoImagemClasse> clas = classificacaoImagemClasseController.getItemsFromCriteria(
		    DetachedCriteriaFactory.getClassificacaoImagemClassePorEscravoEAlocacao(escravo, alocacao));
	    listaDeClassificacao.addAll(clas);
	}
	classificacaoImagemClasseController.getItems().clear();
	classificacaoImagemClasseController.getItems().addAll(listaDeClassificacao);

	// nova instância
	return new ClassificacaoBaseImagemPaginator(classificacaoImagemClasseController, liberacao,
		opcoesDeClassificacao, alocacaoImagemClasseController.getItems(), listaDeClassificacao);
    }

    @Override
    public void preencheVazio() {
	int possivelIndiceInicial = getController().getItems().size() - 1;
	int indiceInicial = possivelIndiceInicial < 0 ? 0 : possivelIndiceInicial;

	for (int i = indiceInicial; getListaDeClassificacao().size() < getListaDeAlocacoes().size(); i++) {

	    ClasssificacaoImagemClasse classificacao = new ClasssificacaoImagemClasse();
	    classificacao.setUsuario(UsuarioController.currentEscravo);
	    classificacao.setAlocacaoImagemClasse(getListaDeAlocacoes().get(i));

	    getListaDeClassificacao().add(classificacao);
	}

	getController().getItems().clear();
	getController().getItems().addAll(getListaDeClassificacao());
    }

    @Override
    public int buscaPrimeiraClassificacaoEfetivada() {
	int i = 0;
	while (i < getListaDeClassificacao().size()
		&& getListaDeClassificacao().get(i).getEscolhaImagemClasse() != null) {
	    i++;
	}
	return i == 0 ? 0 : i - 1;
    }

}
