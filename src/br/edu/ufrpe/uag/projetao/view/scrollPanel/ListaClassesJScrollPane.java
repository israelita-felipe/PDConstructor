/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.scrollPanel;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.edu.ufrpe.uag.projetao.view.listeners.AdicionaClasseActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.RemoveDeListActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.SelecionaLinhaJListMouseListener;
import br.edu.ufrpe.uag.projetao.view.util.GerenciadorDePopUp;

/**
 * @author israel
 *
 */
public class ListaClassesJScrollPane extends JScrollPane {
    private JTextField textField;
    private JButton btnAdiconar;
    private JList<String> list;
    private JPopupMenu popupMenu;
    private JMenuItem mntmRemover;
    private JSeparator separator;

    /**
     * 
     */
    public ListaClassesJScrollPane() {
	init();
	addListeners();
    }

    private void init() {
	list = new JList<String>();
	setViewportView(list);
	list.setModel(new DefaultListModel<>());

	popupMenu = new JPopupMenu();
	GerenciadorDePopUp.addPopup(list, popupMenu);

	mntmRemover = new JMenuItem("Remover");
	popupMenu.add(mntmRemover);

	JPanel panel = new JPanel();
	setColumnHeaderView(panel);
	panel.setLayout(new BorderLayout(0, 0));

	JLabel lblClasse = new JLabel(" Classe: ");
	panel.add(lblClasse, BorderLayout.WEST);

	textField = new JTextField();
	panel.add(textField, BorderLayout.CENTER);
	textField.setColumns(10);

	btnAdiconar = new JButton("Adiconar");
	panel.add(btnAdiconar, BorderLayout.EAST);

	separator = new JSeparator();
	panel.add(separator, BorderLayout.SOUTH);
    }

    private void addListeners() {
	getClassesList().addMouseListener(new SelecionaLinhaJListMouseListener(getClassesList()));
	getAdiconarButton().addActionListener(new AdicionaClasseActionListener(getClasseTextField(), getClassesList()));
	getRemoverPopMenuItem().addActionListener(new RemoveDeListActionListener(getClassesList()));
    }

    public JTextField getClasseTextField() {
	return textField;
    }

    public JButton getAdiconarButton() {
	return btnAdiconar;
    }

    public JList<String> getClassesList() {
	return list;
    }

    public JMenuItem getRemoverPopMenuItem() {
	return mntmRemover;
    }
}
