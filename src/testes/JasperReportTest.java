/**
 * 
 */
package testes;

import static org.junit.Assert.*;

import java.util.jar.JarException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import br.edu.ufrpe.uag.projetao.view.relatorios.JasperReportFactory;
import net.sf.jasperreports.engine.JRException;

/**
 * @author israel
 *
 */
public class JasperReportTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() throws JRException {
	JPanel relatorio = null;
	try {
		relatorio = JasperReportFactory.getRelatorioDeUsuarios().getRelatorio();
	} catch (JarException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JFrame janela = new JFrame();
	janela.setContentPane(relatorio);
	janela.setVisible(true);
	assertTrue(janela.isVisible());
    }

}
