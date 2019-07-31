package com.xie.mybatis.generator.core;


import com.xie.mybatis.generator.model.Column;
import com.xie.mybatis.generator.model.Table;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

public class DataProcessor {
	/**
	 * 表预处理
	 * 
	 * @param tableInfos
	 */
	public void prepareProcessTableInfos(List<Table> tableInfos) {
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		Connection connection = connectionFactory.getConnection();

		try {
			for (Table table : tableInfos) {
				connection.getMetaData().getPrimaryKeys(null, null, table.getTableName());
				String tableName = table.getTableName();
				if(org.apache.commons.lang.StringUtils.isNotBlank(tableName)&&(tableName.startsWith("p_")||tableName.startsWith("d_"))){
					tableName = tableName.substring(2,tableName.length());
				}
				table.setBeanName(StringUtils.underLineToCamel(StringUtils.toUpperCaseFirst(tableName)));
				table.setLowerCaseBeanName(StringUtils.underLineToCamel(StringUtils.toLowerCaseFirst(tableName)));
				prepareProcessColumns(table.getColumns());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			connectionFactory.close(connection);
		}

	}

	/**
	 * 列预处理
	 * 
	 * @param columns
	 */
	public void prepareProcessColumns(List<Column> columns) {
		for (Column column : columns) {

			String lowerProperty = StringUtils.underLineToCamel(column.getColumn());
			column.setLowerProperty(lowerProperty);
			column.setProperty(StringUtils.toUpperCaseFirst(StringUtils.underLineToCamel(lowerProperty)));
		}

	}

	public List<Table> convertToTableInfos(Map<String, Map<String, Map<String, Object>>> tables) {
		List<Table> tableInfos = new ArrayList<Table>();
		Table table = null;
		Column column = null;
		List<Column> columns = null;

		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		Connection connection = connectionFactory.getConnection();

		for (Entry<String, Map<String, Map<String, Object>>> e : tables.entrySet()) {
			table = new Table();
			columns = new ArrayList<Column>();
			table.setColumns(columns);

			tableInfos.add(table);
			String tableName = e.getKey();
			table.setTableName(tableName);
//			table.setDatabaseName();

			prepareProcessTable(table, connection, tableName);

			Map<String, Map<String, Object>> rows = e.getValue();
			for (Entry<String, Map<String, Object>> row : rows.entrySet()) {
				column = new Column();
				column.setColumn(row.getKey());
				Map<String, Object> rowInfo = row.getValue();
				String jdbcType=(String) rowInfo.get("jdbcType");
				column.setJdbcType(jdbcType);
				column.setRemark((String) rowInfo.get("remark"));
				column.setDataType((int) rowInfo.get("dataType"));
				
				String javaType=TypeUtils.getJavaType(column.getDataType());
				column.setType(javaType);
				if("Date".equals(javaType)){
					table.setHasDate(true);
				}else if("BigDecimal".equals(javaType)){
					table.setHasBigdecimal(true);
				}
				columns.add(column);
			}
		}
		connectionFactory.close(connection);
		return tableInfos;
	}

	private void prepareProcessTable(Table table, Connection connection, String tableName) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("select * from information_schema.TABLES where TABLE_NAME='" + tableName + "'");

			/*
			 * ResultSetMetaData meta=rs.getMetaData(); int
			 * cols=meta.getColumnCount(); while(rs.next()){ for(int
			 * i=1;i<=cols;i++){ Object o=rs.getObject(i);
			 * System.out.print(o+"\t"); } System.out.println(); }
			 */
			while (rs.next()) {
				String remark = rs.getString("TABLE_COMMENT");
				table.setRemark(remark);
				table.setDatabaseName(rs.getString(2));
				System.out.println(remark);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Map<String, Map<String, Map<String, Object>>> getTableInfo(String tableNamePattern) {
		Map<String, Map<String, Map<String, Object>>> tables = new LinkedHashMap<>();

		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

		Connection connection = connectionFactory.getConnection();
		try {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet rs = meta.getColumns(null, null, tableNamePattern, null);

			while (rs.next()) {

				String tableName = rs.getString("TABLE_NAME");

				String colName = rs.getString("COLUMN_NAME");
				String jdbcType = rs.getString("TYPE_NAME");
				Integer dataType = rs.getInt("DATA_TYPE");
				String remarks = rs.getString("REMARKS");
				Map<String, Map<String, Object>> table = tables.get(tableName);
				if (table == null) {
					table = new LinkedHashMap<>();
					tables.put(tableName, table);
				}
				Map<String, Object> row = new LinkedHashMap<>();
				// row.put("columnName", colName);
				row.put("jdbcType", jdbcType);
				row.put("remark", remarks);
				row.put("dataType", dataType);

				table.put(colName, row);

			}

			for (Entry<String, Map<String, Map<String, Object>>> e : tables.entrySet()) {
				System.out.println(e);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connectionFactory.close(connection);
		}
		return tables;
	}

	public List<Table> getTableInfos(String tableNamePattern) {
		Map<String, Map<String, Map<String, Object>>> tables = getTableInfo(tableNamePattern);

		List<Table> tableInfos = convertToTableInfos(tables);

		prepareProcessTableInfos(tableInfos);
		return tableInfos;
	}

	public static void main(String[] args) {
		String tableNamePattern = "%";
		DataProcessor t = new DataProcessor();

		List<Table> tableInfos = t.getTableInfos(tableNamePattern);

		System.out.println(tableInfos);
	}
}
