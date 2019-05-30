package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Vauke on 5/29/19.
 */
/** 用于指定当前类是一个Spring的配置类 */
@Configuration
/** 设置要扫描的包, 属性为value, 指定多个包的路径, basePackages作用同value */
// 改进以下包扫描的关系, 使用@Import注解设置主从配置关系, 这样config包下的JdbcConfig.java也就不再需要@Configuration注解了
// @ComponentScan(basePackages = {"com.vauke", "config"})
/** 这时就不用再扫描config包 */
@ComponentScan(basePackages="com.vauke")
/** 指定从配置类的Class类类型 */
@Import(JdbcConfig.class)
public class MainConfig {
}
