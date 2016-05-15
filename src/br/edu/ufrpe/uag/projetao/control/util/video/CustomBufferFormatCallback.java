/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util.video;

import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

/**
 * @author israel
 *
 */
public class CustomBufferFormatCallback implements BufferFormatCallback {

    private int width;
    private int height;

    public CustomBufferFormatCallback(int width, int height) {
	this.width = width;
	this.height = height;
    }

    @Override
    public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
	return new RV32BufferFormat(width, height);
    }

}
