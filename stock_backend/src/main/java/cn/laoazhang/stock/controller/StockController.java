package cn.laoazhang.stock.controller;

import cn.laoazhang.stock.pojo.domain.*;
import cn.laoazhang.stock.pojo.entity.StockRtInfo;
import cn.laoazhang.stock.service.StockService;
import cn.laoazhang.stock.vo.resp.PageResult;
import cn.laoazhang.stock.vo.resp.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author : laoazhang
 * @date : 2024/12/30 22:08
 * @description :
 */
@RestController
@RequestMapping("/api/quot")
@Api(value = "大盘板块功能接口定义",tags = "大盘板块功能")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * 获取国内最新大盘指数
     * @return
     */
    @ApiOperation(value = "获取国内最新大盘指数",notes = "获取国内最新大盘指数",response = R.class)
    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> innerIndexAll() {
        return stockService.innerIndexAll();
    }

    /**
     * 需求说明: 获取沪深两市板块最新数据，以交易总金额降序查询，取前10条数据
     * @return
     */
    @ApiOperation(value = "获取沪深两市板块最新数据，以交易总金额降序查询，取前10条数据",notes = "获取沪深两市板块最新数据，以交易总金额降序查询，取前10条数据",response = R.class)
    @GetMapping("/sector/all")
    public R<List<StockBlockDomain>> sectorAll() {
        return stockService.sectorAllLimit();
    }

    /**
     * 分页查询股票最新数据，并按照涨幅排序查询
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询股票最新数据，并按照涨幅排序查询",notes = "分页查询股票最新数据，并按照涨幅排序查询",response = R.class)
    @GetMapping("/stock/all")
    public R<PageResult> getStockPageInfo(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                          @RequestParam(name = "pageSize",required = false,defaultValue = "20")Integer pageSize) {
        return stockService.getStockPageInfo(page, pageSize);
    }

    /**
     * 统计沪深两市个股最新交易数据，并按涨幅降序排序查询前4条数据
     * @return
     */
    @ApiOperation(value = "统计沪深两市个股最新交易数据，并按涨幅降序排序查询前4条数据",notes = "查询沪深两市个股最新交易数据，并按照涨幅排序查询",response = R.class)
    @GetMapping("/stock/increase")
    public R<List<StockUpdownDomain>> getNewestStockInfo() {
        return stockService.getNewestStockInfo();
    }

    /**
     * 统计最新交易日下股标每分种涨跌停的数量
     * @return
     */
    @GetMapping("/stock/updown/count")
    @ApiOperation(value = "统计涨跌停个数",notes = "统计涨跌停个数",response = R.class)
    public R<Map> getStockUpdownCount() {
        return stockService.getStockUpdownCount();
    }

    /**
     * 功能描述：统计国内A股大盘T日和T-1日成交量对比功能（成交量为沪市和深市成交量之和）
     * @return
     */
    @GetMapping("/stock/tradeAmt")
    @ApiOperation(value = "统计国内A股大盘T日和T-1日成交量对比功能",notes = "（成交量为沪市和深市成交量之和）",response = R.class)
    public R<Map> stockTradeVol4InnerMarket() {
        return stockService.stockTradeVol4InnerMarket();
    }

    /**
     * 查询当前时间下股票的涨跌幅度区间统计功能
     * 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询点
     * @return
     */
    @GetMapping("/stock/updown")
    @ApiOperation(value = "查询当前时间下股票的涨跌幅度区间统计功能",notes = "查询当前时间下股票的涨跌幅度区间统计功能",response = R.class)
    public R<Map> getStockUpDown() {
        return stockService.stockUpDownScopeCount();
    }

    /**
     * 功能描述：查询单个个股的分时行情数据，也就是统计指定股票T日每分钟的交易数据；
     *         如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询时间点
     * @param code 股票编码
     * @return
     */
    @GetMapping("/stock/screen/time-sharing")
    @ApiOperation(value = "查询单个个股的分时行情数据",notes = "查询单个个股的分时行情数据",response = R.class)
    public R<List<Stock4MinuteDomain>> stockScreenTimeSharing(String code) {
        return stockService.stockScreenTimeSharing(code);
    }

    /**
     * 单个个股日K 数据查询 ，可以根据时间区间查询数日的K线数据
     * @param stockCode 股票编码
     */
    @RequestMapping("/stock/screen/dkline")
    @ApiOperation(value = "单个个股日K 数据查询",notes = "单个个股日K 数据查询",response = R.class)
    public R<List<Stock4EvrDayDomain>> getDayKLinData(@RequestParam("code") String stockCode){
        return stockService.getDayKLinData(stockCode);
    }
}
