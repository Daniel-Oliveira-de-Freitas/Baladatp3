package application;

import java.io.File;
import java.io.IOException;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private static Stage stage;
	private static Scene main;
	private static Scene clientes;
	private static Scene produtos;
	private static Scene consumos;
	private static Gerenciamento gerenciamento;
	private static GerenciamentoProduto gerenciamentoProd;
	private static GerenciamentoConsumo gerenciamentoCons;
	private static File cliente;
	private static File produto;
	private static File consumo;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Balada System");
		stage.getIcons().add(new Image("file:imagens//dk.jpg"));
		Pane fxmlmain = (Pane) FXMLLoader.load(getClass().getResource("TelaMain.fxml"));
		main = new Scene(fxmlmain);

		TabPane fxmlcliente = (TabPane) FXMLLoader.load(getClass().getResource("ClienteTela.fxml"));
		clientes = new Scene(fxmlcliente);

		TabPane fxmlproduto = (TabPane) FXMLLoader.load(getClass().getResource("ProdutoTela.fxml"));
		produtos = new Scene(fxmlproduto);
		
		TabPane fxmlconsumo = (TabPane) FXMLLoader.load(getClass().getResource("ConsumoTela.fxml"));
		consumos = new Scene(fxmlconsumo);
		
		primaryStage.setScene(main);
		primaryStage.show();

	}

	public static void changeScene(String scr) {
		switch (scr) {
		case "mainScene":
			stage.setScene(main);
			break;
		case "cliente":
			stage.setScene(clientes);
			break;
		case "produto":
			stage.setScene(produtos);
			break;
		case "consumo":
			stage.setScene(consumos);
		}
	}
	
	public static Gerenciamento instancia() {
		if (gerenciamento==null) {
			gerenciamento = new Gerenciamento();
		}
		return gerenciamento;
	}
	public static File arquivoCliente() {
		if (cliente==null) {
			cliente  = new File("C:\\tmp\\cliente.txt");
		}
		return cliente;
	}
	
	public static GerenciamentoProduto instanciaProduto() {
		if (gerenciamentoProd==null) {
			gerenciamentoProd = new GerenciamentoProduto();
		}
		return gerenciamentoProd;
	}
	public static File arquivoProduto() {
		if (produto==null) {
			produto  = new File("C:\\tmp\\produto.txt");
		}
		return produto;
	}	
	
//CONSUMO
	public static GerenciamentoConsumo instanciaConsumo() {
		if (gerenciamentoCons==null) {
			gerenciamentoCons= new GerenciamentoConsumo();
		}
		return gerenciamentoCons;
	}
	
	public static File arquivoConsumo() {
		if (consumo ==null) {
			consumo  = new File("C:\\tmp\\Consumo.txt");
		}
		return consumo;
	}

	public static Object GerenciamentoConsumo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		launch(args);
	}




}
