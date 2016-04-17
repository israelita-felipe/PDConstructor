/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufrpe.uag.projetao.interfaces;

import java.util.List;

/**
 *
 * @author israel
 * @param <T>
 */
public interface InterfaceViewController<T> {

    List<T> getItems();
}
