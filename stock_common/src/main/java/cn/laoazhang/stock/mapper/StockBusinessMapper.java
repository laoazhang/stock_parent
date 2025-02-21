package cn.laoazhang.stock.mapper;

import cn.laoazhang.stock.pojo.domain.StockBusinessDomain;
import cn.laoazhang.stock.pojo.entity.StockBusiness;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author laoazhang
* @description 针对表【stock_business(主营业务表)】的数据库操作Mapper
* @createDate 2024-12-24 22:24:22
* @Entity cn.laoazhang.stock.pojo.entity.StockBusiness
*/
public interface StockBusinessMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBusiness record);

    int insertSelective(StockBusiness record);

    StockBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBusiness record);

    int updateByPrimaryKey(StockBusiness record);

    List<String> getStockIds();

    List<StockBusinessDomain> searchBusiness(@Param("code") String code);
}
