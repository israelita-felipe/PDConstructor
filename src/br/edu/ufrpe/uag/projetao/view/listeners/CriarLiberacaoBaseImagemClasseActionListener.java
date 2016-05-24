/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.hibernate.exception.ConstraintViolationException;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.enumerate.StatusDeLiberacao;
import br.edu.ufrpe.uag.projetao.view.jdialog.LiberarBaseJDialog;

/**
 * @author Juan Augusto
 *
 */
public class CriarLiberacaoBaseImagemClasseActionListener implements ActionListener {

	private LiberarBaseJDialog janela;

    /**
     * 
     */
    public CriarLiberacaoBaseImagemClasseActionListener(LiberarBaseJDialog janela) {
	// TODO Auto-generated constructor stub
	this.janela = janela;
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
	if (janela.getUsuariosList().getSelectedRow() != -1) {
	    try {
		TransactionManager.begin();

		InterfaceController<LiberacaoBaseImagemClasse> liberacaoController = ControllerFactory
			.getLiberacaoBaseImagemClasseController();

		liberacaoController.prepareCreate();

		// preenchendo os campos
		liberacaoController.getSelected()
			.setBaseImagemClasse((BaseImagemClasse) ControllerFactory.getBaseImagemClasseController().getSelected());
		liberacaoController.getSelected().setStatus(StatusDeLiberacao.LIBERADO);
		liberacaoController.getSelected().setUsuarioByEscravo(
			janela.getUsuariosList().getValueAt(janela.getUsuariosList().getSelectedRow()));
		liberacaoController.getSelected().setUsuarioBySupervisor(UsuarioController.currrentSupervisor);

		liberacaoController.create();
		TransactionManager.end();

		// fechando
		janela.dispose();
		JOptionPane.showMessageDialog(null,
			"Base liberada para " + liberacaoController.getSelected().getUsuarioByEscravo().getNome(),
			"Liberação [" + liberacaoController.getSelected() + "]", JOptionPane.INFORMATION_MESSAGE);
	    } catch (ConstraintViolationException ex) {
		// violação de chave estrangeira, informando que a liberação já
		// foi feita.
		JOptionPane.showMessageDialog(null, "A base já foi liberada para este usuário");
	    }
	}
    }

}
