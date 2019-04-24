package ace.auth.front.miss.dao;

import com.miss.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MissLi
 * @create 2019/4/23 19:33
 */
@Mapper
@Repository
public interface Userdao {
    User findUserByName(String name);
    User findUserByNameAndPassword(String name,String password);
    int saveUser(User user);
    int updateUserByname(User user);
}
