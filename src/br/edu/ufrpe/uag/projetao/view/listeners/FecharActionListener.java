/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author israel
 *
 */
public class FecharActionListener implements ActionListener {

    private Window janela;

    /**
     * 
     */
    public FecharActionListener(Window janela) {
	// TODO Auto-generated constructor stub
	this.janela = janela;
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
	this.janela.dispose();
    }

}
