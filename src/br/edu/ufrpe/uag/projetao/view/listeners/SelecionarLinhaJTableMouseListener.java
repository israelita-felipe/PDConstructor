/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

/**
 * @author israel
 *
 */
public class SelecionarLinhaJTableMouseListener extends MouseAdapter {

    private JTable tabela;

    /**
     * 
     */
    public SelecionarLinhaJTableMouseListener(JTable tabela) {
	this.tabela = tabela;
    }

    @Override
    public void mousePressed(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON3) {
	    int row = tabela.rowAtPoint(e.getPoint());
	    if (row != -1) {
		tabela.setRowSelectionInterval(row, row);
	    }
	}
    }

}
