-- --------------------------------------------------------
-- 호스트:                          49.50.172.215
-- 서버 버전:                        10.2.11-MariaDB-10.2.11+maria~xenial-log - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 뷰 shareonfoot.review_join 구조 내보내기
-- 임시 테이블을 제거하고 최종 VIEW 구조를 생성
DROP TABLE IF EXISTS `review_join`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `review_join` AS select `siheung_st`.`name` AS `name`,`siheung_st`.`category` AS `category`,`reviews`.`review` AS `review`,`reviews`.`star` AS `star`,`siheung_st`.`adress` AS `adress` from (`siheung_st` join `reviews` on(`siheung_st`.`name` = `reviews`.`name`));

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
