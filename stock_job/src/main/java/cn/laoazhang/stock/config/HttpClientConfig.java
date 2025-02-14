package cn.laoazhang.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : laoazhang
 * @date : 2025/02/14 22:23
 * @description : 定义访问http服务的配置类
 */
@Configuration
public class HttpClientConfig {

    /**
     * 定义RestTemplate bean
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
