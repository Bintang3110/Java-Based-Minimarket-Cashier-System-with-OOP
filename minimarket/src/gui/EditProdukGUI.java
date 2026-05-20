package gui;

import database.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditProdukGUI {
    public EditProdukGUI(int idProduk, String namaProduk, int harga, int stok) {
        JFrame frame = new JFrame("Edit Produk");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel lblNama = new JLabel("Nama Produk:");
        lblNama.setBounds(50, 50, 100, 30);
        frame.add(lblNama);

        JTextField txtNama = new JTextField(namaProduk);
        txtNama.setBounds(150, 50, 200, 30);
        frame.add(txtNama);

        JLabel lblHarga = new JLabel("Harga:");
        lblHarga.setBounds(50, 100, 100, 30);
        frame.add(lblHarga);

        JTextField txtHarga = new JTextField(String.valueOf(harga));
        txtHarga.setBounds(150, 100, 200, 30);
        frame.add(txtHarga);

        JLabel lblStok = new JLabel("Stok:");
        lblStok.setBounds(50, 150, 100, 30);
        frame.add(lblStok);

        JTextField txtStok = new JTextField(String.valueOf(stok));
        txtStok.setBounds(150, 150, 200, 30);
        frame.add(txtStok);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(150, 200, 100, 30);
        frame.add(btnSimpan);

        // Action listener untuk tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namaProdukBaru = txtNama.getText();
                int hargaBaru = Integer.parseInt(txtHarga.getText());
                int stokBaru = Integer.parseInt(txtStok.getText());

                if (!namaProdukBaru.isEmpty() && hargaBaru > 0 && stokBaru >= 0) {
                    try {
                        Connection conn = DatabaseConnection.getConnection();
                        String sql = "UPDATE produk SET nama_produk = ?, harga = ?, stok = ? WHERE id_produk = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, namaProdukBaru);
                        stmt.setInt(2, hargaBaru);
                        stmt.setInt(3, stokBaru);
                        stmt.setInt(4, idProduk);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Produk berhasil diperbarui!");
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
