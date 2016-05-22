package br.edu.ufrpe.uag.projetao.abstracts;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import br.edu.ufrpe.uag.projetao.control.hibernate.Facade;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceFacade;

/**
 *
 * @author israel
 * @param <T>
 */
public abstract class AbstractController<T extends InterfaceEntity> implements InterfaceController<T>, Serializable {

    private final InterfaceFacade<T> ejbFacade;
    private T current;
    private List<T> items = null;
    private int selectedItemIndex = 0;

    public AbstractController(Class<T> clazz) {
	this.ejbFacade = new Facade<>(clazz);
    }

    @Override
    public synchronized InterfaceFacade<T> getFacade() {
	return ejbFacade;
    }

    @Override
    public synchronized List<T> prepareList() {

	recreateModel();

	return getItems();
    }

    @Override
    public synchronized T prepareView(int index) {
	selectedItemIndex = index;

	current = getItems().get(selectedItemIndex);

	return current;
    }

    @Override
    public synchronized T create() {

	getFacade().create(current);

	return current;
    }

    @Override
    public synchronized T prepareEdit(int index) {
	selectedItemIndex = index;
	current = getItems().get(selectedItemIndex);
	return current;
    }

    @Override
    public synchronized T update() {

	getFacade().edit(current);

	return current;

    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public synchronized T destroy(int index) {
	selectedItemIndex = index;

	current = getItems().get(selectedItemIndex);

	performDestroy();
	recreatePagination();
	recreateModel();

	return current;
    }

    @Override
    public synchronized T destroyAndView(int index) {
	selectedItemIndex = index;

	current = getItems().get(selectedItemIndex);

	performDestroy();
	recreateModel();

	return current;
    }

    /**
     *
     */
    @Override
    public synchronized void performDestroy() {
	getFacade().remove(current);

    }

    @Override
    public synchronized List<T> getItems() {
	if (items == null) {
	    items = getFacade().findAll();
	}
	return items;
    }

    @Override
    public synchronized void recreateModel() {
	items = null;
    }

    @Override
    public synchronized void recreatePagination() {
	items = null;
    }

    @Override
    public synchronized List<T> getItemsAvailableSelectMany() {

	List<T> list = ejbFacade.findAll();

	return list;
    }

    @Override
    public synchronized List<T> getItemsAvailableSelectOne() {

	List<T> list = ejbFacade.findAll();

	return list;
    }

    @Override
    public synchronized List<T> getItemsFromCriteria(DetachedCriteria criteria) {
	List<T> list = getFacade().getEntitiesByDetachedCriteria(criteria);
	setItems(list);
	return list;
    }

    @Override
    public synchronized T get(Serializable id) {

	T object = ejbFacade.find(id);

	return object;
    }

    @Override
    public synchronized int next() throws IllegalArgumentException {

	setSelectedItemIndex(getSelectedItemIndex() + 1);
	if (getSelectedItemIndex() >= getItems().size()) {
	    setSelectedItemIndex(getSelectedItemIndex() - 1);
	    throw new IllegalArgumentException("Último índice");
	}
	prepareView(getSelectedItemIndex());
	return getSelectedItemIndex();
    }

    @Override
    public synchronized int previous() throws IllegalArgumentException {
	setSelectedItemIndex(getSelectedItemIndex() - 1);
	if (getSelectedItemIndex() < 0) {
	    setSelectedItemIndex(getSelectedItemIndex() + 1);
	    throw new IllegalArgumentException("Primeiro índice");
	}
	prepareView(getSelectedItemIndex());
	return getSelectedItemIndex();
    }

    public synchronized int getSelectedItemIndex() {
	return selectedItemIndex;
    }

    public synchronized void setSelectedItemIndex(int selectedItemIndex) {
	this.selectedItemIndex = selectedItemIndex;
    }

    public synchronized T getCurrent() {
	return current;
    }

    public synchronized void setCurrent(T current) {
	this.current = current;
    }

    public synchronized void setSelected(T current) {
	this.current = current;
    }

    public synchronized void setItems(List<T> items) {
	this.items = items;
    }

}
