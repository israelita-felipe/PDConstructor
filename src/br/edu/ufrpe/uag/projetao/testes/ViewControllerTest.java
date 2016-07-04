/**
 * 
 */
package br.edu.ufrpe.uag.projetao.testes;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;

import br.edu.ufrpe.uag.projetao.control.util.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.util.QueryFactory;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasseHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccaoHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseTextoHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccaoHistograma;

/**
 * @author israel
 *
 */
public class ViewControllerTest {

    @Test
    public void buscaTotalDeVotacoesPorBaseImagemClasse() {
	List<BaseImagemClasseHistograma> histograma = ControllerFactory.getBaseImagemClasseHistogramaController()
		.getItems(QueryFactory.getTodasBasesImagemClasseHistograma());
	assertNotEquals(histograma, null);
    }

    @Test
    public void buscaTotalDeVotacoesPorBaseTexto() {
	List<BaseTextoHistograma> histograma = ControllerFactory.getBaseTextoHistogramaController()
		.getItems(QueryFactory.getTodasBasesTextoHistograma());
	assertNotEquals(histograma, null);
    }

    public void buscaTotalDeVotacoesPorBaseImagemDeteccao() {
	List<BaseImagemDeteccaoHistograma> histograma = ControllerFactory.getBaseImagemDeteccaoHistogramaController()
		.getItems(QueryFactory.getTodasBasesImagemDeteccaoHistograma());
	assertNotEquals(histograma, null);
    }

    public void buscaTotalDeVotacoesPorBaseVideoDeteccao() {
	List<BaseVideoDeteccaoHistograma> histograma = ControllerFactory.getBaseVideoDeteccaoHistogramaController()
		.getItems(QueryFactory.getTodasBasesVideoDeteccaoHistograma());
	assertNotEquals(histograma, null);
    }

}
