/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util.video;

import java.awt.image.BufferedImage;

import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.RenderCallbackAdapter;

/**
 * @author israel
 *
 */
public class CustomRenderCallbackAdapter extends RenderCallbackAdapter {

    private int width;
    private int height;
    private BufferedImage image;
    private VideoSurfacePanel videoSurface;

    public CustomRenderCallbackAdapter(int width, int height, BufferedImage image, VideoSurfacePanel videoSurface) {
	super(new int[width * height]);
	this.width = width;
	this.height = height;
	this.image = image;
	this.videoSurface = videoSurface;
    }

    @Override
    protected void onDisplay(DirectMediaPlayer mediaPlayer, int[] rgbBuffer) {
	image.setRGB(0, 0, width, height, rgbBuffer, 0, width);
	videoSurface.repaint();
    }

}
