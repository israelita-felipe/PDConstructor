/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import br.edu.ufrpe.uag.projetao.view.listeners.CriarLiberacaoBaseImagemClasseActionListener;

/**
 * @author Juan Augusto
 *
 */
public class LiberarBaseImagemClasseJDialog  extends LiberarBaseJDialog {

	/**
	 * 
	 */
	public LiberarBaseImagemClasseJDialog() {
		// TODO Auto-generated constructor stub
	}
	
	 public void addListeners() {
			// TODO Auto-generated method stub
			super.addListeners();
			getSalvarButton().addActionListener(new CriarLiberacaoBaseImagemClasseActionListener(this));
		   }

}
