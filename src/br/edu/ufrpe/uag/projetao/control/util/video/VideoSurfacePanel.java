/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util.video;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 
 * @author israel
 *
 */
public class VideoSurfacePanel extends JPanel {

    private BufferedImage image;

    public VideoSurfacePanel(int width, int height, BufferedImage image) {
	this.image = image;

	setBackground(Color.black);
	setOpaque(true);
	setPreferredSize(new Dimension(width, height));
	setMinimumSize(new Dimension(width, height));
	setMaximumSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	g2.drawImage(image, null, 0, 0);
    }

}
