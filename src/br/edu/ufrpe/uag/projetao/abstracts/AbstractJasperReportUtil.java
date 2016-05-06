/**
 * 
 */
package br.edu.ufrpe.uag.projetao.abstracts;

import javax.swing.JPanel;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceJasperReportUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

/**
 * @author israel
 *
 */
public abstract class AbstractJasperReportUtil implements InterfaceJasperReportUtil {

    private String caminhoDoRelatorio;

    /**
     * 
     */
    public AbstractJasperReportUtil(String caminhoDoRelatorio) {
	// TODO Auto-generated constructor stub
	this.caminhoDoRelatorio = caminhoDoRelatorio;
    }

    @Override
    public String getCaminhoRelatorio() {
	return this.caminhoDoRelatorio;
    }

    @Override
    public JPanel getRelatorio() throws JRException {
	JRDataSource dados = new JRBeanCollectionDataSource(getList());
	JasperReport relatorio = JasperCompileManager.compileReport(getCaminhoRelatorio());
	JasperPrint imprimir = JasperFillManager.fillReport(relatorio, null, dados);
	return new JRViewer(imprimir);
    }

}
