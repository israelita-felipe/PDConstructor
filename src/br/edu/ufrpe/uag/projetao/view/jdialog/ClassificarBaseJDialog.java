package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceWindow;
import br.edu.ufrpe.uag.projetao.view.listeners.FecharActionListener;

public class ClassificarBaseJDialog extends JDialog implements InterfaceWindow {

    private final JPanel contentPanel = new JPanel();
    private JButton proximoButton;
    private JButton sairButton;
    private JButton btnAnterior;
    private JPanel panel;
    private JComboBox comboBox;
    private JPanel panel_1;

    /**
     * Create the dialog.
     */
    public ClassificarBaseJDialog(Frame owner,String title, boolean modal) {
	super(owner, title, modal);
	init();
	addListeners();
	preencheCampos();
    }

    @Override
    public void init() {
	// TODO Auto-generated method stub
	setBounds(100, 100, 706, 412);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);
	contentPanel.setLayout(new BorderLayout(0, 0));
	{
	    panel = new JPanel();
	    contentPanel.add(panel, BorderLayout.NORTH);
	    {
		comboBox = new JComboBox();
	    }

	    JLabel lblClasse = new JLabel("Classe:");
	    panel.setLayout(new BorderLayout(0, 0));
	    panel.add(comboBox);
	    panel.add(lblClasse, BorderLayout.NORTH);
	}

	panel_1 = new JPanel();
	contentPanel.add(panel_1, BorderLayout.CENTER);
	panel_1.setLayout(new BorderLayout(0, 0));
	{
	    JPanel buttonPane = new JPanel();
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		sairButton = new JButton("Sair");
		sairButton.setActionCommand("Cancel");
	    }
	    {
		proximoButton = new JButton("Próximo");
		proximoButton.setActionCommand("OK");
		getRootPane().setDefaultButton(proximoButton);
	    }

	    btnAnterior = new JButton("Anterior");
	    GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
	    gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
		    .addGroup(gl_buttonPane.createSequentialGroup().addContainerGap().addComponent(sairButton)
			    .addGap(430).addComponent(btnAnterior).addPreferredGap(ComponentPlacement.RELATED)
			    .addComponent(proximoButton).addContainerGap()));
	    gl_buttonPane
		    .setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
			    gl_buttonPane.createSequentialGroup()
				    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				    .addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
					    .addComponent(sairButton).addComponent(proximoButton)
					    .addComponent(btnAnterior))
				    .addContainerGap()));
	    buttonPane.setLayout(gl_buttonPane);
	}
    }

    @Override
    public void addListeners() {
	// TODO Auto-generated method stub
	getSairButton().addActionListener(new FecharActionListener(this));
    }

    @Override
    public void preencheCampos() {
	// TODO Auto-generated method stub

    }

    public JButton getProximoButton() {
	return proximoButton;
    }

    public JButton getSairButton() {
	return sairButton;
    }

    public JButton getAnteriorButton() {
	return btnAnterior;
    }

    public JComboBox getClasseComboBox() {
	return comboBox;
    }

    public JPanel getMainPanel() {
	return panel_1;
    }
}
