package br.edu.ufrpe.uag.projetao.testes;

import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.util.ControllerFactory;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceDBController;
import br.edu.ufrpe.uag.projetao.model.Perfil;

public class PerfilTest {

    private InterfaceDBController<Perfil> controller = ControllerFactory.getPerfilController();
    private Perfil perfil;
    private int i = 0;

    @Before
    public void setUp() throws Exception {

	TransactionManager.begin();
	controller.prepareCreate();
	controller.getSelected().setDescricao("descriçãoTeste");
	controller.getSelected().setNome("nomeTeste" + ++i);
	perfil = controller.create();
	TransactionManager.end();
    }

    @After
    public void doAfter() {
	TransactionManager.begin();
	int index = controller.getItems().indexOf(perfil);
	if (index != -1) {
	    controller.destroy(index);
	}
	TransactionManager.end();
    }

    @Test
    public void testPrepareCreate() {
	assertNotEquals(perfil, controller.prepareCreate());
    }

    @Test
    public void testCreate() {
	TransactionManager.begin();

	controller.prepareCreate();

	controller.getSelected().setDescricao("descrição de teste de criação");
	controller.getSelected().setNome("nome de tete de criação");

	perfil = controller.create();

	TransactionManager.end();

	assertNotEquals(null, perfil);
    }

}
