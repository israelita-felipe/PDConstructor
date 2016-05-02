/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;

/**
 * @author israel
 *
 */
@SuppressWarnings("rawtypes")
public class ControllerFactory {

    private static InterfaceController usuarioController;
    private static InterfaceController perfilController;
    private static InterfaceController baseTextoController;
    private static InterfaceController alocacaoTextoController;
    private static InterfaceController classificacaoTextoController;
    private static InterfaceController escolhaClasseTextoController;
    private static InterfaceController liberacaoBaseTextoController;

    public static InterfaceController<Usuario> getUsuarioController() {
	if (usuarioController == null) {
	    usuarioController = new UsuarioController();
	}
	return usuarioController;
    }

    public static InterfaceController<Perfil> getPerfilController() {
	if (perfilController == null) {
	    perfilController = new PerfilController();
	}
	return perfilController;
    }

    public static InterfaceController<BaseTexto> getBaseTextoController() {
	if (baseTextoController == null) {
	    baseTextoController = new BaseTextoController();
	}
	return baseTextoController;
    }

    public static InterfaceController<AlocacaoTexto> getAlocacaoTextoController() {
	if (alocacaoTextoController == null) {
	    alocacaoTextoController = new AlocacaoTextoController();
	}
	return alocacaoTextoController;
    }

    public static InterfaceController<ClassificacaoTexto> getClassificacaoTextoController() {
	if (classificacaoTextoController == null) {
	    classificacaoTextoController = new ClassificacaoTextoController();
	}
	return classificacaoTextoController;
    }

    public static InterfaceController<EscolhaClasseTexto> getEscolhaClasseTextoController() {
	if (escolhaClasseTextoController == null) {
	    escolhaClasseTextoController = new EscolhaClasseTextoController();
	}
	return escolhaClasseTextoController;
    }

    public static InterfaceController<LiberacaoBaseTexto> getLiberacaoBaseTextoController() {
	if (liberacaoBaseTextoController == null) {
	    liberacaoBaseTextoController = new LiberacaoBaseTextoController();
	}
	return liberacaoBaseTextoController;
    }
}
