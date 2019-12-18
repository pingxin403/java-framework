package com.hyp.learn.ssmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssmvc.dto
 * hyp create at 19-12-18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    /**
     * 用户权限
     */
    private Set<String> authorities;
}
