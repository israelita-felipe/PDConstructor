/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.hibernate.FacesContextUtil;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceCriaEAtualizaBase;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.JPane.BaseTextoJPanel;

/**
 * @author israel
 *
 */
public class AtualizarBaseTextoActionListener implements ActionListener {

    private InterfaceCriaEAtualizaBase<BaseTextoJPanel> jdialog;

    public AtualizarBaseTextoActionListener(InterfaceCriaEAtualizaBase<BaseTextoJPanel> jdialog) {
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
	InterfaceController<BaseTexto> baseTextoController = ControllerFactory.getBaseTextoController();

	FacesContextUtil.begin();

	baseTextoController.getSelected().setTitulo(jdialog.getMediaComponent().getTituloTextField().getText());
	baseTextoController.getSelected().setDescricao(jdialog.getMediaComponent().getDescricaoEditorPane().getText());

	baseTextoController.update();

	FacesContextUtil.end();

	jdialog.dispose();
	JOptionPane.showMessageDialog(null,
		"Base " + baseTextoController.getSelected().getId() + " atualizada com sucesso");
    }

}
