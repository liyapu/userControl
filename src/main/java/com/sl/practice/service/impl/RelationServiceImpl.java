package com.sl.practice.service.impl;

import com.sl.practice.enitity.base.Relation;
import com.sl.practice.mapper.base.RelationMapper;
import com.sl.practice.service.RelationService;
import com.sl.practice.web.model.RelationModel;
import com.sl.practice.web.vo.RelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 主子关系表 服务实现类
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    RelationMapper relationMapper;

   @Override
   public RelationVo getById(Integer id) {
      Relation relation = relationMapper.selectById(id);
      RelationVo relationVo = new RelationVo();
      BeanUtils.copyProperties(relation,relationVo);
      return relationVo;
   }

   @Override
   public List<RelationVo> listByIds(List<Integer> ids) {
     List<Relation> relationList = relationMapper.selectBatchIds(ids);
     List<RelationVo> relationVoList = relationList.stream()
                                       .map(entity -> {
                                           RelationVo relationVo = new RelationVo();
                                           BeanUtils.copyProperties(entity, relationVo);
                                           return relationVo;
                                       }).collect(Collectors.toList());
      return relationVoList;
    }

    @Override
    public boolean save(RelationModel relationModel) {
       Relation relation = new Relation();
       BeanUtils.copyProperties(relationModel,relation);
       int rows  = relationMapper.insert(relation);
       return rows > 0;
    }

    @Override
    public boolean update(RelationModel relationModel) {
       Relation relation = new Relation();
       BeanUtils.copyProperties(relationModel,relation);
       int rows  = relationMapper.updateByIdSelective(relation);
       return rows > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
       int rows  = relationMapper.deleteById(id);
       return rows > 0;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
      int rows  = relationMapper.deleteBatchIds(ids);
      return rows > 0;
    }
}
