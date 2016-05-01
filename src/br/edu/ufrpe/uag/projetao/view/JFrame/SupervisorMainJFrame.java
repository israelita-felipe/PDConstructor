package br.edu.ufrpe.uag.projetao.view.JFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.GenericTable;
import br.edu.ufrpe.uag.projetao.view.listeners.EditarBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.ExcluirBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.NovaBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.NovaLiberacaoBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.NovoUsuarioActionListener;
import br.edu.ufrpe.uag.projetao.view.util.PopupManager;

public class SupervisorMainJFrame {

    private JFrame frame;
    private JMenuItem mntmTexto;
    private JPanel panel;
    private GenericTable<BaseTexto> table;
    private JMenuItem mntmEditar;
    private JMenuItem mntmExcluir;
    private JLabel lblUsurio;
    private JMenuItem mntmCriar;
    private JMenuItem mntmLiberarPara;

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

	JMenu mnEscravo = new JMenu("Usuário");
	menuBar.add(mnEscravo);

	mntmCriar = new JMenuItem("Criar");
	mnEscravo.add(mntmCriar);

	JPanel panel_1 = new JPanel();
	panel_1.setBorder(null);
	panel_1.setOpaque(false);
	menuBar.add(panel_1);
	panel_1.setLayout(new BorderLayout(0, 0));

	lblUsurio = new JLabel("Usuário: " + UsuarioController.currrentSupervisor.getNome() + " ");
	panel_1.add(lblUsurio, BorderLayout.EAST);
	lblUsurio.setHorizontalTextPosition(SwingConstants.CENTER);
	lblUsurio.setHorizontalAlignment(SwingConstants.CENTER);

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	panel = new JPanel();
	tabbedPane.addTab("Bases de Texto", null, panel, null);
	panel.setLayout(new BorderLayout(0, 0));

	JScrollPane scrollPane = new JScrollPane();
	panel.add(scrollPane, BorderLayout.CENTER);

	JPopupMenu popupMenu = new JPopupMenu();

	mntmEditar = new JMenuItem("Editar");
	popupMenu.add(mntmEditar);

	mntmExcluir = new JMenuItem("Excluir");
	popupMenu.add(mntmExcluir);

	mntmLiberarPara = new JMenuItem("Liberar para");
	popupMenu.add(mntmLiberarPara);

	table = new GenericTable<BaseTexto>(ControllerFactory.getBaseTextoController().getItemsFromCriteria(
		DetachedCriteriaFactory.getBasesTextoDoUsuario(UsuarioController.currrentSupervisor)));
	scrollPane.setViewportView(table);

	PopupManager.addPopup(table, popupMenu);

    }

    private void addListeners() {

	getCriarBaseTextoMenuItem().addActionListener(new NovaBaseTextoActionListener(getBaseTextoTable()));

	getEditarPopMenuItem().addActionListener(new EditarBaseTextoActionListener(getBaseTextoTable()));

	getExcluirPopMenuItem().addActionListener(new ExcluirBaseTextoActionListener(getBaseTextoTable()));

	getCriarUsuarioButton().addActionListener(new NovoUsuarioActionListener());

	getLiberarBaseParaPopMenuItem()
		.addActionListener(new NovaLiberacaoBaseTextoActionListener(getBaseTextoTable()));
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

    public GenericTable getBaseTextoTable() {
	return table;
    }

    public JMenuItem getExcluirPopMenuItem() {
	return mntmExcluir;
    }

    public JLabel getUsuarioLabel() {
	return lblUsurio;
    }

    public JMenuItem getCriarUsuarioButton() {
	return mntmCriar;
    }

    public JMenuItem getLiberarBaseParaPopMenuItem() {
	return mntmLiberarPara;
    }
}
