package com.xie.mybatis.generator.core;

public class Configure {

	public final String templeteBase = "template/";

	private String targetDir;

	private String modelPackage;

	private String mapperPackage;

	private String examplePackage;

	private String beanJsonPackage;

	public String getBeanJsonPackage() {
		return beanJsonPackage;
	}

	public void setBeanJsonPackage(String beanJsonPackage) {
		this.beanJsonPackage = beanJsonPackage;
	}

	private String primaryKey;

	public String getModelPackage() {
		return modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}

	public String getExamplePackage() {
		return examplePackage;
	}

	public void setExamplePackage(String examplePackage) {
		this.examplePackage = examplePackage;
	}

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

}
