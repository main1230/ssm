package com.zzl.ssm.dao;

import com.zzl.ssm.entity.RnUpload;
import com.zzl.ssm.entity.RnUpload;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RnUploadMapper {
    long countByExample(RnUpload example);

    int deleteByExample(RnUpload example);

    int deleteByPrimaryKey(Integer id);

    int insert(RnUpload record);

    int insertSelective(RnUpload record);

    List<RnUpload> selectByExample(RnUpload example);

    RnUpload selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RnUpload record, @Param("example") RnUpload example);

    int updateByExample(@Param("record") RnUpload record, @Param("example") RnUpload example);

    int updateByPrimaryKeySelective(RnUpload record);

    int updateByPrimaryKey(RnUpload record);
}