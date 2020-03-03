package com.sl.practice.mapper.base;


import com.sl.practice.enitity.base.Relation;
import com.sl.practice.web.model.UserPageQuery;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 主子关系表 Mapper 接口
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
public interface RelationMapper {

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    Relation selectById(@Param("id") Integer id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<Relation> selectBatchIds(@Param("ids") Collection<? extends Serializable> idList);


    /**
     * 插入一条记录
     *
     * @param relation 实体对象
     */
    int insert(Relation relation);

    /**
     * 批量插入
     * @param relationList
     * @return
     */
    int insertBatch(List<Relation> relationList);


    /**
     * 根据 ID 修改
     *
     * @param relation 实体对象
     */
    int updateById(Relation relation);

    /**
     * 根据 ID 修改
     * 选择性更新，更新不为null的
     * @param relation 实体对象
     */
    int updateByIdSelective(Relation relation);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int deleteById(Serializable id);


    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    int deleteBatchIds(@Param("ids") Collection<? extends Serializable> idList);



}
