package com.yang.springboot.service.rule;

import com.yang.springboot.service.rule.rules.WeatherRule;
import com.yang.springboot.service.rule.rules.WeeksRule;
import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.support.composite.ActivationRuleGroup;

/**
 * @author Yang Hao
 * @date 2022-07-08 14:56
 */
public class TestRuleDemo {

    /**
     * easy-rules 规则引擎 builder demo
     *
     * @param args
     */
    public static void main(String[] args) {
        //----------------------------------------RuleBuilder 形式------------------------------------
//        Rule weatherRule = new RuleBuilder()
//                .name("weather_rule")
//                .description("这是天气的规则引擎")
//                .when(facts -> facts.get("rain").equals(true))
//                .then(facts -> System.out.println("规则成立,今天会下雨"))
//                .build();
//
//        Rules rules = new Rules();
//        rules.register(weatherRule);
//
//        Facts facts = new Facts();
//        facts.put("rain", true);
//
//        RulesEngine rulesEngine = new DefaultRulesEngine();
//        rulesEngine.fire(rules, facts);


        //----------------------------------------注解 形式单个rule规则------------------------------------
//        Rules rules = new Rules();
//        rules.register(new WeatherRule());
//
//        Facts facts = new Facts();
//        facts.put("param1", true);
//        facts.put("param2", "大雨2");
//
//        RulesEngine rulesEngine = new DefaultRulesEngine();
//        rulesEngine.fire(rules, facts);

        //----------------------------------------注解 形式多个rule规则------------------------------------

        /**
         * UnitRuleGroup：要么应用所有rule，要么不应用任何rule。
         *
         * ActivationRuleGroup：它触发第一个适用的rule并忽略组中的其他rule（XOR 逻辑）。
         * rule首先按其在组内的自然顺序排序，如果设置了priority，那么数字越小的优先级越高。
         *
         * ConditionalRuleGroup：：只有具有最高优先级的rule评估为真，才触发其余rule。
         */
        WeatherRule weatherRule = new WeatherRule();
        WeeksRule weeksRule = new WeeksRule();
        ActivationRuleGroup ruleGroup = new ActivationRuleGroup("group_role", "这是组合规则");
        ruleGroup.addRule(weatherRule);
        ruleGroup.addRule(weeksRule);

        Rules rules = new Rules();
        rules.register(ruleGroup);

        Facts facts = new Facts();
        facts.put("param1", true);
        facts.put("param2", "大雨");
        facts.put("weeks", "周五");
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}
