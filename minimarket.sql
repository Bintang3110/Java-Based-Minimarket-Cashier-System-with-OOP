-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2024 at 01:08 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `minimarket`
--

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `nama_customer` varchar(100) NOT NULL,
  `nomor_wa` varchar(15) NOT NULL,
  `poin` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `nama_customer`, `nomor_wa`, `poin`) VALUES
(1, 'bgbg', '234567876543', 3),
(2, '3', '9685434567', 0),
(3, 'bintang', '082165858079', 3),
(4, 'Bintang', '08234545098', 2),
(5, 'Bintang', '08234545098', 2);

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id_produk` int(11) NOT NULL,
  `nama_produk` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id_produk`, `nama_produk`, `harga`, `stok`) VALUES
(1, 'Roti Tawar', 15000, 50),
(2, 'Mie Instan', 3000, 100),
(3, 'Biskuit', 10000, 74),
(4, 'Keripik Kentang', 12000, 40),
(5, 'Kacang Goreng', 8000, 60),
(6, 'Air Mineral 600ml', 4000, 198),
(7, 'Teh Botol', 5000, 100),
(8, 'Kopi Instan', 15000, 50),
(9, 'Susu Coklat', 7000, 80),
(11, 'Minyak Goreng 1L', 20000, 30),
(12, 'Tepung Terigu 1kg', 12000, 40),
(13, 'Gula Pasir 1kg', 15000, 25),
(14, 'Garam Dapur', 5000, 100),
(15, 'Penyedap Rasa', 2500, 80),
(16, 'Sabun Mandi', 6000, 70),
(17, 'Shampo 200ml', 15000, 50),
(18, 'Pasta Gigi', 10000, 80),
(19, 'Sikat Gigi', 5000, 100),
(20, 'Handuk', 25000, 20),
(21, 'Pulpen', 2000, 150),
(22, 'Pensil', 1500, 198),
(23, 'Penghapus', 1000, 100),
(24, 'Buku Tulis', 8000, 75),
(25, 'Penggaris', 5000, 60),
(26, 'selai cokelat', 20000, 50);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `nama_customer` varchar(100) NOT NULL,
  `total_bayar` int(11) NOT NULL,
  `poin_didapat` int(11) NOT NULL,
  `is_member` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `nama_customer`, `total_bayar`, `poin_didapat`, `is_member`) VALUES
(1, 'bgbg', 20000, 2, 1),
(2, 'bgbg', 15000, 1, 1),
(3, 'hayo', 9000, 0, 0),
(4, 'bgbg', 1500, 0, 1),
(5, '6', 4000, 0, 0),
(6, 'bgbg', 0, 0, 0),
(7, 'bgbg', 15000, 1, 0),
(8, 'bgbg', 15000, 1, 1),
(9, 'bintang', 30000, 3, 1),
(10, 'bintang', 15000, 1, 1),
(11, 'bintang', 15000, 1, 1),
(12, 'Bintang', 29000, 2, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id_produk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
