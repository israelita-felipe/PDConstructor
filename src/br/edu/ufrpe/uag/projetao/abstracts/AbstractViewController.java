/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufrpe.uag.projetao.abstracts;

import java.util.List;

import org.hibernate.Query;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceViewController;

/**
 *
 * @author israel
 * @param <T>
 */
public abstract class AbstractViewController<T> implements InterfaceViewController<T> {

    @Override
    public List<T> getItems(Query query) {
	return query.list();
    }

}
