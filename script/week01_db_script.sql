-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.1.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table mydb.account: ~5 rows (approximately)
INSERT INTO `account` (`ACCOUNT_ID`, `EMAIL`, `FULLNAME`, `PASSWORD`, `PHONE`, `STATUS`) VALUES
	('an', 'minhan200702@gmail.com', 'NGuyen Van Minh An', '123', '0123172312', 1),
	('phuc', 'phuc@gmail.com', 'NGuyen Ngoc phuc', '123', '123412431234', 1),
	('teo', 'teo@gmail.com', 'nguyen Van Teo', '123', '12312313131', 1),
	('tung', 'tung@gmail.com', 'NGuyen Ngoc Tung', '123', '123412431234', 1),
	('vy', 'vy@gmail.com', 'NGuyen Van ti', '123', '41234123412', 1);

-- Dumping data for table mydb.grantaccess: ~6 rows (approximately)
INSERT INTO `grantaccess` (`ACCOUNT_ID`, `ROLE_ID`, `is_grant`, `NOTE`) VALUES
	('an', 'admin', 1, ''),
	('phuc', 'tester', 1, 'add tester'),
	('teo', 'review', 1, 'Reviewer'),
	('tung', 'pm', 1, 'project manager'),
	('vy', 'admin', 1, 'adminastor'),
	('vy', 'user', 1, 'user');

-- Dumping data for table mydb.logs: ~2 rows (approximately)

-- Dumping data for table mydb.role: ~6 rows (approximately)
INSERT INTO `role` (`ROLE_ID`, `DESCRIPTION`, `ROLENAME`, `STATUS`) VALUES
	('admin', 'adminastor', 'adminastor', 1),
	('lead', 'leader of team role', 'leader', 1),
	('pm', 'Project Manager', 'Project Manager', 1),
	('review', 'reviewer role', 'reviewer', 1),
	('tester', 'tester', 'tester', 1),
	('user', 'user', 'user', -1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
