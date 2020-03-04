package com.sl.practice.mapper.base;

import com.sl.practice.enitity.base.User;
import com.sl.practice.web.model.UserPageQuery;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户主表 Mapper 接口
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
public interface UserMapper {
    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    User selectById(@Param("id") Integer id);

    /**
     * 根据 username 查询
     *
     * @param username
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<User> selectBatchIds(@Param("ids") Collection<? extends Serializable> idList);


    /**
     * 根据 phoneNumber 查询
     *
     * @param phoneNumber
     */
    User selectByPhoneNumber(@Param("phoneNumber")String phoneNumber);
    /**
     * 插入一条记录
     *
     * @param user 实体对象
     */
    int insert(User user);

    /**
     * 批量插入
     * @param userList
     * @return
     */
    int insertBatch(List<User> userList);

    /**
     * 分页查询
     * @param userPageQuery
     * @return
     */
    List<User> listByPage(UserPageQuery userPageQuery);

    /**
     * 根据 ID 修改
     *
     * @param user 实体对象
     */
    int updateById(User user);

    /**
     * 根据 ID 修改
     * 选择性更新，更新不为null的
     * @param user 实体对象
     */
    int updateByIdSelective(User user);

    /**
     * 增加用户的 子账号数量
     * @param id
     * @return
     */
    int incrSubNum(Integer id);

    /**
     * 减少用户的 子账号数量
     * @param id
     * @return
     */
    int descSubNum(Integer id);

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


    /**
     * 根据 username 删除
     *
     * @param username
     */
    int deleteByUsername(String username);


}
