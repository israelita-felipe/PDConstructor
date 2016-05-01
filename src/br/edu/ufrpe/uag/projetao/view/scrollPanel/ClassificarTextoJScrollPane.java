/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.scrollPanel;

import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;

/**
 * @author israel
 *
 */
public class ClassificarTextoJScrollPane extends JScrollPane {
    private JEditorPane editorPane;

    /**
     * 
     */
    public ClassificarTextoJScrollPane(String texto) {
	// TODO Auto-generated constructor stub
	super();
	init();
	getEditorPane().setText(texto);
    }

    private void init() {
	editorPane = new JEditorPane();
	editorPane.setEditable(false);

	setViewportView(editorPane);

	JPanel panel = new JPanel();
	setColumnHeaderView(panel);
	panel.setLayout(new BorderLayout(0, 0));

	JLabel lblTexto = new JLabel("Texto:");
	panel.add(lblTexto, BorderLayout.NORTH);

	JSeparator separator = new JSeparator();
	panel.add(separator, BorderLayout.SOUTH);
    }

    public JEditorPane getEditorPane() {
	return editorPane;
    }
}
