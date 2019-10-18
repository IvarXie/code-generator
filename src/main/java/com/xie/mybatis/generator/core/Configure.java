package com.xie.mybatis.generator.core;

import com.xie.mybatis.generator.entity.GenerateTable;
import com.xie.mybatis.generator.utils.YamlUtils;

public class Configure {

	public Configure() {
		GenerateTable generateTable = YamlUtils.getProperties().getGenerateTable();
        this.targetDir = generateTable.getTargetDir();
		this.primaryKey = generateTable.getPrimaryKey();
		this.rootPackage = generateTable.getRootPackage();
		if(generateTable.isDomain()){
			this.domainPackage = generateTable.getRootPackage()+".domain";
		}
		if(generateTable.isQuery()){
			this.queryPackage = generateTable.getRootPackage()+".query";
		}
		if(generateTable.isManager()){
			this.managerPackage = generateTable.getRootPackage()+".manager";
		}
		if(generateTable.isMapperXml()){
			this.mapperXmlPackage = "resources.mapping";
		}
		if(generateTable.isDao()){
			this.daoPackage = generateTable.getRootPackage()+".dao";
		}

	}


	public final String templeteBase = "template/";

	private String targetDir;

	private String rootPackage;

	private String domainPackage;

	private String mapperPackage;

	private String examplePackage;

	private String beanJsonPackage;

	private String managerPackage;

	private String daoPackage;

	private String mapperXmlPackage;

	private String queryPackage;


	public String getQueryPackage() {
		return queryPackage;
	}

	public void setQueryPackage(String queryPackage) {
		this.queryPackage = queryPackage;
	}

	public String getManagerPackage() {
		return managerPackage;
	}

	public void setManagerPackage(String managerPackage) {
		this.managerPackage = managerPackage;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getBeanJsonPackage() {
		return beanJsonPackage;
	}

	public void setBeanJsonPackage(String beanJsonPackage) {
		this.beanJsonPackage = beanJsonPackage;
	}

	private String primaryKey;

	public String getDomainPackage() {
		return domainPackage;
	}

	public void setDomainPackage(String domainPackage) {
		this.domainPackage = domainPackage;
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

	public String getMapperXmlPackage() {
		return mapperXmlPackage;
	}

	public void setMapperXmlPackage(String mapperXmlPackage) {
		this.mapperXmlPackage = mapperXmlPackage;
	}

	public String getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(String rootPackage) {
		this.rootPackage = rootPackage;
	}
}
