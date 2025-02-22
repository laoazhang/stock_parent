package cn.laoazhang.stock.mapper;

import cn.laoazhang.stock.pojo.domain.*;
import cn.laoazhang.stock.pojo.entity.StockRtInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author laoazhang
* @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
* @createDate 2024-12-24 22:24:22
* @Entity cn.laoazhang.stock.pojo.entity.StockRtInfo
*/
@SuppressWarnings("MybatisXMapperMethodInspection")
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);

    /**
     * 查询指定时间点下股票的数据，并按照涨幅降序排序
     * @param curDate
     * @return
     */
    List<StockUpdownDomain> getNewestStockInfo(@Param("timePoint") Date curDate);

    /**
     * 查询指定时间范围内每分钟涨停或者跌停的数量
     * @param openTime 开始时间
     * @param curTime 结束时间 一般开始时间和结束时间在同一天
     * @param flag 约定:1->涨停 0:->跌停
     * @return
     */
    List<Map> getStockUpdownCount(@Param("openTime") Date openTime, @Param("curTime") Date curTime, @Param("flag") int flag);

    /**
     * 统计指定时间点下，各个涨跌区间内股票的个数
     * @param avlDate
     * @return
     */
    List<Map> getStockUpdownSectionByTime(@Param("avlDate") Date avlDate);

    /**
     * 根据时间范围查询指定股票的交易流水
     * @param stockCode 股票code
     * @param startTime 起始时间
     * @param endTime 终止时间
     * @return
     */
    List<Stock4MinuteDomain> getStockInfoByCodeAndDate(@Param("stockCode") String stockCode,
                                                       @Param("startTime") Date startTime,
                                                       @Param("endTime") Date endTime);

    /**
     * 查询指定日期范围内指定股票每天的交易数据
     * @param stockCode 股票code
     * @param startTime 起始时间
     * @param endTime 终止时间
     * @return
     */
    List<Stock4EvrDayDomain> getStockInfo4EvrDay(@Param("stockCode") String stockCode,
                                                 @Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime);
    /**
     * 查询指定日期范围内指定股票的最大日期
     * @param stockCode 股票code
     * @param startTime 起始时间
     * @param endTime 终止时间
     * @return
     */
    List<Date> getStockInfo4EvrDayDate(@Param("stockCode") String stockCode,
                                                 @Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime);

    /**
     * 查询指定日期范围内指定股票每天的交易数据
     * @param stockCode 股票code
     * @param dateList 日期列表
     * @return
     */
    List<Stock4EvrDayDomain> getStockInfo4EvrDayData(@Param("stockCode") String stockCode,
                                                     @Param("dateList") List<Date> dateList);

    /**
     * 批量插入功能
     * @param stockRtInfoList
     */
    int insertBatch(List<StockRtInfo> stockRtInfoList);

    /**
     * 根据股票代码模糊查询股票信息
     * @param code
     * @return
     */
    List<StockRtSearchDomain> searchAll(@Param("code") String code);

    /**
     * 查询指定股票每周的交易数据
     * @param stockCode 股票code
     * @param startTime 起始时间
     * @param endTime 终止时间
     * @return
     */
    List<Stock4EvrWeekDomain> getStockInfo4EvrWeek(@Param("stockCode") String stockCode,
                                                  @Param("startTime") Date startTime,
                                                  @Param("endTime") Date endTime);
}
