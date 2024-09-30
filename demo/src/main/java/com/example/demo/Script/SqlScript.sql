CREATE TABLE `task_status` (
  `id` int NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ;




CREATE TABLE `session` (
  `user_id` int NOT NULL,
  `token` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `expires_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);



CREATE TABLE `task` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `priority` int DEFAULT NULL,
  `created` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `modified` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `due` date DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `description_UNIQUE` (`description`),
  KEY `fk_tasks_user_id_idx` (`user_id`),
  KEY `fk_tasks_status_id_idx` (`status_id`),
  CONSTRAINT `fk_tasks_status_id` FOREIGN KEY (`status_id`) REFERENCES `task_status` (`id`),
  CONSTRAINT `fk_tasks_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);






