package sample;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Pesan;
import models.Tiket;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerHistory implements Initializable {

    @FXML
    private TableView<Pesan> tableHistory;

    @FXML
    private TableColumn<Tiket, Integer> colId;

    @FXML
    private TableColumn<Pesan, String> colMaskapai;

    @FXML
    private TableColumn<Pesan, String> colTujuan;

    @FXML
    private TableColumn<Pesan, String> colWaktu;

    @FXML
    private TableColumn<Pesan, String> colKelas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTiket();
    }


    public ObservableList<Pesan> getTiketList(){
        ObservableList<Pesan> tiketList = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection =connectionClass.getConnection();
        String sql = "SELECT * FROM pesan";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            Pesan pesanan;
            while (rs.next()){
                pesanan = new Pesan(rs.getString("namaMaskapai"), rs.getString("tujuan"), rs.getString("waktu"),rs.getString("kelas"));
                tiketList.add(pesanan);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tiketList;
    }

    public void showTiket(){
        ObservableList<Pesan> list = getTiketList();
        colMaskapai.setCellValueFactory(new PropertyValueFactory<Pesan, String>("namaMaskapai"));
        colTujuan.setCellValueFactory(new PropertyValueFactory<Pesan, String>("tujuan"));
        colWaktu.setCellValueFactory(new PropertyValueFactory<Pesan, String>("waktu"));
        colKelas.setCellValueFactory(new PropertyValueFactory<Pesan, String>("kelas"));
        tableHistory.setItems(list);
    }


}
