/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JTable;

import br.edu.ufrpe.uag.projetao.view.jdialog.SupervisorCriarEAtualizarBaseClassificacaoJDialog;
import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.GenericTable;
import br.edu.ufrpe.uag.projetao.view.GenericTableModel;
import br.edu.ufrpe.uag.projetao.view.jdialog.SupervisorCriarBaseClassificacaoTextoJDialog;

/**
 * @author israel
 *
 */
public class NovaBaseTextoActionListener implements ActionListener {

    private GenericTable table;

    /**
     * @param table
     * 
     */
    public NovaBaseTextoActionListener(GenericTable table) {
	// TODO Auto-generated constructor stub
	this.table = table;
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
	new SupervisorCriarBaseClassificacaoTextoJDialog().setVisible(true);
	table.clear();
	table.addAll(new LinkedList<>(
		((InterfaceController<BaseTexto>) ControllerFactory.getBaseTextoController()).getItemsFromCriteria(
			DetachedCriteriaFactory.getBasesTextoDoUsuario(UsuarioController.currrentSupervisor))));
	// table.setModel(new GenericTableModel<BaseTexto>()));
    }

}
