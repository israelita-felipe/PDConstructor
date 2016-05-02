/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import br.edu.ufrpe.uag.projetao.view.listeners.CriarLiberacaoBaseTextoActionListener;

/**
 * @author israel
 *
 */
public class LiberarBaseTextoJDialog extends LiberarBaseJDialog {

    /**
     * 
     */
    public LiberarBaseTextoJDialog() {
	// TODO Auto-generated constructor stub
    }

    @Override
    public void addListeners() {
	// TODO Auto-generated method stub
	super.addListeners();
	getSalvarButton().addActionListener(new CriarLiberacaoBaseTextoActionListener(this));
    }
}
