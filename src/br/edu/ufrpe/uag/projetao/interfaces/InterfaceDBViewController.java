/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufrpe.uag.projetao.interfaces;

import java.util.List;

import org.hibernate.Query;

/**
 *
 * @author israel
 * @param <T>
 */
public interface InterfaceDBViewController<T> {

    List<T> getItems(Query query);
}
