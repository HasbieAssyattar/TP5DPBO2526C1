public class Person {
    private String id;
    private String nama;
    private String tahun;
    private String alamat;
    private String jabatan;
    private double ipk;

    public Person(String id, String nama, String tahun, String alamat, String jabatan, double ipk) {
        this.id = id;
        this.nama = nama;
        this.tahun = tahun;
        this.alamat = alamat;
        this.jabatan = jabatan;
        this.ipk = ipk;
    }

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public double getIpk() {
        return ipk;
    }

    public void setIpk(double ipk) {
        this.ipk = ipk;
    }
}
