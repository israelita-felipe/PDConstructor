/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.Usuario;

/**
 *
 * @author israel
 */
public class UsuarioController extends AbstractController<Usuario> {

	private static final long serialVersionUID = 1L;

	public static Usuario currrentSupervisor = (Usuario) ControllerFactory.getUsuarioController()
	    .getItemsFromCriteria(DetachedCriteriaFactory.getTodosSupervisores()).get(0);

    public static Usuario currentEscravo = (Usuario) ControllerFactory.getUsuarioController()
	    .getItemsFromCriteria(DetachedCriteriaFactory.getTodosEscravos()).get(0);

    protected UsuarioController() {
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
