/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

/**
 * @author israel
 *
 */
public class ZoomControles extends VBox {

    private Button mais;
    private Button menos;

    public ZoomControles() {
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
	mais.setText("+");
	mais.setPrefSize(28, 28);
	mais.setMaxSize(28, 28);
	mais.setMinSize(28, 28);
	mais.setTooltip(new Tooltip("Mais zoom"));

	menos = new Button();
	menos.setText("-");
	menos.setPrefSize(28, 28);
	menos.setMaxSize(28, 28);
	menos.setMinSize(28, 28);
	menos.setTooltip(new Tooltip("Menos zoom"));

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
