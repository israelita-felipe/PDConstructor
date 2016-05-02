/**
 * 
 */
package br.edu.ufrpe.uag.projetao.abstracts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceCriaEAtualizaBase;
import br.edu.ufrpe.uag.projetao.view.JPane.BaseTextoJPanel;
import br.edu.ufrpe.uag.projetao.view.listeners.FecharActionListener;

/**
 * @author israel
 *
 */
public abstract class AbstractCriaEAtualizaBase<C extends Component> extends JDialog
	implements InterfaceCriaEAtualizaBase<C> {

    private JButton okButton;
    private JButton cancelButton;
    private C mediaComponent;

    public AbstractCriaEAtualizaBase(Frame owner, String title, boolean modal) {
	super(owner, title, modal);
	init();
	addListeners();
	preencheCampos();
    }

    @Override
    public void addListeners() {
	getCancelarButton().addActionListener(new FecharActionListener(this));
    }

    @Override
    public JButton getSalvarButton() {
	return okButton;
    }

    @Override
    public JButton getCancelarButton() {
	return cancelButton;
    }

    @Override
    public C getMediaComponent() {
	// TODO Auto-generated method stub
	return this.mediaComponent;
    }

    @Override
    public void setMediaComponent(C component) {
	this.mediaComponent = component;
    }

    @Override
    public void init() {

	setBounds(100, 100, 759, 441);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	getContentPane().setLayout(new BorderLayout());
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

}
