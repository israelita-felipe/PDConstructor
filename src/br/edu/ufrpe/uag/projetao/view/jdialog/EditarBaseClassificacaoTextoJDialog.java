/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractCriaEAtualizaBase;
import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.JPane.BaseTextoJPanel;
import br.edu.ufrpe.uag.projetao.view.listeners.AtualizarBaseTextoActionListener;

/**
 * @author israel
 *
 */
public class EditarBaseClassificacaoTextoJDialog extends AbstractCriaEAtualizaBase<BaseTextoJPanel> {

    /**
     * 
     */
    public EditarBaseClassificacaoTextoJDialog() {
	// TODO Auto-generated constructor stub
	super(null, "Editar Base de Texto", true);
    }

    @Override
    public void init() {
	// TODO Auto-generated method stub
	super.init();
	BaseTextoJPanel baseTextoPanel = new BaseTextoJPanel();
	getContentPane().add(baseTextoPanel, BorderLayout.CENTER);
	setMediaComponent(baseTextoPanel);
    }

    @Override
    public void addListeners() {
	super.addListeners();
	getSalvarButton().addActionListener(new AtualizarBaseTextoActionListener(this));
    }

    @Override
    public void preencheCampos() {

	InterfaceController<BaseTexto> baseTextoController = ControllerFactory.getBaseTextoController();

	getMediaComponent().getTituloTextField().setText(baseTextoController.getSelected().getTitulo());
	getMediaComponent().getDescricaoEditorPane().setText(baseTextoController.getSelected().getDescricao());

	getMediaComponent().getListaClasses().getClasseTextField().setEnabled(false);
	getMediaComponent().getListaClasses().getAdiconarButton().setEnabled(false);
	getMediaComponent().getListaClasses().getClassesList().setEnabled(false);

	getMediaComponent().getListaArquivos().getBuscarArquivoButton().setEnabled(false);
	getMediaComponent().getListaArquivos().getArquivosList().setEnabled(false);
    }

    
}
