package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceWindow;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.view.GenericTable;
import br.edu.ufrpe.uag.projetao.view.listeners.FecharActionListener;

public class LiberarBaseJDialog extends JDialog implements InterfaceWindow {

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    private JTextField textField_1;
    private GenericTable<Usuario> list;
    private JButton okButton;
    private JButton cancelButton;
    private JScrollPane scrollPane;

    /**
     * Create the dialog.
     */
    public LiberarBaseJDialog() {
	init();
	addListeners();
	preencheCampos();
    }

    public void init() {
	setBounds(100, 100, 509, 393);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);

	JLabel lblBaseId = new JLabel("Base ID:");

	textField = new JTextField();
	textField.setEditable(false);
	textField.setColumns(10);

	JLabel lblBaseTtulo = new JLabel("Base Título:");

	textField_1 = new JTextField();
	textField_1.setEditable(false);
	textField_1.setColumns(10);

	scrollPane = new JScrollPane();
	GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
	gl_contentPanel
		.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 414,
							Short.MAX_VALUE)
						.addComponent(lblBaseId)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBaseTtulo).addComponent(scrollPane,
							GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
					.addContainerGap()));
	gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(lblBaseId)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblBaseTtulo)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
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

    @Override
    public void addListeners() {
	// TODO Auto-generated method stub
	getCancelarButton().addActionListener(new FecharActionListener(this));
    }

    @Override
    public void preencheCampos() {
	// TODO Auto-generated method stub
	list = new GenericTable<Usuario>(ControllerFactory.getUsuarioController()
		.getItemsFromCriteria(DetachedCriteriaFactory.getTodosEscravos()));
	scrollPane.setViewportView(list);
    }

    public GenericTable<Usuario> getUsuariosList() {
	return list;
    }

    public JTextField getBaseIdTextField() {
	return textField;
    }

    public JTextField getBaseTituloTextField() {
	return textField_1;
    }

    public JButton getSalvarButton() {
	return okButton;
    }

    public JButton getCancelarButton() {
	return cancelButton;
    }

    public JScrollPane getScrollPane() {
	return scrollPane;
    }
}
