/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufrpe.uag.projetao.control.usuario;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.Usuario;

/**
 *
 * @author israel
 */
public class UsuarioController extends AbstractDBController<Usuario> {

    public static Usuario currrentSupervisor;

    public static Usuario currentEscravo;

    public UsuarioController() {
	super(Usuario.class);
    }

    @Override
    public Usuario getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new Usuario());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public Usuario prepareCreate() {
	setCurrent(new Usuario());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
