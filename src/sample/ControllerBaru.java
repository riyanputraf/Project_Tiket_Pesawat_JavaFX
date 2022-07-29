package sample;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Tiket;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerBaru implements Initializable {
    public int selectedIndex;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showOptionMaskapai();
        showTiket();

        tableTiket.setOnMouseClicked(mouseEvent -> {
            selectedIndex = tableTiket.getSelectionModel().getSelectedIndex();
            //++selectedIndex;
            System.out.println(selectedIndex);

        });
    }
    @FXML
    private ComboBox<String> listMaskapai;

    @FXML
    private TableView<Tiket> tableTiket;

    @FXML
    private TableColumn<Tiket, Integer> colId;

    @FXML
    private TableColumn<Tiket, String> colMaskapai;

    @FXML
    private TableColumn<Tiket, String> colTujuan;

    @FXML
    private TableColumn<Tiket, String> colWaktu;

    @FXML
    private TableColumn<Tiket, String> colKelas;

    @FXML
    private TableColumn<Tiket, Integer> colStok;

    @FXML
    private TextField searchField;


    String valueMaskapai;

    private void showOptionMaskapai() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection =connectionClass.getConnection();
        Statement st;
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM maskapai");
            while (rs.next()){
                list.add(rs.getString("namaMaskapai"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //this.listMaskapai.setItems(null);
        this.listMaskapai.setItems(list);

    }

    public ObservableList<Tiket> getTiketList(){
        ObservableList<Tiket> tiketList = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection =connectionClass.getConnection();
        String sql = "SELECT * FROM tiket";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            Tiket tiket;
            while (rs.next()){
                tiket = new Tiket(rs.getInt("idTiket"), rs.getString("tujuan"), rs.getString("waktu"), rs.getInt("stok"),  rs.getString("kelas"), rs.getString("namaMaskapai"));
                tiketList.add(tiket);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tiketList;
    }

    public void showTiket(){
        ObservableList<Tiket> list = getTiketList();

        this.colId.setCellValueFactory(new PropertyValueFactory<Tiket, Integer>("idTiket"));
        this.colMaskapai.setCellValueFactory(new PropertyValueFactory<Tiket, String>("namaMaskapai"));
        this.colTujuan.setCellValueFactory(new PropertyValueFactory<Tiket, String>("tujuan"));
        this.colWaktu.setCellValueFactory(new PropertyValueFactory<Tiket, String>("waktu"));
        this.colStok.setCellValueFactory(new PropertyValueFactory<Tiket, Integer>("stok"));
        this.colKelas.setCellValueFactory(new PropertyValueFactory<Tiket, String>("kelas"));
        this.tableTiket.setItems(list);
    }

    private void executeQuery(String sql) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection =connectionClass.getConnection();
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateStok(){
//        int coba = colStok.getCellData(selectedIndex)-1;
        String sql = "UPDATE tiket SET stok = stok-1 WHERE idTiket = "+searchField.getText()+"";


        executeQuery(sql);
        showTiket();
    }

    private void deleteStok(){

        String sql = "DELETE FROM tiket WHERE stok = 0";
        executeQuery(sql);
        showTiket();
    }
    @FXML
    void order(ActionEvent event) {

        System.out.println(colMaskapai.getCellData(selectedIndex));
        System.out.println(colKelas.getCellData(selectedIndex));
        //int coba = colStok.getCellData(selectedIndex);
        System.out.println(colStok.getCellData(selectedIndex) - 1);

        String sql = "INSERT INTO pesan (namaMaskapai, tujuan, waktu, kelas) VALUES('"+colMaskapai.getCellData(selectedIndex)+"','"+colTujuan.getCellData(selectedIndex)+"','"+colWaktu.getCellData(selectedIndex)+"','"+colKelas.getCellData(selectedIndex)+"')";
        executeQuery(sql);

        updateStok();
        deleteStok();
        showTiket();
    }


    @FXML
    void selectMaskapai(ActionEvent event) {
        valueMaskapai = listMaskapai.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void search(ActionEvent event) {
        String k = colMaskapai.getCellData(2);
        if (valueMaskapai == null){
            ObservableList<Tiket> list = getTiketList();
            this.tableTiket.setItems(list);
            System.out.println(k);
        }else {
            ObservableList<Tiket> tiketList = FXCollections.observableArrayList();
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String sql = "Select * FROM tiket where namaMaskapai LIKE '%"+valueMaskapai+"%'";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(sql);
                Tiket tiket;
                while (rs.next()){
                    tiket = new Tiket(rs.getInt("idTiket"), rs.getString("tujuan"), rs.getString("waktu"), rs.getInt("stok"),  rs.getString("kelas"), rs.getString("namaMaskapai"));
                    tiketList.add(tiket);
                }

                this.tableTiket.setItems(tiketList);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(valueMaskapai);
        }


    }


}
