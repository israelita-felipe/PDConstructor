/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * @author israel
 *
 */
public class ControlesDecteccao extends VBox {

    private ToggleButton selecao;
    private ToggleButton deteccao;

    /**
     * 
     */
    public ControlesDecteccao() {
	super(2);
	init();
    }

    private void init() {
	this.selecao = new ToggleButton();
	this.deteccao = new ToggleButton();

	ToggleGroup group = new ToggleGroup();
	this.selecao.setToggleGroup(group);
	this.deteccao.setToggleGroup(group);

	getChildren().add(deteccao);
	getChildren().add(selecao);
    }

    public ToggleButton getSelecao() {
	return selecao;
    }

    public ToggleButton getDeteccao() {
	return deteccao;
    }

    public boolean isDeteccao() {
	return deteccao.isArmed();
    }

    public boolean isSelecao() {
	return selecao.isArmed();
    }

}
