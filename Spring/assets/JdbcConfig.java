package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by Vauke on 5/29/19.
 */
/** 在主配置文件中使用@Import引入这个配置类后就可以去掉@Configuration注解了*/
// @Configuration
/** 指定数据源的配置文件路径 */
@PropertySource("classpath:db.properties")
public class JdbcConfig {
    /**
     在Spring 4.3之前需要手动配置

    @Bean
    public static PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceHolderConfigurer () {
        return new PropertySourcesPlaceholderConfigurer();
    }
     */

    /** @Value 用于向容器中注入基本类型和String类型的依赖, 这里结合SpEL读取配置文件 */
    @Value("${driverClass}")
    private String jdbcDriver;

    @Value("${jdbcUrl}")
    private String jdbcUrl;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;


    /** 将方法的返回值作为bean注入容器中, name和value属性都是为bean指定id */
    @Bean(name = "dataSource", autowire = Autowire.BY_NAME)
    public DataSource createDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(jdbcDriver);
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(user);
            dataSource.setPassword(password);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    /** 如果方法中带有参数, 则会根据参数的类型到容器中查找, 找到唯一的类型匹配的bean后会自动注入, 否则报错, 如果有多个, 则使用@Qualifier指定 */
    // @Bean("dbAssist")
    // public DBAssist createDBAssist(DataSource dataSource) {
    //     return new DBAssist(dataSource);
    // }
}
