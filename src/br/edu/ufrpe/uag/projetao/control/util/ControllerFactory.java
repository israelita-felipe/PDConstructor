/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBViewController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao.AlocacaoImagemClasseController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao.BaseImagemClasseController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao.ClassificacaoImagemClasseController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao.EscolhaClasseImagemClasseController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao.ImagemClasseController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao.LiberacaoImagemClasseController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.deteccao.AlocacaoImagemDeteccaoController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.deteccao.BaseImagemDeteccaoController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.deteccao.DeteccaoImagemController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.deteccao.ImagemDeteccaoController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.deteccao.LiberacaoBaseImagemDeteccaoController;
import br.edu.ufrpe.uag.projetao.control.base.texto.AlocacaoTextoController;
import br.edu.ufrpe.uag.projetao.control.base.texto.BaseTextoController;
import br.edu.ufrpe.uag.projetao.control.base.texto.ClassificacaoTextoController;
import br.edu.ufrpe.uag.projetao.control.base.texto.EscolhaClasseTextoController;
import br.edu.ufrpe.uag.projetao.control.base.texto.LiberacaoBaseTextoController;
import br.edu.ufrpe.uag.projetao.control.base.video.AlocacaoVideoDeteccaoController;
import br.edu.ufrpe.uag.projetao.control.base.video.BaseVideoDeteccaoController;
import br.edu.ufrpe.uag.projetao.control.base.video.DeteccaoVideoController;
import br.edu.ufrpe.uag.projetao.control.base.video.LiberacaoBaseVideoDeteccaoController;
import br.edu.ufrpe.uag.projetao.control.base.video.VideoDeteccaoController;
import br.edu.ufrpe.uag.projetao.control.usuario.PerfilController;
import br.edu.ufrpe.uag.projetao.control.usuario.UsuarioController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceDBController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceDBViewController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.AlocacaoVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasseHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccaoHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.BaseTextoHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccaoHistograma;
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

    private static InterfaceDBController usuarioController;
    private static InterfaceDBController perfilController;
    private static InterfaceDBController baseTextoController;
    private static InterfaceDBController alocacaoTextoController;
    private static InterfaceDBController classificacaoTextoController;
    private static InterfaceDBController escolhaClasseTextoController;
    private static InterfaceDBController liberacaoBaseTextoController;
    private static InterfaceDBController liberacaoBaseImagemClasseController;
    private static InterfaceDBController alocacaoImagemClasseController;
    private static InterfaceDBController classificacaoImagemClasseController;
    private static InterfaceDBController baseImagemClasseController;
    private static InterfaceDBController escolhaImagemClasseController;
    private static InterfaceDBController imagemClasseController;
    private static InterfaceDBController baseImagemDeteccaoController;
    private static InterfaceDBController deteccaoImagemController;
    private static InterfaceDBController alocacaoImagemDeteccaoController;
    private static InterfaceDBController imagemDeteccaoController;
    private static InterfaceDBController liberacaoBaseImagemDeteccaoController;
    private static InterfaceDBController baseVideoDeteccaoController;
    private static InterfaceDBController alocacaoVideoDeteccaoController;
    private static InterfaceDBController videoDeteccaoController;
    private static InterfaceDBController deteccaoVideoController;
    private static InterfaceDBController liberacaoBaseVideoDeteccaoController;
    private static InterfaceDBViewController baseImagemClasseHistograma;
    private static InterfaceDBViewController baseTextoHistograma;
    private static InterfaceDBViewController baseImagemDeteccaoHistograma;
    private static InterfaceDBViewController baseVideoDeteccaoHistograma;

    public static InterfaceDBController<Usuario> getUsuarioController() {
	if (usuarioController == null) {
	    usuarioController = new UsuarioController();
	}
	return usuarioController;
    }

    public static InterfaceDBController<Perfil> getPerfilController() {
	if (perfilController == null) {
	    perfilController = new PerfilController();
	}
	return perfilController;
    }

    public static InterfaceDBController<BaseTexto> getBaseTextoController() {
	if (baseTextoController == null) {
	    baseTextoController = new BaseTextoController();
	}
	return baseTextoController;
    }

    public static InterfaceDBController<AlocacaoTexto> getAlocacaoTextoController() {
	if (alocacaoTextoController == null) {
	    alocacaoTextoController = new AlocacaoTextoController();
	}
	return alocacaoTextoController;
    }

    public static InterfaceDBController<ClassificacaoTexto> getClassificacaoTextoController() {
	if (classificacaoTextoController == null) {
	    classificacaoTextoController = new ClassificacaoTextoController();
	}
	return classificacaoTextoController;
    }

    public static InterfaceDBController<EscolhaClasseTexto> getEscolhaClasseTextoController() {
	if (escolhaClasseTextoController == null) {
	    escolhaClasseTextoController = new EscolhaClasseTextoController();
	}
	return escolhaClasseTextoController;
    }

    public static InterfaceDBController<LiberacaoBaseTexto> getLiberacaoBaseTextoController() {
	if (liberacaoBaseTextoController == null) {
	    liberacaoBaseTextoController = new LiberacaoBaseTextoController();
	}
	return liberacaoBaseTextoController;
    }

    public static InterfaceDBController<LiberacaoBaseImagemClasse> getLiberacaoBaseImagemClasseController() {
	if (liberacaoBaseImagemClasseController == null) {
	    liberacaoBaseImagemClasseController = new LiberacaoImagemClasseController();
	}
	return liberacaoBaseImagemClasseController;
    }

    public static InterfaceDBController<AlocacaoImagemClasse> getAlocacaoImagemClasseController() {
	if (alocacaoImagemClasseController == null) {
	    alocacaoImagemClasseController = new AlocacaoImagemClasseController();
	}
	return alocacaoImagemClasseController;
    }

    public static InterfaceDBController<ClasssificacaoImagemClasse> getClassificacaoImagemClasseController() {
	if (classificacaoImagemClasseController == null) {
	    classificacaoImagemClasseController = new ClassificacaoImagemClasseController();
	}
	return classificacaoImagemClasseController;
    }

    public static InterfaceDBController<BaseImagemClasse> getBaseImagemClasseController() {
	if (baseImagemClasseController == null) {
	    baseImagemClasseController = new BaseImagemClasseController();
	}
	return baseImagemClasseController;
    }

    public static InterfaceDBController<EscolhaImagemClasse> getEscolhaClasseImagemClasseController() {
	if (escolhaImagemClasseController == null) {
	    escolhaImagemClasseController = new EscolhaClasseImagemClasseController();
	}
	return escolhaImagemClasseController;
    }

    public static InterfaceDBController<ImagemClasse> getImagemClasseController() {
	if (imagemClasseController == null) {
	    imagemClasseController = new ImagemClasseController();
	}
	return imagemClasseController;
    }

    public static InterfaceDBController<BaseImagemDeteccao> getBaseImagemDeteccaoController() {
	if (baseImagemDeteccaoController == null) {
	    baseImagemDeteccaoController = new BaseImagemDeteccaoController();
	}
	return baseImagemDeteccaoController;
    }

    public static InterfaceDBController<DeteccaoImagem> getDeteccaoImagemController() {
	if (deteccaoImagemController == null) {
	    deteccaoImagemController = new DeteccaoImagemController();
	}
	return deteccaoImagemController;
    }

    public static InterfaceDBController<AlocacaoImagemDeteccao> getAlocacaoImagemDeteccaoController() {
	if (alocacaoImagemDeteccaoController == null) {
	    alocacaoImagemDeteccaoController = new AlocacaoImagemDeteccaoController();
	}
	return alocacaoImagemDeteccaoController;
    }

    public static InterfaceDBController<ImagemDeteccao> getImagemDeteccaoController() {
	if (imagemDeteccaoController == null) {
	    imagemDeteccaoController = new ImagemDeteccaoController();
	}
	return imagemDeteccaoController;
    }

    public static InterfaceDBController<LiberacaoBaseImagemDeteccao> getLiberacaoBaseImagemDeteccaoController() {
	if (liberacaoBaseImagemDeteccaoController == null) {
	    liberacaoBaseImagemDeteccaoController = new LiberacaoBaseImagemDeteccaoController();
	}
	return liberacaoBaseImagemDeteccaoController;
    }

    public static InterfaceDBController<BaseVideoDeteccao> getBaseVideoDeteccaoController() {
	if (baseVideoDeteccaoController == null) {
	    baseVideoDeteccaoController = new BaseVideoDeteccaoController();
	}
	return baseVideoDeteccaoController;
    }

    public static InterfaceDBController<AlocacaoVideoDeteccao> getAlocacaoVideoDeteccaoController() {
	if (alocacaoVideoDeteccaoController == null) {
	    alocacaoVideoDeteccaoController = new AlocacaoVideoDeteccaoController();
	}
	return alocacaoVideoDeteccaoController;
    }

    public static InterfaceDBController<VideoDeteccao> getVideoDeteccaoController() {
	if (videoDeteccaoController == null) {
	    videoDeteccaoController = new VideoDeteccaoController();
	}
	return videoDeteccaoController;
    }

    public static InterfaceDBController<DeteccaoVideo> getDeteccaoVideoController() {
	if (deteccaoVideoController == null) {
	    deteccaoVideoController = new DeteccaoVideoController();
	}
	return deteccaoVideoController;
    }

    public static InterfaceDBController<LiberacaoBaseVideoDeteccao> getLiberacaoBaseVideoDeteccaoController() {
	if (liberacaoBaseVideoDeteccaoController == null) {
	    liberacaoBaseVideoDeteccaoController = new LiberacaoBaseVideoDeteccaoController();
	}
	return liberacaoBaseVideoDeteccaoController;

    }

    public static InterfaceDBViewController<BaseImagemClasseHistograma> getBaseImagemClasseHistogramaController() {
	if (baseImagemClasseHistograma == null) {
	    baseImagemClasseHistograma = new AbstractDBViewController() {
	    };
	}
	return baseImagemClasseHistograma;
    }

    public static InterfaceDBViewController<BaseTextoHistograma> getBaseTextoHistogramaController() {
	if (baseTextoHistograma == null) {
	    baseTextoHistograma = new AbstractDBViewController() {
	    };
	}
	return baseTextoHistograma;
    }

    public static InterfaceDBViewController<BaseImagemDeteccaoHistograma> getBaseImagemDeteccaoHistogramaController() {
	if (baseImagemDeteccaoHistograma == null) {
	    baseImagemDeteccaoHistograma = new AbstractDBViewController() {
	    };
	}
	return baseImagemDeteccaoHistograma;
    }

    public static InterfaceDBViewController<BaseVideoDeteccaoHistograma> getBaseVideoDeteccaoHistogramaController() {
	if (baseVideoDeteccaoHistograma == null) {
	    baseVideoDeteccaoHistograma = new AbstractDBViewController() {
	    };
	}
	return baseVideoDeteccaoHistograma;
    }
}
