# WebFlux.md
Wednesday, February 27th 2019, 12:00

在SpringBoot2.0中, 使用的是Spring5.0, 其中WebFlux可以支持

```java
@Configuration
public class RouterFunctionConfiguration {
    /**
     * 既支持Servlet规范, 又支持自定义规范, 如Netty(web server)
     * 定义GET请求, 并且返回所有对象 URI: /user/find/all
     * Mono 0-1个对象的集合
     * Flux 0-N个对象的集合
     * Reactive中的Flux或者Mono 是异步非阻塞的 提高了吞吐量
     * 集合对象基本上是同步处理
     * @param userRepository
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> userFindAll(UserRepository userRepository) {
        return RouterFunctions.route(RequestPredicates.GET("/user/find/all"), requests -> {
            Collection<User> users = userRepository.findAll();
            Flux<User> userFlux = Flux.fromIterable(users);
            return ServerResponse.ok().body(userFlux, User.class);
        });
    }
}
```
