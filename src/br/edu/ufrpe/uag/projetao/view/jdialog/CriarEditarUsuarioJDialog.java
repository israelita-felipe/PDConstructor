package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceWindow;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.view.listeners.FecharActionListener;

public class CriarEditarUsuarioJDialog extends JDialog implements InterfaceWindow {

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    private JTextField textField_1;

    private JComboBox<Perfil> comboBox;
    private JButton okButton;
    private JButton cancelButton;

    /**
     * Create the dialog.
     */
    public CriarEditarUsuarioJDialog() {
	init();
	preencheCampos();
	addListeners();
    }

    @Override
    public void preencheCampos() {

	InterfaceController<Perfil> controller = ControllerFactory.getPerfilController();
	DefaultComboBoxModel<Perfil> model = new DefaultComboBoxModel<>();

	for (Perfil perfil : controller.prepareList()) {
	    model.addElement(perfil);
	}
	comboBox.setModel(model);
	comboBox.setEditable(true);
    }

    @Override
    public void addListeners() {
	getCancelarButton().addActionListener(new FecharActionListener(this));
    }

    @Override
    public void init() {
	setTitle("Criar Usuário");
	setModal(true);
	setResizable(false);
	setBounds(100, 100, 450, 244);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);

	JLabel lblNome = new JLabel("Nome:");

	textField = new JTextField();
	textField.setColumns(10);

	JLabel lblEmail = new JLabel("Email:");

	textField_1 = new JTextField();
	textField_1.setColumns(10);

	JLabel lblPerfil = new JLabel("Perfil:");

	comboBox = new JComboBox<Perfil>();

	GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
	gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
			.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(comboBox, 0, 414, Short.MAX_VALUE)
				.addComponent(textField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 414,
					Short.MAX_VALUE)
				.addComponent(lblEmail)
				.addComponent(textField, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
				.addComponent(lblNome).addComponent(lblPerfil))
			.addContainerGap()));
	gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
		Alignment.TRAILING,
		gl_contentPanel.createSequentialGroup().addContainerGap(18, Short.MAX_VALUE).addComponent(lblPerfil)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNome)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblEmail)
			.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField_1,
				GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			.addContainerGap()));
	contentPanel.setLayout(gl_contentPanel);
	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	    }
	    {
		okButton = new JButton("Salvar");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	    }
	}
    }

    public JComboBox<Perfil> getPerfilComboBox() {
	return comboBox;
    }

    public JButton getSalvarButton() {
	return okButton;
    }

    public JButton getCancelarButton() {
	return cancelButton;
    }

    public JTextField getUsuarioNomeTextField() {
	return textField;
    }

    public JTextField getUsuarioEmailTextField() {
	return textField_1;
    }
}
