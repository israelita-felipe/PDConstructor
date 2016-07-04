/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.principal;

import org.kairos.ActionBarDrawerToggle;
import org.kairos.Toolbar;
import org.kairos.core.Activity;
import org.kairos.layouts.SlidingTabLayout;
import org.kairos.layouts.ViewPager;

import br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao.BaseImagemClasseListController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.deteccao.BaseImagemDeteccaoListController;
import br.edu.ufrpe.uag.projetao.control.base.texto.BaseTextoListController;
import br.edu.ufrpe.uag.projetao.control.base.video.BaseVideoDetaccaoListController;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.principal.util.CustomDrawerLayout;
import br.edu.ufrpe.uag.projetao.control.principal.util.Tab;
import br.edu.ufrpe.uag.projetao.control.principal.util.ViewPagerAdapter;
import br.edu.ufrpe.uag.projetao.control.usuario.LoginController;
import br.edu.ufrpe.uag.projetao.control.usuario.UsuarioController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @author israel
 * @author Bruno Barros
 */
public class PrincipalSupervisorController extends Activity {

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
	setContentView(getClass().getResource("PrincipalSupervisorView.fxml"));

	toolbar.setTitle("PDConstructor");
	toolbar.setDisplayShowHomeEnabled(true);
	setActionBar(toolbar);

	drawer.setForceNarrow(true);
	drawer.setDrawerListener(new ActionBarDrawerToggle(this, drawer, toolbar));

	ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
	viewPager.setAdapter(pagerAdapter);
	pagerAdapter.addTab(new Tab("Base de Texto", new BaseTextoListController()));
	pagerAdapter.addTab(new Tab("Base de Imagem", new BaseImagemClasseListController()));
	pagerAdapter.addTab(new Tab("Base de Detecção", new BaseImagemDeteccaoListController()));
	pagerAdapter.addTab(new Tab("Base de Vídeos", new BaseVideoDetaccaoListController()));
	tabLayout.setViewPager(viewPager);

	usuarioNome.setText(UsuarioController.currrentSupervisor.getNome());
	usuarioEmail.setText(UsuarioController.currrentSupervisor.getEmail());

	viewPager.setCurrentItem(0);

    }

    @FXML
    private void sair() {
	TransactionManager.setRequestSession(null);
	UsuarioController.currrentSupervisor = null;
	startActivity(LoginController.class);

    }

}
