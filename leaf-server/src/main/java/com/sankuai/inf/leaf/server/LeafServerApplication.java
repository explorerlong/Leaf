package com.sankuai.inf.leaf.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sankuai.inf.leaf.ext.BizSystemManager;
import com.sankuai.inf.leaf.ext.SegmentConfigX;

@SpringBootApplication
public class LeafServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeafServerApplication.class, args);
	}
	
	
	@Bean
	public SegmentConfigX createSegmentConfigX() {
		return new SegmentConfigX();
	}
	
	@Bean
	public BizSystemManager createBizSystemManager() {
		return new BizSystemManager();
	}
	
}
