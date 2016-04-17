package br.edu.ufrpe.uag.projetao.abstracts;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.ufrpe.uag.projetao.control.util.Facade;
import br.edu.ufrpe.uag.projetao.control.util.FacesContextUtil;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceFacade;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author israel
 * @param <T>
 */
public abstract class AbstractController<T extends InterfaceEntity> implements InterfaceController<T>, Serializable {

    private final InterfaceFacade<T> ejbFacade;
    private T current;
    private List<T> items = null;
    private AbstractPaginator pagination;
    private int selectedItemIndex;

    public AbstractController(Class<T> clazz) {
        this.ejbFacade = new Facade<>(clazz);
    }

    @Override
    public InterfaceFacade<T> getFacade() {
        return ejbFacade;
    }

    @Override
    public AbstractPaginator getPagination() {
        if (pagination == null) {
            pagination = new AbstractPaginator(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public List<T> createPageDataModel() {
                    return getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
                }
            };
        }
        return pagination;
    }
    
    

    @Override
    public List<T> prepareList() {
        FacesContextUtil.begin();
        recreateModel();

        FacesContextUtil.end();
        return getItems();
    }

    @Override
    public T prepareView(int index) {
        selectedItemIndex = index;
        FacesContextUtil.begin();
        current = (T) getItems().get(selectedItemIndex);

        FacesContextUtil.end();
        return current;
    }

    @Override
    public T create() {

        getFacade().create(current);

        FacesContextUtil.end();
        return current;
    }

    @Override
    public T prepareEdit(int index) {
        selectedItemIndex = index;
        FacesContextUtil.begin();
        current = (T) getItems().get(selectedItemIndex);
        return current;
    }

    @Override
    public T update() {

        getFacade().edit(current);
        FacesContextUtil.end();

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

        FacesContextUtil.begin();

        current = (T) getItems().get(selectedItemIndex);

        performDestroy();
        recreatePagination();
        recreateModel();

        return current;
    }

    @Override
    public T destroyAndView(int index) {
        selectedItemIndex = index;

        FacesContextUtil.begin();

        current = (T) getItems().get(selectedItemIndex);

        performDestroy();
        recreateModel();
        updateCurrentItem();

        FacesContextUtil.end();
        return current;
    }

    /**
     *
     */
    @Override
    public void performDestroy() {
        getFacade().remove(current);
        FacesContextUtil.end();
    }

    @Override
    public void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    @Override
    public List<T> getItems() {
        if (items == null) {
            items = (List<T>) getPagination().createPageDataModel();
        }
        return items;
    }

    @Override
    public void recreateModel() {
        items = null;
    }

    @Override
    public void recreatePagination() {
        pagination = null;
    }

    @Override
    public List<T> first() {
        getPagination().firstPage();
        recreateModel();
        return getItems();
    }

    @Override
    public List<T> last() {
        getPagination().lastPage();
        recreateModel();
        return getItems();
    }

    @Override
    public List<T> next() {
        getPagination().nextPage();
        recreateModel();
        return getItems();
    }

    @Override
    public List<T> previous() {
        getPagination().previousPage();
        recreateModel();
        return getItems();
    }

    @Override
    public List<T> getItemsAvailableSelectMany() {
    	FacesContextUtil.begin();
    	List<T> list = ejbFacade.findAll();
    	FacesContextUtil.end();
        return list;
    }

    @Override
    public List<T> getItemsAvailableSelectOne() {
    	FacesContextUtil.begin();
    	List<T> list = ejbFacade.findAll();
    	FacesContextUtil.end();
    	return list;
    }

    @Override
    public T get(Serializable id) {
    	FacesContextUtil.begin();
    	T object =ejbFacade.find(id);
    	FacesContextUtil.end();
        return object;
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

    public void setPagination(AbstractPaginator pagination) {
        this.pagination = pagination;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
