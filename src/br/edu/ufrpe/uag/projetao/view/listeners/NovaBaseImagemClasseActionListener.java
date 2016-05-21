/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.view.GenericTable;
import br.edu.ufrpe.uag.projetao.view.jdialog.CriarBaseClassificacaoImagemClasseJDialog;

/**
 * @author Juan Augusto
 *
 */
public class NovaBaseImagemClasseActionListener implements ActionListener {

	private GenericTable<BaseImagemClasse> table;

	/**
	 * @param genericTable 
	 * 
	 */
	public NovaBaseImagemClasseActionListener(GenericTable<BaseImagemClasse> table) {
		this.table = table;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		new CriarBaseClassificacaoImagemClasseJDialog().setVisible(true);
		table.clear();
		table.addAll(new LinkedList<>(
			((InterfaceController<BaseImagemClasse>) ControllerFactory.getBaseImagemClasseController()).getItemsFromCriteria(
				DetachedCriteriaFactory.getBasesImagemClasseDoUsuario(UsuarioController.currrentSupervisor))));
	}

}
