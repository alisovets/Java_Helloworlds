
CREATE DATABASE IF NOT EXISTS `issues_subscriptions`; 

DROP TABLE IF EXISTS `issues_subscriptions`.`order_items`;
DROP TABLE IF EXISTS `issues_subscriptions`.`orders`;
DROP TABLE IF EXISTS `issues_subscriptions`.`issues`;
DROP TABLE IF EXISTS `issues_subscriptions`.`consumers`;
DROP TABLE IF EXISTS `issues_subscriptions`.`administrators`;

-- administrators
CREATE TABLE  `issues_subscriptions`.`administrators` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(64) NOT NULL UNIQUE,
  `password` varchar(64) NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB
CHARACTER SET utf8 COLLATE utf8_general_ci;


-- consumers
CREATE TABLE `issues_subscriptions`.`consumers` (
  `id` INT  NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(64)  NOT NULL UNIQUE,
  `password` VARCHAR(64)  NOT NULL,
  `address` VARCHAR(255)  NOT NULL DEFAULT '',
  `name` VARCHAR(100)  NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE  `issues_subscriptions`.`issues` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `periodicity` enum('daily','weekly','monthly') NOT NULL DEFAULT 'monthly',
  `price` decimal(13,2) NOT NULL,
  `actual` tinyint(1) NOT NULL DEFAULT '1',
  `admin_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  CONSTRAINT `admin_fk` FOREIGN KEY `admin_fk` (`admin_id`)
    REFERENCES `issues_subscriptions`.`administrators` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
) 
ENGINE = InnoDB
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `issues_subscriptions`.`orders` (
  `id` INT  NOT NULL AUTO_INCREMENT,
  `consumer_id` INT  NOT NULL,
  `subscript_year` INT  NOT NULL, 	
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paid_date` DATE ,
  PRIMARY KEY (`id`),
  CONSTRAINT `consumer_fk` FOREIGN KEY `consumer_fk` (`consumer_id`)
    REFERENCES `issues_subscriptions`.`consumers` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
ENGINE = InnoDB
CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE `issues_subscriptions`.`order_items` (
  `id` INT  NOT NULL AUTO_INCREMENT,
  `order_id` INT  NOT NULL,
  `issue_id` INT  NOT NULL,
  `quantity` INT  NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `order_fk` FOREIGN KEY `order_fk` (`order_id`)
    REFERENCES `issues_subscriptions`.`orders` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `issue_fk` FOREIGN KEY `issue_fk` (`issue_id`)
    REFERENCES `issues_subscriptions`.`issues` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
ENGINE = InnoDB
CHARACTER SET utf8 COLLATE utf8_general_ci;

INSERT INTO `issues_subscriptions`.`consumers` VALUES
(1,'Читатель 1','password','Киев, пр. Победы 12', 'Иванов И. И.'),
(2,'Читатель 2','password','Киев, ул. Хрещатик, 26','Иванчук И. А.'),
(3,'Читатель 3','password','Киев, подземный переход возле М. Политехнический Институт','Иванченко С. П'),
(4,'Читатель 4','password','Киев','Петров-Водкин К. С'),
(5,'Читатель 5','password','Киев','Сидоров А. Б.'),
(6,'Читатель 6','password','Киев','Фердищенко І. Є.'),
(7,'coolboy','password','Киев','Фердищенко І. Є.')
;

INSERT INTO `issues_subscriptions`.`administrators` VALUES 
(1,'mainadmin','password','Админенко О. С.'),
(2,'majoradmin','password','Админов П. К.'),
(3,'Admin','password','Фердiщенко Ї. Є.')
;

INSERT INTO `issues_subscriptions`.`issues` VALUES 
(1, 'Газета 1','Издательство новостей','weekly', 15.30, 0, 1),
(2, 'Журнал 1','Модное издательство','monthly', 28.22, 1, 1),
(3, 'Газета 2','Издательство новостей','weekly', 35.01, 1, 1),
(4, 'Газета 3','Издательство новостей','weekly', 45.15, 1, 1),
(5, 'Газета 4','Издательство новостей','weekly', 55.30, 1, 1),
(6, 'Газета 5','Издательствo','weekly', 15.30, 1, 1),
(7, 'Журнал 2','Не модное издательство','monthly', 28.36, 1, 1),
(8, 'Газета 6','Издательство новостей','weekly', 35.33, 1, 1),
(9, 'Журнал 3','Немодное издательство','monthly', 98.36, 1, 1),
(10, 'Газета 7','Издательство','weekly', 45.30, 1, 1),
(11, 'Газета 8','Издательство','daily', 55.30, 1, 1),
(12, 'Газета 9','Издательство','weekly', 65.29, 1, 1),
(13, 'Газета 10','Издательство новостей','weekly', 75.99, 1, 2),
(14, 'Журнал 4','Модное издательство','monthly', 88.36, 1, 2),
(15, 'Газета 11','Издательство новостей','daily', 145.30, 1, 2),
(66, 'Журнал обо всем','Правда инк.','monthly', 8.87, 1, 2)
;
