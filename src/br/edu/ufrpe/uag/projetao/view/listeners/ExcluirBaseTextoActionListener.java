/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.edu.ufrpe.uag.projetao.control.hibernate.FacesContextUtil;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.GenericTableModel;
import br.edu.ufrpe.uag.projetao.view.jdialog.SupervisorEditarBaseClassificacaoTextoJDialog;

/**
 * @author israel
 *
 */
public class ExcluirBaseTextoActionListener implements ActionListener {

    private JTable table;
    private InterfaceController<BaseTexto> controller;

    /**
     * 
     */
    public ExcluirBaseTextoActionListener(JTable table, InterfaceController<BaseTexto> controller) {
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

	    if (JOptionPane.showConfirmDialog(null, "Deseja excluir a Base "
		    + controller.prepareEdit(table.getSelectedRow()).getId() + "?","Exclusão",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
		FacesContextUtil.begin();

		controller.destroy(table.getSelectedRow());
		table.setModel(new GenericTableModel<BaseTexto>(new LinkedList<>(controller.getItems())));

		FacesContextUtil.end();
	    }
	}
    }

}
