package br.edu.ufrpe.uag.projetao.testes;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.usuario.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.util.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.util.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.util.VideoDigital;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.model.VideoDeteccao;

public class ReleaseSteps {

    InterfaceController<Usuario> supervisorControlador;
    InterfaceController<Perfil> perfilControlador;
    Usuario supervisor;
    String sucesso = "Base criada com sucesso";
    String resultado = "Base criada com sucesso";

    @Given("Um supervisor")
    public void entreComSupervisor() {
	supervisorControlador = ControllerFactory.getUsuarioController();
	perfilControlador = ControllerFactory.getPerfilController();
	supervisor = supervisorControlador.getItemsFromCriteria(DetachedCriteriaFactory
		.getUsuario("supervisor@supervisor.com", "supervisor", new Perfil("SUPERVISOR", "SUPERVISOR"))).get(0);
    }

    @When("Ele quiser criar uma base de video com titulo <titulo>, descricao <descricao> e arquivo <arquivo>")
    @Alias("Ele quiser criar uma base de video com titulo $titulo, descricao $descricao e arquivo $arquivo")
    public void criaBaseVideo(@Named("titulo") String titulo, @Named("descricao") String descricao,
	    @Named("arquivo") String arquivo) {
	try {
	    InterfaceController<VideoDeteccao> videoDeteccaoController = ControllerFactory.getVideoDeteccaoController();
	    InterfaceController<AlocacaoVideoDeteccao> alocacaoVideoDeteccao = ControllerFactory
		    .getAlocacaoVideoDeteccaoController();
	    InterfaceController<BaseVideoDeteccao> controlador = ControllerFactory.getBaseVideoDeteccaoController();

	    TransactionManager.begin();
	    controlador.prepareCreate();
	    controlador.getSelected().setTitulo(titulo);
	    controlador.getSelected().setDescricao(descricao);
	    controlador.getSelected().setUsuario(supervisor);
	    controlador.create();
	    TransactionManager.end();

	    TransactionManager.begin();
	    videoDeteccaoController.prepareCreate();

	    videoDeteccaoController.getSelected().setObjeto(VideoDigital.toByte(new File(arquivo)));

	    videoDeteccaoController.getSelected().setUsuario(UsuarioController.currrentSupervisor);
	    videoDeteccaoController.create();

	    // aloca um vídeo para uma base

	    alocacaoVideoDeteccao.prepareCreate();
	    alocacaoVideoDeteccao.getSelected().setBaseVideoDeteccao(controlador.getSelected());
	    alocacaoVideoDeteccao.getSelected().setUsuario(UsuarioController.currrentSupervisor);
	    alocacaoVideoDeteccao.getSelected().setVideoDeteccao(videoDeteccaoController.getSelected());
	    alocacaoVideoDeteccao.create();

	    TransactionManager.end();
	} catch (Exception e) {
	    resultado = e.getMessage();
	} finally {
	    TransactionManager.end();
	}

    }

    @Then("retorne a mensagem de criacao da base <mensagem>")
    @Alias("retorne a mensagem de criacao da base $mensagem")
    public void compareMensagem(@Named("mensagem") String mensagem) {
	assertEquals(sucesso, resultado);
    }
}
