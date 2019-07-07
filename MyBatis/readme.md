# readme.md
Thursday, June 27th 2019, 23:12

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

- [readme.md](#readmemd)
- [JDBC编程步骤](#jdbc编程步骤)
  - [JDBC存在的问题](#jdbc存在的问题)
- [MyBatis架构](#mybatis架构)
  - [MyBatis编程步骤](#mybatis编程步骤)
    - [#{}和${}的区别](#和的区别)
  - [SqlSession](#sqlsession)
  - [Mapper动态代理](#mapper动态代理)
  - [SqlMapConfig.xml](#sqlmapconfigxml)
  - [动态SQL](#动态sql)

<!-- /code_chunk_output -->

# JDBC编程步骤

```java
public class JDBCDemo {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void jdbc() {
        try {
            // 1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 创建Connection连接对象
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // 3. 创建PreparedStatement对象
            String sql = "select username, address from user where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 5);

            // 4. 执行SQL语句, 获得ResultSet结果集
            resultSet = preparedStatement.executeQuery();

            // 5. 遍历ResultSet结果集, 封装查询结果
            User user;

            while (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setAddress(resultSet.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 释放连接和资源 ResultSet -> PrepareStatement -> Connection
            if (resultSet != null)
                resultSet.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }
}
```

## JDBC存在的问题

1. 数据库连接的创建和释放操作频繁, 容易造成系统资源的浪费, 并且创建和释放连接需要时间, 影响系统性能, 使用数据库连接池解决此问题.
2. SQL语句写在代码中不利于维护
3. ResultSet的解析封装不方便, 重复代码多

# MyBatis架构

![MyBatis架构](./assets/MyBatis架构.png)

1. SqlMapConfig.xml，此文件作为mybatis的全局配置文件，配置了mybatis的运行环境等信息。
mapper.xml文件即sql映射文件，文件中配置了操作数据库的sql语句。此文件需要在SqlMapConfig.xml中加载。
2. 通过mybatis环境等配置信息构造SqlSessionFactory
3. 由SqlSessionFactory会话工厂创建SqlSession即会话，操作数据库需要通过SqlSession进行。
4. mybatis底层自定义了Executor执行器接口操作数据库，Executor接口有两个实现，一个是基本执行器、一个是缓存执行器。
5. MappedStatement也是mybatis一个底层封装对象，它包装了mybatis配置信息及sql映射信息等。mapper.xml文件中一个sql对应一个MappedStatement对象，sql的id即是Mapped statement的id。
6. MappedStatement对sql执行输入参数进行定义，包括HashMap、基本类型、pojo，Executor通过MappedStatement在执行sql前将输入的java对象映射至sql中，输入参数映射就是jdbc编程中对preparedStatement设置参数。
7. MappedStatement对sql执行输出结果进行定义，包括HashMap、基本类型、pojo，Executor通过MappedStatement在执行sql后将输出结果映射至java对象中，输出结果映射过程相当于jdbc编程中对结果的解析处理过程。

## MyBatis编程步骤

```xml
<mapper namespace="test">

	<!-- id:statement的id 或者叫做sql的id-->
	<!-- parameterType:声明输入参数的类型 -->
	<!-- resultType:声明输出结果的类型，应该填写pojo的全路径 -->
	<!-- #{}：输入参数的占位符，相当于jdbc的？占位符 -->
	<select id="findUserById" parameterType="int"
		resultType="com.vauke.pojo.User">
		SELECT * FROM `user` WHERE id  = #{id}
	</select>

    <!-- 如果返回多个结果，mybatis会自动把返回的结果放在list容器中 -->
	<!-- resultType指定List泛型的类型 -->
	<select id="findUserByUsername" parameterType="string"
		resultType="com.vauke.pojo.User">
        <!-- 对应的SQL
            SELECT * FROM `user` WHERE username LIKE 'username'
        -->
		SELECT * FROM `user` WHERE username LIKE #{username}

        <!-- 使用通配符时, SQL语句的多种写法 -->
        <!-- 1. 使用${value}, 对应的SQL
            SELECT * FROM `user` WHERE username LIKE '%value%'
        -->
        SELECT * FROM `user` WHERE username LIKE '%${value}%'

        <!-- 2. 使用#{username}, 对应的SQL
            SELECT * FROM `user` WHERE username LIKE "%"'username'"%"
        -->
        SELECT * FROM `user` WHERE username LIKE "%"#{username}"%"
	</select>

    <!-- 保存用户 -->
	<insert id="saveUser" parameterType="com.vauke.pojo.User">
        <!-- selectKey 标签实现主键返回
                keyColumn:主键对应的表中的哪一列
                keyProperty：主键对应的POJO中的哪一个属性
                order： BEFORE或AFTER, 设置在执行insert语句前执行此sql，还是在执行insert语句之后执行此sql
                resultType：设置返回的id的类型
         -->
        <selectKey keyColumn="id" keyProperty="id" order="AFTER" resultType="int">
            SELECT LAST_INSERT_ID()
        </selectKey>

        <!-- 使用#{}并填写对应的getter名可以进行POJO属性的自动获取和填充 -->
		INSERT INTO `user`
		(username,birthday,sex,address) VALUES
		(#{username},#{birthday},#{sex},#{address})
	</insert>

<!-- 以下为orders表的mapper.xml -->
    <!-- 查询所有订单, 由于表中为user_id而属性为userId, 不一致, 直接这样查会有结果但结果中userId没有值 -->
    <select id="selectOrdersList" resultType="orders">
        select * from orders
    </select>

    <!-- 使用resultMap解决 -->
    <select id="selectOrdersList" resultMap="ordersResultMap">
        select * from orders
    </select>

    <resultMap id="ordersResultMap" type="orders">
        <!-- 只需指定属性和表列名不同的属性, 相同的不用配置, 会自动识别 -->
        <result column="user_id" property="userId"/>
    </resultMap>
</mapper>
```

### #{}和${}的区别

`#{}`表示一个*占位符*, 类似JDBC中PreparedStatement中的`?`, 可以*防止SQL注入*, 会自动进行java和JDBC的类型转换, 可用于*接收基本类型和POJO的属性值*, *转换后会在值上包裹单引号`''`*

`${}`表示字符串*拼接*, *不防*SQL注入, *不进行java和JDBC之间的类型转换*, 只能写为`${value}`

```java
public void testMyBatisNotUsingMapper() {
    // 1. 读取sqlMapConfig配置
    InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");

    // 2. 创建SqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder.builder(inputStream);

    // 3. 获得SqlSession对象
    SqlSession sqlSession = sqlSessionFactory.openSession();

    // 4. 执行查询
    // 第一个参数是UserMapper.xml的statement的id，第二个参数是执行sql需要的参数
    User user = sqlSession.selectOne("test.findUserById", 1);

    // 5. 关闭资源
    sqlSession.close();

    // ...

    List<User> userList = sqlSession.selectList("test.findUserByUsername", "五");

    User user = new User();
    // ...
    int affectedRowCount = sqlSession.insert("test.saveUser", user);

    // 添加selectKey标签查询id后, 可直接使用POJO的getter获得id
    int id = user.getId();
}
```

## SqlSession

> SqlSession中封装了对数据库的操作，如：查询、插入、更新、删除等。SqlSession通过SqlSessionFactory创建。SqlSessionFactory是通过SqlSessionFactoryBuilder进行创建。
> SqlSessionFactoryBuilder用于创建SqlSessionFacoty，SqlSessionFacoty一旦创建完成就不需要SqlSessionFactoryBuilder了，因为SqlSession是通过SqlSessionFactory创建的。所以可以将SqlSessionFactoryBuilder当成一个工具类使用，最佳使用范围是方法范围即方法体内局部变量。
> SqlSessionFactory是一个接口，接口中定义了openSession的不同重载方法，SqlSessionFactory的*最佳使用范围是整个应用运行期间*，一旦创建后可以重复使用，通常以*单例*模式管理SqlSessionFactory

## Mapper动态代理

> Mapper接口开发方法只需要程序员编写Mapper接口（相当于Dao接口），由Mybatis框架根据接口定义创建接口的动态代理对象，代理对象的方法体同上边Dao接口实现类方法。

要遵循的规范:
1. Mapper.xml中定义的namespace要和对应的Mapper接口类的全限定类名相同
2. Mapper.xml中定义的sql的id要和方法名一致
3. Mapper.xml中的parameterType和方法的参数类型要一致
4. Mapper.xml中的resultType和方法的返回值或返回值附带的泛型一致

由此, 对应以上`UserMapper.xml`的java代码如下(UserMapper.xml中的namespace要先更改为UserMapper接口的路径):

```java
public interface UserMapper {
    public User findUserById(Integer id);

    public List<User> findUserByUsername(String username);

    public int saveUser(User user);
}
```

## SqlMapConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<!-- 是用resource属性加载外部配置文件 -->
	<properties resource="db.properties">
		<!-- 在properties内部用property定义属性 -->
		<!-- 如果外部配置文件有该属性，则内部定义属性被外部属性覆盖 -->
		<property name="jdbc.username" value="root123" />
		<property name="jdbc.password" value="root123" />
	</properties>

    <typeAliases>
		<!-- 单个类型别名定义 -->
		<typeAlias alias="user" type="com.vauke.pojo.User" />

		<!-- 批量类型别名定义，扫描整个包下的类，别名为类名（大小写不敏感） -->
		<package name="com.vauke.pojo" />
		<package name="其它包" />
	</typeAliases>

	<!-- 和spring整合后 environments配置将废除 -->
	<environments default="development">
		<environment id="development">
			<!-- 使用jdbc事务管理 -->
			<transactionManager type="JDBC" />
			<!-- 数据库连接池 -->
			<dataSource type="POOLED">
				<property name="driver" value="${jdbc.driver}" />
				<property name="url" value="${jdbc.url}" />
				<property name="username" value="${jdbc.username}" />
				<property name="password" value="${jdbc.password}" />
			</dataSource>
		</environment>
	</environments>

	<!-- 加载映射文件 -->
	<mappers>
        <!-- 加载单个mapper配置的2种方式 -->
        <!-- 1. 使用resource指定xml的位置 -->
		<mapper resource="mapper/UserMapper.xml" />
        <!-- 2. 使用class指定mapper接口类的路径, 需要UserMapper接口和UserMapper.xml名称相同且在同一目录中 -->
        <mapper class="com.vauke.mapper.UserMapper" />

        <!-- 加载多个配置, 注册指定包下的所有mapper,需要UserMapper接口和UserMapper.xml名称相同且在同一目录中 -->
        <package name="com.vauke.mapper" />
	</mappers>
</configuration>
```

## 动态SQL
