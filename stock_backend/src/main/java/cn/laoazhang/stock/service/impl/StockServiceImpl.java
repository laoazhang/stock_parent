package cn.laoazhang.stock.service.impl;

import cn.laoazhang.stock.mapper.StockBusinessMapper;
import cn.laoazhang.stock.mapper.StockMarketIndexInfoMapper;
import cn.laoazhang.stock.pojo.domain.InnerMarketDomain;
import cn.laoazhang.stock.pojo.entity.StockBusiness;
import cn.laoazhang.stock.pojo.vo.StockInfoConfig;
import cn.laoazhang.stock.service.StockService;
import cn.laoazhang.stock.utils.DateTimeUtil;
import cn.laoazhang.stock.vo.resp.R;
import io.swagger.models.License;
import org.checkerframework.checker.units.qual.A;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    /**
     * 获取国内大盘的实时数据
     * @return
     */
    @Override
    public R<List<InnerMarketDomain>> innerIndexAll() {
        //1.获取国内A股大盘的id集合
        List<String> inners = stockInfoConfig.getInner();
        //2.获取最近股票交易日期
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock测试数据，后期数据通过第三方接口动态获取实时数据 可删除
        lastDate=DateTime.parse("2022-01-02 09:32:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //3.将获取的java Date传入接口
        List<InnerMarketDomain> list= stockMarketIndexInfoMapper.getMarketInfo(inners,lastDate);
        //4.返回查询结果
        return R.ok(list);

    }
}