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
    private String code;

    private String name;

    private BigDecimal preClosePrice;

    private BigDecimal tradePrice;

    private BigDecimal increase;

    private BigDecimal upDown;

    private BigDecimal amplitude;

    private Long tradeAmt;

    private BigDecimal tradeVol;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curDate;
}
