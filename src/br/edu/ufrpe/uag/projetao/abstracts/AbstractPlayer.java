/**
 * 
 */
package br.edu.ufrpe.uag.projetao.abstracts;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import br.edu.ufrpe.uag.projetao.control.util.audio.AudioConverter;
import br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer;

/**
 * @author israel
 *
 */
public abstract class AbstractPlayer implements InterfacePlayer {

    private final File arquivoDeMedia;
    private Clip player;

    public static InterfacePlayer getAudioPlayer(File file) throws NoPlayerException, CannotRealizeException,
	    MalformedURLException, IOException, LineUnavailableException, UnsupportedAudioFileException {
	return new AbstractPlayer(file) {
	    @Override
	    void configure() throws LineUnavailableException, UnsupportedAudioFileException, IOException {

		AudioInputStream audio = AudioConverter
			.convertToPCM(AudioSystem.getAudioInputStream(getArquivoDeMedia()));

		// alocando o audio para ser tocado
		this.setPlayer(AudioSystem.getClip());
		this.getPlayer().open(audio);
	    }
	};
    }

    private AbstractPlayer(File arquivoDeMedia)
	    throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	this.arquivoDeMedia = arquivoDeMedia;
	configure();
    }

    abstract void configure() throws LineUnavailableException, UnsupportedAudioFileException, IOException;

    /*
     * (non-Javadoc)
     * 
     * @see br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer#iniciar()
     */
    @Override
    public synchronized boolean iniciar() {
	this.player.loop(0);
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer#parar()
     */
    @Override
    public synchronized boolean parar() {
	this.player.stop();
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer#getTempoRestante()
     */
    @Override
    public synchronized double getTempoRestante() {
	// return this.player.getDuration().getSeconds() -
	// this.player.getMediaTime().getSeconds();
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer#getTempoTotal()
     */
    @Override
    public synchronized double getTempoTotal() {
	// return this.player.getDuration().getSeconds();
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer#getTempoAtual()
     */
    @Override
    public synchronized double getTempoAtual() {
	// return this.player.getMediaTime().getSeconds();
	return 0;
    }

    Clip getPlayer() {
	return player;
    }

    void setPlayer(Clip player) {
	this.player = player;
    }

    File getArquivoDeMedia() {
	return arquivoDeMedia;
    }

    public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException,
	    NoPlayerException, CannotRealizeException {
	InterfacePlayer player = AbstractPlayer.getAudioPlayer(new File("reproduzir.mp3"));
	player.iniciar();

	JOptionPane.showMessageDialog(null, "teste");
	player.parar();
	JOptionPane.showMessageDialog(null, "teste2");
	player.iniciar();
	JOptionPane.showMessageDialog(null, "teste3");
    }
}
