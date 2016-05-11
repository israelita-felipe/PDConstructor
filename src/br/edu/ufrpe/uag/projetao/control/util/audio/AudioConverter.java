/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util.audio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 * @author israel
 *
 */
public class AudioConverter {

    /**
     * Converts an AudioInputStream to PCM_SIGNED format if it is not already
     * either PCM_SIGNED or PCM_UNSIGNED.
     */
    public static AudioInputStream convertToPCM(AudioInputStream audioInputStream) {
	AudioFormat format = audioInputStream.getFormat();

	if ((format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED)
		&& (format.getEncoding() != AudioFormat.Encoding.PCM_UNSIGNED)) {
	    AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16,
		    format.getChannels(), format.getChannels() * 2, format.getSampleRate(), format.isBigEndian());
	    audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
	}

	return audioInputStream;
    }

    public static boolean copiarArquivo(InputStream inputStream, OutputStream out) throws IOException {
	byte buf[] = new byte[1024];
	int len;

	while ((len = inputStream.read(buf)) != -1) {
	    out.write(buf, 0, len);
	}
	out.close();
	inputStream.close();

	return true;
    }

}
