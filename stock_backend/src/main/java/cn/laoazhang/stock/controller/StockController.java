package cn.laoazhang.stock.controller;

import cn.laoazhang.stock.pojo.domain.InnerMarketDomain;
import cn.laoazhang.stock.pojo.domain.StockBlockDomain;
import cn.laoazhang.stock.pojo.domain.StockUpdownDomain;
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
}
