package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class produtoController {
	
	private static long idCounter = 1001;
	
	@FXML
	private TextField barradepesquisa;
	
	@FXML
	private TableView<Produto> tabelaproduto;
	@FXML
	private Pane alterarProdutoTela;
	@FXML
	private TableColumn<String, Produto> codt;
	@FXML
	private TableColumn<String, Produto> nomet;
	@FXML
	private TableColumn<String, Produto> desct;
	@FXML
	private TableColumn<String, Produto> quantt;
	@FXML
	private TableColumn<String, Produto> pesot;
	@FXML
	private TableColumn<String, Produto> dtcompt;
	@FXML
	private TableColumn<String, Produto> dtvalt;
	@FXML
	private TableColumn<String, Produto> nft;
	@FXML
	private TableColumn<String, Produto> fornecedort;
	@FXML
	private TextField  Aquantidade, Apeso, Adatacompra, Adatavalidade, Avalorunitcomp, Avalortotcomp, Avalorunitvenda, Avalortotvenda,Adescricao,Anotafiscal;
	@FXML
	private Tab telaincluirProduto, telaConsultarProduto;
	@FXML
	private TextField lblnome, lbldescProd, lblquant, lblfornecedor, lblcnpj, lblcategoria, lblnf,
			lblpeso, lblvlunitcomp, lblvltotcomp, lblvlunitvend, lblvltotvend, lbldtcomp, lbldtval;
	@FXML
	private Label lblcod, Acod , Anome,Afornecedor;

	public void acaoSalvar() {
		try {
			cadastroProduto();
		} catch (Exception e) {
			System.out.println(e);
			alertMessage("Tipo invalido");
		}
	}

	public void acaobotaovoltar() {
		Main.changeScene("mainScene");

	}

	public void acaobotaovoltarT() {
		alterarProdutoTela.setVisible(false);

	}

	
	public void acaobotaogeracod() {
	 
		//String currentCounter = String.valueOf(atomicCounter.getAndIncrement());
       
		lblcod.setText(String.valueOf(idCounter++));
		
		}
	
	public void cadastroProduto() throws FileNotFoundException, IOException {
		String cod, nome, descProd, quant, fornecedor, cnpj, categoria, nf, dataComp, dataVal;
		String peso;
		String valorUniComp, valorTotComp, valorUniVend, valorTotVend;
		if (!lblcod.getText().isEmpty() && !lblnome.getText().isEmpty() && !lbldescProd.getText().isEmpty()
				&& !lblquant.getText().isEmpty() && !lblfornecedor.getText().isEmpty() && !lblcnpj.getText().isEmpty()
				&& !lblcategoria.getText().isEmpty() && !lblnf.getText().isEmpty() && !lbldtcomp.getText().isEmpty()
				&& !lbldtval.getText().isEmpty() && !lblpeso.getText().isEmpty() && !lblvlunitcomp.getText().isEmpty()
				&& !lblvltotcomp.getText().isEmpty() && !lblvlunitvend.getText().isEmpty()
				&& !lblvltotvend.getText().isEmpty()) {
			cod = lblcod.getText();
			nome = lblnome.getText();
			descProd = lbldescProd.getText();
			quant = lblquant.getText();
			fornecedor = lblfornecedor.getText();
			cnpj = lblcnpj.getText();
			categoria = lblcategoria.getText();
			nf = lblnf.getText();
			dataComp = lbldtcomp.getText();
			dataVal = lbldtval.getText();
			peso = lblpeso.getText();
			valorUniComp = lblvlunitcomp.getText();
			valorTotComp = lblvltotcomp.getText();
			valorUniVend = lblvlunitvend.getText();
			valorTotVend = lblvltotvend.getText();
			Produto p = new Produto(cod, nome, descProd, quant, fornecedor, cnpj, categoria, nf, dataComp, dataVal,
					peso, valorUniComp, valorTotComp, valorUniVend, valorTotVend);
			Main.instanciaProduto().AdicionarProduto(p);
			Main.instanciaProduto().salvarProduto();
			alertMessage("Produto cadastrado com sucesso");
			acaobotaovoltar();
			limpacampos();
			System.out.println(p);
		} else {
			alertMessage("Campos Obrigatorios vazios");
		}
	}

	public void alertMessage(String m) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atenção");
		alert.setContentText(m);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	public void tabelaProduto() {
		String codp, nomep, descProdp, quantp, fornecedorp, cnpjp, categoriap, nfp, dataCompp, dataValp, pesop,
				valorUniCompp, valorTotCompp, valorUniVendp, valorTotVendp, linha;

		ObservableList<Produto> itemsproduto = FXCollections.observableArrayList();
		try {
			FileReader fr = new FileReader(Main.arquivoProduto());
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				linha = br.readLine();
				String dados[] = linha.split(";");
				codp = dados[0];
				nomep = dados[1];
				descProdp = dados[2];
				quantp = dados[3];
				fornecedorp = dados[4];
				cnpjp = dados[5];
				categoriap = dados[6];
				nfp = dados[7];
				dataCompp = dados[8];
				dataValp = dados[9];
				pesop = dados[10];
				valorUniCompp = dados[11];
				valorTotCompp = dados[12];
				valorUniVendp = dados[13];
				valorTotVendp = dados[14];
				itemsproduto.add(new Produto(codp, nomep, descProdp, quantp, fornecedorp, cnpjp, categoriap, nfp,
						dataCompp, dataValp, pesop, valorUniCompp, valorTotCompp, valorUniVendp, valorTotVendp));
			}
		} catch (Exception e) {
			alertMessage("Erro na tabela produto");
			System.out.println(e);
		}
		tabelaproduto.setItems(itemsproduto);
		codt.setCellValueFactory(new PropertyValueFactory<String, Produto>("cod"));
		nomet.setCellValueFactory(new PropertyValueFactory<String, Produto>("nome"));
		desct.setCellValueFactory(new PropertyValueFactory<String, Produto>("descProd"));
		quantt.setCellValueFactory(new PropertyValueFactory<String, Produto>("quant"));
		pesot.setCellValueFactory(new PropertyValueFactory<String, Produto>("peso"));
		dtcompt.setCellValueFactory(new PropertyValueFactory<String, Produto>("dataComp"));
		dtvalt.setCellValueFactory(new PropertyValueFactory<String, Produto>("dataVal"));
		nft.setCellValueFactory(new PropertyValueFactory<String, Produto>("nf"));
		fornecedort.setCellValueFactory(new PropertyValueFactory<String, Produto>("fornecedor"));
		tabelaproduto.refresh();
	}

	public void limpacampos() {
		lblcod.setText("");
		lblnome.setText("");
		lbldescProd.setText("");
		lblquant.setText("");
		lblfornecedor.setText("");
		lblcnpj.setText("");
		lblcategoria.setText("");
		lblnf.setText("");
		lbldtcomp.setText("");
		lbldtval.setText("");
		lblpeso.setText("");
		lblvlunitcomp.setText("");
		lblvltotcomp.setText("");
		lblvlunitvend.setText("");
		lblvltotvend.setText("");
	}

	public void pesquisaProduto() {
		String codp, nomep, descProdp, quantp, fornecedorp, cnpjp, categoriap, nfp, dataCompp, dataValp, pesop,
				valorUniCompp, valorTotCompp, valorUniVendp, valorTotVendp, linha;

		ObservableList<Produto> itemsproduto = FXCollections.observableArrayList();
		try {
			FileReader fr = new FileReader(Main.arquivoProduto());
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				linha = br.readLine();
				String dados[] = linha.split(";");
				codp = dados[0];
				nomep = dados[1];
				descProdp = dados[2];
				quantp = dados[3];
				fornecedorp = dados[4];
				cnpjp = dados[5];
				categoriap = dados[6];
				nfp = dados[7];
				dataCompp = dados[8];
				dataValp = dados[9];
				pesop = dados[10];
				valorUniCompp = dados[11];
				valorTotCompp = dados[12];
				valorUniVendp = dados[13];
				valorTotVendp = dados[14];
				itemsproduto.add(new Produto(codp, nomep, descProdp, quantp, fornecedorp, cnpjp, categoriap, nfp,
						dataCompp, dataValp, pesop, valorUniCompp, valorTotCompp, valorUniVendp, valorTotVendp));
			}
		} catch (Exception e) {
			alertMessage("Erro na tabela produto");
			System.out.println(e);
		}
		tabelaproduto.setItems(itemsproduto);
		codt.setCellValueFactory(new PropertyValueFactory<String, Produto>("cod"));
		nomet.setCellValueFactory(new PropertyValueFactory<String, Produto>("nome"));
		desct.setCellValueFactory(new PropertyValueFactory<String, Produto>("descProd"));
		quantt.setCellValueFactory(new PropertyValueFactory<String, Produto>("quant"));
		pesot.setCellValueFactory(new PropertyValueFactory<String, Produto>("peso"));
		dtcompt.setCellValueFactory(new PropertyValueFactory<String, Produto>("dataComp"));
		dtvalt.setCellValueFactory(new PropertyValueFactory<String, Produto>("dataVal"));
		nft.setCellValueFactory(new PropertyValueFactory<String, Produto>("nf"));
		fornecedort.setCellValueFactory(new PropertyValueFactory<String, Produto>("fornecedor"));
		tabelaproduto.refresh();
		FilteredList<Produto> itemsfilter = new FilteredList<>(itemsproduto, b -> true);
		barradepesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
			itemsfilter.setPredicate(c -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (String.valueOf(c.getCod()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (c.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (c.getDescProd().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false;
			});
			SortedList<Produto> cc = new SortedList<>(itemsfilter);
			cc.comparatorProperty().bind(tabelaproduto.comparatorProperty());
			tabelaproduto.setItems(cc);
		});
	}

	public void delet() {
		Alert AvisoConfirm = new Alert(Alert.AlertType.CONFIRMATION);
		
		try {
			AvisoConfirm.setTitle("Atenção");
			AvisoConfirm.setContentText("Você esta seguro na exclusão deste item");
			AvisoConfirm.setHeaderText(null);
			Optional<ButtonType> result = AvisoConfirm.showAndWait()  ;
			if(result.isPresent()&& result.get() == ButtonType.OK) {
			Produto item = tabelaproduto.getSelectionModel().getSelectedItem();
   			tabelaproduto.getItems().remove(item);
			Main.instanciaProduto().removerProduto(item.getNome());
			Main.instanciaProduto().salvarProduto();
			}
		} catch (Exception e) {
			alertMessage("Não há mais produtos");
		}

	}
	
	public void alterarProduto() {
		String prod;
		Produto p;
		try {
			prod = tabelaproduto.getSelectionModel().getSelectedItem().getCod();
			alterarProdutoTela.setVisible(true);
			p = Main.instanciaProduto().ConsultaProdutoCod(prod);
			Acod.setText(p.getCod());
			Anome.setText(p.getNome());
			Afornecedor.setText(p.getFornecedor());
			Adescricao.setText(p.getDescProd());
			Aquantidade.setText(p.getQuant());
			Apeso.setText(p.getPeso());
			Anotafiscal.setText(p.getNf());
			Adatacompra.setText(p.getDataComp());
			Adatavalidade.setText(p.getDataVal());
			Avalorunitcomp.setText(p.getValorUniComp());
			Avalortotcomp.setText(p.getValorTotComp());
			Avalorunitvenda.setText(p.getValorUniComp());
			Avalortotvenda.setText(p.getValorTotVend());
			
		} catch (Exception e) {
			alertMessage("Não produtos para editar");
		}
	}

	public void salvarAlteracaoProduto() {
		String prod;
		Produto p;
		try {
		if (  !Adescricao.getText().isEmpty() && !Anotafiscal.getText().isEmpty() && !Aquantidade.getText().isEmpty()  && !Apeso.getText().isEmpty()
				&& !Adatacompra.getText().isEmpty() && !Adatavalidade.getText().isEmpty() && !Avalorunitcomp.getText().isEmpty()
				&& !Avalortotcomp.getText().isEmpty() && !Avalorunitvenda.getText().isEmpty()
				&& !Avalortotvenda.getText().isEmpty()) {
			
			prod = tabelaproduto.getSelectionModel().getSelectedItem().getCod();
			
			p = Main.instanciaProduto().ConsultaProdutoCod(prod);
	        p.setDescProd(Adescricao.getText());
	        p.setNf(Anotafiscal.getText());
			p.setQuant(Aquantidade.getText());
			p.setPeso(Apeso.getText());
			p.setDataComp(Adatacompra.getText());
			p.setDataVal(Adatavalidade.getText());
			p.setValorUniComp(Avalorunitcomp.getText());
			p.setValorTotComp(Avalortotcomp.getText());
			p.setValorUniVend(Avalorunitvenda.getText());
			p.setValorTotVend(Avalortotvenda.getText());
			
			alertMessage("Alterado com sucesso");
			Main.instanciaProduto().salvarProduto();
			alterarProdutoTela.setVisible(false);
			tabelaProduto();
			
				
				
		} else {
			alertMessage("Preencha todos os campos para salvar");
		}
		}catch(Exception e) {
			alertMessage("Ocorreu algum erro");
		}
	}
}
