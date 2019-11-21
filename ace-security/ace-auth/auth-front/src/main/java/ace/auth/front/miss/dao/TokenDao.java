package ace.auth.front.miss.dao;

import com.miss.pojo.Token;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/24 21:19
 */
@Repository
@Mapper
public interface TokenDao {
    List<Token> findAllToken();
    Token findTokenByUserName(@Param("userName") String userName);
    int saveToken(@Param("token")Token token);
}
