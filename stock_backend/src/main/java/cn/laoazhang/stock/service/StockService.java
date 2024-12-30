package cn.laoazhang.stock.service;

import cn.laoazhang.stock.pojo.domain.InnerMarketDomain;
import cn.laoazhang.stock.vo.resp.R;

import java.util.List;

/**
 * @author : laoazhang
 * @date : 2024/12/30 22:11
 * @description : 定义股票服务接口
 */
public interface StockService {
    /**
     * 获取国内大盘的实时数据
     * @return
     */
    R<List<InnerMarketDomain>> innerIndexAll();
}
