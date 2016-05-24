/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.scrollPanel;

import java.awt.Component;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * @author Juan Augusto
 *
 */
public class ClassificarImagemClasseJScrollPane extends JScrollPane {
	private JLabel label;
	
	/**
	 * 
	 */
	public ClassificarImagemClasseJScrollPane(BufferedImage imagem) {
		super();
		init();
		label = new JLabel(new ImageIcon(imagem));
		
	}
	
	private void init(){
		JLabel lblImagem = new JLabel("Imagem:");
		setColumnHeaderView(lblImagem);
		
		setViewportView(label);		
	}
	/**
	 * @param arg0
	 * @param arg1
	 */
	public ClassificarImagemClasseJScrollPane(int arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public ClassificarImagemClasseJScrollPane(Component arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	public JLabel getImagemLabel() {
		return label;
	}

}
