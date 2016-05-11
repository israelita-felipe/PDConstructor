/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.JPane;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.edu.ufrpe.uag.projetao.view.listeners.SelecionaLinhaJListMouseListener;
import br.edu.ufrpe.uag.projetao.view.scrollPanel.ListaArquivosJScrollPane;
import br.edu.ufrpe.uag.projetao.view.scrollPanel.ListaClassesJScrollPane;

/**
 * @author israel
 *
 */
public class BaseTextoJPanel extends JPanel {
    private JTextField textField;
    private JEditorPane editorPane;
    private GroupLayout groupLayout;
    private ListaArquivosJScrollPane listaArquivos;
    private ListaClassesJScrollPane listaClasses;

    /**
     * 
     */
    public BaseTextoJPanel() {
	init();
    }    
    private void init() {

	JLabel lblNomeDabase = new JLabel("Título da base:");

	textField = new JTextField();
	textField.setColumns(10);

	JScrollPane scrollPane = new JScrollPane();

	this.listaArquivos = new ListaArquivosJScrollPane();
	this.listaClasses = new ListaClassesJScrollPane();

	groupLayout = new GroupLayout(this);
	groupLayout
		.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
					.addComponent(lblNomeDabase)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addComponent(listaClasses, GroupLayout.DEFAULT_SIZE, 327,
							Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(listaArquivos,
							GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)))
				.addContainerGap()));
	groupLayout
		.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblNomeDabase)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(listaClasses, GroupLayout.PREFERRED_SIZE, 182,
						GroupLayout.PREFERRED_SIZE)
					.addComponent(listaArquivos, GroupLayout.PREFERRED_SIZE, 183,
						GroupLayout.PREFERRED_SIZE))
				.addContainerGap(26, Short.MAX_VALUE)));

	JPanel panel = new JPanel();
	scrollPane.setColumnHeaderView(panel);
	panel.setLayout(new BorderLayout(0, 0));

	JLabel lblDescrioDaBase = new JLabel("Descrição da base:");
	panel.add(lblDescrioDaBase, BorderLayout.NORTH);

	JSeparator separator = new JSeparator();
	panel.add(separator, BorderLayout.SOUTH);

	editorPane = new JEditorPane();
	scrollPane.setViewportView(editorPane);
	setLayout(groupLayout);

    }

    public GroupLayout getGroupLayout() {
	return groupLayout;
    }

    public void setGroupLayout(GroupLayout groupLayout) {
	this.groupLayout = groupLayout;
    }

    public JTextField getTituloTextField() {
	return textField;
    }

    public JEditorPane getDescricaoEditorPane() {
	return editorPane;
    }

    public ListaArquivosJScrollPane getListaArquivos() {
	return listaArquivos;
    }

    public void setListaArquivos(ListaArquivosJScrollPane listaArquivos) {
	this.listaArquivos = listaArquivos;
    }

    public ListaClassesJScrollPane getListaClasses() {
	return listaClasses;
    }

    public void setListaClasses(ListaClassesJScrollPane listaClasses) {
	this.listaClasses = listaClasses;
    }

}
