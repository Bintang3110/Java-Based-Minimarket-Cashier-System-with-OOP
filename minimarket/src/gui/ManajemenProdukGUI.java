package gui;

import database.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManajemenProdukGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;

    public ManajemenProdukGUI() {
        frame = new JFrame("Manajemen Produk");
        frame.setSize(600, 400);
        frame.setLayout(null);

        // Tombol Tambah Produk
        JButton btnTambah = new JButton("Tambah Produk");
        btnTambah.setBounds(20, 20, 150, 30);
        frame.add(btnTambah);

        // Tombol Edit Produk
        JButton btnEdit = new JButton("Edit Produk");
        btnEdit.setBounds(180, 20, 150, 30);
        frame.add(btnEdit);

        // Tombol Hapus Produk
        JButton btnHapus = new JButton("Hapus Produk");
        btnHapus.setBounds(340, 20, 150, 30);
        frame.add(btnHapus);

        // Tabel Produk
        table = new JTable();
        model = new DefaultTableModel(new Object[]{"ID", "Nama Produk", "Harga", "Stok"}, 0);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 70, 540, 200);
        frame.add(scrollPane);

        // Tombol Kembali
        JButton btnKembali = new JButton("Kembali");
        btnKembali.setBounds(240, 300, 120, 30);
        frame.add(btnKembali);

        // Action Listener untuk tombol
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TambahProdukGUI();
                frame.dispose(); // Menutup frame manajemen produk
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProduk();
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusProduk();
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Kembali ke menu utama
            }
        });

        // Menampilkan produk di tabel
        tampilkanProduk();

        frame.setVisible(true);
    }

    private void tampilkanProduk() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM produk";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

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
        int row = table.getSelectedRow();
        if (row != -1) {
            int idProduk = (int) table.getValueAt(row, 0);
            String namaProduk = (String) table.getValueAt(row, 1);
            int harga = (int) table.getValueAt(row, 2);
            int stok = (int) table.getValueAt(row, 3);

            new EditProdukGUI(idProduk, namaProduk, harga, stok);
            frame.dispose();
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
