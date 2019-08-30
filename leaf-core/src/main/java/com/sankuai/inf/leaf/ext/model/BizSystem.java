package com.sankuai.inf.leaf.ext.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public class BizSystem {
	private String code;
	private List<BizModule> modules = new LinkedList<>();
	private Set<String> moduleSet = new HashSet<>();
	
	
	public BizSystem(String code, Collection<String> moduleCodeList) {
		this.code = code;
		Optional.ofNullable(moduleCodeList)
			.ifPresent(list -> {
				list.forEach(m -> {
					moduleSet.add(m);
					modules.add(new BizModule(m));
				});
			});
	}
	
	
	public boolean containsModule(String module) {
		return module != null && moduleSet.contains(module);
	}


	public String getCode() {
		return code;
	}
	
	public boolean match(String system) {
		return code != null && code.equals(system);
	}
	
	public boolean contailModule(String module) {
		return moduleSet.contains(module);
	}
}
