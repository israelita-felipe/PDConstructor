package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.edu.ufrpe.uag.projetao.view.listeners.RemoveDeListActionListener;
import br.edu.ufrpe.uag.projetao.view.util.PopupManager;

public class SupervisorCriarEAtualizarBaseClassificacaoJDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    private JTextField textField_1;
    private JLabel lblTtuloDaBase;
    private JLabel lblDescrio;
    private JLabel lblClasses;
    private JLabel lblBuscarArquivosDe;
    private JButton button;
    private JButton btnAdicionar;
    private JTextArea textArea;
    private JList list;
    private JList list_1;
    private JButton okButton;
    private JButton cancelButton;
    private JPopupMenu popupMenu;
    private JPopupMenu popupMenu_1;
    private JMenuItem mntmRemover_1;
    private JMenuItem mntmRemoverArquivo;

    /**
     * Create the dialog.
     */
    public SupervisorCriarEAtualizarBaseClassificacaoJDialog(Frame owner, String title, boolean modal) {
	super(owner, title, modal);
	init();
	addListeners();
    }

    private void init() {
	setBounds(100, 100, 759, 441);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);

	JScrollPane scrollPane = new JScrollPane();

	JScrollPane scrollPane_1 = new JScrollPane();

	JScrollPane scrollPane_2 = new JScrollPane();

	lblTtuloDaBase = new JLabel("Título da base:");

	textField_1 = new JTextField();
	lblTtuloDaBase.setLabelFor(textField_1);
	textField_1.setColumns(10);
	GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
	gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
			.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
				.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
				.addComponent(lblTtuloDaBase)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 340,
						GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)))
			.addContainerGap()));
	gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblTtuloDaBase)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addGap(12)
			.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
				.addComponent(scrollPane_2).addComponent(scrollPane))
			.addContainerGap(43, Short.MAX_VALUE)));

	JPanel panel = new JPanel();
	scrollPane_2.setColumnHeaderView(panel);
	panel.setLayout(new BorderLayout(0, 0));

	lblBuscarArquivosDe = new JLabel(" Buscar arquivos");
	panel.add(lblBuscarArquivosDe);

	button = new JButton("...");
	panel.add(button, BorderLayout.EAST);

	JSeparator separator = new JSeparator();
	panel.add(separator, BorderLayout.SOUTH);

	list_1 = new JList();
	list_1.setModel(new DefaultListModel<String>());
	scrollPane_2.setViewportView(list_1);

	popupMenu = new JPopupMenu();
	PopupManager.addPopup(list_1, popupMenu);

	mntmRemoverArquivo = new JMenuItem("Remover Arquivo");
	popupMenu.add(mntmRemoverArquivo);

	textArea = new JTextArea();
	scrollPane_1.setViewportView(textArea);

	JPanel panel_2 = new JPanel();
	scrollPane_1.setColumnHeaderView(panel_2);
	panel_2.setLayout(new BorderLayout(0, 0));

	lblDescrio = new JLabel(" Descrição:");
	lblDescrio.setLabelFor(textArea);
	panel_2.add(lblDescrio);
	lblDescrio.setMaximumSize(new Dimension(74, 25));
	lblDescrio.setMinimumSize(new Dimension(74, 25));
	lblDescrio.setPreferredSize(new Dimension(74, 25));

	JSeparator separator_2 = new JSeparator();
	panel_2.add(separator_2, BorderLayout.SOUTH);

	list = new JList();
	list.setModel(new DefaultListModel<File>());
	lblBuscarArquivosDe.setLabelFor(list);
	scrollPane.setViewportView(list);

	JPanel panel_1 = new JPanel();
	scrollPane.setColumnHeaderView(panel_1);
	panel_1.setLayout(new BorderLayout(0, 0));

	lblClasses = new JLabel(" Classe:");
	lblClasses.setLabelFor(list);

	popupMenu_1 = new JPopupMenu();
	PopupManager.addPopup(list, popupMenu_1);

	mntmRemover_1 = new JMenuItem("Remover Classe");
	popupMenu_1.add(mntmRemover_1);
	panel_1.add(lblClasses, BorderLayout.WEST);
	lblClasses.setPreferredSize(new Dimension(64, 25));
	lblClasses.setMinimumSize(new Dimension(176, 25));
	lblClasses.setMaximumSize(new Dimension(176, 25));

	textField = new JTextField();
	panel_1.add(textField, BorderLayout.CENTER);
	textField.setColumns(10);

	btnAdicionar = new JButton("Adicionar");
	panel_1.add(btnAdicionar, BorderLayout.EAST);

	JSeparator separator_1 = new JSeparator();
	panel_1.add(separator_1, BorderLayout.SOUTH);
	contentPanel.setLayout(gl_contentPanel);
	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		okButton = new JButton("Salvar");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	    }
	    {
		cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	    }
	}
    }

    public JLabel getTituloBaseLabel() {
	return lblTtuloDaBase;
    }

    public JLabel getDescricaoBaseLabel() {
	return lblDescrio;
    }

    public JLabel getClasseLabel() {
	return lblClasses;
    }

    public JLabel getBuscarArquivosLabel() {
	return lblBuscarArquivosDe;
    }

    public JButton getBuscarArquivosButton() {
	return button;
    }

    public JButton getAdicionarClasseButton() {
	return btnAdicionar;
    }

    public JTextField getTituloBaseTextField() {
	return textField_1;
    }

    public JTextArea getDescricaoBaseTextArea() {
	return textArea;
    }

    public JTextField getClasseParaAdicionarTextField() {
	return textField;
    }

    public JList getClassesList() {
	return list;
    }

    public JList getArquivosList() {
	return list_1;
    }

    public JButton getCriarButton() {
	return okButton;
    }

    public JButton getCancelarButton() {
	return cancelButton;
    }

    public JMenuItem getRemoverClassePopMenuItem() {
	return mntmRemover_1;
    }

    public JMenuItem getRemoverArquivoPopMenuItem() {
	return mntmRemoverArquivo;
    }

    private void addListeners() {
	getRemoverArquivoPopMenuItem().addActionListener(new RemoveDeListActionListener(getArquivosList()));
	getRemoverClassePopMenuItem().addActionListener(new RemoveDeListActionListener(getClassesList()));
    }
}
