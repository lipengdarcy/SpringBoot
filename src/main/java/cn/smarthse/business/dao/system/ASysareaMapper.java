package cn.smarthse.business.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smarthse.business.model.system.ASysarea;
import cn.smarthse.business.model.system.ASysareaExample;
import cn.smarthse.framework.generic.GenericDao;

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