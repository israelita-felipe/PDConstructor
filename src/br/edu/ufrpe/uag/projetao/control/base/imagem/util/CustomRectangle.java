/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.util;

import br.edu.ufrpe.uag.projetao.model.DeteccaoImagem;
import javafx.scene.shape.Rectangle;

/**
 * @author Bruno Barros
 *
 */
public class CustomRectangle extends Rectangle {

    private DeteccaoImagem coordenada;

    /**
     * 
     * @param coordenada
     */
    public CustomRectangle(DeteccaoImagem coordenada) {
	super(coordenada.getX1(), coordenada.getY1(),
		(coordenada.getX2() - coordenada.getX1()) < 3 ? 3 : coordenada.getX2() - coordenada.getX1(),
		(coordenada.getY2() - coordenada.getY1()) < 3 ? 3 : coordenada.getY2() - coordenada.getY1());
	this.coordenada = coordenada;
    }

    public DeteccaoImagem getCoordenada() {
	return coordenada;
    }

    public void setCoordenada(DeteccaoImagem coordenada) {
	this.coordenada = coordenada;
    }
}
