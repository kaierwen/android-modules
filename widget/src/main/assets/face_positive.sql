/*
1.建表
2.插入数据
*/
--CREATE TABLE "face_positive" ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `code` TEXT, `cldr_short_name` TEXT )

/*
http://www.unicode.org/emoji/charts/full-emoji-list.html#face-positive
*/
INSERT INTO face_positive values
(1,'0x1f600','grinning face'),
(2,'0x1f601','beaming face with smiling eyes'),
(3,'0x1f602','face with tears of joy')