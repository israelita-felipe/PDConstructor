/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util.imagem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceComponenteListavel;
import br.edu.ufrpe.uag.projetao.view.listeners.RemoveDeListActionListener;
import br.edu.ufrpe.uag.projetao.view.util.GerenciadorDePopUp;

/**
 * @author israel
 *
 */
public class ImagemDetectavel extends MouseAdapter implements InterfaceComponenteListavel<Integer[]> {

    private JList<Integer[]> coordenadas;
    private JLabel label;

    private boolean fecharRetangulo;
    private int[][][] imagemMatriz;

    private Integer[] coordenada;

    public ImagemDetectavel(int[][][] imagemMatriz, JLabel label) {
	super();
	this.imagemMatriz = imagemMatriz.clone();
	this.label = label;
	init();
    }

    /**
     * Inicializa os componentes visuais
     */
    private void init() {
	this.coordenadas = new JList<Integer[]>(new DefaultListModel<Integer[]>());

	JPopupMenu pop = new JPopupMenu();
	JMenuItem removerMenuItem = new JMenuItem("Excluir");
	pop.add(removerMenuItem);
	GerenciadorDePopUp.addPopup(coordenadas, pop);

	// listener que remove e pinta novamente a imagem
	removerMenuItem.addActionListener(new RemoveDeListActionListener(getCoordenadas()) {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		gerarImage();
	    }
	});

	// gera a primeira imagem
	gerarImage();
    }

    private void gerarImage() {
	try {
	    this.label.setIcon(new ImageIcon(pintar()));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    @Override
    public void mousePressed(MouseEvent e) {
	super.mousePressed(e);

	coordenada = new Integer[4];
	coordenada[0] = e.getX();
	coordenada[1] = e.getY();

	fecharRetangulo = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	super.mouseReleased(e);
	if (fecharRetangulo) {

	    coordenada[2] = e.getX();
	    coordenada[3] = e.getY();

	    if (coordenada[2] < coordenada[0] && coordenada[3] < coordenada[1]) {
		// inverte as duas coordenadas
		int y1 = coordenada[1];
		int x1 = coordenada[2];

		// y1<-y2
		coordenada[1] = coordenada[3];
		// y2<-y1
		coordenada[3] = y1;

		// x1<-x2
		coordenada[0] = coordenada[2];
		// x2<-x1
		coordenada[2] = x1;

	    } else if (coordenada[2] > coordenada[0] && coordenada[3] < coordenada[1]) {

		int y1 = coordenada[1];
		// y1<-y2
		coordenada[1] = coordenada[3];
		// y2<-y1
		coordenada[3] = y1;

	    } else if (coordenada[0] > coordenada[2] && coordenada[3] > coordenada[1]) {

		int x1 = coordenada[2];
		// x1<-x2
		coordenada[0] = coordenada[2];
		// x2<-x1
		coordenada[2] = x1;

	    }

	    ((DefaultListModel<Integer[]>) coordenadas.getModel()).addElement(coordenada.clone());

	    coordenada = null;

	    fecharRetangulo = false;
	}
	gerarImage();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
	super.mouseDragged(e);
    }

    /**
     * Pinta os retângulos capturados
     * 
     * @param imagemMatriz
     *            matriz RGB que compõe a imagem
     * @return imagem pintada com os retângulos
     * @throws IOException
     */
    public BufferedImage pintar() throws IOException {
	int w = imagemMatriz[0].length;
	int h = imagemMatriz.length;
	ImagemDigital teste = new ImagemDigital(w, h);
	for (int y = 0; y < h; y++) {
	    for (int x = 0; x < w; x++) {
		// int i = y * h + x;
		teste.pintar(y, x, new Color(imagemMatriz[y][x][0], imagemMatriz[y][x][1], imagemMatriz[y][x][2]));
	    }
	}

	// pintando os retângulos
	for (int i = 0; i < coordenadas.getModel().getSize(); i++) {
	    Integer[] coordenada = coordenadas.getModel().getElementAt(i);
	    pintarRetangulo(teste.getBuffer(), coordenada);
	}
	return teste.getBuffer();
    }

    /**
     * Pinta um retângulo em uma imagem
     * 
     * @param imagem
     *            a ser pintado os retângulos
     * @param coordenada
     *            do retângulo a ser pintado
     */
    private void pintarRetangulo(BufferedImage imagem, Integer[] coordenada) {
	Graphics g = imagem.createGraphics();
	g.drawRect(coordenada[0], coordenada[1], coordenada[2] - coordenada[0], coordenada[3] - coordenada[1]);
    }

    public JList<Integer[]> getCoordenadas() {
	return coordenadas;
    }

    public static void main(String[] args) {

	JFrame frm = new JFrame("teste");
	JPanel pan = new JPanel();
	JLabel lbl = new JLabel();
	ImagemDetectavel cm = new ImagemDetectavel(ImagemDigital.carregarImagemCor("/home/israel/Downloads/DilermandoReis.jpg"),
		lbl);
	lbl.addMouseListener(cm);
	pan.add(lbl);
	frm.getContentPane().add(pan);
	frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frm.pack();
	Dimension dim = frm.getToolkit().getScreenSize();
	Rectangle abounds = frm.getBounds();
	frm.setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 3);
	frm.requestFocus();
	frm.setVisible(true);
    }
}
