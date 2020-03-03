package com.sl.practice.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户主表
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户主表")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名", position = 1)
    private String username;


    @ApiModelProperty(value = "手机号", position = 2)
    private String phoneNumber;

    private String isMaster;


}
