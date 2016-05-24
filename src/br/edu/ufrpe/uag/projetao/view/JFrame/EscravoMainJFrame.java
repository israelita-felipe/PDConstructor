package br.edu.ufrpe.uag.projetao.view.JFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.view.GenericTable;
import br.edu.ufrpe.uag.projetao.view.listeners.NovaClassificacaoImagemClasseActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.NovaClassificacaoTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.util.GerenciadorDePopUp;

public class EscravoMainJFrame {

    private JFrame frame;
    private GenericTable<LiberacaoBaseTexto> tableBaseTexto;
    private JMenuItem mntmClassificar;
    private GenericTable<LiberacaoBaseImagemClasse> tabelaBaseImagemClasse;
    private JPopupMenu popupMenu_1;
    private JMenuItem mntmClassificar_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    EscravoMainJFrame window = new EscravoMainJFrame();
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
    public EscravoMainJFrame() {
	initialize();
	addListeners();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 710, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	JPanel panel = new JPanel();
	tabbedPane.addTab("Base de Texto", null, panel, null);
	panel.setLayout(new BorderLayout(0, 0));

	JScrollPane scrollPane = new JScrollPane();
	panel.add(scrollPane, BorderLayout.CENTER);

	tableBaseTexto = new GenericTable(ControllerFactory.getLiberacaoBaseTextoController().getItemsFromCriteria(
		DetachedCriteriaFactory.getLiberacoesBaseTextoDoEscravo(UsuarioController.currentEscravo)));
	scrollPane.setViewportView(tableBaseTexto);

	JPopupMenu popupMenu = new JPopupMenu();
	GerenciadorDePopUp.addPopup(tableBaseTexto, popupMenu);

	mntmClassificar = new JMenuItem("Classificar");
	popupMenu.add(mntmClassificar);

	JPanel panel_1 = new JPanel();
	tabbedPane.addTab("Base de Imagem", null, panel_1, null);
	panel_1.setLayout(new BorderLayout(0, 0));

	JScrollPane scrollPane_1 = new JScrollPane();
	panel_1.add(scrollPane_1);

	tabelaBaseImagemClasse = new GenericTable(
		ControllerFactory.getLiberacaoBaseImagemClasseController().getItemsFromCriteria(DetachedCriteriaFactory
			.getLiberacoesBaseImagemClasseDoEscravo(UsuarioController.currentEscravo)));
	scrollPane_1.setViewportView(tabelaBaseImagemClasse);

	popupMenu_1 = new JPopupMenu();
	GerenciadorDePopUp.addPopup(tabelaBaseImagemClasse, popupMenu_1);

	mntmClassificar_1 = new JMenuItem("Classificar");
	popupMenu_1.add(mntmClassificar_1);
    }

    private void addListeners() {
	getClassificarPopMenuItem().addActionListener(new NovaClassificacaoTextoActionListener(tableBaseTexto));
	getClassificarBaseImagemClasseMenuItem()
		.addActionListener(new NovaClassificacaoImagemClasseActionListener(tabelaBaseImagemClasse));
    }

    public JMenuItem getClassificarPopMenuItem() {
	return mntmClassificar;
    }

    public GenericTable<LiberacaoBaseImagemClasse> getTabelaImagem() {
	return tabelaBaseImagemClasse;
    }

    public JPopupMenu getBaseImagemClassePopUpMenu() {
	return popupMenu_1;
    }

    public JMenuItem getClassificarBaseImagemClasseMenuItem() {
	return mntmClassificar_1;
    }
}
