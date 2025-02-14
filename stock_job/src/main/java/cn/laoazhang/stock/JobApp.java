package cn.laoazhang.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : laoazhang
 * @date : 2025/02/14 22:20
 * @description :
 */
@SpringBootApplication
@MapperScan("cn.laoazhang.stock.mapper")
public class JobApp {
    public static void main(String[] args) {
        SpringApplication.run(JobApp.class, args);
    }
}
