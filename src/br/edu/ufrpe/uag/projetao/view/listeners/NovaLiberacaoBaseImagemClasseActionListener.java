/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.view.jdialog.LiberarBaseImagemClasseJDialog;

/**
 * @author Juan Augusto
 *
 */
public class NovaLiberacaoBaseImagemClasseActionListener implements ActionListener {
	private JTable tabela;

    public NovaLiberacaoBaseImagemClasseActionListener(JTable tabela) {
	// TODO Auto-generated constructor stub
	this.tabela = tabela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if (tabela.getSelectedRow() != -1) {
	    ControllerFactory.getBaseImagemClasseController().prepareView(tabela.getSelectedRow());
	    new LiberarBaseImagemClasseJDialog().setVisible(true);
	}
    }

}
