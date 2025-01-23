package cn.laoazhang.stock.service;

import cn.laoazhang.stock.pojo.domain.InnerMarketDomain;
import cn.laoazhang.stock.pojo.domain.StockBlockDomain;
import cn.laoazhang.stock.pojo.domain.StockUpdownDomain;
import cn.laoazhang.stock.pojo.entity.StockRtInfo;
import cn.laoazhang.stock.vo.resp.PageResult;
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

    /**
     * 需求说明: 获取沪深两市板块最新数据，以交易总金额降序查询，取前10条数据
     * @return
     */
    R<List<StockBlockDomain>> sectorAllLimit();

    /**
     * 分页查询股票最新数据，并按照涨幅排序查询
     * @param page
     * @param pageSize
     * @return
     */
    R<PageResult> getStockPageInfo(Integer page, Integer pageSize);

    /**
     * 统计沪深两市个股最新交易数据，并按涨幅降序排序查询前4条数据
     * @return
     */
    R<List<StockUpdownDomain>> getNewestStockInfo();

}
