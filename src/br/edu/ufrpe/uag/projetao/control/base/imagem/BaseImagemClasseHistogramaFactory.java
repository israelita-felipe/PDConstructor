/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasseHistograma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * @author israel
 *
 */
public class BaseImagemClasseHistogramaFactory {

    public static BarChart getBaseImagemClasseHistograma() {

	final CategoryAxis xAxis = new CategoryAxis();
	final NumberAxis yAxis = new NumberAxis();
	final BarChart<String, Number> histograma = new BarChart<>(xAxis, yAxis);

	// buscando os dados no banco
	ObservableList<BaseImagemClasseHistograma> listagem = FXCollections
		.observableList(ControllerFactory.getBaseImagemClasseHistogramaController().getItems());

	XYChart.Series<String, Number> series = new XYChart.Series<>();

	series.setName("Classificações até o momento");

	for (BaseImagemClasseHistograma bich : listagem) {
	    System.out.println(bich);
	    series.getData().add(new XYChart.Data<>(bich.getTitulo(), bich.getTotal()));
	}

	xAxis.setLabel("Bases");
	yAxis.setLabel("Nº de Classificações");
	histograma.getData().add(series);

	return histograma;
    }

}
