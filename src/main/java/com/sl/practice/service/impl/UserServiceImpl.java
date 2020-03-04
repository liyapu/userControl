package com.sl.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.sl.practice.base.exception.BusinessException;
import com.sl.practice.base.utils.CommonUtil;
import com.sl.practice.base.utils.Md5Utils;
import com.sl.practice.base.web.PageInfo;
import com.sl.practice.enitity.base.Relation;
import com.sl.practice.enitity.base.Subuser;
import com.sl.practice.enitity.base.User;
import com.sl.practice.enums.StatusEnum;
import com.sl.practice.mapper.base.RelationMapper;
import com.sl.practice.mapper.base.SubuserMapper;
import com.sl.practice.mapper.base.UserMapper;
import com.sl.practice.service.UserService;
import com.sl.practice.web.model.LoginModel;
import com.sl.practice.web.model.UserModel;
import com.sl.practice.web.model.UserPageQuery;
import com.sl.practice.web.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RelationMapper relationMapper;
    @Autowired
    SubuserMapper subuserMapper;


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
    public PageInfo<UserVo> getByPage(UserPageQuery userPageQuery) {
        userPageQuery.setStatus(StatusEnum.YES.getCode());
        userPageQuery.setOrderBy("id DESC");
        PageHelper.startPage(userPageQuery.getPageNum(),userPageQuery.getPageSize());
        List<User> userList = userMapper.listByPage(userPageQuery);

        List<UserVo> voList = userList.stream()
                .map(entry -> {
                    UserVo vo = new UserVo();
                    BeanUtils.copyProperties(entry, vo);
                    return vo;
                }).collect(Collectors.toList());

        return PageInfo.of(userList,voList);
   }

    @Override
    public boolean save(UserModel userModel) {
       User user = new User();
       String username = userModel.getUsername();
       String password = userModel.getPassword();

       if(StringUtils.isBlank(username)){
           throw BusinessException.build("用户名不能为空");
       }
       if(StringUtils.isBlank(password)){
           throw BusinessException.build("密码不能为空");
       }
       if(null == userModel.getIsMaster()){
           throw BusinessException.build("是否是主账号字段不能为空");
       }
       username = username.trim();
       password = password.trim();

       if(password.length() < 6){
           throw BusinessException.build("密码长度至少为6位");
       }

       BeanUtils.copyProperties(userModel,user);

        Relation relationDb = relationMapper.selectByUsername(username);
        if(null != relationDb){
            throw BusinessException.usernameExistErr();
        }

        Relation relation = new Relation();
        relation.setUsername(username);
        relation.setMaster(StatusEnum.YES.getCode());

        if(StatusEnum.YES.getCode() == userModel.getIsMaster().intValue()){
            //添加主账号
            //主账号 才判断手机号
            String phoneNumber = userModel.getPhoneNumber();
            if(CommonUtil.isNotPhone(phoneNumber)){
                throw BusinessException.build("请输入合法手机号");
            }
            User userDb = userMapper.selectByPhoneNumber(phoneNumber);
            if(null != userDb){
                throw BusinessException.build("此手机号用户已经注册过了,请重新输入");
            }

            relationMapper.insert(relation);
            user.setSubNum(0);
            user.setPassword(Md5Utils.generate(password));
            user.setLoginState(StatusEnum.NO.getCode());
            //新创建的账号，默认为有效
            user.setStatus(StatusEnum.YES.getCode());

            userMapper.insert(user);
        }else{
            //添加子账号
            //子账号判断 主账号id是否有值
            Integer masterId = userModel.getMasterId();
            if(null == masterId){
                throw BusinessException.build("主账号id不合法");
            }
            User userDb = userMapper.selectById(masterId);
            if(null == userDb){
                throw BusinessException.build("主账号id不合法");
            }
            relationMapper.insert(relation);

            Subuser subuser = new Subuser();
            BeanUtils.copyProperties(userModel,subuser);
            subuser.setPassword(Md5Utils.generate(password));
            subuser.setLoginState(StatusEnum.NO.getCode());
            subuser.setMasterId(masterId);
            //新创建的账号，默认为有效
            subuser.setStatus(StatusEnum.YES.getCode());
            subuserMapper.insert(subuser);

            userMapper.incrSubNum(masterId);
        }

       return true;
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

    @Override
    public boolean deleteByUsername(String username) {
       int rows = 0;
        Relation relation = relationMapper.selectByUsername(username);
        if(null != relation){
            if(StatusEnum.YES.getCode() == relation.getMaster().intValue()){
                 rows = userMapper.deleteByUsername(username);
            }else{
                rows = subuserMapper.deleteByUsername(username);
            }
            relationMapper.deleteByUsername(username);
        }
        return rows > 0;
    }

    @Override
    public boolean login(LoginModel loginModel) {
       String username = loginModel.getUsername();
       String password = loginModel.getPassword();
       if(StringUtils.isAnyBlank(username,password)){
           throw BusinessException.build("用户名或密码错误");
       }
        username = username.trim();
        password = password.trim();

        User user = userMapper.selectByUsername(username);
        if(null == user){
            throw BusinessException.build("用户名或密码错误");
        }
        if(!Md5Utils.verify(password,user.getPassword())){
            throw BusinessException.build("用户名或密码错误");
        }

        User userTemp = new User();
        userTemp.setId(user.getId());
        userTemp.setLoginState(StatusEnum.YES.getCode());
        userMapper.updateByIdSelective(userTemp);
        return true;
    }

    @Override
    public boolean logout(String username) {
       if(StringUtils.isBlank(username)){
           throw BusinessException.build("用户名错误");
       }
        username = username.trim();
        User user = userMapper.selectByUsername(username);
        if(null == user){
            throw BusinessException.build("用户错误");
        }
        User userTemp = new User();
        userTemp.setId(user.getId());
        userTemp.setLoginState(StatusEnum.NO.getCode());
        userMapper.updateByIdSelective(userTemp);
        return true;
   }
}
