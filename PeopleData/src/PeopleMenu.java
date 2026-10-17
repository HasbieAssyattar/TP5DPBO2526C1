import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleMenu extends JFrame {
    public static void main(String[] args) {
        // buat object window
        PeopleMenu menu = new PeopleMenu();

        // atur ukuran window
        menu.setSize(700, 600);

        // letakkan window di tengah layar
        menu.setLocationRelativeTo(null);

        // isi window
        menu.setContentPane(menu.mainPanel);

        // ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);

        // tampilkan window
        menu.setVisible(true);

        // agar program ikut berhenti saat window diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik (-1 jika tidak ada yang dipilih)
    private int selectedIndex = -1;
    // koneksi ke database
    private Database database;

    private JTextField textFieldId;
    private JTextField textFieldNama;
    private JTextField textFieldTahun;
    private JTextField textFieldAlamat;
    private JTextField textFieldIpk;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JComboBox<String> comboBoxJabatan;
    private JLabel labelId;
    private JLabel labelNama;
    private JLabel labelTahun;
    private JLabel labelAlamat;
    private JLabel labelJabatan;
    private JLabel labelIpk;
    private JTable table1;
    private JLabel labelJudul;
    private JPanel mainPanel;

    public PeopleMenu() {
        // inisialisasi koneksi database
        database = new Database();

        // isi item pilihan pada comboBoxJabatan
        comboBoxJabatan.addItem("Ketua Kelas");
        comboBoxJabatan.addItem("Wakil Ketua");
        comboBoxJabatan.addItem("Sekretaris");
        comboBoxJabatan.addItem("Bendahara");
        comboBoxJabatan.addItem("Anggota");

        // set model tabel dari data yang ada di database
        table1.setModel(setTable());

        // sembunyikan tombol remove saat belum ada data yang dipilih
        buttonRemove.setVisible(false);

        // Listener tombol Add / Update
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // Listener tombol Remove (dengan prompt konfirmasi penghapusan)
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });

        // Listener saat baris di tabel diklik
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = table1.getSelectedRow();
                if (selectedIndex >= 0) {
                    // ambil value langsung dari tabel, tanpa ArrayList
                    textFieldId.setText(table1.getModel().getValueAt(selectedIndex, 0).toString());
                    textFieldNama.setText(table1.getModel().getValueAt(selectedIndex, 1).toString());
                    textFieldTahun.setText(table1.getModel().getValueAt(selectedIndex, 2).toString());
                    textFieldAlamat.setText(table1.getModel().getValueAt(selectedIndex, 3).toString());
                    comboBoxJabatan.setSelectedItem(table1.getModel().getValueAt(selectedIndex, 4).toString());
                    textFieldIpk.setText(table1.getModel().getValueAt(selectedIndex, 5).toString());

                    // ubah teks tombol Add menjadi Update
                    buttonAdd.setText("Update");
                    // tampilkan tombol Remove
                    buttonRemove.setVisible(true);
                }
            }
        });

        // Listener klik area panel untuk reset/batalkan seleksi tabel
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearForm();
                table1.clearSelection();
            }
        });
    }

    // Method untuk membuat model data tabel dari hasil query database
    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] cols = {"Id", "Nama", "Tahun lahir", "Alamat", "Jabatan", "IPK"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, cols);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM person");

            // isi tabel dengan hasil query
            while (resultSet.next()) {
                Object[] data = {
                    resultSet.getString("id"),
                    resultSet.getString("NAMA"),
                    resultSet.getString("tahun"),
                    resultSet.getString("alamat"),
                    resultSet.getString("jabatan"),
                    resultSet.getDouble("ipk")
                };
                temp.addRow(data);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return temp;
    }

    // Method untuk menambah data baru
    public void insertData() {
        try {
            // ambil value textfield dan combobox
            String id = textFieldId.getText().trim();
            String nama = textFieldNama.getText().trim();
            String tahun = textFieldTahun.getText().trim();
            String alamat = textFieldAlamat.getText().trim();
            String jabatan = comboBoxJabatan.getSelectedItem() != null
                    ? comboBoxJabatan.getSelectedItem().toString()
                    : "";
            String ipk = textFieldIpk.getText().trim();

            // validasi input kosong
            if (id.isEmpty() || nama.isEmpty() || tahun.isEmpty()
                    || alamat.isEmpty() || jabatan.isEmpty() || ipk.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // validasi ID sudah dipakai atau belum
            if (isIdExists(id)) {
                JOptionPane.showMessageDialog(this, "ID " + id + " sudah ada, gunakan ID lain!",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // validasi IPK harus berupa angka
            double nilaiIpk;
            try {
                nilaiIpk = Double.parseDouble(ipk);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IPK harus berupa angka!",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // tambahkan data ke database
            String sqlQuery = "INSERT INTO person VALUES('" + id + "', '" + nama + "', '"
                    + tahun + "', '" + alamat + "', '" + jabatan + "', " + nilaiIpk + ")";
            database.insertUpdateDeleteQuery(sqlQuery);

            // update tabel
            table1.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tahun Lahir harus berupa angka!");
        }
    }

    // Method untuk mengubah data yang dipilih
    public void updateData() {
        try {
            // ambil value textfield dan combobox
            String id = textFieldId.getText().trim();
            String nama = textFieldNama.getText().trim();
            String tahun = textFieldTahun.getText().trim();
            String alamat = textFieldAlamat.getText().trim();
            String jabatan = comboBoxJabatan.getSelectedItem() != null
                    ? comboBoxJabatan.getSelectedItem().toString()
                    : "";
            String ipk = textFieldIpk.getText().trim();

            // validasi input kosong
            if (id.isEmpty() || nama.isEmpty() || tahun.isEmpty()
                    || alamat.isEmpty() || jabatan.isEmpty() || ipk.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // validasi IPK harus berupa angka
            double nilaiIpk;
            try {
                nilaiIpk = Double.parseDouble(ipk);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IPK harus berupa angka!",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // ubah data di database
            String sqlQuery = "UPDATE person SET NAMA = '" + nama + "', tahun = '" + tahun
                    + "', alamat = '" + alamat + "', jabatan = '" + jabatan + "', ipk = " + nilaiIpk
                    + " WHERE id = '" + id + "'";
            database.insertUpdateDeleteQuery(sqlQuery);

            // update tabel
            table1.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tahun Lahir harus berupa angka!");
        }
    }

    // Method untuk menghapus data dengan confirmation prompt
    public void deleteData() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Apakah Anda yakin ingin menghapus data ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            String id = textFieldId.getText().trim();
            String sqlQuery = "DELETE FROM person WHERE id = '" + id + "'";
            database.insertUpdateDeleteQuery(sqlQuery);

            table1.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        }
    }

    // Method untuk cek apakah ID sudah ada di database
    public boolean isIdExists(String id) {
        try {
            String sqlQuery = "SELECT * FROM person WHERE id = '" + id + "'";
            ResultSet resultSet = database.selectQuery(sqlQuery);
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method untuk mereset form input dan status tombol
    public void clearForm() {
        textFieldId.setText("");
        textFieldNama.setText("");
        textFieldTahun.setText("");
        textFieldAlamat.setText("");
        textFieldIpk.setText("");
        if (comboBoxJabatan.getItemCount() > 0) {
            comboBoxJabatan.setSelectedIndex(0);
        }
        buttonAdd.setText("Add");
        buttonRemove.setVisible(false);
        selectedIndex = -1;
    }
}
