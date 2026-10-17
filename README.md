# TP4 DPBO

# Jandji
Saya <strong>Muhamad Hasbie Assyattar</strong> dengan <strong>NIM 2508288</strong> mengerjakan Tugas Praktikum 4 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain
-Database
  - Connection
  - Statement

- Person
  - id : String
  - nama : String
  - tahunLahir : String
  - alamat : String
  - jabatan : String
- PeopleMenu
  - JTextField textFieldId;
  - JTextField textFieldNama;
  - JTextField textFieldTahun;
  - JTextField textFieldAlamat;
  - JButton buttonAdd;
  - JButton buttonRemove;
  - JComboBox comboBoxJabatan;
  - JLabel labelId;
  - JLabel labelNama;
  - JLabel labelTahun;
  - JLabel labelAlamat;
  - JLabel labelJabatan;
  - JTable table1;
  - JLabel labelJudul;
  - JPanel mainPanel;

# Struktur File
```
Main
├── PeopleData/
│   └── src/
│       ├── Person.java
│       ├── PeopleMenu.java
│       └── PeopleMenu.form
│
├── Dokumentasi/
│   └── Remove.gif
│   └── Add.gif
│
└── README.md
```
# Anuan Program
nanti ada isian Id nama tahun alamat sama combo box isinya jabata dan juga isian IPK , terus bisa di CRUD ... kalau mau hapus dan edit spilih dulu dari tabelnya dan kalau mau add tinnggal add
sebernyata ini pakai yang TP4 cuma di gabung aja sama database sql
terus untuk error handling
- kalo ada id sama
- kalo ada yang ga diisi pas  Update
- kalo adaa yang ga diisi pas create
# Dokumentasi

## Add
![Add](Dokumentasi/Add.gif)

## Delete
![Remove](Dokumentasi/DeleteConfirmation.gif)

## Update
![Remove](Dokumentasi/Edit.gif)

## Error Add Empty FIeld
![Remove](Dokumentasi/ErrorAddEmptyField.gif)

## Error Edit Empty FIeld
![Remove](Dokumentasi/ErrorEditEmptyField.gif)

## Error Id 
![Remove](Dokumentasi/ErrorIdSama.gif)
