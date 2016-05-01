/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;

/**
 * @author israel
 *
 */
public class SubtableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	    int row, int column) {

	if (value instanceof InterfaceEntity) {
	    final JTable embedded = new JTable(new GenericTableModel(Arrays.asList(value)));

	    embedded.setDefaultRenderer(Object.class, new SubtableCellRenderer());
	    embedded.setBorder(BorderFactory.createEmptyBorder());

	    if (isSelected) {
		embedded.setBackground(table.getSelectionBackground());
		embedded.setForeground(table.getSelectionForeground());
	    }

	    setPreferredSize(embedded.getPreferredSize());
	    
	    return embedded;
	} else {
	    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
    }

    public static void main(String[] args) {
	JFrame f = new JFrame();
	JScrollPane sp = new JScrollPane();
	JTable t = new JTable(new GenericTableModel<BaseTexto>(
		ControllerFactory.getBaseTextoController().getItems()));
	t.setDefaultRenderer(Object.class, new SubtableCellRenderer());
	sp.setViewportView(t);
	f.setContentPane(sp);
	f.setVisible(true);
    }
}
