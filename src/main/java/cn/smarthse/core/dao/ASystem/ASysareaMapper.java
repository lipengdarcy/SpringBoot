package cn.smarthse.core.dao.ASystem;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smarthse.core.dao.GenericDao;
import cn.smarthse.core.model.ASystem.ASysarea;
import cn.smarthse.core.model.ASystem.ASysareaExample;

public interface ASysareaMapper extends GenericDao<ASysarea>{
	
    int countByExample(ASysareaExample example);

    int deleteByExample(ASysareaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ASysarea record);

    int insertSelective(ASysarea record);

    List<ASysarea> selectByExample(ASysareaExample example);

    ASysarea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ASysarea record, @Param("example") ASysareaExample example);

    int updateByExample(@Param("record") ASysarea record, @Param("example") ASysareaExample example);

    int updateByPrimaryKeySelective(ASysarea record);

    int updateByPrimaryKey(ASysarea record);
}