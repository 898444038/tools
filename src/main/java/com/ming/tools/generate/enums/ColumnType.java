package com.ming.tools.generate.enums;

/**
 * 类型	大小	范围（有符号）	范围（无符号）	用途
 TINYINT	1 字节	(-128，127)	(0，255)	小整数值
 SMALLINT	2 字节	(-32 768，32 767)	(0，65 535)	大整数值
 MEDIUMINT	3 字节	(-8 388 608，8 388 607)	(0，16 777 215)	大整数值
 INT或INTEGER	4 字节	(-2 147 483 648，2 147 483 647)	(0，4 294 967 295)	大整数值
 BIGINT	8 字节	(-9,223,372,036,854,775,808，9 223 372 036 854 775 807)	(0，18 446 744 073 709 551 615)	极大整数值
 FLOAT	4 字节	(-3.402 823 466 E+38，-1.175 494 351 E-38)，0，(1.175 494 351 E-38，3.402 823 466 351 E+38)	0，(1.175 494 351 E-38，3.402 823 466 E+38)	单精度
 浮点数值
 DOUBLE	8 字节	(-1.797 693 134 862 315 7 E+308，-2.225 073 858 507 201 4 E-308)，0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308)	0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308)	双精度
 浮点数值
 DECIMAL	对DECIMAL(M,D) ，如果M>D，为M+2否则为D+2	依赖于M和D的值	依赖于M和D的值	小数值
 */
public enum ColumnType {
    AUTO,

    TINYINT, SMALLINT, MEDIUMINT, INT, INTEGER, BIGINT, FLOAT, DOUBLE, DECIMAL,

    DATE,TIME,YEAR,DATETIME,TIMESTAMP,

    CHAR,VARCHAR,TINYBLOB,TINYTEXT,BLOB,TEXT,MEDIUMBLOB,MEDIUMTEXT,LONGBLOB,LONGTEXT;
}
