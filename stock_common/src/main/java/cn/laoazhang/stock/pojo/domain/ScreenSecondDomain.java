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
 * @description : 个股实时交易流水数据封装
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenSecondDomain {
    /**
     * 日期，eg:202201280809
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date Date;
    /**
     * 交易量
     */
    private Long tradeAmt;
    /**
     * 当前交易总金额
     */
    private BigDecimal tradeVol;
    /**
     * 当前价格
     */
    private BigDecimal tradePrice;

}
