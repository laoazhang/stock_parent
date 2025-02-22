package cn.laoazhang.stock.service;

import cn.laoazhang.stock.pojo.domain.*;
import cn.laoazhang.stock.pojo.entity.StockOuterMarketIndexInfo;
import cn.laoazhang.stock.pojo.entity.StockRtInfo;
import cn.laoazhang.stock.vo.resp.PageResult;
import cn.laoazhang.stock.vo.resp.R;

import java.util.List;
import java.util.Map;

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

    /**
     * 统计最新交易日下股标每分种涨跌停的数量
     * @return
     */
    R<Map> getStockUpdownCount();

    /**
     * 功能描述：统计国内A股大盘T日和T-1日成交量对比功能（成交量为沪市和深市成交量之和）
     * @return
     */
    R<Map> stockTradeVol4InnerMarket();

    /**
     * 查询当前时间下股票的涨跌幅度区间统计功能
     * 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询点
     * @return
     */
    R<Map> stockUpDownScopeCount();

    /**
     * 功能描述：查询单个个股的分时行情数据，也就是统计指定股票T日每分钟的交易数据；
     *         如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询时间点
     * @param code 股票编码
     * @return
     */
    R<List<Stock4MinuteDomain>> stockScreenTimeSharing(String code);

    /**
     * 单个个股日K 数据查询 ，可以根据时间区间查询数日的K线数据
     * @param stockCode 股票编码
     */
    R<List<Stock4EvrDayDomain>> getDayKLinData(String stockCode);

    /**
     * 外盘指数行情数据查询，根据时间和大盘点数降序排序取前4
     * @return
     */
    R<List<OuterMarketDomain>> outerIndexAll();

    /**
     * 根据输入的个股代码，进行模糊查询，返回证券代码和证券名称
     * @param code
     * @return
     */
    R<List<StockRtSearchDomain>> searchAll(String code);

    /**
     * 个股主营业务查询接口
     * @param code
     * @return
     */
    R<List<StockBusinessDomain>> searchBusiness(String code);

    /**
     * 单个个股周K 数据查询 ，可以根据时间区间查询一周的K线数据
     * @param stockCode 股票编码
     */
    R<List<Stock4EvrWeekDomain>> getWeekKLinData(String stockCode);

}
