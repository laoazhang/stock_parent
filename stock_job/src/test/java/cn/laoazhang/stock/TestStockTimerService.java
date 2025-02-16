package cn.laoazhang.stock;

import cn.laoazhang.stock.service.StockTimerTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : laoazhang
 * @date : 2025/02/14 23:13
 * @description :
 */

@SpringBootTest
public class TestStockTimerService {

    @Autowired
    private StockTimerTaskService stockTimerService;

    /**
     * 获取大盘数据
     */
    @Test
    public void test01(){
        stockTimerService.getInnerMarketInfo();
    }

    /**
     * 获取个股数据
     */
    @Test
    public void test02(){
        stockTimerService.getStockRtIndex();
    }

}
