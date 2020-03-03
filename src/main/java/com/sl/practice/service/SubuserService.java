package com.sl.practice.service;


import com.sl.practice.web.model.SubuserModel;
import com.sl.practice.web.vo.SubuserVo;

import java.util.List;

/**
 * <p>
 * 子用户表 服务类
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
public interface SubuserService{

  /**
  * 根据id查询
  * @param id
  * @return
  */
  SubuserVo getById(Integer id);

  /**
  * 根据多个id 批量查询
  * @param ids
  * @return
  */
  List<SubuserVo> listByIds(List<Integer> ids);

  /**
  * 分页查询记录
  * @return
  */
  //    List<SubuserVo> listByPage();

   /**
   * 新增保存一条记录
   * @param subuserModel
   * @return
   */
   boolean save(SubuserModel subuserModel);

   /**
   * 编辑更新一条记录
   * @param subuserModel
   * @return
   */
   boolean update(SubuserModel subuserModel);

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
}
