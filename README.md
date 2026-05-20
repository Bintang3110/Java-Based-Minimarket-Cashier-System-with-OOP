# Java-Based-Minimarket-Cashier-System-with-OOP
Aplikasi desktop kasir sederhana menggunakan Java Swing untuk manajemen inventaris produk, pencatatan transaksi penjualan, perhitungan otomatis total belanja, serta sistem poin membership.
## 🚀 Fitur Utama Aplikasi

Aplikasi ini dirancang dengan prinsip Pemrograman Berbasis Objek (OOP) dan dilengkapi dengan antarmuka grafis (GUI) yang interaktif:

* **Sistem Pendaftaran & Database Member:** Fitur untuk mendata *Customer Member* baru dengan menyimpan informasi nama, nomor WhatsApp, serta kalkulasi akumulasi poin belanja.
* **Manajemen Inventaris Produk:** Modul CRUD (*Create, Read, Update, Delete*) lengkap untuk mengelola stok barang di minimarket (Tambah produk baru, mengedit info/harga, dan menghapus data produk).
* **Transaksi Penjualan Interaktif:** Kasir dapat menginput belanjaan secara *real-time*, menghitung total otomatis, dan sistem langsung memperbarui jumlah stok di database MySQL lokal setelah transaksi berhasil.

---

## 🛠️ Prasyarat (Prerequisites)

Sebelum menjalankan aplikasi ini, pastikan komputer kamu sudah terinstal perangkat lunak berikut:

* **Java Development Kit (JDK):** Versi 8 atau yang lebih baru.
* **MySQL Server:** Disarankan menggunakan bundel **XAMPP** untuk mempermudah manajemen server database lokal.
* **MySQL JDBC Driver:** File `mysql-connector-java.jar` sebagai penghubung komunikasi (driver JDBC) antara kode Java dan basis data MySQL.

---

## 💻 Cara Menjalankan Aplikasi

Ikuti langkah-langkah berikut untuk memasang dan menjalankan proyek di lingkungan lokal kamu:

### 1. Konfigurasi Database
1. Nyalakan modul **Apache** dan **MySQL** pada panel kontrol XAMPP kamu.
2. Buka browser lalu akses `http://localhost/phpmyadmin/`.
3. Buat database baru bernama **`minimarket`**.
4. Pilih database tersebut, masuk ke menu **Import**, lalu pilih file SQL yang berada di dalam folder proyek: `schema/minimarket.sql`.

### 2. Pengaturan Environment Project
1. Buka folder proyek ini menggunakan IDE pilihanmu (NetBeans, Eclipse, atau IntelliJ IDEA/VS Code).
2. Pastikan file library driver `mysql-connector-java.jar` sudah dimasukkan ke dalam daftar **Classpath / Dependencies** pada pengaturan proyek di IDE kamu agar koneksi database tidak error.

### 3. Eksekusi Program
1. Cari berkas utama aplikasi yang berada di direktori:
   ```text
   src/main/Main.java
