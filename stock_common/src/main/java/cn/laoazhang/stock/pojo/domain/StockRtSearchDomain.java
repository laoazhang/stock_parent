package cn.laoazhang.stock.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : laoazhang
 * @date : 2025/02/20 21:35
 * @description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockRtSearchDomain {
    /**
     * 股票编码
     */
    private String code;
    /**
     * 股票名称
     */
    private String name;
}
