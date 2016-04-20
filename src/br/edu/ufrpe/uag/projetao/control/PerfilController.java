/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;

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
	public void setEmbeddableKeys() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeEmbeddableKey() {
		// TODO Auto-generated method stub

	}

	@Override
	public Perfil getSelected() {
		if (getCurrent() == null) {
			setCurrent(new Perfil());
			initializeEmbeddableKey();
			setSelectedItemIndex(-1);
		}
		return getCurrent();
	}

	@Override
	public Perfil prepareCreate() {
		setCurrent(new Perfil());
		initializeEmbeddableKey();
		setSelectedItemIndex(-1);
		return getCurrent();
	}

}
