/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.relatorios;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractJasperReportUtil;
import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceJasperReportUtil;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import net.sf.jasperreports.engine.JRException;

/**
 * @author israel
 *
 */
public class JasperReportFactory {

    private static InterfaceJasperReportUtil relatorioDeUsuarios;

    /**
     * Método no qual é possível verificar o caminho, o mapeamento e o JPanel
     * contendo o relatório rederizado referente a todos os usuários
     * 
     * @return Interface de manipulação de relatórios
     */
    public static InterfaceJasperReportUtil getRelatorioDeUsuarios() {
	if (relatorioDeUsuarios == null) {
	    relatorioDeUsuarios = new AbstractJasperReportUtil("relatorios/usuario.jrxml") {

		@Override
		public List<Map<String, ?>> getList() {
		    InterfaceController<Usuario> controller = ControllerFactory.getUsuarioController();
		    List<Map<String, ?>> list = new LinkedList<>();
		    for (Usuario usuario : controller.getItems()) {
			Map<String, Object> mapa = new HashMap<>();
			mapa.put("id", usuario.getId());
			mapa.put("nome", usuario.getNome());
			mapa.put("email", usuario.getEmail());
			mapa.put("perfil", usuario.getPerfil().getNome());
			list.add(mapa);
		    }
		    return list;
		}
	    };
	}
	return relatorioDeUsuarios;
    }

    public static void main(String[] args) throws JRException {
	JPanel relatorio = JasperReportFactory.getRelatorioDeUsuarios().getRelatorio();
	JFrame janela = new JFrame();
	janela.setContentPane(relatorio);
	janela.setVisible(true);
    }
}
