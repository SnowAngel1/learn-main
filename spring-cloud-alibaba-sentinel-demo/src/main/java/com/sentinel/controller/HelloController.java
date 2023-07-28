package com.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/hello")
@Slf4j
public class HelloController {

    private static final String RESOURCES_NAME = "hello";

    @RequestMapping(value = "")
    public String hello(){
        Entry entry = null;
        try {
            entry = SphU.entry(RESOURCES_NAME);
            String str= "hello word";
            log.info("=====" + str+"========");
            return str;
        } catch (BlockException e) {
            log.info("被流控了");
            return "被流控了";
        }catch (Exception e){
            Tracer.traceEntry(e,entry);
        }finally {
            if (entry != null){
                entry.close();
            }
        }
        return null;
    }

    @PostConstruct
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //受保护的资源
        rule.setResource(RESOURCES_NAME);
        //设置流控规则QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //Set limit QPS to 20
        rule.setCount(1);
        rules.add(rule);
        //加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }


}
