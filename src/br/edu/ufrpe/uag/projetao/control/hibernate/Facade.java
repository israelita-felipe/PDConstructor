package br.edu.ufrpe.uag.projetao.control.hibernate;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.ufrpe.uag.projetao.abstracts.AbstractFacade;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;
import org.hibernate.Session;

/**
 *
 * @author israel
 * @param <T>
 */
public class Facade<T extends InterfaceEntity> extends AbstractFacade<T> {

    @Override
    public Session getSession() {
        return FacesContextUtil.getRequestSession();
    }

    public Facade(Class<T> clazz) {
        super(clazz);
    }    

}
