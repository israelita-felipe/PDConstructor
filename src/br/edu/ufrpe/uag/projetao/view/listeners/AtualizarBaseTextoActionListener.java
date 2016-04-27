/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.edu.ufrpe.uag.projetao.control.hibernate.FacesContextUtil;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.jdialog.SupervisorCriarEAtualizarBaseClassificacaoJDialog;

/**
 * @author israel
 *
 */
public class AtualizarBaseTextoActionListener implements ActionListener {

    private SupervisorCriarEAtualizarBaseClassificacaoJDialog jdialog;
    private InterfaceController<BaseTexto> baseTextoController;

    public AtualizarBaseTextoActionListener(SupervisorCriarEAtualizarBaseClassificacaoJDialog jdialog,
	    InterfaceController<BaseTexto> baseTextoController) {
	// TODO Auto-generated constructor stub
	this.jdialog = jdialog;
	this.baseTextoController = baseTextoController;
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

	baseTextoController.getSelected().setTitulo(jdialog.getTituloBaseTextField().getText());
	baseTextoController.getSelected().setDescricao(jdialog.getDescricaoBaseTextArea().getText());

	baseTextoController.update();

	FacesContextUtil.end();

	jdialog.dispose();
	JOptionPane.showMessageDialog(null,
		"Base " + baseTextoController.getSelected().getId() + " atualizada com sucesso");
    }

}
