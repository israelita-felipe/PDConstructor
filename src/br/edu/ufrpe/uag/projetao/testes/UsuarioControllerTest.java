package br.edu.ufrpe.uag.projetao.testes;

import static org.junit.Assert.assertNotEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.util.ControllerFactory;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceDBController;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;

public class UsuarioControllerTest {

    InterfaceDBController<Usuario> uc = ControllerFactory.getUsuarioController();
    InterfaceDBController<Perfil> pc = ControllerFactory.getPerfilController();
    Usuario us;

    List<Usuario> toRemove = new LinkedList<>();

    @Before
    public void setUp() throws Exception {

	TransactionManager.begin();

	// criando perfil caso não exista
	if (pc.getItemsAvailableSelectOne().size() < 1) {
	    pc.prepareCreate();
	    pc.getSelected().setNome("SUPERVISOR");
	    pc.getSelected().setDescricao("Supervisor do sistema");
	    pc.create();
	}

	TransactionManager.end();
    }

    @After
    public void doAfter() {
	TransactionManager.begin();
	for (Usuario usuario : toRemove) {
	    int index = uc.getItems().indexOf(usuario);
	    if (index != -1) {
		uc.destroy(index);
	    }
	}
	TransactionManager.end();
    }

    @Test
    public void testPrepareCreate() {
	assertNotEquals(us, uc.prepareCreate());
    }

    @Test
    public void testCreate() {
	TransactionManager.begin();

	uc.prepareCreate();

	uc.getSelected().setPerfil(pc.prepareList().get(0));
	uc.getSelected().setNome("testCreate");
	uc.getSelected().setEmail("testeCreate");
	uc.getSelected().setSenha("testCreate");

	toRemove.add(uc.create());

	TransactionManager.end();
	assertNotEquals(null, uc.getSelected());
    }

}
