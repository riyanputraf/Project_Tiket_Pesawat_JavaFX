package models;

public class Maskapai {
    private int idMaskapai;
    private String namaMaskapai;

    public Maskapai(int idMaskapai, String namaMaskapai){
        this.namaMaskapai = namaMaskapai;
        this.idMaskapai = idMaskapai;
    }

    public int getIdMaskapai() {
        return idMaskapai;
    }

    public void setIdMaskapai(int idMaskapai) {
        this.idMaskapai = idMaskapai;
    }


    public String getNamaMaskapai() {
        return namaMaskapai;
    }

    public void setNamaMaskapai(String namaMaskapai) {
        this.namaMaskapai = namaMaskapai;
    }
}
