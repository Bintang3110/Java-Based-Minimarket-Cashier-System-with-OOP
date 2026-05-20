package gui;

import database.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PendaftaranMemberGUI {
    public PendaftaranMemberGUI() {
        JFrame frame = new JFrame("Pendaftaran Member");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel lblNama = new JLabel("Nama:");
        lblNama.setBounds(50, 50, 100, 30);
        frame.add(lblNama);

        JTextField txtNama = new JTextField();
        txtNama.setBounds(150, 50, 200, 30);
        frame.add(txtNama);

        JLabel lblWA = new JLabel("No WA:");
        lblWA.setBounds(50, 100, 100, 30);
        frame.add(lblWA);

        JTextField txtWA = new JTextField();
        txtWA.setBounds(150, 100, 200, 30);
        frame.add(txtWA);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(150, 150, 100, 30);
        frame.add(btnSimpan);

        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtNama.getText();
                String noWA = txtWA.getText();
                if (!nama.isEmpty() && !noWA.isEmpty()) {
                    try {
                        Connection conn = DatabaseConnection.getConnection();
                        String sql = "INSERT INTO members (nama_customer, nomor_wa) VALUES (?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, nama);
                        stmt.setString(2, noWA);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Member berhasil disimpan!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Semua field harus diisi!");
                }
            }
        });

        frame.setVisible(true);
    }
}
