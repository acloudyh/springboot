## springboot 学习demo

### 仅记录个人学习过程，不定期更新

## 填坑之路
- [mysql5.7的json类型中文乱码问题](https://www.jianshu.com/p/5bcf37b76d3c)
- [fasterxml中string字符串转对象json格式单引号错误](https://www.jianshu.com/p/7d6f8926710d)

## 更新记录

#### 2019.5.13
1. 阿里easyexcel 利用注解model导出文件
2. 无注解动态导出
> TIPS: 2018年12月之后没有提交代码。issue也没有更新。怀疑停止维护（不太建议使用）
> 另外，对大数据量 poi 不会自动生成多个sheet；但是，poi大数据量会出现oom


### 2019.5.7
1. BigDecimalUtil数字运算工具类
> TIPS: mysql涉及到浮点型数据时采用decimal(10,2)，具体精确度视情况而定；此时jpa中对应的字段 (吃过亏)
```
@Column(columnDefinition = "decimal(15,3) COMMENT ‘用量'")
private BigDecimal overCycleDataUsage = BigDecimal.ZERO;
```

#### 2019.4.29
1. netty jt808协议 [大佬的github](https://github.com/hylexus/jt-808-protocol)

#### 2019.4.24
1. springboot 整合spring kafka（先暂停）


#### 2019.4.22
1. 单机版redis Jackson2JsonRedisSerializer序列化类型（集群暂不做处理）

#### 2019.4.19
1. 导出文件工具类ExcelUtil
2. 时间转换工具类DateUtil
3. 邮件模板thymeleaf调用
4. 导出数量过大，后台导出以附件的形式发送到邮箱

#### 2019.4.18
1. 多模块项目初始化
2. jpa简单增删改查及分页查询
3. 导出数据web导出，数量不多情况下



