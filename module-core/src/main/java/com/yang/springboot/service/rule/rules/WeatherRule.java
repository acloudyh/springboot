package com.yang.springboot.service.rule.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

/**
 * @author Yang Hao
 * @date 2022-07-08 14:56
 */
@Rule(name = "weather_rule", description = "这是天气的规则引擎", priority = 1)
public class WeatherRule {
    /**
     * 当 该判断返回true时.才能执行下面的 action方法
     *
     * @param param1 参数1
     * @param param2 参数2
     * @return 返回结果
     */
    @Condition
    public boolean when(@Fact("param1") boolean param1, @Fact("param2") String param2) {
        if (param1 && param2.equals("大雨")) {
            System.out.printf("WeatherRule--参数条件满足param1=true 并且param2=大雨; 此时 param1= " + param1 + "并且param2=" + param2);
            return true;
        }
        return false;
    }

    @Action(order = 1)
    public void action1(@Fact("param1") boolean param1) {
        System.out.println("这是action1 打印param1----" + param1);
    }

    @Action(order = 2)
    public void action2(@Fact("param1") boolean param1, @Fact("param2") String param2) {
        System.out.println("这是action2 打印所有参数----" + param1 + "-----" + param2);
    }
}
