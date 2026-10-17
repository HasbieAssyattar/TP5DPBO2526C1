-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 15, 2026 at 05:43 PM
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
-- Database: `db_tp5`
--

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` varchar(255) NOT NULL,
  `NAMA` varchar(255) NOT NULL,
  `tahun` int(11) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `jabatan` varchar(255) NOT NULL,
  `ipk` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `NAMA`, `tahun`, `alamat`, `jabatan`, `ipk`) VALUES
('C1001', 'Yusuf', 2005, 'Padalarang', 'Ketua Kelas', 3.85),
('C1002', 'Hasbie', 2007, 'Margaasih', 'Wakil Ketua Kelas', 3.75),
('C1003', 'Wingko', 2008, 'Lembang', 'Editor', 3.4),
('C1004', 'Riza', 2006, 'Depok', 'Anggota Kelas', 3.6),
('C1005', 'Luthfi', 1945, 'Cibiru', 'Pengangguran Kelas', 3.2),
('C1006', 'Ilal', 2007, 'Bekasi', 'Sekretaris', 3.67),
('C1007', 'Nejad', 2019, 'Jakarta', 'Anggota', 3.78);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
