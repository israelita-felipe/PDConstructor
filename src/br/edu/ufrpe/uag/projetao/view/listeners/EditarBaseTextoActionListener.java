/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JTable;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.GenericTableModel;
import br.edu.ufrpe.uag.projetao.view.jdialog.SupervisorEditarBaseClassificacaoTextoJDialog;

/**
 * @author israel
 *
 */
public class EditarBaseTextoActionListener implements ActionListener {

    private JTable table;
    private InterfaceController<BaseTexto> controller;

    public EditarBaseTextoActionListener(JTable table, InterfaceController<BaseTexto> controller) {
	// TODO Auto-generated constructor stub
	this.table = table;
	this.controller = controller;
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
	if (table.getSelectedRow() != -1) {
	    controller.prepareEdit(table.getSelectedRow());
	    new SupervisorEditarBaseClassificacaoTextoJDialog(controller).setVisible(true);
	    table.setModel(new GenericTableModel<BaseTexto>(new LinkedList<>(controller.getItems())));
	}
    }

}
