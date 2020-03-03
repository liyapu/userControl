package com.sl.practice.service.impl;

import com.sl.practice.enitity.base.Subuser;
import com.sl.practice.mapper.base.SubuserMapper;
import com.sl.practice.service.SubuserService;
import com.sl.practice.web.model.SubuserModel;
import com.sl.practice.web.vo.SubuserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 子用户表 服务实现类
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@Service
public class SubuserServiceImpl  implements SubuserService {

    @Autowired
    SubuserMapper subuserMapper;

   @Override
   public SubuserVo getById(Integer id) {
      Subuser subuser = subuserMapper.selectById(id);
      SubuserVo subuserVo = new SubuserVo();
      BeanUtils.copyProperties(subuser,subuserVo);
      return subuserVo;
   }

   @Override
   public List<SubuserVo> listByIds(List<Integer> ids) {
     List<Subuser> subuserList = subuserMapper.selectBatchIds(ids);
     List<SubuserVo> subuserVoList = subuserList.stream()
                                       .map(entity -> {
                                           SubuserVo subuserVo = new SubuserVo();
                                           BeanUtils.copyProperties(entity, subuserVo);
                                           return subuserVo;
                                       }).collect(Collectors.toList());
      return subuserVoList;
    }

    @Override
    public boolean save(SubuserModel subuserModel) {
       Subuser subuser = new Subuser();
       BeanUtils.copyProperties(subuserModel,subuser);
       int rows  = subuserMapper.insert(subuser);
       return rows > 0;
    }

    @Override
    public boolean update(SubuserModel subuserModel) {
       Subuser subuser = new Subuser();
       BeanUtils.copyProperties(subuserModel,subuser);
       int rows =  subuserMapper.updateByIdSelective(subuser);
       return rows > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
       int rows  = subuserMapper.deleteById(id);
       return rows > 0;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
      int rows = subuserMapper.deleteBatchIds(ids);
      return rows > 0;
    }
}
