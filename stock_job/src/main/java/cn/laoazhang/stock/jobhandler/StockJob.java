package cn.laoazhang.stock.jobhandler;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author : laoazhang
 * @date : 2025/02/17 22:42
 * @description : 定义股票相关数据的定时任务
 */
@Component
public class StockJob {

    @XxlJob("hema_job_test")
    public void jobTest(){
        System.out.println("jobTest run.....");
    }
}
