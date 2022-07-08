package com.yang.springboot.service.rule.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

/**
 * @author Yang Hao
 * @date 2022-07-08 15:30
 */
@Rule(name = "weeks_rule", description = "这是星期的规则引擎", priority = 1)
public class WeeksRule {

    @Condition
    public boolean when(@Fact("weeks") String weeks) {

        if (weeks.equals("周五")) {
            System.out.printf("WeeksRule--参数条件满足weeks=周五; 此时 weeks= " + weeks);
            return true;
        }
        return false;
    }

    @Action(order = 1)
    public void action1() {
        System.out.println("WeeksRule规则执行的action1----");
    }

}
