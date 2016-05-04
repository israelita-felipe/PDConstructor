/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractPaginador;
import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceClassificaBase;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.enumerate.StatusDeLiberacao;
import br.edu.ufrpe.uag.projetao.view.scrollPanel.ClassificarTextoJScrollPane;

/**
 * @author israel
 *
 */
public class ClassificarBaseTextoAnteriorActionListener implements ActionListener {

    private InterfaceController<LiberacaoBaseTexto> liberacaoBaseTextoController;

    private InterfaceClassificaBase<ClassificarTextoJScrollPane, EscolhaClasseTexto> classificacaoBaseTextoDialog;
    private AbstractPaginador<LiberacaoBaseTexto, ClassificacaoTexto, EscolhaClasseTexto, AlocacaoTexto> paginador;

    /**
     * 
     */
    public ClassificarBaseTextoAnteriorActionListener(
	    InterfaceClassificaBase<ClassificarTextoJScrollPane, EscolhaClasseTexto> classificacaoBaseTextoDialog,
	    AbstractPaginador<LiberacaoBaseTexto, ClassificacaoTexto, EscolhaClasseTexto, AlocacaoTexto> paginador) {
	this.classificacaoBaseTextoDialog = classificacaoBaseTextoDialog;
	this.paginador = paginador;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
	if (paginador.getItemAtual().getEscolhaClasseTexto() == null) {
	    paginador.setInserindo();
	} else {
	    paginador.setEditando();
	}
	paginador.getItemAtual().setEscolhaClasseTexto(
		(EscolhaClasseTexto) classificacaoBaseTextoDialog.getClasseComboBox().getSelectedItem());
	paginador.gravaAlteracoes();

	selecionaAnteriorItem();
    }

    /**
     * Seleciona o elemento anterior a ser manipulado
     */
    private void selecionaAnteriorItem() {

	try {
	    paginador.anterior();
	    // preenchendo as classe
	    DefaultComboBoxModel<EscolhaClasseTexto> model = new DefaultComboBoxModel<>();
	    for (EscolhaClasseTexto classe : paginador.getAlocacaoAtual().getEscolhaClasseTextos()) {
		model.addElement(classe);
	    }

	    classificacaoBaseTextoDialog.getClasseComboBox()
		    .setSelectedItem(paginador.getItemAtual().getEscolhaClasseTexto());
	    classificacaoBaseTextoDialog.getMediaComponet().getEditorPane()
		    .setText(paginador.getAlocacaoAtual().getTexto());

	} catch (IllegalArgumentException ex) {
	    aprovaFechamentoDaBase();
	}

    }

    /**
     * Verifica se o usuário deseja fechar a base que está trabalhando.
     */
    private void aprovaFechamentoDaBase() {
	int aprovacao = JOptionPane.showConfirmDialog(null, "Parabéns, tudo finalizado, deseja encerrar a base?",
		"Finalizado", JOptionPane.YES_NO_OPTION);
	if (aprovacao == JOptionPane.YES_OPTION) {

	    // iniciar o fechamento da base
	    liberacaoBaseTextoController = ControllerFactory.getLiberacaoBaseTextoController();

	    TransactionManager.begin();

	    liberacaoBaseTextoController
		    .prepareEdit(liberacaoBaseTextoController.getItems().indexOf(paginador.getLiberacao()));
	    liberacaoBaseTextoController.getSelected().setStatus(StatusDeLiberacao.BLOQUEADO);
	    liberacaoBaseTextoController.update();

	    TransactionManager.end();

	    this.classificacaoBaseTextoDialog.dispose();
	}
    }
}
