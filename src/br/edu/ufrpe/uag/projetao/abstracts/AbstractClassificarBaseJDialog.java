package br.edu.ufrpe.uag.projetao.abstracts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceClassificaBase;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;
import br.edu.ufrpe.uag.projetao.view.listeners.FecharActionListener;

/**
 * Classe genérica para classificação de bases
 * 
 * @author israel
 *
 * @param <C>
 *            tipo de componente da mídia a ser analizada
 * @param <T>
 *            tipo de dado da escolha das classes do modelo de banco de dados
 */
public abstract class AbstractClassificarBaseJDialog<C extends Component, T extends InterfaceEntity> extends JDialog
	implements InterfaceClassificaBase<C, T> {

    private final JPanel contentPanel = new JPanel();
    private JButton proximoButton;
    private JButton sairButton;
    private JButton btnAnterior;
    private JPanel panel;
    private JComboBox<T> comboBox;
    private JPanel panel_1;
    private JPanel separator;

    /**
     * Create the dialog.
     */
    public AbstractClassificarBaseJDialog(Frame owner, String title, boolean modal) {
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
		comboBox = new JComboBox<T>();
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

    @Override
    public JButton getProximoButton() {
	return proximoButton;
    }

    @Override
    public JButton getSairButton() {
	return sairButton;
    }

    @Override
    public JButton getAnteriorButton() {
	return btnAnterior;
    }

    @Override
    public JComboBox<T> getClasseComboBox() {
	return comboBox;
    }

    public JPanel getMainPanel() {
	return panel_1;
    }
}
