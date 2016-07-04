/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.usuario;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.Perfil;

/**
 * @author israel
 *
 */
public class PerfilController extends AbstractController<Perfil> {

    public PerfilController() {
	super(Perfil.class);
	// TODO Auto-generated constructor stub
    }

    @Override
    public Perfil getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new Perfil());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public Perfil prepareCreate() {
	setCurrent(new Perfil());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
