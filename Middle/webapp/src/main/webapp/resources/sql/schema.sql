CREATE TABLE `Persons` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Pets` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL AUTO_INCREMENT,
	`ownerId` INT NOT NULL,
	`type` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Places` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`description` TEXT NOT NULL,
	`type` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Friendship` (
	`id1` INT NOT NULL,
	`id2` INT NOT NULL
);

CREATE TABLE `FavoritePlaces` (
	`pet` INT NOT NULL,
	`place` INT NOT NULL,
	`comment` TEXT NOT NULL
);

ALTER TABLE `Pets` ADD CONSTRAINT `Pets_fk0` FOREIGN KEY (`ownerId`) REFERENCES `Persons`(`id`);

ALTER TABLE `Friendship` ADD CONSTRAINT `Friendship_fk0` FOREIGN KEY (`id1`) REFERENCES `Persons`(`id`);

ALTER TABLE `Friendship` ADD CONSTRAINT `Friendship_fk1` FOREIGN KEY (`id2`) REFERENCES `Persons`(`id`);

ALTER TABLE `FavoritePlaces` ADD CONSTRAINT `FavoritePlaces_fk0` FOREIGN KEY (`pet`) REFERENCES `Pets`(`id`);

ALTER TABLE `FavoritePlaces` ADD CONSTRAINT `FavoritePlaces_fk1` FOREIGN KEY (`place`) REFERENCES `Places`(`id`);
