/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util.texto;

import java.util.LinkedList;
import java.util.List;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractPaginador;
import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.Usuario;

/**
 * @author israel
 *
 */
public class ClassificacaoBaseTextoPaginator
	extends AbstractPaginador<LiberacaoBaseTexto, ClassificacaoTexto, EscolhaClasseTexto, AlocacaoTexto> {

    private ClassificacaoBaseTextoPaginator(InterfaceController<ClassificacaoTexto> controller,
	    LiberacaoBaseTexto liberacao, List<EscolhaClasseTexto> listaDeClasses, List<AlocacaoTexto> listaDeAlocacao,
	    List<ClassificacaoTexto> listaDeClassificacao) {
	super(controller, liberacao, listaDeClasses, listaDeAlocacao, listaDeClassificacao);
    }

    /**
     * Inicia uma instância de Paginador dado uma liberação de texto
     * 
     * @param liberacao
     * @return
     */
    public static ClassificacaoBaseTextoPaginator getInstance(LiberacaoBaseTexto liberacao) {

	// instanciando os controladores
	InterfaceController<LiberacaoBaseTexto> liberacaoTextoController = ControllerFactory
		.getLiberacaoBaseTextoController();
	InterfaceController<AlocacaoTexto> alocacaoTextoController = ControllerFactory.getAlocacaoTextoController();
	InterfaceController<ClassificacaoTexto> classificacaoTextoController = ControllerFactory
		.getClassificacaoTextoController();

	liberacaoTextoController.prepareView(liberacaoTextoController.getItems().indexOf(liberacao));

	// preparando listas para passagem da nova instância
	alocacaoTextoController.getItemsFromCriteria(DetachedCriteriaFactory.getAlocacoesTextoPorLiberacao(liberacao));

	// criação de lista de opções de classificação
	List<EscolhaClasseTexto> opcoesDeClassificacao = new LinkedList<EscolhaClasseTexto>(
		alocacaoTextoController.getItems().get(0).getEscolhaClasseTextos());

	// criação de lista de classificação
	List<ClassificacaoTexto> listaDeClassificacao = new LinkedList<>();
	Usuario escravo = UsuarioController.currentEscravo;

	for (AlocacaoTexto alocacao : alocacaoTextoController.getItems()) {
	    List<ClassificacaoTexto> clas = classificacaoTextoController.getItemsFromCriteria(
		    DetachedCriteriaFactory.getClassificacaoTextoPorEscravoEAlocacao(escravo, alocacao));
	    listaDeClassificacao.addAll(clas);
	}
	classificacaoTextoController.getItems().clear();
	classificacaoTextoController.getItems().addAll(listaDeClassificacao);

	// nova instância
	return new ClassificacaoBaseTextoPaginator(classificacaoTextoController, liberacao, opcoesDeClassificacao,
		alocacaoTextoController.getItems(), listaDeClassificacao);
    }

    @Override
    public void preencheVazio() {
	int possivelIndiceInicial = getController().getItems().size() - 1;
	int indiceInicial = possivelIndiceInicial < 0 ? 0 : possivelIndiceInicial;

	for (int i = indiceInicial; getListaDeClassificacao().size() < getListaDeAlocacoes().size(); i++) {

	    ClassificacaoTexto classificacao = new ClassificacaoTexto();
	    classificacao.setUsuario(UsuarioController.currentEscravo);
	    classificacao.setAlocacaoTexto(getListaDeAlocacoes().get(i));

	    getListaDeClassificacao().add(classificacao);
	}

	getController().getItems().clear();
	getController().getItems().addAll(getListaDeClassificacao());
    }

    @Override
    public int buscaPrimeiraClassificacaoEfetivada() {
	int i = 0;
	while (i < getListaDeClassificacao().size()
		&& getListaDeClassificacao().get(i).getEscolhaClasseTexto() != null) {
	    i++;
	}
	return i == 0 ? 0 : i - 1;
    }

}
