package ace.auth.front.miss.interator;

import ace.auth.front.miss.dao.TokenDao;
import com.miss.pojo.FinalEnum;
import com.miss.pojo.Token;
import com.miss.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/24 22:15
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().equals("/user/login")||
                request.getRequestURI().equals("/user/register")||
                RequestMethod.OPTIONS.toString().equals(request.getMethod())||
                request.getRequestURI().equals("/user/judgeName")){
            return true;
        }
        final String head=request.getHeader("AppToken");
        System.out.println("head:"+head);
        try {
            final Claims claims = Jwts.parser().setSigningKey("missli").parseClaimsJws(head).getBody();
            String dbToken = getTokenByRedis(claims.getSubject());
            if(dbToken==null||dbToken.trim()==""){
                return false;
            }
            if(dbToken.equals(head)){
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
    public String getTokenByRedis(String userName) {
        List<Token> allToken = redisTemplate.opsForList().range(FinalEnum.JWT, 0, -1);
        for (Token token : allToken) {
            if (userName.equals(token.getUserName())) {
                return token.getToken();
            }
        }
        Token token=tokenService.findTokenByUserName(userName);
        return token.getToken();
    }
}
