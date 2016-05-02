/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.hibernate.FacesContextUtil;
import br.edu.ufrpe.uag.projetao.control.util.FileManager;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.view.jdialog.SupervisorCriarEAtualizarBaseClassificacaoJDialog;

/**
 * @author israel
 *
 */
public class CriarBaseTextoActionListener implements ActionListener {

    private SupervisorCriarEAtualizarBaseClassificacaoJDialog jdialog;

    /**
     * 
     */
    public CriarBaseTextoActionListener(SupervisorCriarEAtualizarBaseClassificacaoJDialog jdialog) {
	// TODO Auto-generated constructor stub
	this.jdialog = jdialog;
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

	FacesContextUtil.begin();

	InterfaceController<BaseTexto> base = ControllerFactory.getBaseTextoController();
	InterfaceController<EscolhaClasseTexto> escolhaClasseTexto = ControllerFactory
		.getEscolhaClasseTextoController();
	InterfaceController<AlocacaoTexto> alocacaoTexto = ControllerFactory.getAlocacaoTextoController();

	// criação da base
	base.prepareCreate();
	base.getSelected().setTitulo(jdialog.getTituloBaseTextField().getText());
	base.getSelected().setDescricao(jdialog.getDescricaoBaseTextArea().getText());
	base.getSelected().setUsuario(UsuarioController.currrentSupervisor);
	base.create();

	// criação dos textos
	for (int i = 0; i < jdialog.getArquivosList().getModel().getSize(); i++) {
	    try {

		// aloca um texto para uma base
		alocacaoTexto.prepareCreate();
		alocacaoTexto.getSelected().setBaseTexto(base.getSelected());
		alocacaoTexto.getSelected().setUsuario(UsuarioController.currrentSupervisor);
		alocacaoTexto.getSelected().setTexto(
			FileManager.lerArquivo(jdialog.getArquivosList().getModel().getElementAt(i).toString()));
		alocacaoTexto.create();

		// criação das classes
		for (int j = 0; j < jdialog.getClassesList().getModel().getSize(); j++) {

		    // aloca uma classe para uma alocacao de texto
		    escolhaClasseTexto.prepareCreate();
		    escolhaClasseTexto.getSelected().setAlocacaoTexto(alocacaoTexto.getSelected());
		    escolhaClasseTexto.getSelected()
			    .setDescricao(jdialog.getClassesList().getModel().getElementAt(j).toString());
		    escolhaClasseTexto.create();
		}

	    } catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null,
			"Erro ao ler arquivo:\n" + jdialog.getArquivosList().getModel().getElementAt(i).toString());
	    }
	}

	FacesContextUtil.end();
	jdialog.dispose();
	JOptionPane.showMessageDialog(null, "Base " + base.getSelected().getId() + " criada com sucesso");
    }

}
