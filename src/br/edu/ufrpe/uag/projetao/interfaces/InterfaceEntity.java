package br.edu.ufrpe.uag.projetao.interfaces;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Israel Araújo
 */
public interface InterfaceEntity extends Serializable {

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();

    @Override
    public String toString();

}
