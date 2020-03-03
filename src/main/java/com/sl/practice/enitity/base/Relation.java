package com.sl.practice.enitity.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 主子关系表
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class Relation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 是否是主账户 0:不是 1:是
     */
    private Integer master;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
