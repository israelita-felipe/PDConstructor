package br.edu.ufrpe.uag.projetao.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.ufrpe.uag.projetao.abstracts.AbstractPaginator;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Israel Araújo
 * @param <T>
 */
public interface InterfaceController<T extends InterfaceEntity> {
    
    void setEmbeddableKeys();

    void initializeEmbeddableKey();

    T getSelected();

    InterfaceFacade<T> getFacade();

    AbstractPaginator getPagination();

    List<T> prepareList();

    T prepareView(int index);

    T prepareCreate();

    T create();

    T prepareEdit(int index);

    T update();

    T destroy(int index);

    T destroyAndView(int index);

    void performDestroy();

    void updateCurrentItem();

    List<T> getItems();

    void recreateModel();

    void recreatePagination();

    List next();

    List previous();

    List first();

    List last();

    List<T> getItemsAvailableSelectMany();

    List<T> getItemsAvailableSelectOne();

    T get(Serializable id);

}
