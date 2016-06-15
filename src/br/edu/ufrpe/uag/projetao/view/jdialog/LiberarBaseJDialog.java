package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceWindow;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.view.GenericTable;
import br.edu.ufrpe.uag.projetao.view.listeners.FecharActionListener;

public class LiberarBaseJDialog extends JDialog implements InterfaceWindow {

    private final JPanel contentPanel = new JPanel();
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

	scrollPane = new JScrollPane();
	GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
	gl_contentPanel
		.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
				.addContainerGap()));
	gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
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
