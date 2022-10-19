package top.keyle.service;

import top.keyle.pojo.User;
@SuppressWarnings("all")
public interface UserService {
    //增加一名用户 /user/insertUser?....
    Integer insertUser(User user);
    //删除一名用户 /user/deleteUser?id=1
    Integer deleteUser(Integer id);
    //修改一名用户信息 /user/updateUser?...
    Integer updateUser(User user);
    //查询一名用户信息 /user/detailUser?id=1
    User detailUser(Integer id);
    //查询用户是否存在
    User selectUser(String uname,String pwd);
}
