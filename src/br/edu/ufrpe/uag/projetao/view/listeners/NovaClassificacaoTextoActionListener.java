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
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.util.ClassificacaoBaseTextoPaginator;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceClassificaBase;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.view.GenericTable;
import br.edu.ufrpe.uag.projetao.view.jdialog.ClassificarBaseTextoJDialog;
import br.edu.ufrpe.uag.projetao.view.scrollPanel.ClassificarTextoJScrollPane;

/**
 * @author israel
 *
 */
public class NovaClassificacaoTextoActionListener implements ActionListener {

    private InterfaceClassificaBase<ClassificarTextoJScrollPane, EscolhaClasseTexto> classificacaoBaseTextoDialog;
    private GenericTable<LiberacaoBaseTexto> tabela;
    private AbstractPaginador<LiberacaoBaseTexto, ClassificacaoTexto, EscolhaClasseTexto, AlocacaoTexto> paginador;

    /**
     * 
     */
    public NovaClassificacaoTextoActionListener(GenericTable<LiberacaoBaseTexto> tabela) {
	// TODO Auto-generated constructor stub
	this.tabela = tabela;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {

	if (tabela.getSelectedRow() != -1) {
	    this.paginador = ClassificacaoBaseTextoPaginator.getInstance(tabela.getValueAt(tabela.getSelectedRow()));
	}

	// preenchimento da GUI
	classificacaoBaseTextoDialog = new ClassificarBaseTextoJDialog();
	addListeners();

	DefaultComboBoxModel<EscolhaClasseTexto> model = new DefaultComboBoxModel<>();
	for (EscolhaClasseTexto classe : paginador.getAlocacaoAtual().getEscolhaClasseTextos()) {
	    model.addElement(classe);
	}
	classificacaoBaseTextoDialog.getClasseComboBox().setModel(model);
	classificacaoBaseTextoDialog.getMediaComponet().getEditorPane()
		.setText(paginador.getAlocacaoAtual().getTexto());
	classificacaoBaseTextoDialog.getClasseComboBox()
		.setSelectedItem(paginador.getItemAtual().getEscolhaClasseTexto());

	// mostra descrição da base na inicialização
	JOptionPane.showMessageDialog(null, paginador.getAlocacaoAtual().getBaseTexto().getDescricao(),
		"Classificação de Bases", JOptionPane.INFORMATION_MESSAGE);

	classificacaoBaseTextoDialog.setVisible(true);

	// atualiza a tabela de liberações, exibindo somente as que estão
	// liberadas e não foram finalizadas
	atualizaTabela();

    }

    /**
     * Adiciona escutadores ao jdialog de classificação
     */
    private void addListeners() {
	classificacaoBaseTextoDialog.getProximoButton().addActionListener(
		new ClassificarBaseTextoProximoActionListener(classificacaoBaseTextoDialog, paginador));
	classificacaoBaseTextoDialog.getAnteriorButton().addActionListener(
		new ClassificarBaseTextoAnteriorActionListener(classificacaoBaseTextoDialog, paginador));
    }

    /**
     * Atualização de tabela de liberação
     */
    private void atualizaTabela() {
	tabela.clear();
	tabela.addAll(ControllerFactory.getLiberacaoBaseTextoController().getItemsFromCriteria(
		DetachedCriteriaFactory.getLiberacoesBaseTextoDoEscravo(UsuarioController.currentEscravo)));
    }

}
