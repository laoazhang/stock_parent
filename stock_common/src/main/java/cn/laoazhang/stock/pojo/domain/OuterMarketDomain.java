package cn.laoazhang.stock.pojo.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : laoazhang
 * @date : 2025/02/19 21:54
 * @description : 定义封装外盘数据的实体类
 */
@Data
public class OuterMarketDomain {
    /**
     * 大盘名称
     */
    private String name;

    /**
     * 大盘当前点
     */
    private BigDecimal curPoint;

    /**
     * 大盘涨跌值
     */
    private BigDecimal updown;

    /**
     * 大盘涨幅
     */
    private BigDecimal rose;

    /**
     * 当前时间
     */
    private Date curTime;
}
