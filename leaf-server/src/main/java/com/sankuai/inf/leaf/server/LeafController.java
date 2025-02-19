package com.sankuai.inf.leaf.server;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.sankuai.inf.leaf.ext.BizSystemManager;
import com.sankuai.inf.leaf.server.exception.LeafServerException;
import com.sankuai.inf.leaf.server.exception.NoKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeafController {
    private Logger logger = LoggerFactory.getLogger(LeafController.class);
    @Autowired
    SegmentService segmentService;
    @Autowired
    SnowflakeService snowflakeService;
    
    @Autowired
    BizSystemManager bizSystemManager;

    @RequestMapping(value = "/api/segment/get/{system}/{module}/{key}")
    public String getSegmentID(@PathVariable("system") String system, @PathVariable("module") String module, @PathVariable("key") String key) {
    	String fullKey = checkBizSystem(system, module, key);
        return get(key, segmentService.getId(fullKey));
    }

    @RequestMapping(value = "/api/snowflake/get/{key}")
    public String getSnowflakeID(@PathVariable("key") String key) {
        return get(key, snowflakeService.getId(key));

    }

    private String get(@PathVariable("key") String key, Result id) {
        Result result;
        if (key == null || key.isEmpty()) {
            throw new NoKeyException();
        }

        result = id;
        if (result.getStatus().equals(Status.EXCEPTION)) {
            throw new LeafServerException(result.toString());
        }
        return String.valueOf(result.getId());
    }
    
    private String checkBizSystem(String system, String module, String key) throws IllegalStateException {
    	bizSystemManager.ensureContainsSystemModule(system, module);
    	return system.concat("-").concat(module).concat("-").concat(key);
    }
}
