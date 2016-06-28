/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractViewController;
import br.edu.ufrpe.uag.projetao.annotations.BaseVideoDeteccaoController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceViewController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.AlocacaoVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasseHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.BaseTextoHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.ClasssificacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.DeteccaoImagem;
import br.edu.ufrpe.uag.projetao.model.DeteccaoVideo;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaImagemClasse;
import br.edu.ufrpe.uag.projetao.model.ImagemClasse;
import br.edu.ufrpe.uag.projetao.model.ImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.model.VideoDeteccao;

/**
 * @author israel
 *
 */

public class ControllerFactory {

    private static InterfaceController usuarioController;
    private static InterfaceController perfilController;
    private static InterfaceController baseTextoController;
    private static InterfaceController alocacaoTextoController;
    private static InterfaceController classificacaoTextoController;
    private static InterfaceController escolhaClasseTextoController;
    private static InterfaceController liberacaoBaseTextoController;
    private static InterfaceController liberacaoBaseImagemClasseController;
    private static InterfaceController alocacaoImagemClasseController;
    private static InterfaceController classificacaoImagemClasseController;
    private static InterfaceController baseImagemClasseController;
    private static InterfaceController escolhaImagemClasseController;
    private static InterfaceController imagemClasseController;
    private static InterfaceController baseImagemDeteccaoController;
    private static InterfaceController deteccaoImagemController;
    private static InterfaceController alocacaoImagemDeteccaoController;
    private static InterfaceController imagemDeteccaoController;
    private static InterfaceController liberacaoBaseImagemDeteccaoController;
    private static InterfaceController baseVideoDeteccaoController;
    private static InterfaceController alocacaoVideoDeteccaoController;
    private static InterfaceController videoDeteccaoController;
    private static InterfaceController deteccaoVideoController;
    private static InterfaceController liberacaoBaseVideoDeteccaoController;
    private static InterfaceViewController baseTextoHistograma;
    private static InterfaceViewController baseImagemClasseHistograma;

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

    public static InterfaceController<LiberacaoBaseImagemClasse> getLiberacaoBaseImagemClasseController() {
	if (liberacaoBaseImagemClasseController == null) {
	    liberacaoBaseImagemClasseController = new LiberacaoImagemClasseController();
	}
	return liberacaoBaseImagemClasseController;
    }

    public static InterfaceController<AlocacaoImagemClasse> getAlocacaoImagemClasseController() {
	if (alocacaoImagemClasseController == null) {
	    alocacaoImagemClasseController = new AlocacaoImagemClasseController();
	}
	return alocacaoImagemClasseController;
    }

    public static InterfaceController<ClasssificacaoImagemClasse> getClassificacaoImagemClasseController() {
	if (classificacaoImagemClasseController == null) {
	    classificacaoImagemClasseController = new ClassificacaoImagemClasseController();
	}
	return classificacaoImagemClasseController;
    }

    public static InterfaceController<BaseImagemClasse> getBaseImagemClasseController() {
	if (baseImagemClasseController == null) {
	    baseImagemClasseController = new BaseImagemClasseController();
	}
	return baseImagemClasseController;
    }

    public static InterfaceController<EscolhaImagemClasse> getEscolhaClasseImagemClasseController() {
	if (escolhaImagemClasseController == null) {
	    escolhaImagemClasseController = new EscolhaClasseImagemClasseController();
	}
	return escolhaImagemClasseController;
    }

    public static InterfaceController<ImagemClasse> getImagemClasseController() {
	if (imagemClasseController == null) {
	    imagemClasseController = new ImagemClasseController();
	}
	return imagemClasseController;
    }

    public static InterfaceController<BaseImagemDeteccao> getBaseImagemDeteccaoController() {
	if (baseImagemDeteccaoController == null) {
	    baseImagemDeteccaoController = new BaseImagemDeteccaoController();
	}
	return baseImagemDeteccaoController;
    }

    public static InterfaceController<DeteccaoImagem> getDeteccaoImagemController() {
	if (deteccaoImagemController == null) {
	    deteccaoImagemController = new DeteccaoImagemController();
	}
	return deteccaoImagemController;
    }

    public static InterfaceController<AlocacaoImagemDeteccao> getAlocacaoImagemDeteccaoController() {
	if (alocacaoImagemDeteccaoController == null) {
	    alocacaoImagemDeteccaoController = new AlocacaoImagemDeteccaoController();
	}
	return alocacaoImagemDeteccaoController;
    }

    public static InterfaceController<ImagemDeteccao> getImagemDeteccaoController() {
	if (imagemDeteccaoController == null) {
	    imagemDeteccaoController = new ImagemDeteccaoController();
	}
	return imagemDeteccaoController;
    }

    public static InterfaceController<LiberacaoBaseImagemDeteccao> getLiberacaoBaseImagemDeteccaoController() {
	if (liberacaoBaseImagemDeteccaoController == null) {
	    liberacaoBaseImagemDeteccaoController = new LiberacaoBaseImagemDeteccaoController();
	}
	return liberacaoBaseImagemDeteccaoController;
    }

    public static InterfaceController<BaseVideoDeteccao> getBaseVideoDeteccaoController() {
	if (baseVideoDeteccaoController == null) {
	    baseVideoDeteccaoController = new BaseVideoDeteccaoController();
	}
	return baseVideoDeteccaoController;
    }

    public static InterfaceController<AlocacaoVideoDeteccao> getAlocacaoVideoDeteccaoController() {
	if (alocacaoVideoDeteccaoController == null) {
	    alocacaoVideoDeteccaoController = new AlocacaoVideoDeteccaoController();
	}
	return alocacaoVideoDeteccaoController;
    }

    public static InterfaceController<VideoDeteccao> getVideoDeteccaoController() {
	if (videoDeteccaoController == null) {
	    videoDeteccaoController = new VideoDeteccaoController();
	}
	return videoDeteccaoController;
    }

    public static InterfaceController<DeteccaoVideo> getDeteccaoVideoController() {
	if (deteccaoVideoController == null) {
	    deteccaoVideoController = new DeteccaoVideoController();
	}
	return deteccaoVideoController;
    }

    public static InterfaceController<LiberacaoBaseVideoDeteccao> getLiberacaoBaseVideoDeteccaoController() {
	if (liberacaoBaseVideoDeteccaoController == null) {
	    liberacaoBaseVideoDeteccaoController = new LiberacaoBaseVideoDeteccaoController();
	}
	return liberacaoBaseVideoDeteccaoController;

    }

    public static InterfaceViewController<BaseTextoHistograma> getBaseTextoHistogramaController() {
	if (baseTextoHistograma == null) {
	    baseTextoHistograma = new AbstractViewController("totalClassificacaoPorBaseTexto") {
	    };
	}
	return baseTextoHistograma;
    }

    public static InterfaceViewController<BaseImagemClasseHistograma> getBaseImagemClasseHistogramaController() {
	if (baseImagemClasseHistograma == null) {
	    baseImagemClasseHistograma = new AbstractViewController("totalClassificacaoPorBaseImagemClasse") {
	    };
	}
	return baseImagemClasseHistograma;
    }
}
