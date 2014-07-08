/*用户表*/
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `article`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `category` int(11) NOT NULL,
  `tags` varchar(400) NOT NULL,
  `createDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL,
  `viewCount` int(11) NOT NULL DEFAULT '0',
  `replyCount` int(11) NOT NULL DEFAULT '0',
  `finish` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `category`(
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `name` varchar(100) NOT NULL,
   `orderby` int(5) NOT NULL,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
