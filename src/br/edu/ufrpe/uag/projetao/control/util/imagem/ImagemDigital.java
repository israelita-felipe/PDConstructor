package br.edu.ufrpe.uag.projetao.control.util.imagem;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagemDigital {

    public static BufferedImage carregarImagemCor(String nomeArquivo) {
	try {
	    return ImageIO.read(new File(nomeArquivo));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    public static void salvarImagemCorPNG(BufferedImage imagem, String enderecoImagem) {
	try {
	    ImageIO.write(imagem, "png", new File(enderecoImagem));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public static BufferedImage toImage(byte[] b) {
	try {
	    return ImageIO.read(new ByteArrayInputStream(b));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

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

}
