/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * @author israel
 *
 */
public class Controles extends VBox {

    private Button mais;
    private Button menos;

    public Controles() {
	super();
	init();
    }

    private void init() {
	setSpacing(8);
	setPrefWidth(44);
	setMinWidth(44);
	setMaxWidth(44);
	setPadding(new Insets(8));
	setAlignment(Pos.TOP_CENTER);
	getStyleClass().add("card");

	mais = new Button();
	mais.setPrefSize(28, 28);
	mais.setMaxSize(28, 28);
	mais.setMinSize(28, 28);

	menos = new Button();
	menos.setPrefSize(28, 28);
	menos.setMaxSize(28, 28);
	menos.setMinSize(28, 28);

	getChildren().add(mais);
	getChildren().add(menos);

    }

    public Button getMais() {
	return mais;
    }

    public Button getMenos() {
	return menos;
    }
}
