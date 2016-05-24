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
import javax.swing.SwingConstants;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.GenericTable;
import br.edu.ufrpe.uag.projetao.view.listeners.EditarBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.ExcluirBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.NovaBaseImagemClasseActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.NovaBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.NovaLiberacaoBaseImagemClasseActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.NovaLiberacaoBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.NovoUsuarioActionListener;
import br.edu.ufrpe.uag.projetao.view.util.GerenciadorDePopUp;

public class SupervisorMainJFrame {

    private JFrame frame;
    private JMenuItem mntmTexto;
    private JPanel panel;
    private GenericTable<BaseTexto> baseTextoJTable;
    private JMenuItem mntmEditar;
    private JMenuItem mntmExcluir;
    private JLabel lblUsurio;
    private JMenuItem mntmCriar;
    private JMenuItem mntmLiberarPara;
    private JMenuItem mntmClassificaoDeImagem;
    private JPanel panel_2;
    private JScrollPane scrollPane_1;
    private GenericTable<BaseImagemClasse> baseImagemClasseJTable;
    private JPopupMenu popupMenu_1;
    private JMenuItem mntmEditar_1;
    private JMenuItem mntmExcluir_1;
    private JMenuItem mntmLiberarPara_1;

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
	
	mntmClassificaoDeImagem = new JMenuItem("Classifica\u00E7\u00E3o de Imagem");
	mnCriar.add(mntmClassificaoDeImagem);

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
	panel.add(scrollPane, BorderLayout.WEST);

	JPopupMenu popupMenu = new JPopupMenu();

	mntmEditar = new JMenuItem("Editar");
	popupMenu.add(mntmEditar);

	mntmExcluir = new JMenuItem("Excluir");
	popupMenu.add(mntmExcluir);

	mntmLiberarPara = new JMenuItem("Liberar para");
	popupMenu.add(mntmLiberarPara);

	baseTextoJTable = new GenericTable<BaseTexto>(ControllerFactory.getBaseTextoController().getItemsFromCriteria(
		DetachedCriteriaFactory.getBasesTextoDoUsuario(UsuarioController.currrentSupervisor)));
	scrollPane.setViewportView(baseTextoJTable);

	GerenciadorDePopUp.addPopup(baseTextoJTable, popupMenu);
	
	panel_2 = new JPanel();
	tabbedPane.addTab("Bases de Imagens", null, panel_2, null);
	panel_2.setLayout(new BorderLayout(0, 0));
	
	scrollPane_1 = new JScrollPane();
	panel_2.add(scrollPane_1);
	
	baseImagemClasseJTable = new GenericTable<BaseImagemClasse>(ControllerFactory.getBaseImagemClasseController().getItemsFromCriteria(
			DetachedCriteriaFactory.getBasesImagemClasseDoUsuario(UsuarioController.currrentSupervisor)));
		scrollPane.setViewportView(baseTextoJTable);
	scrollPane_1.setViewportView(baseImagemClasseJTable);
	
	popupMenu_1 = new JPopupMenu();
	GerenciadorDePopUp.addPopup(baseImagemClasseJTable, popupMenu_1);
	
	mntmEditar_1 = new JMenuItem("Editar");
	popupMenu_1.add(mntmEditar_1);
	
	mntmExcluir_1 = new JMenuItem("Excluir");
	popupMenu_1.add(mntmExcluir_1);
	
	mntmLiberarPara_1 = new JMenuItem("Liberar Para");
	popupMenu_1.add(mntmLiberarPara_1);

    }

    private void addListeners() {
    	
    	//Atribuição na base de Texto

	getCriarBaseTextoMenuItem().addActionListener(new NovaBaseTextoActionListener(getBaseTextoTable()));
	
	getEditarPopMenuItem().addActionListener(new EditarBaseTextoActionListener(getBaseTextoTable()));

	getExcluirPopMenuItem().addActionListener(new ExcluirBaseTextoActionListener(getBaseTextoTable()));

	getCriarUsuarioButton().addActionListener(new NovoUsuarioActionListener());

	getLiberarBaseParaPopMenuItem()
		.addActionListener(new NovaLiberacaoBaseTextoActionListener(getBaseTextoTable()));
	
	 //Atribuição na base de imagem classe
	
	getCriarBaseClassificacaoDeImagemMenuItem().addActionListener(new NovaBaseImagemClasseActionListener(getBaseImagemClasseJTable()));
	
	getLiberarBaseImagemClasseMenuItem()
	.addActionListener(new NovaLiberacaoBaseImagemClasseActionListener(getBaseImagemClasseJTable()));
	
	

	
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
	return baseTextoJTable;
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
	public JMenuItem getCriarBaseClassificacaoDeImagemMenuItem() {
		return mntmClassificaoDeImagem;
	}
	public GenericTable getBaseImagemClasseJTable() {
		return baseImagemClasseJTable;
	}

	public JPopupMenu getPopupImagemClasse() {
		return popupMenu_1;
	}
	public JMenuItem getEditarBaseImagemClasseMenuItem() {
		return mntmEditar_1;
	}
	public JMenuItem getExcluirBaseImagemClasseMenuItem() {
		return mntmExcluir_1;
	}
	public JMenuItem getLiberarBaseImagemClasseMenuItem() {
		return mntmLiberarPara_1;
	}
}
