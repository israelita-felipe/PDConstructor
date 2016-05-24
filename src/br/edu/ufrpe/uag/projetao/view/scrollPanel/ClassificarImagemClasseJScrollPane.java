/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.scrollPanel;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * @author Juan Augusto
 *
 */
public class ClassificarImagemClasseJScrollPane extends JScrollPane {
    private JLabel label;
    private BufferedImage imagem;

    /**
     * 
     */
    public ClassificarImagemClasseJScrollPane(BufferedImage imagem) {
	super();
	this.imagem = imagem;
	init();
    }

    private void init() {
	JLabel lblImagem = new JLabel("Imagem:");
	setColumnHeaderView(lblImagem);
	label = new JLabel();
	if (this.imagem != null) {
	    label.setIcon(new ImageIcon(imagem));
	}
	setViewportView(label);
    }

    public JLabel getImagemLabel() {
	return label;
    }

    /**
     * @return the imagem
     */
    public BufferedImage getImagem() {
	return imagem;
    }

    /**
     * @param imagem
     *            the imagem to set
     */
    public void setImagem(BufferedImage imagem) {
	this.imagem = imagem;
    }

}
