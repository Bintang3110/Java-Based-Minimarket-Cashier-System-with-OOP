package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinimarketAppGUI {
    public MinimarketAppGUI() {
        // Frame utama
        JFrame frame = new JFrame("Minimarket Jaya");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Tombol Pendaftaran Member
        JButton btnMember = new JButton("Pendaftaran Member");
        btnMember.setBounds(100, 50, 200, 30);
        frame.add(btnMember);

        // Tombol Transaksi
        JButton btnTransaksi = new JButton("Transaksi");
        btnTransaksi.setBounds(100, 100, 200, 30);
        frame.add(btnTransaksi);

        // Tombol Manajemen Produk
        JButton btnProduk = new JButton("Manajemen Produk");
        btnProduk.setBounds(100, 150, 200, 30);
        frame.add(btnProduk);

        // Tombol Keluar
        JButton btnKeluar = new JButton("Keluar");
        btnKeluar.setBounds(100, 200, 200, 30);
        frame.add(btnKeluar);

        // Action Listener untuk Pendaftaran Member
        btnMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PendaftaranMemberGUI();
            }
        });

        // Action Listener untuk Transaksi
        btnTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransaksiGUI();
            }
        });

        // Action Listener untuk Manajemen Produk
        btnProduk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProdukGUI();  // Menampilkan GUI Manajemen Produk
            }
        });

        // Action Listener untuk Keluar
        btnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Menampilkan frame
        frame.setVisible(true);
    }
}
