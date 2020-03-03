package com.sl.practice.service;

import com.sl.practice.web.model.RelationModel;
import com.sl.practice.web.vo.RelationVo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 主子关系表 服务类
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
public interface RelationService {

  /**
  * 根据id查询
  * @param id
  * @return
  */
  RelationVo getById(Integer id);

  /**
  * 根据多个id 批量查询
  * @param ids
  * @return
  */
  List<RelationVo> listByIds(List<Integer> ids);

  /**
  * 分页查询记录
  * @return
  */
  //    List<RelationVo> listByPage();

   /**
   * 新增保存一条记录
   * @param relationModel
   * @return
   */
   boolean save(RelationModel relationModel);

   /**
   * 编辑更新一条记录
   * @param relationModel
   * @return
   */
   boolean update(RelationModel relationModel);

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
