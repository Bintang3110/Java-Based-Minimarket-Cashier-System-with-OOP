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

public class TransaksiGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtCustomer;
    private JTextField txtJumlah;
    private JLabel lblTotal;
    private int totalBayar = 0;

    public TransaksiGUI() {
        frame = new JFrame("Transaksi");
        frame.setSize(600, 400);
        frame.setLayout(null);

        JLabel lblNama = new JLabel("Nama Customer:");
        lblNama.setBounds(20, 20, 120, 30);
        frame.add(lblNama);

        txtCustomer = new JTextField();
        txtCustomer.setBounds(150, 20, 200, 30);
        frame.add(txtCustomer);

        // Opsi untuk memilih apakah customer member atau non-member
        JLabel lblMembership = new JLabel("Membership:");
        lblMembership.setBounds(20, 60, 120, 30);
        frame.add(lblMembership);

        JRadioButton rbMember = new JRadioButton("Member");
        rbMember.setBounds(150, 60, 80, 30);
        JRadioButton rbNonMember = new JRadioButton("Non-Member");
        rbNonMember.setBounds(240, 60, 120, 30);

        ButtonGroup group = new ButtonGroup();
        group.add(rbMember);
        group.add(rbNonMember);

        frame.add(rbMember);
        frame.add(rbNonMember);

        // Tombol untuk menambah produk
        JButton btnTambah = new JButton("Tambah ke Keranjang");
        btnTambah.setBounds(400, 20, 160, 30);
        frame.add(btnTambah);

        // Tabel untuk menampilkan produk yang dibeli
        table = new JTable();
        model = new DefaultTableModel(new Object[]{"ID", "Nama Produk", "Harga", "Jumlah", "Subtotal"}, 0);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 100, 540, 150);
        frame.add(scrollPane);

        // Input jumlah produk
        JLabel lblJumlah = new JLabel("Jumlah:");
        lblJumlah.setBounds(20, 240, 80, 30);
        frame.add(lblJumlah);

        txtJumlah = new JTextField();
        txtJumlah.setBounds(100, 240, 100, 30);
        frame.add(txtJumlah);

        // Label total bayar
        JLabel lblTotalText = new JLabel("Total Bayar:");
        lblTotalText.setBounds(20, 280, 100, 30);
        frame.add(lblTotalText);

        lblTotal = new JLabel("Rp 0");
        lblTotal.setBounds(150, 280, 200, 30);
        frame.add(lblTotal);

        // Tombol untuk menyimpan transaksi
        JButton btnSimpan = new JButton("Simpan Transaksi");
        btnSimpan.setBounds(400, 320, 160, 30);
        frame.add(btnSimpan);

        // Action Listener untuk tombol tambah produk
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahProdukKeKeranjang();
            }
        });

        // Action Listener untuk tombol simpan transaksi
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanTransaksi(rbMember.isSelected()); // Menggunakan pilihan member untuk menentukan poin
            }
        });

        frame.setVisible(true);
    }

    // Fungsi untuk menambah produk ke keranjang
    private void tambahProdukKeKeranjang() {
        try {
            String idProduk = JOptionPane.showInputDialog(frame, "Masukkan ID Produk:");
            if (idProduk == null || idProduk.trim().isEmpty()) return;

            int jumlah = Integer.parseInt(txtJumlah.getText().trim());
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(frame, "Jumlah harus lebih dari 0!");
                return;
            }

            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM produk WHERE id_produk = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idProduk));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String namaProduk = rs.getString("nama_produk");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");

                if (stok >= jumlah) {
                    int subtotal = harga * jumlah;
                    totalBayar += subtotal;

                    model.addRow(new Object[]{idProduk, namaProduk, harga, jumlah, subtotal});
                    lblTotal.setText("Rp " + totalBayar);

                    // Update stok di database
                    String updateSql = "UPDATE produk SET stok = stok - ? WHERE id_produk = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setInt(1, jumlah);
                    updateStmt.setInt(2, Integer.parseInt(idProduk));
                    updateStmt.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(frame, "Stok tidak mencukupi!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Produk tidak ditemukan!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }
    }

    // Fungsi untuk menyimpan transaksi
    private void simpanTransaksi(boolean isMember) {
        try {
            String namaCustomer = txtCustomer.getText().trim();
            if (namaCustomer.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nama customer harus diisi!");
                return;
            }

            // Hitung poin hanya untuk member
            int poinDidapat = isMember ? totalBayar / 10000 : 0;

            Connection conn = DatabaseConnection.getConnection();

            // Menyimpan transaksi
            String sql = "INSERT INTO transaksi (nama_customer, total_bayar, poin_didapat, is_member) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, namaCustomer);
            stmt.setInt(2, totalBayar);
            stmt.setInt(3, poinDidapat);
            stmt.setBoolean(4, isMember);  // Menyimpan status member
            stmt.executeUpdate();

            // Memperbarui poin di tabel members
            if (isMember) {
                String updatePoinSql = "UPDATE members SET poin = poin + ? WHERE nama_customer = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updatePoinSql);
                updateStmt.setInt(1, poinDidapat);
                updateStmt.setString(2, namaCustomer);
                updateStmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(frame, "Transaksi berhasil disimpan!");
            frame.dispose(); // Tutup frame transaksi
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }
    }
}
