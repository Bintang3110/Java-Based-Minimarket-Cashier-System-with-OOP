package gui;

import database.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdukGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;

    public ProdukGUI() {
        frame = new JFrame("Daftar Produk");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Menambahkan panel untuk tombol
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Tombol "Tambah Produk"
        JButton btnTambah = new JButton("Tambah Produk");
        panel.add(btnTambah);

        // Tombol "Edit Produk"
        JButton btnEdit = new JButton("Edit Produk");
        panel.add(btnEdit);

        // Tombol "Hapus Produk"
        JButton btnHapus = new JButton("Hapus Produk");
        panel.add(btnHapus);

        frame.add(panel, BorderLayout.NORTH);

        // Membuat tabel untuk menampilkan produk
        table = new JTable();
        model = new DefaultTableModel(new Object[]{"ID", "Nama Produk", "Harga", "Stok"}, 0);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action listener untuk tombol
        btnTambah.addActionListener(e -> {
            new TambahProdukGUI();
            frame.dispose();
        });

        btnEdit.addActionListener(e -> {
            editProduk();
        });

        btnHapus.addActionListener(e -> {
            hapusProduk();
        });

        // Menampilkan produk di tabel
        tampilkanProduk();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Fungsi untuk menampilkan daftar produk dalam tabel
    private void tampilkanProduk() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM produk";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Menghapus baris yang sudah ada
            model.setRowCount(0);

            // Menambahkan data produk ke JTable
            while (rs.next()) {
                int id = rs.getInt("id_produk");
                String nama = rs.getString("nama_produk");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");

                model.addRow(new Object[]{id, nama, harga, stok});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editProduk() {
        int row = table.getSelectedRow(); // Mendapatkan baris yang dipilih
        if (row != -1) {  // Pastikan ada produk yang dipilih
            int idProduk = (int) table.getValueAt(row, 0);  // ID produk yang dipilih
            String namaProduk = (String) table.getValueAt(row, 1);  // Nama produk
            int harga = (int) table.getValueAt(row, 2);  // Harga produk
            int stok = (int) table.getValueAt(row, 3);  // Stok produk

            // Membuka Edit Produk GUI dengan data produk yang dipilih
            new EditProdukGUI(idProduk, namaProduk, harga, stok);
            frame.dispose();  // Menutup frame manajemen produk
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih produk yang ingin diedit!");
        }
    }

    private void hapusProduk() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int idProduk = (int) table.getValueAt(row, 0);

            try {
                Connection conn = DatabaseConnection.getConnection();
                String sql = "DELETE FROM produk WHERE id_produk = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idProduk);
                stmt.executeUpdate();

                model.removeRow(row);
                JOptionPane.showMessageDialog(frame, "Produk berhasil dihapus!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih produk yang ingin dihapus!");
        }
    }
}
