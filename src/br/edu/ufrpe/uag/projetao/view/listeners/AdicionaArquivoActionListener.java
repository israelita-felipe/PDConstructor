/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;

import br.edu.ufrpe.uag.projetao.view.filter.TextoFileFilter;

/**
 * @author israel
 *
 */
public class AdicionaArquivoActionListener implements ActionListener {

    private JFileChooser chooser;
    private JList<File> listaDeArquivos;

    /**
     * 
     */
    public AdicionaArquivoActionListener(JList<File> listaDeArquivos) {
	// TODO Auto-generated constructor stub
	this.listaDeArquivos = listaDeArquivos;
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
	this.chooser = new JFileChooser();
	this.chooser.setMultiSelectionEnabled(true);
	this.chooser.setAcceptAllFileFilterUsed(false);
	this.chooser.addChoosableFileFilter(new TextoFileFilter());

	this.chooser.showOpenDialog(null);
	for (File f : this.chooser.getSelectedFiles()) {
	    ((DefaultListModel<File>) this.listaDeArquivos.getModel()).addElement(f);
	}

    }

}
