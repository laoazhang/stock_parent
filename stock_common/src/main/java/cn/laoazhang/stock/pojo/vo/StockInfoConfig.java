package cn.laoazhang.stock.pojo.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author : laoazhang
 * @date : 2024/12/30 21:56
 * @description :
 */
@ConfigurationProperties(prefix = "stock")
@Data
public class StockInfoConfig {
    //A股大盘ID集合
    private List<String> inner;
    //外盘ID集合
    private List<String> outer;
    //股票区间
    private List<String> upDownRange;
}
