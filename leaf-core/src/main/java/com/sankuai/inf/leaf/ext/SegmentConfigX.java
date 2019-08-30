package com.sankuai.inf.leaf.ext;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sankuai.inf.leaf.common.PropertyFactory;

/**
 * connect to config server to get biz configurations.
 * 
 * @author lyf
 *
 */
@RefreshScope
@Component
public class SegmentConfigX {
	
	public static final Integer DEFAULT_STEP_SIZE = 1000;
	public static final Integer DEFAULT_MAX_ID     = 1;
	
	public static Integer stepSize = DEFAULT_STEP_SIZE;
	
	
	private Properties properties;
	
	private String jdbcUrl;
	private String jdbcUserName;
	private String jdbcPassword;
	private String zkAddress;
	private Integer zkPort;
	
	
	private String sysJson;
	
	public SegmentConfigX() {
		properties =  PropertyFactory.getProperties();
	}


	@Value("${leaf.dynamic.step.size}")
	public void setStepSize(Integer size) {
		if (size != null && size > 0) {
			stepSize = size;
		}
	}


	@Value("${leaf.jdbc.url:}")
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	@Value("${leaf.jdbc.username:}")
	public void setJdbcUserName(String jdbcUserName) {
		this.jdbcUserName = jdbcUserName;
	}

	@Value("${leaf.jdbc.password:}")
	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	@Value("${leaf.snowflake.zk.address:}")
	public void setZkAddress(String zkAddress) {
		this.zkAddress = zkAddress;
	}

	@Value("${leaf.snowflake.port:}")
	public void setZkPort(Integer zkPort) {
		this.zkPort = zkPort;
	}

	
	public String getSysJson() {
		return sysJson;
	}


	@Value("${sys.code.json}")
	public void setSysJson(String sysJson) {
		this.sysJson = sysJson;
	}
}
