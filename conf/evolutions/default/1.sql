-- !Ups

 CREATE TABLE `product` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `title` VARCHAR(45) NULL,
   `description` VARCHAR(45) NULL,
   `rating` INT NULL,
   `price` FLOAT NULL,
   PRIMARY KEY (`id`));

-- !Downs

drop table "product" if exists;

