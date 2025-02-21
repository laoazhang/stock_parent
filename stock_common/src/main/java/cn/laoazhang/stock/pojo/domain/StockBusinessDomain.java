package cn.laoazhang.stock.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : laoazhang
 * @date : 2025/02/21 21:37
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockBusinessDomain {

    /**
     * 股票编码
     */
    private String code;

    /**
     * 公司行业
     */
    private String trade;

    /**
     * 公司业务
     */
    private String business;

    /**
     * 公司名称
     */
    private String name;
}
