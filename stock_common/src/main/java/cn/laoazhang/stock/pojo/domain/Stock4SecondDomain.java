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
 * @date : 2025/02/12 22:36
 * @description : 个股分时(second)数据封装
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock4SecondDomain {
    /**
     * 交易量
     */
    private Long tradeAmt;
    /**
     * 最低价
     */
    private BigDecimal lowPrice;
    /**
     * 前收盘价
     */
    private BigDecimal preClosePrice;
    /**
     * 最高价
     */
    private BigDecimal highPrice;
    /**
     * 开盘价
     */
    private BigDecimal openPrice;
    /**
     * 当前交易总金额
     */
    private BigDecimal tradeVol;
    /**
     * 当前价格
     */
    private BigDecimal tradePrice;
    /**
     * 日期，eg:202201280809
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "Asia/Shanghai")
    private Date curDate;
}
