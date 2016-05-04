/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufrpe.uag.projetao.abstracts;

import java.util.List;

import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceViewController;

/**
 *
 * @author israel
 * @param <T>
 */
public abstract class AbstractViewController<T> implements InterfaceViewController<T> {

    private final String namedQuery;

    public AbstractViewController(String namedQuery) {
	this.namedQuery = namedQuery;
    }

    @Override
    public List<T> getItems() {
	return TransactionManager.getRequestSession().getNamedQuery(this.namedQuery).list();
    }

}
