package com.sankuai.inf.leaf.ext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sankuai.inf.leaf.ext.model.BizSystem;

/**
 * 
 * @author lyf
 *
 */
@Component
public class BizSystemManager implements InitializingBean {
	
	private static ObjectMapper jsonMapper = new ObjectMapper();
	
	private volatile Map<String, BizSystem> systemMap = new HashMap<>();
	
	private volatile String systemJson;
	
	@Autowired
	private SegmentConfigX config;
	
	
	

	public void ensureContainsSystemModule(String system, String module) throws IllegalStateException {
		BizSystem bizSystem = this.getSystem(system);
		if (bizSystem != null) {
			if (module != null && !bizSystem.contailModule(module)) {
				throw new IllegalStateException("Module["+ module + "] of BizSystem[" + system + "] not exists!");
			}
		} else {
			throw new IllegalStateException("BizSystem[" + system + "] not exists!");
		}
	}
	
	public BizSystem getSystem(String code) {
		refresh();
		return systemMap.get(code);
	}
	
	private void registerSystem(Map<String, BizSystem> map, BizSystem system) {
		map.put(system.getCode(), system);
	}
	
	/**
	 *  rebuild the system list on configuration changed.
	 */
	@SuppressWarnings("unchecked")
	private void refresh() {
		Map<String, List<String>> map = null;
		String newSystemJson = config.getSysJson();
		
		if (newSystemJson != null && !newSystemJson.equals(systemJson)) {
			Map<String, BizSystem> systemMap = new HashMap<>();
			this.systemJson = newSystemJson;
			
			try {
				map = (Map<String, List<String>>) jsonMapper.readValue(newSystemJson, new TypeReference<Map<String, List<String>>>(){});
			} catch (Exception e) {
				throw new IllegalArgumentException("Fail to read dynamical system json configuration!");
			}
			
			map.forEach((key, value) -> {
				registerSystem(systemMap, new BizSystem(key, value));
			}); 
			this.systemMap = systemMap;
		}
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		refresh();
	}
	
}
