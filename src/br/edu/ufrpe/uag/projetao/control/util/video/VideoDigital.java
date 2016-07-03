/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util.video;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author israel
 *
 */
public class VideoDigital {

    /**
     * Converte um arquivo de vídeo em um array de bytes
     * 
     * @param video
     *            do tipo <code>File</code>
     * @return array de bytes do vídeo
     * @throws IOException
     */
    public static byte[] toByte(File video) throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	FileInputStream fis = new FileInputStream(video);

	byte[] buf = new byte[1024];
	int n;
	while (-1 != (n = fis.read(buf))) {
	    baos.write(buf, 0, n);
	}
	return baos.toByteArray();
    }

    /**
     * Converte um array de bytes em um arquivo de vídeo
     * 
     * @param video
     *            array de bytes a ser convertido
     * @param nome
     *            nome do arquivo a ser gravado em disco
     * @return arquivo de vídeo
     * @throws IOException
     */
    public static File toVideo(byte[] video, String nome) throws IOException {
	File arquivo = new File("media/" + nome + ".pdc");
	FileOutputStream fos = new FileOutputStream(arquivo);
	fos.write(video);
	fos.flush();
	fos.close();
	return arquivo;
    }

    public static void main(String[] args) {
	try {
	    byte[] b = toByte(new File("/media/israel/Backup/Arquivos/Vídeos/SERIES/ce01.avi"));
	    toVideo(b, "teste.avi");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
