package com.znothings.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;


/**
 * 用户操作类
 * @author zenghh, 625111833@qq.com
 * @date 2019-06-19 9:30
 * @version 1.0.0
 */
public interface UserDao extends JpaRepository<User,Integer> {

}
