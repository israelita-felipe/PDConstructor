/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.util;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;

/**
 * @author israel
 *
 */
public class ZoomnableImageView extends SimpleDoubleProperty {

    private final ImageView imageView;
    private final ScrollPane scroll;

    /**
     * 
     */
    public ZoomnableImageView(ImageView imageView, ScrollPane scroll, Double value) {
	super(value);
	this.imageView = imageView;
	this.scroll = scroll;
	init();
    }

    private void init() {
	this.scroll.setContent(this.imageView);

	addListener(new InvalidationListener() {
	    @Override
	    public void invalidated(Observable arg0) {
		imageView.setFitWidth(get() * 4);
		imageView.setFitHeight(get() * 3);
	    }
	});
	scroll.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
	    @Override
	    public void handle(ScrollEvent event) {
		if (event.getDeltaY() > 0) {
		    set(get() * 1.1);
		} else if (event.getDeltaY() < 0) {
		    set(get() / 1.1);
		}
	    }
	});
    }

}
