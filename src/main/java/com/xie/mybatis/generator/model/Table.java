package com.xie.mybatis.generator.model;

import java.util.List;

public class Table {

	private String tableName;

	private String beanName;

	private String remark;

	private List<Column> columns;
	
	private boolean hasDate;
	
	private boolean hasBigdecimal;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public boolean isHasDate() {
		return hasDate;
	}

	public void setHasDate(boolean hasDate) {
		this.hasDate = hasDate;
	}

	public boolean isHasBigdecimal() {
		return hasBigdecimal;
	}

	public void setHasBigdecimal(boolean hasBigdecimal) {
		this.hasBigdecimal = hasBigdecimal;
	}

	
}
