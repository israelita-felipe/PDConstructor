/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.QueryFactory;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasseHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccaoHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.BaseTextoHistograma;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccaoHistograma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * @author israel
 *
 */
public class BaseGraficosFactory {

    /**
     * Cria um gráfico de barras resumidos por classe dado uma base de imagem
     * 
     * @param base
     *            a ser plotada
     * @return BarChart
     */
    public static BarChart getBaseImagemClasseHistograma(BaseImagemClasse base) {

	// criando os componentes do gráfico
	CategoryAxis xAxis = new CategoryAxis();
	NumberAxis yAxis = new NumberAxis();
	BarChart<String, Number> histograma = new BarChart<>(xAxis, yAxis);

	// buscando os dados no banco
	ObservableList<BaseImagemClasseHistograma> listagem = FXCollections
		.observableList(ControllerFactory.getBaseImagemClasseHistogramaController()
			.getItems(QueryFactory.getBaseImagemClasseHistogramaPorBase(base)));

	// criando uma série de dados
	XYChart.Series<String, Number> series = new XYChart.Series<>();
	series.setName(base.getTitulo());

	// preenchendo a série com as informações do banco
	for (BaseImagemClasseHistograma bich : listagem) {
	    series.getData().add(new XYChart.Data<>(bich.getClasse(), bich.getTotal()));
	}

	// colocando os títulos
	xAxis.setLabel("Classes");
	yAxis.setLabel("Nº de Classificações");

	// atribuindo a série ao gráfico
	histograma.getData().add(series);

	return histograma;
    }

    /**
     * Cria um gráfico de barras resumidos por classe dado uma base de texto
     * 
     * @param base
     *            a ser plotada
     * @return BarChart
     */
    public static BarChart getBaseTextoHistograma(BaseTexto base) {

	// criando os componentes do gráfico
	CategoryAxis xAxis = new CategoryAxis();
	NumberAxis yAxis = new NumberAxis();
	BarChart<String, Number> histograma = new BarChart<>(xAxis, yAxis);

	// buscando os dados no banco
	ObservableList<BaseTextoHistograma> listagem = FXCollections.observableList(ControllerFactory
		.getBaseTextoHistogramaController().getItems(QueryFactory.getBaseTextoHistogramaPorBase(base)));

	// criando uma série de dados
	XYChart.Series<String, Number> series = new XYChart.Series<>();
	series.setName(base.getTitulo());

	// preenchendo a série com as informações do banco
	for (BaseTextoHistograma bich : listagem) {
	    series.getData().add(new XYChart.Data<>(bich.getClasse(), bich.getTotal()));
	}

	// colocando os títulos
	xAxis.setLabel("Classes");
	yAxis.setLabel("Nº de Classificações");

	// atribuindo a série ao gráfico
	histograma.getData().add(series);

	return histograma;
    }

    /**
     * Cria um gráfico de barras resumidos por total de classificações por
     * imagem
     * 
     * @param base
     *            a ser plotada
     * @return BarChart
     */
    public static BarChart getBaseImagemDeteccaoHistograma(BaseImagemDeteccao base) {

	// criando os componentes do gráfico
	CategoryAxis xAxis = new CategoryAxis();
	NumberAxis yAxis = new NumberAxis();
	BarChart<String, Number> histograma = new BarChart<>(xAxis, yAxis);

	// buscando os dados no banco
	ObservableList<BaseImagemDeteccaoHistograma> listagem = FXCollections
		.observableList(ControllerFactory.getBaseImagemDeteccaoHistogramaController()
			.getItems(QueryFactory.getBaseImagemDeteccaoHistogramaPorBase(base)));

	// criando uma série de dados
	XYChart.Series<String, Number> series = new XYChart.Series<>();
	series.setName(base.getTitulo());

	// preenchendo a série com as informações do banco
	for (BaseImagemDeteccaoHistograma bich : listagem) {
	    series.getData().add(new XYChart.Data<>(String.valueOf(bich.getId()), bich.getTotal()));
	}

	// colocando os títulos
	xAxis.setLabel("Imagens");
	yAxis.setLabel("Nº de Detecções");

	// atribuindo a série ao gráfico
	histograma.getData().add(series);

	return histograma;
    }

    public static BarChart getBaseVideoDeteccaoHistograma(BaseVideoDeteccao base) {

	// criando os componentes do gráfico
	CategoryAxis xAxis = new CategoryAxis();
	NumberAxis yAxis = new NumberAxis();
	BarChart<String, Number> histograma = new BarChart<>(xAxis, yAxis);

	// buscando os dados no banco
	ObservableList<BaseVideoDeteccaoHistograma> listagem = FXCollections
		.observableList(ControllerFactory.getBaseVideoDeteccaoHistogramaController()
			.getItems(QueryFactory.getBaseVideoDeteccaoHistogramaPorBase(base)));

	// criando uma série de dados
	XYChart.Series<String, Number> series = new XYChart.Series<>();
	series.setName(base.getTitulo());

	// preenchendo a série com as informações do banco
	for (BaseVideoDeteccaoHistograma bich : listagem) {
	    series.getData().add(new XYChart.Data<>(String.valueOf(bich.getId()), bich.getTotal()));
	}

	// colocando os títulos
	xAxis.setLabel("Videos");
	yAxis.setLabel("Nº de Detecções");

	// atribuindo a série ao gráfico
	histograma.getData().add(series);

	return histograma;
    }

}
