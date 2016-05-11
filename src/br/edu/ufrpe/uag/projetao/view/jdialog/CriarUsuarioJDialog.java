/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import br.edu.ufrpe.uag.projetao.view.listeners.CriarUsuarioActionListener;

/**
 * @author israel
 *
 */
public class CriarUsuarioJDialog extends CriarEditarUsuarioJDialog {

    public CriarUsuarioJDialog() {
	super();
    }

    @Override
    public void addListeners() {
	super.addListeners();
	getSalvarButton().addActionListener(new CriarUsuarioActionListener(this));
    }
}
