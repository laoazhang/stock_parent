package cn.laoazhang.stock.service.impl;

import cn.laoazhang.stock.mapper.StockBlockRtInfoMapper;
import cn.laoazhang.stock.mapper.StockBusinessMapper;
import cn.laoazhang.stock.mapper.StockMarketIndexInfoMapper;
import cn.laoazhang.stock.mapper.StockRtInfoMapper;
import cn.laoazhang.stock.pojo.domain.InnerMarketDomain;
import cn.laoazhang.stock.pojo.domain.StockBlockDomain;
import cn.laoazhang.stock.pojo.domain.StockUpdownDomain;
import cn.laoazhang.stock.pojo.vo.StockInfoConfig;
import cn.laoazhang.stock.service.StockService;
import cn.laoazhang.stock.utils.DateTimeUtil;
import cn.laoazhang.stock.vo.resp.PageResult;
import cn.laoazhang.stock.vo.resp.R;
import cn.laoazhang.stock.vo.resp.ResponseCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : laoazhang
 * @date : 2024/12/30 22:14
 * @description :
 */
@Service("stockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private StockBusinessMapper stockBusinessMapper;

    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;

    @Autowired
    private StockInfoConfig stockInfoConfig;

    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;

    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;

    /**
     * 获取国内大盘的实时数据
     *
     * @return
     */
    @Override
    public R<List<InnerMarketDomain>> innerIndexAll() {
        //1.获取国内A股大盘的id集合
        List<String> inners = stockInfoConfig.getInner();
        //2.获取最近股票交易日期
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock测试数据，后期数据通过第三方接口动态获取实时数据 可删除
        lastDate = DateTime.parse("2022-01-02 09:32:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //3.将获取的java Date传入接口
        List<InnerMarketDomain> list = stockMarketIndexInfoMapper.getMarketInfo(inners, lastDate);
        //4.返回查询结果
        return R.ok(list);

    }

    /**
     * 需求说明: 沪深两市板块分时行情数据查询，以交易时间和交易总金额降序查询，取前10条数据
     *
     * @return
     */
    @Override
    public R<List<StockBlockDomain>> sectorAllLimit() {
        //获取股票最新交易时间点
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock数据,后续删除
        lastDate = DateTime.parse("2021-12-21 14:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //1.调用mapper接口获取数据
        List<StockBlockDomain> infos = stockBlockRtInfoMapper.sectorAllLimit(lastDate);
        //2.组装数据
        if (CollectionUtils.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(infos);
    }

    /**
     * 分页查询股票最新数据，并按照涨幅排序查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public R<PageResult> getStockPageInfo(Integer page, Integer pageSize) {
        //1.设置PageHelper分页参数
        PageHelper.startPage(page, pageSize);
        //2.获取当前最新的股票交易时间点
        Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //todo
        curDate = DateTime.parse("2022-06-07 15:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //3.调用mapper接口查询
        List<StockUpdownDomain> infos= stockRtInfoMapper.getNewestStockInfo(curDate);
        if (CollectionUtils.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA);
        }
        //4.组装PageInfo对象，获取分页的具体信息,因为PageInfo包含了丰富的分页信息，而部分分页信息是前端不需要的
        //PageInfo<StockUpdownDomain> pageInfo = new PageInfo<>(infos);
        //PageResult<StockUpdownDomain> pageResult = new PageResult<>(pageInfo);
        PageResult<StockUpdownDomain> pageResult = new PageResult<>(new PageInfo<>(infos));
        //5.封装响应数据
        return R.ok(pageResult);
    }

    /**
     * 统计沪深两市个股最新交易数据，并按涨幅降序排序查询前4条数据
     * @return
     */
    @Override
    public R<List<StockUpdownDomain>> getNewestStockInfo() {
        //获取股票最新交易时间点
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock数据,后续删除
        lastDate = DateTime.parse("2021-12-30 09:32:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //1.调用mapper接口获取数据
        List<StockUpdownDomain> infos = stockRtInfoMapper.getNewestStockInfo(lastDate);
        //2.组装数据
        if (CollectionUtils.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(infos);
    }

    /**
     * 统计最新交易日下股票每分钟涨跌停的数量
     * @return
     */
    @Override
    public R<Map> getStockUpdownCount() {
        //1.获取最新的交易时间范围 openTime  curTime
        //1.1 获取最新股票交易时间点
        DateTime curDateTime = DateTimeUtil.getLastDate4Stock(DateTime.now());
        Date curTime = curDateTime.toDate();
        //TODO
        curTime = DateTime.parse("2022-01-06 14:25:00",DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //1.2 获取最新交易时间对应的开盘时间
        DateTime openDate = DateTimeUtil.getOpenDate(curDateTime);
        Date openTime = openDate.toDate();
        //TODO
        openTime = DateTime.parse("2022-01-06 09:30:00",DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //2.查询涨停数据
        //约定mapper中flag入参: 1-涨停 0-跌停
        List<Map> upCounts = stockRtInfoMapper.getStockUpdownCount(openTime,curTime,1);
        //3.查询跌停数据
        List<Map> dwCounts = stockRtInfoMapper.getStockUpdownCount(openTime,curTime,0);
        //4.组装数据
        HashMap<String, List> mapInfo = new HashMap<>();
        mapInfo.put("upList",upCounts);
        mapInfo.put("downList",dwCounts);
        //5.返回数据
        return R.ok(mapInfo);
    }
}
