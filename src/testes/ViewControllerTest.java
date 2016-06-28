/**
 * 
 */
package testes;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasseHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseTextoHistograma;

/**
 * @author israel
 *
 */
public class ViewControllerTest {

    @Test
    public void buscaTotalDeVotacoesPorBaseTexto(){
	List<BaseTextoHistograma> histograma = ControllerFactory.getBaseTextoHistogramaController().getItems();
	System.out.println(histograma);
	assertNotEquals(histograma, null);
    }
    
    @Test
    public void buscaTotalDeVotacoesPorBaseImagemClasse(){
	List<BaseImagemClasseHistograma> histograma = ControllerFactory.getBaseImagemClasseHistogramaController().getItems();
	System.out.println(histograma);
	assertNotEquals(histograma, null);
    }
}
