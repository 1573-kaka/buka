package cn.imust.dao.system;

import cn.imust.domain.system.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface UserDao {

	//根据企业id查询全部
	List<User> findAll(String companyId);

	//根据id查询
    User findById(String userId);

	//根据id删除
	int delete(String id);

	//保存
	int save(User user);

	//更新
	int update(User user);

    User findByEmail(String email);

	User findByOpenid(String openid);

	int updateOpenId(@Param("email") String email, @Param("openId") String openId);
}