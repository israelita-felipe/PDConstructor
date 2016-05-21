/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view;

import java.awt.Component;
import java.awt.Dimension;
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
	    GenericTable<?> embedded = new GenericTable(Arrays.asList(value));
	    JScrollPane scroll = new JScrollPane();
	    scroll.setViewportView(embedded);

	    embedded.setBorder(BorderFactory.createEmptyBorder());
	    scroll.setBorder(BorderFactory.createEmptyBorder());

	    if (isSelected) {
		embedded.setBackground(table.getSelectionBackground());
		embedded.setForeground(table.getSelectionForeground());
	    }
	    setPreferredSize(new Dimension(embedded.getPreferredSize().width, embedded.getPreferredSize().height
		    + (embedded.getPreferredSize().height / embedded.getRowCount())+4));
	    // setPreferredSize(embedded.getPreferredSize());

	    if (getPreferredSize().height > table.getRowHeight(row)) {
		table.setRowHeight(row, getPreferredSize().height);
	    }

	    return scroll;
	} else {
	    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
    }

    public static void main(String[] args) {
	JFrame f = new JFrame();
	JScrollPane sp = new JScrollPane();
	JTable t = new JTable(new GenericTableModel<BaseTexto>(ControllerFactory.getBaseTextoController().getItems()));
	t.setDefaultRenderer(Object.class, new SubtableCellRenderer());
	sp.setViewportView(t);
	f.setContentPane(sp);
	f.setVisible(true);
    }
}
