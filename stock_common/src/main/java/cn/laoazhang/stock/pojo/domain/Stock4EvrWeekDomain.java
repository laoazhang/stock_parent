package cn.laoazhang.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : laoazhang
 * @date : 2025/02/13 21:38
 * @description : 个股周K数据封装
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock4EvrWeekDomain {

    /**
     * 一周内平均价
     */
    private BigDecimal avgPrice;

    /**
     * 一周内最低价
     */
    private BigDecimal minPrice;

    /**
     * 一周开盘价
     */
    private BigDecimal openPrice;

    /**
     * 一周内最高价
     */
    private BigDecimal maxPrice;

    /**
     * 当前收盘价格指周五收盘时的价格，如果当天不到周五，则显示最新cur_price）
     */
    private BigDecimal closePrice;

    /**
     * 一周内最大的时间，eg:202201280809
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "Asia/Shanghai")
    private Date mxTime;

    /**
     * 股票编码
     */
    private String stockCode;

}
