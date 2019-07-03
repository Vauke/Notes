# readme.md
Thursday, June 27th 2019, 23:12

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

- [ readme.md](#readmemd)
- [ JDBC编程步骤](#jdbc编程步骤)

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
