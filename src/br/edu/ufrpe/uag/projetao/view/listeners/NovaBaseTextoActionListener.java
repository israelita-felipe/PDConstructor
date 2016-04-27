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
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.GenericTableModel;
import br.edu.ufrpe.uag.projetao.view.jdialog.SupervisorCriarBaseClassificacaoTextoJDialog;

/**
 * @author israel
 *
 */
public class NovaBaseTextoActionListener implements ActionListener {

    private JTable table;
    private InterfaceController<BaseTexto> controller;

    /**
     * @param table 
     * 
     */
    public NovaBaseTextoActionListener(JTable table,InterfaceController<BaseTexto> controller) {
	// TODO Auto-generated constructor stub
	this.table = table;
	this.controller = controller;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	SupervisorCriarEAtualizarBaseClassificacaoJDialog novaBaseClassificacaoTexto = new SupervisorCriarBaseClassificacaoTextoJDialog();
	novaBaseClassificacaoTexto.setVisible(true);
	table.setModel(new GenericTableModel<BaseTexto>(
		new LinkedList<>(controller.getItems())));
    }

}
