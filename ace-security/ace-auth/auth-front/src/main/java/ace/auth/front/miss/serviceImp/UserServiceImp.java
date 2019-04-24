package ace.auth.front.miss.serviceImp;

import ace.auth.front.miss.dao.Userdao;
import com.miss.pojo.User;
import com.miss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MissLi
 * @create 2019/4/23 19:31
 * 开启事务
 */
@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private Userdao userdao;

    /**
     * 判断用户名是否存在
     * @param name
     * @return
     */
    @Override
    public boolean findUserByName(String name) {
        User user=userdao.findUserByName(name);
        return user != null;
    }

    @Override
    public boolean findUserByNameAndPassword(String name, String password) {
        return userdao.findUserByNameAndPassword(name,password)!=null;
    }

    @Override
    public boolean saveUser(User user) {
        return userdao.saveUser(user) != 0;
    }

    @Override
    public boolean updateUserByname(User user) {
        return userdao.updateUserByname(user)!=0;
    }
}
