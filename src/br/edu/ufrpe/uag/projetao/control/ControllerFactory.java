/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;

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

    public static InterfaceController getUsuarioController() {
	if (usuarioController == null) {
	    usuarioController = new UsuarioController();
	}
	return usuarioController;
    }

    public static InterfaceController getPerfilController() {
	if (perfilController == null) {
	    perfilController = new PerfilController();
	}
	return perfilController;
    }

    public static InterfaceController getBaseTextoController() {
	if (baseTextoController == null) {
	    baseTextoController = new BaseTextoController();
	}
	return baseTextoController;
    }

    public static InterfaceController getAlocacaoTextoController() {
	if (alocacaoTextoController == null) {
	    alocacaoTextoController = new AlocacaoTextoController();
	}
	return alocacaoTextoController;
    }

    public static InterfaceController getClassificacaoTextoController() {
	if (classificacaoTextoController == null) {
	    classificacaoTextoController = new ClassificacaoTextoController();
	}
	return classificacaoTextoController;
    }

    public static InterfaceController getEscolhaClasseTextoController() {
	if (escolhaClasseTextoController == null) {
	    escolhaClasseTextoController = new EscolhaClasseTextoController();
	}
	return escolhaClasseTextoController;
    }

    public static InterfaceController getLiberacaoBaseTextoController() {
	if (liberacaoBaseTextoController == null) {
	    liberacaoBaseTextoController = new LiberacaoBaseTextoController();
	}
	return liberacaoBaseTextoController;
    }
}
