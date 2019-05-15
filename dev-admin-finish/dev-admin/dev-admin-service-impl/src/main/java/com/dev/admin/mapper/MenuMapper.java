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
import com.dev.admin.vo.MenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 */
@Mapper
public interface MenuMapper {

	//@Insert("insert into user(name,login_name,phone,email,region,create_date) values (#{user.name},#{user.loginName},#{user.phone},#{user.email},#{user.region},#{user.createDate})")
	int insert(Menu menu);

	int update(Menu menu);

	int delete(int id);

	int deleteBatch(@Param("ids") int[] ids);

	List<Menu> getMenuList(MenuVo vo);

	List<Menu> getMenuListAll(MenuVo vo);


}