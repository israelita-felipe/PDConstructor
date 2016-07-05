package br.edu.ufrpe.uag.projetao.abstracts;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import br.edu.ufrpe.uag.projetao.control.hibernate.Facade;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceDBController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceDBFacade;

/**
 *
 * @author israel
 * @param <T>
 */
public abstract class AbstractDBController<T extends InterfaceEntity> implements InterfaceDBController<T> {

    private final InterfaceDBFacade<T> ejbFacade;
    private T current;
    private List<T> items = null;
    private int selectedItemIndex = 0;

    public AbstractDBController(Class<T> clazz) {
	this.ejbFacade = new Facade<>(clazz);
    }

    @Override
    public InterfaceDBFacade<T> getFacade() {
	return ejbFacade;
    }

    @Override
    public List<T> prepareList() {

	recreateModel();

	return getItems();
    }

    @Override
    public T prepareView(int index) {
	selectedItemIndex = index;

	current = getItems().get(selectedItemIndex);

	return current;
    }

    @Override
    public T prepareView(T entity) {
	current = entity;
	return current;
    }

    @Override
    public T create() {

	getFacade().create(current);

	return current;
    }

    @Override
    public T prepareEdit(int index) {
	selectedItemIndex = index;
	current = getItems().get(selectedItemIndex);
	return current;
    }

    @Override
    public T prepareEdit(T entity) {
	current = entity;
	return current;
    }

    @Override
    public T update() {

	getFacade().edit(current);

	return current;

    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public T destroy(int index) {
	selectedItemIndex = index;

	current = getItems().get(selectedItemIndex);

	performDestroy();
	recreatePagination();
	recreateModel();

	return current;
    }

    @Override
    public T destroy(T entity) {
	current = entity;

	performDestroy();
	recreatePagination();
	recreateModel();

	return current;
    }

    @Override
    public T destroyAndView(int index) {
	selectedItemIndex = index;

	current = getItems().get(selectedItemIndex);

	performDestroy();
	recreateModel();

	return current;
    }

    @Override
    public T destroyAndView(T entity) {
	current = entity;

	performDestroy();
	recreateModel();

	return current;
    }

    /**
     *
     */
    @Override
    public void performDestroy() {
	getFacade().remove(current);

    }

    @Override
    public List<T> getItems() {
	if (items == null) {
	    items = getFacade().findAll();
	}
	return items;
    }

    @Override
    public void recreateModel() {
	items = null;
    }

    @Override
    public void recreatePagination() {
	items = null;
    }

    @Override
    public List<T> getItemsAvailableSelectMany() {

	List<T> list = ejbFacade.findAll();

	return list;
    }

    @Override
    public List<T> getItemsAvailableSelectOne() {

	List<T> list = ejbFacade.findAll();

	return list;
    }

    @Override
    public List<T> getItemsFromCriteria(DetachedCriteria criteria) {
	List<T> list = getFacade().getEntitiesByDetachedCriteria(criteria);
	setItems(list);
	return list;
    }

    @Override
    public T get(Serializable id) {

	T object = ejbFacade.find(id);

	return object;
    }

    @Override
    public int next() throws IllegalArgumentException {

	setSelectedItemIndex(getSelectedItemIndex() + 1);
	if (getSelectedItemIndex() >= getItems().size()) {
	    setSelectedItemIndex(getSelectedItemIndex() - 1);
	    throw new IllegalArgumentException("Último índice");
	}
	prepareView(getSelectedItemIndex());
	return getSelectedItemIndex();
    }

    @Override
    public int previous() throws IllegalArgumentException {
	setSelectedItemIndex(getSelectedItemIndex() - 1);
	if (getSelectedItemIndex() < 0) {
	    setSelectedItemIndex(getSelectedItemIndex() + 1);
	    throw new IllegalArgumentException("Primeiro índice");
	}
	prepareView(getSelectedItemIndex());
	return getSelectedItemIndex();
    }

    public int getSelectedItemIndex() {
	return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
	this.selectedItemIndex = selectedItemIndex;
    }

    public T getCurrent() {
	return current;
    }

    public void setCurrent(T current) {
	this.current = current;
    }

    public void setSelected(T current) {
	this.current = current;
    }

    public void setItems(List<T> items) {
	this.items = items;
    }

}
