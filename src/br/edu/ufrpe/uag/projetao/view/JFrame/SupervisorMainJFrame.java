package br.edu.ufrpe.uag.projetao.view.JFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.GenericTableModel;
import br.edu.ufrpe.uag.projetao.view.listeners.EditarBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.ExcluirBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.NovaBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.util.PopupManager;

public class SupervisorMainJFrame {

    private JFrame frame;
    private JMenuItem mntmTexto;
    private JPanel panel;
    private JTable table;
    private JMenuItem mntmEditar;
    private JMenuItem mntmExcluir;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    SupervisorMainJFrame window = new SupervisorMainJFrame();
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public SupervisorMainJFrame() {
	initialize();
	addListeners();
	updateTableModel();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JMenuBar menuBar = new JMenuBar();
	frame.setJMenuBar(menuBar);

	JMenu mnBase = new JMenu("Base");
	menuBar.add(mnBase);

	JMenu mnCriar = new JMenu("Criar");
	mnBase.add(mnCriar);

	mntmTexto = new JMenuItem("Texto");
	mnCriar.add(mntmTexto);

	JMenu mnEscravo = new JMenu("Escravo");
	menuBar.add(mnEscravo);

	JMenuItem mntmCriar_1 = new JMenuItem("Criar");
	mnEscravo.add(mntmCriar_1);

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	panel = new JPanel();
	tabbedPane.addTab("Bases de Texto", null, panel, null);
	panel.setLayout(new BorderLayout(0, 0));

	JScrollPane scrollPane = new JScrollPane();
	panel.add(scrollPane, BorderLayout.CENTER);

	table = new JTable();
	scrollPane.setViewportView(table);
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	JPopupMenu popupMenu = new JPopupMenu();
	PopupManager.addPopup(table, popupMenu);

	mntmEditar = new JMenuItem("Editar");
	popupMenu.add(mntmEditar);

	mntmExcluir = new JMenuItem("Excluir");
	popupMenu.add(mntmExcluir);

	JMenuItem mntmLiberarPara = new JMenuItem("Liberar para");
	popupMenu.add(mntmLiberarPara);

    }

    private void updateTableModel() {
	table.setModel(new GenericTableModel<BaseTexto>(
		new LinkedList<>(ControllerFactory.getBaseTextoController().getItems())));
    }

    private void addListeners() {
	
	getCriarBaseTextoMenuItem().addActionListener(
		new NovaBaseTextoActionListener(getBaseTextoTable(), ControllerFactory.getBaseTextoController()));
	
	getEditarPopMenuItem().addActionListener(
		new EditarBaseTextoActionListener(getBaseTextoTable(), ControllerFactory.getBaseTextoController()));

	getExcluirPopMenuItem().addActionListener(
		new ExcluirBaseTextoActionListener(getBaseTextoTable(), ControllerFactory.getBaseTextoController()));
    }

    public JMenuItem getCriarBaseTextoMenuItem() {
	return mntmTexto;
    }

    public JPanel getBaseTextoPanel() {
	return panel;
    }

    public JMenuItem getEditarPopMenuItem() {
	return mntmEditar;
    }

    public JTable getBaseTextoTable() {
	return table;
    }

    public JMenuItem getExcluirPopMenuItem() {
	return mntmExcluir;
    }
}
