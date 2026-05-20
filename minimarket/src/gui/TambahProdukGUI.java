package gui;

import database.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TambahProdukGUI {
    public TambahProdukGUI() {
        JFrame frame = new JFrame("Tambah Produk");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel lblNama = new JLabel("Nama Produk:");
        lblNama.setBounds(50, 50, 100, 30);
        frame.add(lblNama);

        JTextField txtNama = new JTextField();
        txtNama.setBounds(150, 50, 200, 30);
        frame.add(txtNama);

        JLabel lblHarga = new JLabel("Harga:");
        lblHarga.setBounds(50, 100, 100, 30);
        frame.add(lblHarga);

        JTextField txtHarga = new JTextField();
        txtHarga.setBounds(150, 100, 200, 30);
        frame.add(txtHarga);

        JLabel lblStok = new JLabel("Stok:");
        lblStok.setBounds(50, 150, 100, 30);
        frame.add(lblStok);

        JTextField txtStok = new JTextField();
        txtStok.setBounds(150, 150, 200, 30);
        frame.add(txtStok);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(150, 200, 100, 30);
        frame.add(btnSimpan);

        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namaProduk = txtNama.getText();
                int harga = Integer.parseInt(txtHarga.getText());
                int stok = Integer.parseInt(txtStok.getText());

                if (!namaProduk.isEmpty() && harga > 0 && stok > 0) {
                    try {
                        Connection conn = DatabaseConnection.getConnection();
                        String sql = "INSERT INTO produk (nama_produk, harga, stok) VALUES (?, ?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, namaProduk);
                        stmt.setInt(2, harga);
                        stmt.setInt(3, stok);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Produk berhasil ditambahkan!");
                        frame.dispose();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Semua field harus diisi dengan benar!");
                }
            }
        });

        frame.setVisible(true);
    }
}
