/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceDeteccao;
import javafx.scene.shape.Rectangle;

/**
 * @author Bruno Barros
 *
 */
public class CustomRectangle extends Rectangle {

    private InterfaceDeteccao coordenada;

    /**
     * 
     * @param coordenada
     */
    public CustomRectangle(InterfaceDeteccao coordenada) {
	super(coordenada.getX1(), coordenada.getY1(),
		(coordenada.getX2() - coordenada.getX1()) < 3 ? 3 : coordenada.getX2() - coordenada.getX1(),
		(coordenada.getY2() - coordenada.getY1()) < 3 ? 3 : coordenada.getY2() - coordenada.getY1());
	this.coordenada = coordenada;
    }

    public InterfaceDeteccao getCoordenada() {
	return coordenada;
    }

    public void setCoordenada(InterfaceDeteccao coordenada) {
	this.coordenada = coordenada;
    }
}
