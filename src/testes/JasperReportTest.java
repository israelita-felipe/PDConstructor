/**
 * 
 */
package testes;

import static org.junit.Assert.*;

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
	JPanel relatorio = JasperReportFactory.getRelatorioDeUsuarios().getRelatorio();
	JFrame janela = new JFrame();
	janela.setContentPane(relatorio);
	janela.setVisible(true);
	assertTrue(janela.isVisible());
    }

}
