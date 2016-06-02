/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.util;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
    private ZoomControles controles;

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
	
	this.controles = new ZoomControles();

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

	this.controles.getMais().setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		set(get() * 1.1);
	    }
	});
	this.controles.getMenos().setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		set(get() / 1.1);
	    }
	});
    }

    public ZoomControles getControles() {
	return controles;
    }
}
