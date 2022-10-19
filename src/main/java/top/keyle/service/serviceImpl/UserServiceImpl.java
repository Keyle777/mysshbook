package top.keyle.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.keyle.mapper.UserMapper;
import top.keyle.pojo.User;
import top.keyle.service.UserService;
@Service
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer insertUser(User user) {
        Integer rows = userMapper.insertUser(user);
        return rows;
    }

    @Override
    public Integer deleteUser(Integer id) {
        Integer rows = userMapper.deleteUser(id);
        return rows;
    }

    @Override
    public Integer updateUser(User user) {
        Integer rows = userMapper.updateUser(user);
        return rows;
    }

    @Override
    public User detailUser(Integer id) {
        User user = userMapper.detailUser(id);
        return user;
    }

    @Override
    public User selectUser(String uname, String pwd) {
        return userMapper.selectUser(uname,pwd);
    }
}
