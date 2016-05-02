package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
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
    private JPanel separator;

    /**
     * Create the dialog.
     */
    public ClassificarBaseJDialog(Frame owner, String title, boolean modal) {
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

	    separator = new JPanel();
	    panel.add(separator, BorderLayout.SOUTH);
	}

	panel_1 = new JPanel();
	contentPanel.add(panel_1, BorderLayout.CENTER);
	panel_1.setLayout(new BorderLayout(0, 0));
	{
	    JToolBar buttonPane = new JToolBar();
	    buttonPane.setFloatable(false);
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
	    buttonPane.setLayout(new GridLayout(0, 3, 0, 0));
	    buttonPane.add(sairButton);
	    buttonPane.add(btnAnterior);
	    buttonPane.add(proximoButton);
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
