package br.edu.ufrpe.uag.projetao.testes;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.util.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.util.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.util.VideoDigital;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceDBController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.model.VideoDeteccao;

public class ReleaseSteps {

    InterfaceDBController<Usuario> supervisorControlador;
    InterfaceDBController<Perfil> perfilControlador;
    Usuario supervisor;
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
    	resultado = "Base criada com sucesso";
	try {
	    InterfaceDBController<VideoDeteccao> videoDeteccaoController = ControllerFactory.getVideoDeteccaoController();
	    InterfaceDBController<AlocacaoVideoDeteccao> alocacaoVideoDeteccao = ControllerFactory
		    .getAlocacaoVideoDeteccaoController();
	    InterfaceDBController<BaseVideoDeteccao> controlador = ControllerFactory.getBaseVideoDeteccaoController();

	    TransactionManager.begin();
	    controlador.prepareCreate();
	    controlador.getSelected().setTitulo(titulo);
	    controlador.getSelected().setDescricao(descricao);
	    controlador.getSelected().setUsuario(supervisor);
	    validar(controlador.getSelected(),Arrays.asList(new File(arquivo)));
	    controlador.create();
	    TransactionManager.end();

	    TransactionManager.begin();
	    videoDeteccaoController.prepareCreate();

	    videoDeteccaoController.getSelected().setObjeto(VideoDigital.toByte(new File(arquivo)));

	    videoDeteccaoController.getSelected().setUsuario(supervisor);
	    videoDeteccaoController.create();

	    // aloca um vídeo para uma base

	    alocacaoVideoDeteccao.prepareCreate();
	    alocacaoVideoDeteccao.getSelected().setBaseVideoDeteccao(controlador.getSelected());
	    alocacaoVideoDeteccao.getSelected().setUsuario(supervisor);
	    alocacaoVideoDeteccao.getSelected().setVideoDeteccao(videoDeteccaoController.getSelected());
	    alocacaoVideoDeteccao.create();

	    TransactionManager.end();
	} catch (Throwable e) {
	    resultado = "Erro";
	} finally {
	    TransactionManager.end();
	}
    }
    
    private void validar(BaseVideoDeteccao base,List<File> arquivos) throws IllegalArgumentException {
    	if (base.getTitulo() == null || base.getTitulo().trim().equals("")) {
    	    throw new IllegalArgumentException("Campo de título não pode estar vazio");
    	}
    	if (base.getDescricao() == null || base.getDescricao().equals("")) {
    	    throw new IllegalArgumentException("Campo de descrição não pode estar vazio");
    	}
    	if (arquivos.size() == 0) {
    	    throw new IllegalArgumentException("Lista de arquivos de vídeos não pode estar vazia");
    	}
        }

    @Then("retorne a mensagem de criacao da base <mensagem>")
    @Alias("retorne a mensagem de criacao da base $mensagem")
    public void compareMensagem(@Named("mensagem") String mensagem) {
	assertEquals(resultado, mensagem);
    }
}
