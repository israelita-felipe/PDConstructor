/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JTable;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.GenericTableModel;
import br.edu.ufrpe.uag.projetao.view.jdialog.EditarBaseClassificacaoTextoJDialog;

/**
 * @author israel
 *
 */
public class EditarBaseTextoActionListener implements ActionListener {

    private JTable table;

    public EditarBaseTextoActionListener(JTable table) {
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
	InterfaceController<BaseTexto> controller = ControllerFactory.getBaseTextoController();
	if (table.getSelectedRow() != -1) {
	    controller.prepareEdit(table.getSelectedRow());
	    new EditarBaseClassificacaoTextoJDialog().setVisible(true);
	    table.setModel(new GenericTableModel<BaseTexto>(new LinkedList<>(controller.getItemsFromCriteria(
		    DetachedCriteriaFactory.getBasesTextoDoUsuario(UsuarioController.currrentSupervisor)))));
	}
    }

}
