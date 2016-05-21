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

import br.edu.ufrpe.uag.projetao.view.scrollPanel.ListaArquivosJScrollPane;
import br.edu.ufrpe.uag.projetao.view.scrollPanel.ListaClassesJScrollPane;

public class BaseImagemClasseJPanel extends JPanel {
	private JTextField textField;
	private ListaClassesJScrollPane scrollPane_1;
	private ListaArquivosJScrollPane scrollPane_2;
	private JEditorPane dtrpnBigorna;

	/**
	 * Create the panel.
	 */
	public BaseImagemClasseJPanel() {
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo da Base");
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane_1 = new ListaClassesJScrollPane();
		
		scrollPane_2 = new ListaArquivosJScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
						.addComponent(lblTtulo)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTtulo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		dtrpnBigorna = new JEditorPane();
		dtrpnBigorna.setText("Bigorna");
		scrollPane.setViewportView(dtrpnBigorna);
		
		JPanel panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDescrioDaBase = new JLabel("Descri\u00E7\u00E3o da Base");
		panel.add(lblDescrioDaBase, BorderLayout.NORTH);
		
		JSeparator separator = new JSeparator();
		panel.add(separator, BorderLayout.SOUTH);
		setLayout(groupLayout);

	}
	public ListaClassesJScrollPane getClassesList() {
		return scrollPane_1;
	}
	public ListaArquivosJScrollPane getArquivosList() {
		return scrollPane_2;
	}
	public JTextField getTituloTextField() {
		return textField;
	}
	public JEditorPane getDescricaoEditorPane() {
		return dtrpnBigorna;
	}
}
