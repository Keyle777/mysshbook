package top.keyle.mapper;

import org.apache.ibatis.annotations.Param;
import top.keyle.pojo.User;

@SuppressWarnings("all")
public interface UserMapper {
    //增加一名用户 /user/insertUser?....
    Integer insertUser(User user);
    //删除一名用户 /user/deleteUser?id=1
    Integer deleteUser(Integer id);
    //修改一名用户信息 /user/updateUser?...
    Integer updateUser(User user);
    //查询一名用户信息 /user/detailUser?id=1
    User detailUser(Integer id);
    //查询用户是否存在
    User selectUser(
            @Param("uname")
            String uname,
            @Param("pwd")
            String pwd);
}
