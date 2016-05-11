/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.scrollPanel;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import br.edu.ufrpe.uag.projetao.view.listeners.RemoveDeListActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.SelecionaLinhaJListMouseListener;
import br.edu.ufrpe.uag.projetao.view.util.GerenciadorDePopUp;
import javax.swing.JSeparator;

/**
 * @author israel
 *
 */
public class ListaArquivosJScrollPane extends JScrollPane {
    private JList<File> list;
    private JButton button;
    private JMenuItem mntmRemover;
    private JSeparator separator;

    /**
     * 
     */
    public ListaArquivosJScrollPane() {
	init();
	addListeners();
    }

    private void init() {
	list = new JList<File>();
	setViewportView(list);
	list.setModel(new DefaultListModel<>());

	JPopupMenu popupMenu = new JPopupMenu();
	GerenciadorDePopUp.addPopup(list, popupMenu);

	mntmRemover = new JMenuItem("Remover");
	popupMenu.add(mntmRemover);

	JPanel panel = new JPanel();
	setColumnHeaderView(panel);
	panel.setLayout(new BorderLayout(0, 0));

	button = new JButton("...");
	panel.add(button, BorderLayout.EAST);

	JLabel lblSelecionarArquivos = new JLabel(" Selecionar arquivo:");
	panel.add(lblSelecionarArquivos, BorderLayout.WEST);

	separator = new JSeparator();
	panel.add(separator, BorderLayout.SOUTH);
    }

    private void addListeners() {
	getArquivosList().addMouseListener(new SelecionaLinhaJListMouseListener(getArquivosList()));
	getRemoverPopMenuItem().addActionListener(new RemoveDeListActionListener(getArquivosList()));
    }

    public JList<File> getArquivosList() {
	return list;
    }

    public JButton getBuscarArquivoButton() {
	return button;
    }

    public JMenuItem getRemoverPopMenuItem() {
	return mntmRemover;
    }
}
