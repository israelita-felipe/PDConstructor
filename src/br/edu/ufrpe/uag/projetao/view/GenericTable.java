/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view;

import java.util.Collection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;

/**
 * @author israel
 *
 */
public class GenericTable<T extends InterfaceEntity> extends JTable {

    public GenericTable(List<T> list) {
	super(new GenericTableModel<T>(list));
	setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	// this.setDefaultRenderer(Object.class, new SubtableCellRenderer());
    }

    @SuppressWarnings("unchecked")
    @Override
    public GenericTableModel<T> getModel() {
	// TODO Auto-generated method stub
	return (GenericTableModel<T>) super.getModel();
    }

    public T getValueAt(int row) {
	return (T) getModel().loadItem(row);
    }

    public void remove(T element) {
	getModel().deleteItem(getModel().indexOf(element));
    }

    public void clear() {
	getModel().clear();
    }

    public void addAll(Collection<T> collection) {
	getModel().addAll(collection);
    }

    public void addElement(T element) {
	getModel().addElement(element);
    }

}
