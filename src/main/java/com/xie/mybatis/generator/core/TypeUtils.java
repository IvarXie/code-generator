package com.xie.mybatis.generator.core;

public class TypeUtils {

	public static String getJavaType(int dataType){
		return getJavaType(CommonDataType.getJdbcType(dataType));
	}
	
	public static String getJavaType(String jdbcType) {
		String lowerJdbcType = jdbcType.toLowerCase();
		String type = "unknownType";
		if (lowerJdbcType.equals("char") || lowerJdbcType.equals("varchar") || lowerJdbcType.equals("longvarchar")) {
			type = "String";
		}
		if (lowerJdbcType.equals("bit")) {
			type = "Boolean";
		}

		if (lowerJdbcType.equals("tinyint") || lowerJdbcType.equals("smallint")
				|| lowerJdbcType.equals("integer")) {
			type = "Integer";
		}

		if (lowerJdbcType.equals("bigint")) {
			type = "Long";
		}
		if (lowerJdbcType.equals("float")) {
			type = "Float";
		}
		if (lowerJdbcType.equals("double")) {
			type = "Double";
		}
		if (lowerJdbcType.equals("numeric") || lowerJdbcType.equals("decimal")) {
			type = "BigDecimal";
		}
		
		if (lowerJdbcType.equals("date")||lowerJdbcType.equals("time")||lowerJdbcType.equals("timestamp")) {
			type = "Date";
		}
		if (lowerJdbcType.equals("binary")||lowerJdbcType.equals("varbinary")||lowerJdbcType.equals("longvarbinary")) {
			type = "byte[]";
		}

		return type;
	}
}
