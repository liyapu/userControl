package com.sl.practice.service;


import com.sl.practice.base.web.PageInfo;
import com.sl.practice.web.model.LoginModel;
import com.sl.practice.web.model.UserModel;
import com.sl.practice.web.model.UserPageQuery;
import com.sl.practice.web.vo.UserVo;

import java.util.List;

/**
 * <p>
 * 用户主表 服务类
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
public interface UserService {

  /**
  * 根据id查询
  * @param id
  * @return
  */
  UserVo getById(Integer id);

  /**
  * 根据多个id 批量查询
  * @param ids
  * @return
  */
  List<UserVo> listByIds(List<Integer> ids);

    /**
     * 分页查询，返回PageInfo对象
     */
    PageInfo<UserVo> getByPage(UserPageQuery userPageQuery);
   /**
   * 新增保存一条记录
   * @param userModel
   * @return
   */
   boolean save(UserModel userModel);

   /**
   * 编辑更新一条记录
   * @param userModel
   * @return
   */
   boolean update(UserModel userModel);

   /**
   * 根据id删除一条记录
   * @param id
   * @return
   */
   boolean deleteById(Integer id);

   /**
   * 根据id批量删除记录
   * @param ids
   * @return
   */
   boolean deleteByIds(List<Integer> ids);

    /**
     * 根据id删除一条记录
     * @param username
     * @return
     */
    boolean deleteByUsername(String username);

    /**
     * 登录
     * @param loginModel
     * @return
     */
    boolean login(LoginModel loginModel);

    /**
     * 退出
     * @param username
     * @return
     */
    boolean logout(String username);
}
