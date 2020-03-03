package com.sl.practice.service.impl;

import com.sl.practice.enitity.base.User;
import com.sl.practice.mapper.base.UserMapper;
import com.sl.practice.service.UserService;
import com.sl.practice.web.model.UserModel;
import com.sl.practice.web.vo.UserVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户主表 服务实现类
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

   @Override
   public UserVo getById(Integer id) {
      User user = userMapper.selectById(id);
      UserVo userVo = new UserVo();
      BeanUtils.copyProperties(user,userVo);
      return userVo;
   }

   @Override
   public List<UserVo> listByIds(List<Integer> ids) {
     List<User> userList = userMapper.selectBatchIds(ids);
     List<UserVo> userVoList = userList.stream()
                                       .map(entity -> {
                                           UserVo userVo = new UserVo();
                                           BeanUtils.copyProperties(entity, userVo);
                                           return userVo;
                                       }).collect(Collectors.toList());
      return userVoList;
    }

    @Override
    public boolean save(UserModel userModel) {
       User user = new User();
       BeanUtils.copyProperties(userModel,user);
       int rows  = userMapper.insert(user);
       return rows > 0;
    }

    @Override
    public boolean update(UserModel userModel) {
       User user = new User();
       BeanUtils.copyProperties(userModel,user);
       int rows  = userMapper.updateByIdSelective(user);
       return rows > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
       int rows  = userMapper.deleteById(id);
       return rows > 0;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
      int rows  = userMapper.deleteBatchIds(ids);
      return rows > 0;
    }
}
