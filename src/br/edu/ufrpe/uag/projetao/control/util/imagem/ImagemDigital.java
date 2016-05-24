package br.edu.ufrpe.uag.projetao.control.util.imagem;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagemDigital {

    private static int width = 400;
    private static int height = 400;

    /**
     * Carrega uma imagem de um arquivo
     * 
     * @param nomeArquivo
     * @return BufferedImage do arquivo de imagem
     */
    public static BufferedImage carregarImagemCor(String nomeArquivo) {
	try {
	    return ImageIO.read(new File(nomeArquivo));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * Grava uma buffered image em um arquivo no formato png
     * 
     * @param imagem
     * @param enderecoImagem
     */
    public static void salvarImagemCorPNG(BufferedImage imagem, String enderecoImagem) {
	try {
	    ImageIO.write(imagem, "png", new File(enderecoImagem));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Converte um array de bytes em um BufferedImage
     * 
     * @param b
     * @return
     */
    public static BufferedImage toImage(byte[] b) {
	try {
	    return ImageIO.read(new ByteArrayInputStream(b));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * Converte uma BufferdImage para um array de bytes
     * 
     * @param image
     * @return
     */
    public static byte[] toByte(BufferedImage image) {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	try {
	    ImageIO.write(image, "png", baos);
	    baos.flush();
	    byte[] imageInByte = baos.toByteArray();
	    baos.close();
	    return imageInByte;
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * Recebe um BufferedImage e retorna uma BufferedImage redimensionada para
     * os novos tamanhos
     * 
     * @param image
     * @param newWidth
     * @param newHeight
     * @return BufferedImage redimensionada
     */
    public static BufferedImage resize(BufferedImage image, int newWidth, int newHeight) {
	BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
	Graphics2D g2 = resizedImg.createGraphics();
	g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g2.drawImage(image, 0, 0, newWidth, newHeight, null);
	g2.dispose();
	return resizedImg;
    }

    /**
     * @return the width
     */
    public static int getWidth() {
	return width;
    }

    /**
     * @return the height
     */
    public static int getHeight() {
	return height;
    }

}
