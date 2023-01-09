-- categories
INSERT INTO `youeat`.`categories` (`id`,`name`) VALUES (1,'肉');
INSERT INTO `youeat`.`categories` (`id`,`name`) VALUES (2,'魚');
INSERT INTO `youeat`.`categories` (`id`,`name`) VALUES (3,'野菜');
INSERT INTO `youeat`.`categories` (`id`,`name`) VALUES (4,'果物');
INSERT INTO `youeat`.`categories` (`id`,`name`) VALUES (5,'乳製品');
INSERT INTO `youeat`.`categories` (`id`,`name`) VALUES (6,'飲み物');
INSERT INTO `youeat`.`categories` (`id`,`name`) VALUES (9,'その他');

-- food
INSERT INTO `youeat`.`food` (`id`,`name`,`category_id`,`calories_per_gram`) VALUES (1,'牛肉',1,100);
INSERT INTO `youeat`.`food` (`id`,`name`,`category_id`,`calories_per_gram`) VALUES (2,'マグロ',2,200);
