package com.cainew.mapper;

import com.cainew.vo.TbFontUser;
import com.cainew.mapper.TbFontUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbFontUserMapper {
    int countByExample(TbFontUserExample example);

    int deleteByExample(TbFontUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbFontUser record);

    int insertSelective(TbFontUser record);

    List<TbFontUser> selectByExample(TbFontUserExample example);

    TbFontUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbFontUser record, @Param("example") TbFontUserExample example);

    int updateByExample(@Param("record") TbFontUser record, @Param("example") TbFontUserExample example);

    int updateByPrimaryKeySelective(TbFontUser record);

    int updateByPrimaryKey(TbFontUser record);
}