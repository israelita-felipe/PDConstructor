package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.ufrpe.uag.projetao.control.PerfilController;
import br.edu.ufrpe.uag.projetao.control.UsuarioConstroller;
import br.edu.ufrpe.uag.projetao.control.util.FacesContextUtil;
import br.edu.ufrpe.uag.projetao.model.Usuario;

public class UsuarioControllerTest {

    UsuarioConstroller uc;
    PerfilController pc;
    Usuario us;

    List<Usuario> toRemove = new LinkedList<>();

    @Before
    public void setUp() throws Exception {
	uc = new UsuarioConstroller();
	pc = new PerfilController();
	FacesContextUtil.begin();

	// criando perfil caso não exista
	if (pc.getItemsAvailableSelectOne().size() < 1) {
	    pc.prepareCreate();
	    pc.getCurrent().setNome("SUPERVISOR");
	    pc.getCurrent().setDescricao("Supervisor do sistema");
	    pc.create();
	}

	// criando usuários de teste
	for (int i = 0; i < 50; i++) {

	    Usuario u = new Usuario();
	    u.setEmail(i + "@mail.com");
	    u.setNome("Nome(" + i + ")");
	    u.setPerfil(pc.getItemsAvailableSelectOne().get(0));
	    u.setSenha("senha" + i);
	    uc.prepareCreate();
	    uc.setCurrent(u);
	    us = uc.create();
	    toRemove.add(us);
	}
	FacesContextUtil.end();
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
	FacesContextUtil.begin();
	Usuario u = new Usuario();
	u.setEmail(i + "@mail.com");
	u.setNome("Nome(" + i + ")");
	u.setPerfil(pc.getItemsAvailableSelectOne().get(0));
	u.setSenha("senha" + i);
	uc.prepareCreate();
	uc.setCurrent(u);
	us = uc.create();
	toRemove.add(us);
	FacesContextUtil.end();
	assertNotEquals(null, us);
    }

    @Test
    public void testUpdate() {
	FacesContextUtil.begin();
	us = uc.prepareList().get(0);
	us.setNome("alterado");
	uc.prepareEdit(0);
	Usuario current = uc.update();
	FacesContextUtil.end();
	assertEquals(us, current);
    }

    @Test
    public void testDestroy() {
	FacesContextUtil.begin();
	us = uc.prepareList().get(0);
	uc.destroy(0);
	Usuario current = uc.prepareList().get(0);
	FacesContextUtil.end();
	assertNotEquals(us, current);
    }

    @Test
    public void testFirst() {
	List<Usuario> list = uc.prepareList();
	assertEquals(list, uc.first());
    }

    @Test
    public void testLast() {
	List<Usuario> list = uc.prepareList();
	assertNotEquals(list, uc.last());
    }

    @Test
    public void testNext() {
	List<Usuario> list = uc.prepareList();
	assertNotEquals(list, uc.next());
    }

    @Test
    public void testPrevious() {
	uc.prepareList();
	List<Usuario> list = uc.last();
	assertNotEquals(list, uc.previous());
    }

    @After
    public void removeAll() {
	FacesContextUtil.begin();
	for (Usuario u : toRemove) {
	    uc.setCurrent(u);
	    uc.performDestroy();
	}
	FacesContextUtil.end();
    }
}
