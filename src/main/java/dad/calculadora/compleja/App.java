package dad.calculadora.compleja;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class App extends Application {

	private TextField real1Text, imag1Text, real2Text, imag2Text, realResultadoText, imagResultadoText;

	private VBox opBox, numBox;

	private HBox fila1Box, fila2Box, resultadosBox, root;

	private ComboBox<String> opComboBox;

	private ObjectProperty<Complejo> complejo1Property = new SimpleObjectProperty<Complejo>(new Complejo());
	private ObjectProperty<Complejo> complejo2Property = new SimpleObjectProperty<Complejo>(new Complejo());
	private ObjectProperty<Complejo> resultadoComplejo = new SimpleObjectProperty<Complejo>(new Complejo());

	private StringProperty operadorProperty = new SimpleStringProperty();

	private DoubleProperty real1Property = complejo1Property.get().realProperty();
	private DoubleProperty real2Property = complejo2Property.get().realProperty();
	private DoubleProperty imag1Property = complejo1Property.get().imaginarioProperty();
	private DoubleProperty imag2Property = complejo2Property.get().imaginarioProperty();
	private DoubleProperty resultadoRealProperty = resultadoComplejo.get().realProperty();
	private DoubleProperty resultadoImagProperty = resultadoComplejo.get().imaginarioProperty();

	@Override
	public void start(Stage primaryStage) throws Exception {

		opComboBox = new ComboBox<String>();
		opComboBox.getItems().addAll("+", "-", "*", "/");

		opBox = new VBox(5, opComboBox);
		opBox.setAlignment(Pos.CENTER_RIGHT);

		real1Text = new TextField("0");
		real1Text.setMaxWidth(50);
		real1Text.setAlignment(Pos.CENTER);

		imag1Text = new TextField("0");
		imag1Text.setMaxWidth(50);
		imag1Text.setAlignment(Pos.CENTER);

		real2Text = new TextField("0");
		real2Text.setMaxWidth(50);
		real2Text.setAlignment(Pos.CENTER);

		imag2Text = new TextField("0");
		imag2Text.setMaxWidth(50);
		imag2Text.setAlignment(Pos.CENTER);

		realResultadoText = new TextField("0");
		realResultadoText.setMaxWidth(50);
		realResultadoText.setAlignment(Pos.CENTER);
		realResultadoText.setDisable(true);

		imagResultadoText = new TextField("0");
		imagResultadoText.setMaxWidth(50);
		imagResultadoText.setAlignment(Pos.CENTER);
		imagResultadoText.setDisable(true);

		fila1Box = new HBox(5, real1Text, new Label("+"), imag1Text, new Label("i"));
		fila2Box = new HBox(5, real2Text, new Label("+"), imag2Text, new Label("i"));
		resultadosBox = new HBox(5, realResultadoText, new Label("+"), imagResultadoText, new Label("i"));

		numBox = new VBox(5, fila1Box, fila2Box, new Separator(), resultadosBox);
		numBox.setAlignment(Pos.CENTER);

		root = new HBox(5, opBox, numBox);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("CalculadoraCompleja");
		primaryStage.setScene(scene);
		primaryStage.show();

		real1Text.textProperty().bindBidirectional(real1Property, new NumberStringConverter());
		imag1Text.textProperty().bindBidirectional(imag1Property, new NumberStringConverter());

		real2Text.textProperty().bindBidirectional(real2Property, new NumberStringConverter());
		imag2Text.textProperty().bindBidirectional(imag2Property, new NumberStringConverter());

		realResultadoText.textProperty().bindBidirectional(resultadoRealProperty, new NumberStringConverter());
		imagResultadoText.textProperty().bindBidirectional(resultadoImagProperty, new NumberStringConverter());

		operadorProperty.bind(opComboBox.getSelectionModel().selectedItemProperty());

		operadorProperty.addListener((o, ov, nv) -> onOperatorChanged(nv));
	}

	private void onOperatorChanged(String a) {
		switch (a) {
		case "+":
			resultadoRealProperty.bind(real1Property.add(real2Property));
			resultadoImagProperty.bind(imag1Property.add(imag2Property));
			break;

		case "-":
			resultadoRealProperty.bind(real1Property.subtract(real2Property));
			resultadoImagProperty.bind(imag1Property.subtract(imag2Property));
			break;

		case "*":
			resultadoRealProperty
					.bind((real1Property.multiply(real2Property)).subtract(imag1Property.multiply(imag2Property)));
			resultadoImagProperty
					.bind((real1Property.multiply(imag2Property)).add(imag1Property.multiply(real2Property)));
			break;

		case "/":
			resultadoRealProperty
					.bind((real1Property.multiply(real2Property).add(imag1Property.multiply(imag2Property))).divide(
							(real2Property.multiply(real2Property)).add(imag2Property.multiply(imag2Property))));
			resultadoImagProperty
					.bind(imag1Property.multiply(real2Property.subtract(real1Property.multiply(imag2Property)).divide(
							(real2Property.multiply(real2Property)).add(imag2Property.multiply(imag2Property)))));
			break;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
