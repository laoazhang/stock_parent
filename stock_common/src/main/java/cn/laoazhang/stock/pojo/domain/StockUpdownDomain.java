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
 * @date : 2025/01/01 20:43
 * @description : 股票涨跌信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockUpdownDomain {
    /**
     * 股票编码
     */
    private String code;

    /**
     * 股票名称
     */
    private String name;

    /**
     * 前收盘价
     */
    private BigDecimal preClosePrice;

    /**
     * 当前价格
     */
    private BigDecimal tradePrice;

    /**
     * 涨跌
     */
    private BigDecimal increase;

    /**
     * 涨幅
     */
    private BigDecimal upDown;

    /**
     * 振幅
     */
    private BigDecimal amplitude;

    /**
     * 交易量
     */
    private Long tradeAmt;

    /**
     * 交易金额
     */
    private BigDecimal tradeVol;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curDate;
}
