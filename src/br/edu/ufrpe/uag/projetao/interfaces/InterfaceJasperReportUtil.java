/**
 * 
 */
package br.edu.ufrpe.uag.projetao.interfaces;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import net.sf.jasperreports.engine.JRException;

/**
 * @author israel
 *
 */
public interface InterfaceJasperReportUtil {

    /**
     * Mapeia os objetos que serão inseridos nos relatórios
     * 
     * @return List de Mapeamento dos objetos para serem exibidos no relatório
     */
    List<Map<String, ?>> getList();

    /**
     * 
     * @return caminho do relatório *.jrxml
     */
    String getCaminhoRelatorio();

    /**
     * 
     * @return Painel contendo o relatório
     * @throws JRException
     */
    JPanel getRelatorio() throws JRException;
}
