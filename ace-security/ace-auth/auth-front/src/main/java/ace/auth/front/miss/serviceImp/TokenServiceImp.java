package ace.auth.front.miss.serviceImp;

import ace.auth.front.miss.dao.TokenDao;
import com.miss.pojo.Token;
import com.miss.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/24 21:19
 */
@Service
public class TokenServiceImp implements TokenService {
    @Autowired
    private TokenDao tokenDao;
    @Override
    public List<Token> findAllToken() {
        return tokenDao.findAllToken();
    }

    @Override
    public Token findTokenByUserName(String userName) {
        return tokenDao.findTokenByUserName(userName);
    }

    @Override
    public boolean saveToken(Token token) {
        return tokenDao.saveToken(token)!=0;
    }
}
