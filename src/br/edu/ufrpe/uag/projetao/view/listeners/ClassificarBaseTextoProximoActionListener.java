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
import br.edu.ufrpe.uag.projetao.control.hibernate.FacesContextUtil;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.enumerate.StatusDeLiberacao;
import br.edu.ufrpe.uag.projetao.view.jdialog.ClassificarBaseTextoJDialog;

/**
 * @author israel
 *
 */
public class ClassificarBaseTextoProximoActionListener implements ActionListener {

    private ClassificarBaseTextoJDialog classificacaoBaseTextoDialog;

    /**
     * 
     */
    public ClassificarBaseTextoProximoActionListener(ClassificarBaseTextoJDialog classificacaoBaseTextoDialog) {
	// TODO Auto-generated constructor stub
	this.classificacaoBaseTextoDialog = classificacaoBaseTextoDialog;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {

	InterfaceController<ClassificacaoTexto> classificacaoDoEscravo = ControllerFactory
		.getClassificacaoTextoController();
	InterfaceController<AlocacaoTexto> alocacaoTextoController = ControllerFactory.getAlocacaoTextoController();
	InterfaceController<EscolhaClasseTexto> escolhaClasseTextoController = ControllerFactory
		.getEscolhaClasseTextoController();

	FacesContextUtil.begin();

	classificacaoDoEscravo.prepareCreate();
	classificacaoDoEscravo.getSelected().setAlocacaoTexto(alocacaoTextoController.getSelected());
	classificacaoDoEscravo.getSelected().setEscolhaClasseTexto(
		(EscolhaClasseTexto) this.classificacaoBaseTextoDialog.getClasseComboBox().getSelectedItem());
	classificacaoDoEscravo.getSelected().setUsuario(UsuarioController.currentEscravo);
	classificacaoDoEscravo.create();

	FacesContextUtil.end();

	// selecionando próximo item da list para classificar

	int proximoIndice = alocacaoTextoController.getItems().indexOf(alocacaoTextoController.getSelected()) + 1;

	if (proximoIndice < alocacaoTextoController.getItems().size()) {
	    alocacaoTextoController.prepareView(proximoIndice);

	    // preenchendo as classe
	    DefaultComboBoxModel<EscolhaClasseTexto> model = new DefaultComboBoxModel<>();
	    for (EscolhaClasseTexto classe : escolhaClasseTextoController.getItemsFromCriteria(
		    DetachedCriteriaFactory.getClassesTextoPorAlocacao(alocacaoTextoController.getSelected()))) {
		model.addElement(classe);
	    }

	    // preenchendo os campos
	    this.classificacaoBaseTextoDialog.getClasseComboBox().setModel(model);
	    this.classificacaoBaseTextoDialog.getClassificaTextoScrollPane().getEditorPane()
		    .setText(alocacaoTextoController.getSelected().getTexto());
	} else {
	    int aprovacao = JOptionPane.showConfirmDialog(null, "Parabéns, tudo finalizado, deseja encerrar a base?",
		    "Finalizado", JOptionPane.YES_NO_OPTION);
	    if (aprovacao == JOptionPane.YES_OPTION) {

		InterfaceController<LiberacaoBaseTexto> baseTextoController = ControllerFactory
			.getLiberacaoBaseTextoController();
		FacesContextUtil.begin();

		baseTextoController
			.prepareEdit(baseTextoController.getItems().indexOf(baseTextoController.getSelected()));
		baseTextoController.getSelected().setStatus(StatusDeLiberacao.BLOQUEADO);
		baseTextoController.update();

		FacesContextUtil.end();

		this.classificacaoBaseTextoDialog.dispose();
	    }
	}
    }

}
