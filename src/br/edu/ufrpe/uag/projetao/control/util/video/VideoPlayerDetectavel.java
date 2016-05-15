/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util.video;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer;
import br.edu.ufrpe.uag.projetao.view.listeners.RemoveDeListActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.SelecionaLinhaJListMouseListener;
import br.edu.ufrpe.uag.projetao.view.util.GerenciadorDePopUp;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.direct.RenderCallback;

/**
 * @author israel
 *
 */
public class VideoPlayerDetectavel implements InterfacePlayer {

    private final int width;
    private final int height;
    private final VideoSurfacePanel videoSurface;
    private final BufferedImage image;
    private final DirectMediaPlayerComponent mediaPlayerComponent;

    private JList<CoordenadaTempoVideo> coordenadas;
    private CoordenadaTempoVideo coordenada;
    private boolean fecharRetangulo;

    /**
     * Cria um player com tamanho específico
     * 
     * @param width
     *            largura do video
     * @param height
     *            altura do vídeo
     */
    public VideoPlayerDetectavel(int width, int height) {

	this.width = width;
	this.height = height;
	this.image = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
		.getDefaultConfiguration().createCompatibleImage(width, height);

	this.videoSurface = new VideoSurfacePanel(this.width, this.height, this.image);

	this.mediaPlayerComponent = new DirectMediaPlayerComponent(new CustomBufferFormatCallback(width, height)) {

	    @Override
	    protected RenderCallback onGetRenderCallback() {
		return new CustomRenderCallbackAdapter(width, height, image, videoSurface);
	    }
	};

	init();
	addListeners();
    }

    private void init() {
	this.coordenadas = new JList<CoordenadaTempoVideo>(new DefaultListModel<CoordenadaTempoVideo>());

	JPopupMenu pop = new JPopupMenu();
	JMenuItem removerMenuItem = new JMenuItem("Excluir");
	pop.add(removerMenuItem);
	GerenciadorDePopUp.addPopup(coordenadas, pop);

	// listener que remove e pinta novamente a imagem
	removerMenuItem.addActionListener(new RemoveDeListActionListener(getCoordenadas()));
    }

    private void addListeners() {
	this.coordenadas.addMouseListener(new SelecionaLinhaJListMouseListener(coordenadas));

	this.videoSurface.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
		super.mousePressed(e);

		coordenada = new CoordenadaTempoVideo(new Integer[4], getTempoAtual());
		coordenada.getCoordenadas()[0] = e.getX();
		coordenada.getCoordenadas()[1] = e.getY();

		fecharRetangulo = true;
		parar();
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		if (fecharRetangulo) {

		    coordenada.getCoordenadas()[2] = e.getX();
		    coordenada.getCoordenadas()[3] = e.getY();

		    if (coordenada.getCoordenadas()[2] < coordenada.getCoordenadas()[0]
			    && coordenada.getCoordenadas()[3] < coordenada.getCoordenadas()[1]) {
			// inverte as duas coordenadas
			int y1 = coordenada.getCoordenadas()[1];
			int x1 = coordenada.getCoordenadas()[2];

			// y1<-y2
			coordenada.getCoordenadas()[1] = coordenada.getCoordenadas()[3];
			// y2<-y1
			coordenada.getCoordenadas()[3] = y1;

			// x1<-x2
			coordenada.getCoordenadas()[0] = coordenada.getCoordenadas()[2];
			// x2<-x1
			coordenada.getCoordenadas()[2] = x1;

		    } else if (coordenada.getCoordenadas()[2] > coordenada.getCoordenadas()[0]
			    && coordenada.getCoordenadas()[3] < coordenada.getCoordenadas()[1]) {

			int y1 = coordenada.getCoordenadas()[1];
			// y1<-y2
			coordenada.getCoordenadas()[1] = coordenada.getCoordenadas()[3];
			// y2<-y1
			coordenada.getCoordenadas()[3] = y1;

		    } else if (coordenada.getCoordenadas()[0] > coordenada.getCoordenadas()[2]
			    && coordenada.getCoordenadas()[3] > coordenada.getCoordenadas()[1]) {

			int x1 = coordenada.getCoordenadas()[2];
			// x1<-x2
			coordenada.getCoordenadas()[0] = coordenada.getCoordenadas()[2];
			// x2<-x1
			coordenada.getCoordenadas()[2] = x1;

		    }

		    ((DefaultListModel<CoordenadaTempoVideo>) coordenadas.getModel()).addElement(coordenada);

		    coordenada = null;

		    fecharRetangulo = false;
		}
		pintarRetangulo(image, coordenadas.getModel().getElementAt(coordenadas.getModel().getSize() - 1));
		iniciar();
	    }

	});
    }

    /**
     * Pinta um retângulo em uma imagem
     * 
     * @param imagem
     *            a ser pintado os retângulos
     * @param coordenada
     *            do retângulo a ser pintado
     */
    private void pintarRetangulo(BufferedImage imagem, CoordenadaTempoVideo coordenada) {
	Graphics g = imagem.createGraphics();
	g.drawRect(coordenada.getCoordenadas()[0], coordenada.getCoordenadas()[1],
		coordenada.getCoordenadas()[2] - coordenada.getCoordenadas()[0],
		coordenada.getCoordenadas()[3] - coordenada.getCoordenadas()[1]);
    }

    @Override
    public void setMidia(String arquivoDeMidia) {
	this.mediaPlayerComponent.getMediaPlayer().playMedia(arquivoDeMidia);
	this.parar();
    }

    @Override
    public boolean parar() {
	this.mediaPlayerComponent.getMediaPlayer().pause();
	return true;
    }

    @Override
    public boolean iniciar() {
	this.mediaPlayerComponent.getMediaPlayer().play();
	return true;
    }

    @Override
    public JPanel getVideoPanel() {
	return this.videoSurface;
    }

    @Override
    public double getTempoTotal() {
	return this.mediaPlayerComponent.getMediaPlayer().getMediaMeta().getLength() / 1000;
    }

    @Override
    public double getTempoAtual() {
	return this.mediaPlayerComponent.getMediaPlayer().getTime() / 1000;

    }

    @Override
    public double getTempoRestante() {
	return getTempoTotal() - getTempoAtual();
    }

    @Override
    public void encerrar() {
	this.mediaPlayerComponent.getMediaPlayer().release();
    }

    @Override
    public JList<CoordenadaTempoVideo> getCoordenadas() {
	// TODO Auto-generated method stub
	return this.coordenadas;
    }

    public static void main(String[] args) {
	new NativeDiscovery().discover();
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		InterfacePlayer player = new VideoPlayerDetectavel(80, 60);

		player.setMidia("/media/israel/Backup/Arquivos/ce02.avi");
		JFrame f = new JFrame("teste");
		f.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
			player.encerrar();
			super.windowClosing(e);
		    }
		});
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(player.getVideoPanel(), BorderLayout.CENTER);
		f.getContentPane().add(new JScrollPane(player.getCoordenadas()), BorderLayout.EAST);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	    }
	});
    }
}
