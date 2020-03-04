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
 * 子用户表
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SubuserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "用户名", position = 1)
    private String username;

    @ApiModelProperty(value = "用户密码", position = 2)
    private String password;

    @ApiModelProperty(value = "父id", position = 3)
    private Integer parentId;

    @ApiModelProperty(value = "0-未登录，1-已登陆", position = 4)
    private Integer loginState;

    @ApiModelProperty(value = "创建时间", position = 5)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间", position = 6)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "用户状态 0:无效  1:有效", position = 7)
    private Integer status;


}
