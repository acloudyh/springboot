package com.yang.springboot.service.rule;

import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;

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
        Rule weatherRule = new RuleBuilder()
                .name("weather_rule")
                .description("这是天气的规则引擎")
                .when(facts -> facts.get("rain").equals(true))
                .then(facts -> System.out.println("规则成立,今天会下雨"))
                .build();

        Rules rules = new Rules();
        rules.register(weatherRule);

        Facts facts = new Facts();
        facts.put("rain", true);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);

    }
}
