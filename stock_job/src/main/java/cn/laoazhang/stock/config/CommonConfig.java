package cn.laoazhang.stock.config;

import cn.laoazhang.stock.pojo.vo.StockInfoConfig;
import cn.laoazhang.stock.utils.IdWorker;
import cn.laoazhang.stock.utils.ParserStockInfoUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : laoazhang
 * @date : 2025/02/14 22:52
 * @description :
 */
@EnableConfigurationProperties(StockInfoConfig.class)
@Configuration
public class CommonConfig {

    /**
     * 配置基于雪花算法生成全局唯一id
     *   参与元算的参数： 时间戳 + 机房id + 机器id + 序列号
     *   保证id唯一
     * @return
     */
    @Bean
    public IdWorker idWorker(){
        //指定当前为1号机房的2号机器生成
        return new IdWorker(2L,1L);
    }

    /**
     * 配置解析工具bean
     * @param idWorker
     * @return
     */
    @Bean
    public ParserStockInfoUtil parserStockInfoUtil(IdWorker idWorker){
        return new ParserStockInfoUtil(idWorker);
    }
}
