/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceClassificaBase;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
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

    private GenericTable<LiberacaoBaseTexto> tabela;

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
	// TODO Auto-generated method stub
	if (tabela.getSelectedRow() != -1) {

	    // seleciona a liberacao indicada na tabela
	    InterfaceController<LiberacaoBaseTexto> liberacaoTextoController = ControllerFactory
		    .getLiberacaoBaseTextoController();
	    liberacaoTextoController.prepareView(tabela.getSelectedRow());

	    // seleciona a alocacao de texto para a liberação selecionada da
	    // tabela
	    InterfaceController<AlocacaoTexto> alocacaoTextoController = ControllerFactory.getAlocacaoTextoController();
	    alocacaoTextoController.getItemsFromCriteria(
		    DetachedCriteriaFactory.getAlocacoesTextoPorLiberacao(liberacaoTextoController.getSelected()));
	    alocacaoTextoController.prepareView(0);

	    InterfaceClassificaBase<ClassificarTextoJScrollPane, EscolhaClasseTexto> classificacaoBaseTextoDialog = new ClassificarBaseTextoJDialog();
	    DefaultComboBoxModel<EscolhaClasseTexto> model = new DefaultComboBoxModel<>();
	    for (EscolhaClasseTexto classe : alocacaoTextoController.getSelected().getEscolhaClasseTextos()) {
		model.addElement(classe);
	    }
	    classificacaoBaseTextoDialog.getClasseComboBox().setModel(model);

	    classificacaoBaseTextoDialog.getMediaComponet().getEditorPane()
		    .setText(alocacaoTextoController.getSelected().getTexto());

	    JOptionPane.showMessageDialog(null, liberacaoTextoController.getSelected().getBaseTexto().getDescricao(),
		    "Classificação de Bases", JOptionPane.INFORMATION_MESSAGE);

	    classificacaoBaseTextoDialog.setVisible(true);

	    // preenchendo atualizações
	    tabela.clear();
	    tabela.addAll(ControllerFactory.getLiberacaoBaseTextoController().getItemsFromCriteria(
		    DetachedCriteriaFactory.getLiberacoesBaseTextoDoEscravo(UsuarioController.currentEscravo)));

	}
    }

}
