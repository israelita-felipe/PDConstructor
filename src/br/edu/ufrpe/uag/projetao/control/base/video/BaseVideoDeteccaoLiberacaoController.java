package br.edu.ufrpe.uag.projetao.control.base.video;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.kairos.components.TextInputLayout;
import org.kairos.core.Fragment;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.util.Controles;
import br.edu.ufrpe.uag.projetao.control.base.imagem.util.CustomRectangle;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.util.video.VideoDigital;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.DeteccaoVideo;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseVideoDeteccao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Callback;

public class BaseVideoDeteccaoLiberacaoController extends Fragment {
    private FXMLLoader loader;

    @FXML
    private TableView<LiberacaoBaseVideoDeteccao> tabelaLiberacoesVideoDeteccao;
    @FXML
    private Pagination pagination;
    @FXML
    private List<AlocacaoVideoDeteccao> alocacoes;

    private MediaPlayer mp;

    private ListView<DeteccaoVideo> deteccoes;

    private AlocacaoVideoDeteccao alocacaoAtual;

    private InterfaceController<DeteccaoVideo> deteccaoController = ControllerFactory.getDeteccaoVideoController();

    private Group group = new Group();

    @Override
    public void onCreateView(FXMLLoader fxmlLoader) {
	this.loader = fxmlLoader;
	list();
    }

    @FXML
    private void list() {
	getChildren().clear();
	this.loader.setLocation(getClass()
		.getResource("/br/edu/ufrpe/uag/projetao/view/base/video/BaseVideoDeteccaoLiberacaoView.fxml"));
	try {
	    this.loader.load();
	    this.tabelaLiberacoesVideoDeteccao.setItems(FXCollections.observableList(ControllerFactory
		    .getLiberacaoBaseVideoDeteccaoController().getItemsFromCriteria(DetachedCriteriaFactory
			    .getLiberacoesBaseVideoDeteccaoDoEscravo(UsuarioController.currentEscravo))));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Cria uma página para detecção dado o índice da alocação
     * 
     * @param PageIndex
     *            número da página e da alocação ao qual será criando uma tela
     *            de detecção
     * @return Página para ser adicionada no paginador
     * @throws IOException
     */
    private Node createPage(int pageIndex) throws IOException {
	removeMidiasAntigas();
	// cria as caixas de armazenamento dos componentes visuais
	BorderPane borderPane = new BorderPane();
	borderPane.setPadding(new Insets(8));

	// preenchendo as detecções
	this.deteccoes = new ListView<>(
		FXCollections
			.observableList(
				new LinkedList<>(ControllerFactory.getDeteccaoVideoController()
					.getItemsFromCriteria(DetachedCriteriaFactory
						.getDeteccaoVideoPorEscravoEAlocacao(UsuarioController.currentEscravo,
							this.alocacoes.get(pageIndex))))));

	// cria caixa de descrição da base
	TextInputLayout descricaoLayout = new TextInputLayout();
	descricaoLayout.setPadding(new Insets(16));
	TextArea descricaoTextArea = new TextArea();
	descricaoTextArea.setEditable(false);
	descricaoTextArea.setWrapText(true);
	descricaoTextArea.setText(this.alocacoes.get(pageIndex).getBaseVideoDeteccao().getDescricao());
	descricaoTextArea.setPromptText("Descrição da base");
	descricaoLayout.getChildren().add(descricaoTextArea);
	descricaoLayout.setMaxHeight(80);
	descricaoLayout.setMinHeight(80);
	descricaoLayout.setPrefHeight(80);
	borderPane.setTop(descricaoLayout);

	alocacaoAtual = this.alocacoes.get(pageIndex);
	// cria caixa de texto a classificar
	MediaView mediaView = new MediaView();
	Media media = new Media(
		"file://" + VideoDigital.toVideo(this.alocacoes.get(pageIndex).getVideoDeteccao().getObjeto(),
			this.alocacoes.get(pageIndex).getVideoDeteccao().toString()).getAbsolutePath());
	mp = new MediaPlayer(media);
	mediaView.setMediaPlayer(mp);
	mediaView.preserveRatioProperty().set(true);

	ScrollPane scroll = new ScrollPane();
	BorderPane center = new BorderPane();
	center.getStyleClass().add("card");
	center.setPadding(new Insets(8));
	center.setCenter(scroll);
	borderPane.setCenter(center);

	Controles c = new Controles();	
	addControlePlayer(c);
	center.setRight(c);

	scroll.setContent(group);

	addComportamentoItentificacao(mediaView);

	gerarImagem(mediaView);
	return borderPane;
    }

    private void addControlePlayer(Controles controle) {

	controle.getMais().setTooltip(new Tooltip("Play"));
	controle.getMenos().setTooltip(new Tooltip("Pause"));

	controle.getMais().setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		mp.play();
	    }
	});

	controle.getMenos().setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		mp.pause();
	    }
	});
    }

    /**
     * Remove as mídias antigas para não gerar espaço extra em disco
     */
    private void removeMidiasAntigas() {
	File f = new File("media");

	File[] files = f.listFiles();
	if (files != null) {
	    for (File file : files) {
		file.delete();
	    }
	}

    }

    /**
     * Comportamento dos ítens, ações e ações atribuídas a cada componente
     * 
     * @param mediaView
     */
    private void addComportamentoItentificacao(MediaView mediaView) {

	mediaView.setOnMousePressed(new EventHandler<MouseEvent>() {

	    @Override
	    public void handle(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {

		    TransactionManager.begin();
		    deteccaoController.prepareCreate();

		    deteccaoController.getSelected().setAlocacaoVideoDeteccao(alocacaoAtual);
		    deteccaoController.getSelected().setUsuario(UsuarioController.currentEscravo);
		    deteccaoController.getSelected().setX1(event.getX());
		    deteccaoController.getSelected().setY1(event.getY());
		    deteccaoController.getSelected().setTempo(mp.getCurrentTime().toSeconds());
		}

	    }
	});

	mediaView.setOnMouseReleased(new EventHandler<MouseEvent>() {

	    @Override
	    public void handle(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
		    deteccaoController.getSelected().setX2(event.getX());
		    deteccaoController.getSelected().setY2(event.getY());

		    if (deteccaoController.getSelected().getX2() < deteccaoController.getSelected().getX1()
			    && deteccaoController.getSelected().getY2() < deteccaoController.getSelected().getY1()) {
			// inverte as duas coordenadas
			Double y1 = deteccaoController.getSelected().getY1();
			Double x1 = deteccaoController.getSelected().getX1();

			// y1<-y2
			deteccaoController.getSelected().setY1(deteccaoController.getSelected().getY2());
			// y2<-y1
			deteccaoController.getSelected().setY2(y1);

			// x1<-x2
			deteccaoController.getSelected().setX1(deteccaoController.getSelected().getX2());
			// x2<-x1
			deteccaoController.getSelected().setX2(x1);

		    } else if (deteccaoController.getSelected().getX2() > deteccaoController.getSelected().getX1()
			    && deteccaoController.getSelected().getY2() < deteccaoController.getSelected().getY1()) {

			Double y1 = deteccaoController.getSelected().getY1();
			// y1<-y2
			deteccaoController.getSelected().setY1(deteccaoController.getSelected().getY2());
			// y2<-y1
			deteccaoController.getSelected().setY2(y1);

		    } else if (deteccaoController.getSelected().getX1() > deteccaoController.getSelected().getX2()
			    && deteccaoController.getSelected().getY2() > deteccaoController.getSelected().getY1()) {

			Double x1 = deteccaoController.getSelected().getX1();
			// x1<-x2
			deteccaoController.getSelected().setX1(deteccaoController.getSelected().getX2());
			// x2<-x1
			deteccaoController.getSelected().setX2(x1);
		    }

		    deteccaoController.create();
		    TransactionManager.end();

		    // preenchendo as detecções
		    deteccoes.setItems(
			    FXCollections.observableList(new LinkedList<>(ControllerFactory.getDeteccaoVideoController()
				    .getItemsFromCriteria(DetachedCriteriaFactory.getDeteccaoVideoPorEscravoEAlocacao(
					    UsuarioController.currentEscravo, alocacaoAtual)))));
		    group.getChildren().clear();
		    deteccoes.refresh();
		    gerarImagem(mediaView);
		}

	    }
	});

    }

    /**
     * Gera uma imagem
     * 
     * @param imagemView
     */
    private void gerarImagem(MediaView imagemView) {

	group.getChildren().clear();
	group.getChildren().add(imagemView);

	// pintando os retângulos
	for (int i = 0; i < deteccoes.getItems().size(); i++) {
	    DeteccaoVideo coordenada = deteccoes.getItems().get(i);

	    CustomRectangle r = new CustomRectangle(coordenada);

	    r.setStroke(Color.BLUE);
	    r.setStrokeWidth(1);
	    r.setStrokeLineCap(StrokeLineCap.ROUND);
	    r.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.2));

	    group.getChildren().add(r);

	    ContextMenu cm = new ContextMenu();
	    MenuItem removeMenuItem = new MenuItem("Remover");
	    cm.getItems().add(removeMenuItem);
	    // cm.setAutoHide(false);
	    removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
		    removeDeteccao(coordenada);
		    group.getChildren().remove(r);
		    gerarImagem(imagemView);
		}
	    });

	    r.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
		    if (e.getButton() == MouseButton.SECONDARY)
			cm.show(r, e.getScreenX(), e.getScreenY());
		}
	    });

	    r.setOnMouseEntered(new EventHandler<Event>() {
		@Override
		public void handle(Event event) {
		    r.setStroke(Color.RED);
		    r.setFill(Color.LIGHTCORAL.deriveColor(0, 1.2, 1, 0.2));
		}
	    });

	    r.setOnMouseExited(new EventHandler<Event>() {
		@Override
		public void handle(Event event) {
		    r.setStroke(Color.BLUE);
		    r.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.2));
		}
	    });
	}
    }

    /**
     * Remove uma detecção selecionada
     */
    private void removeDeteccao(DeteccaoVideo deteccao) {

	group.getChildren().clear();
	InterfaceController<DeteccaoVideo> deteccaoVideoController = ControllerFactory.getDeteccaoVideoController();

	TransactionManager.begin();
	deteccaoVideoController.destroy(deteccao);
	TransactionManager.end();
	this.deteccoes.getItems().remove(deteccao);
	this.deteccoes.refresh();
    }

    @FXML
    private void paginator() {
	LiberacaoBaseVideoDeteccao liberacao = tabelaLiberacoesVideoDeteccao.getSelectionModel().getSelectedItem();

	if (liberacao != null) {
	    getChildren().clear();

	    this.loader.setLocation(getClass().getResource(
		    "/br/edu/ufrpe/uag/projetao/view/base/video/classificacao/BaseVideoDeteccaoView.fxml"));

	    try {
		this.loader.load();

		this.alocacoes = ControllerFactory.getAlocacaoVideoDeteccaoController()
			.getItemsFromCriteria(DetachedCriteriaFactory.getAlocacoesVideoDeteccaoPorLiberacao(
				this.tabelaLiberacoesVideoDeteccao.getSelectionModel().getSelectedItem()));

		pagination.setPageFactory(new Callback<Integer, Node>() {

		    @Override
		    public Node call(Integer pageIndex) {
			try {
			    return createPage(pageIndex);
			} catch (IOException ex) {
			    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
			    dialogoErro.setTitle("Não foi possível fazer...");
			    dialogoErro.setHeaderText("Algo saiu errado");
			    dialogoErro.setContentText(
				    "Não foi possível criar a imagem para detecção, por favor tente novamente");

			    // Create expandable Exception.
			    StringWriter sw = new StringWriter();
			    PrintWriter pw = new PrintWriter(sw);
			    ex.printStackTrace(pw);
			    String exceptionText = sw.toString();

			    Label label = new Label("The exception stacktrace was:");

			    TextArea textArea = new TextArea(exceptionText);
			    textArea.setEditable(false);
			    textArea.setWrapText(true);

			    textArea.setMaxWidth(Double.MAX_VALUE);
			    textArea.setMaxHeight(Double.MAX_VALUE);
			    GridPane.setVgrow(textArea, Priority.ALWAYS);
			    GridPane.setHgrow(textArea, Priority.ALWAYS);

			    GridPane expContent = new GridPane();
			    expContent.setMaxWidth(Double.MAX_VALUE);
			    expContent.add(label, 0, 0);
			    expContent.add(textArea, 0, 1);

			    // Set expandable Exception into the dialog pane.
			    dialogoErro.getDialogPane().setExpandableContent(expContent);

			    dialogoErro.showAndWait();
			}
			return null;
		    }
		});

		pagination.setPageCount(alocacoes.size());
		pagination.setCurrentPageIndex(buscaPrimeiraAlocacaoSemDeteccao());

	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
	    dialogoErro.setTitle("Informação");
	    dialogoErro.setHeaderText("Seleção da base");
	    dialogoErro.setContentText("Selecione uma base para poder classificar");
	    dialogoErro.showAndWait();
	    list();
	}
    }

    /**
     * Ordena as alocações de forma que as com menos detecções fiquem primeiro
     */
    public void sortAlocacoes() {
	Collections.sort(alocacoes, new Comparator<AlocacaoVideoDeteccao>() {
	    @Override
	    public int compare(AlocacaoVideoDeteccao o1, AlocacaoVideoDeteccao o2) {
		return o1.getDeteccaoVideos().size() - o2.getDeteccaoVideos().size();
	    }
	});
    }

    public int buscaPrimeiraAlocacaoSemDeteccao() {
	sortAlocacoes();
	return 0;
    }

    public DeteccaoVideo selecionaDeteccao() {

	return null;
    }
}
