/**
 *    Copyright 2015-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.dev.admin.mapper;


import com.dev.admin.entity.Menu;
import com.dev.admin.entity.User;
import com.dev.admin.entity.UserRole;
import com.dev.admin.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 */
@Mapper
public interface UserMapper {

	//@Insert("insert into user(name,login_name,phone,email,region,create_date) values (#{user.name},#{user.loginName},#{user.phone},#{user.email},#{user.region},#{user.createDate})")
	int insert(User user);

	int insertUserRole(UserRole userRole);

	int update(User user);

	int updatePass(User user);

	int delete(int id);

	int deleteUserRoleByUserId(int userId);

	int deleteBatch(@Param("ids") int[] ids);

	List<User> getUserList(UserVo userVo);

	User getUserByLoginName(String loginName);

	List<UserRole> getRoleByUserId(int roleId);

	List<Menu> getUserMenus(int userId);

	User getUserById(int id);
}