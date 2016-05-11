/**
 * 
 */
package br.edu.ufrpe.uag.projetao.abstracts;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.media.CannotRealizeException;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.format.AudioFormat;
import javax.media.pim.PlugInManager;
import javax.swing.JOptionPane;

import br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer;

/**
 * @author israel
 *
 */
public abstract class AbstractMediaPlayer implements InterfacePlayer {

    private Player player;
    private File arquivoDeMidia;

    /**
     * Cria um player de áudio
     * 
     * @param arquivoDeMidia
     *            arquivo mp3 a ser reproduzido
     * @return InterfacePlayer de reprodução de áudio
     * @throws NoPlayerException
     * @throws MalformedURLException
     * @throws IOException
     * @throws CannotRealizeException
     */
    public static InterfacePlayer getAudioPlayer(File arquivoDeMidia)
	    throws NoPlayerException, MalformedURLException, IOException, CannotRealizeException {
	InterfacePlayer player = new AbstractMediaPlayer(arquivoDeMidia) {

	    @Override
	    void configure() throws NoPlayerException, MalformedURLException, IOException {
		setPlayer(Manager.createPlayer(arquivoDeMidia.toURI().toURL()));
	    }
	};
	return player;
    }

    /**
     * Cria um player de vídeo
     * 
     * @param arquivoDeMidia
     * @return InterfacePlayer de reprodução de vídeo
     * @throws NoPlayerException
     * @throws MalformedURLException
     * @throws IOException
     * @throws CannotRealizeException
     */
    public static InterfacePlayer getVideoPlayer(File arquivoDeMidia)
	    throws NoPlayerException, MalformedURLException, IOException, CannotRealizeException {
	InterfacePlayer player = new AbstractMediaPlayer(arquivoDeMidia) {

	    @Override
	    void configure() throws NoPlayerException, MalformedURLException, IOException, CannotRealizeException {
		setPlayer(Manager.createRealizedPlayer(new MediaLocator(arquivoDeMidia.toURI().toURL())));
	    }
	};
	return player;
    }

    /**
     * Cria um player para reprodução de mídia, sendo necessário implementar o
     * método <code>configure()</code>
     * 
     * @param arquivoDeMidia
     *            arquivo a ser reproduzido
     * @throws NoPlayerException
     * @throws MalformedURLException
     * @throws IOException
     * @throws CannotRealizeException
     */
    private AbstractMediaPlayer(File arquivoDeMidia)
	    throws NoPlayerException, MalformedURLException, IOException, CannotRealizeException {
	this.arquivoDeMidia = arquivoDeMidia;
	PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder",
		new Format[] { new AudioFormat(AudioFormat.MPEGLAYER3), new AudioFormat(AudioFormat.MPEG) },
		new Format[] { new AudioFormat(AudioFormat.LINEAR) }, PlugInManager.CODEC);
	configure();
    }

    /**
     * Método que configura o tipo de mídia a ser criado um player para
     * reproduzí-la
     * 
     * @throws NoPlayerException
     * @throws MalformedURLException
     * @throws IOException
     * @throws CannotRealizeException
     */
    abstract void configure() throws NoPlayerException, MalformedURLException, IOException, CannotRealizeException;

    /*
     * (non-Javadoc)
     * 
     * @see br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer#iniciar()
     */
    @Override
    public boolean iniciar() {
	this.player.start();
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer#parar()
     */
    @Override
    public boolean parar() {
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
    public double getTempoRestante() {
	return this.player.getDuration().getSeconds() - this.player.getMediaTime().getNanoseconds();
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer#getTempoTotal()
     */
    @Override
    public double getTempoTotal() {
	return this.player.getDuration().getSeconds();
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.edu.ufrpe.uag.projetao.interfaces.InterfacePlayer#getTempoAtual()
     */
    @Override
    public double getTempoAtual() {
	// TODO Auto-generated method stub
	return this.player.getMediaTime().getNanoseconds();
    }

    protected void setPlayer(Player player) {
	this.player = player;
    }

    public static void main(String[] args) {
	try {
	    InterfacePlayer audio = AbstractMediaPlayer.getAudioPlayer(new File("reproduzir.mp3"));
	    audio.iniciar();
	    JOptionPane.showMessageDialog(null, "tocando");
	    audio.parar();
	    JOptionPane.showMessageDialog(null, "parado");
	    audio.iniciar();
	    JOptionPane.showMessageDialog(null, "tocando");
	} catch (NoPlayerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (MalformedURLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (CannotRealizeException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
