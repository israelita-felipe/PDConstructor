/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.principal;

import org.kairos.ActionBarDrawerToggle;
import org.kairos.Toolbar;
import org.kairos.core.Activity;
import org.kairos.layouts.SlidingTabLayout;
import org.kairos.layouts.ViewPager;

import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.BaseImagemClasseLiberacaoController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.BaseImagemDeteccaoLiberacaoController;
import br.edu.ufrpe.uag.projetao.control.base.texto.BaseTextoLiberacaoController;
import br.edu.ufrpe.uag.projetao.control.base.video.BaseVideoDeteccaoLiberacaoController;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.principal.util.CustomDrawerLayout;
import br.edu.ufrpe.uag.projetao.control.principal.util.Tab;
import br.edu.ufrpe.uag.projetao.control.principal.util.ViewPagerAdapter;
import br.edu.ufrpe.uag.projetao.control.usuario.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @author israel
 *
 */
public class PrincipalEscravoController extends Activity {

    @FXML
    private Toolbar toolbar;
    @FXML
    private SlidingTabLayout tabLayout;
    @FXML
    private ViewPager viewPager;
    @FXML
    private CustomDrawerLayout drawer;
    @FXML
    private Label usuarioNome;
    @FXML
    private Label usuarioEmail;

    @Override
    public void onCreate() {
	super.onCreate();
	setContentView(getClass().getResource("/br/edu/ufrpe/uag/projetao/view/principal/PrincipalEscravoView.fxml"));

	toolbar.setTitle("PDConstructor");
	toolbar.setDisplayShowHomeEnabled(true);
	setActionBar(toolbar);

	drawer.setForceNarrow(true);
	drawer.setDrawerListener(new ActionBarDrawerToggle(this, drawer, toolbar));

	ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
	viewPager.setAdapter(pagerAdapter);
	pagerAdapter.addTab(new Tab("Base de Texto", new BaseTextoLiberacaoController()));
	pagerAdapter.addTab(new Tab("Base de Imagem", new BaseImagemClasseLiberacaoController()));
	pagerAdapter.addTab(new Tab("Base de Detecção", new BaseImagemDeteccaoLiberacaoController()));
	pagerAdapter.addTab(new Tab("Base de Video", new BaseVideoDeteccaoLiberacaoController()));
	tabLayout.setViewPager(viewPager);

	usuarioNome.setText(UsuarioController.currentEscravo.getNome());
	usuarioEmail.setText(UsuarioController.currentEscravo.getEmail());

	viewPager.setCurrentItem(0);

    }

    @FXML
    private void sair() {
	TransactionManager.setRequestSession(null);
	UsuarioController.currentEscravo = null;
	startActivity(LoginController.class);

    }
}
