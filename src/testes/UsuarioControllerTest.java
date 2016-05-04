package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;

public class UsuarioControllerTest {

    InterfaceController<Usuario> uc;
    InterfaceController<Perfil> pc;
    Usuario us;

    List<Usuario> toRemove = new LinkedList<>();

    @Before
    public void setUp() throws Exception {
	uc = ControllerFactory.getUsuarioController();
	pc = ControllerFactory.getPerfilController();
	TransactionManager.begin();

	// criando perfil caso não exista
	if (pc.getItemsAvailableSelectOne().size() < 1) {
	    pc.prepareCreate();
	    pc.getSelected().setNome("SUPERVISOR");
	    pc.getSelected().setDescricao("Supervisor do sistema");
	    pc.create();
	}

	// criando usuários de teste
	for (int i = 0; i < 50; i++) {

	    uc.prepareCreate();
	    uc.getSelected().setEmail(i + "@mail.com");
	    uc.getSelected().setNome("Nome(" + i + ")");
	    uc.getSelected().setPerfil(pc.getItemsAvailableSelectOne().get(0));
	    uc.getSelected().setSenha("senha" + i);

	    us = uc.create();
	    toRemove.add(us);
	}
	TransactionManager.end();
    }

    @Test
    public void testGetSelected() {
	assertEquals(us, uc.getSelected());
    }

    @Test
    public void testPrepareCreate() {
	assertNotEquals(us, uc.prepareCreate());
    }

    @Test
    public void testPrepareList() {
	assertEquals(uc.prepareList().size(), 10);
    }

    @Test
    public void testCreate() {
	int i = -1;
	TransactionManager.begin();
	uc.prepareCreate();
	Usuario u = new Usuario();
	uc.getSelected().setEmail(i + "@mail.com");
	uc.getSelected().setNome("Nome(" + i + ")");
	uc.getSelected().setPerfil(pc.getItemsAvailableSelectOne().get(0));
	uc.getSelected().setSenha("senha" + i);

	us = uc.create();
	toRemove.add(us);
	TransactionManager.end();
	assertNotEquals(null, us);
    }

    @Test
    public void testUpdate() {
	TransactionManager.begin();
	us = uc.prepareList().get(0);
	us.setNome("alterado");
	uc.prepareEdit(0);
	Usuario current = uc.update();
	TransactionManager.end();
	assertEquals(us, current);
    }

    @Test
    public void testDestroy() {
	TransactionManager.begin();
	us = uc.prepareList().get(0);
	uc.destroy(0);
	Usuario current = uc.prepareList().get(0);
	TransactionManager.end();
	assertNotEquals(us, current);
    }
}
