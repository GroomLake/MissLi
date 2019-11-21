package ace.auth.front.miss.model;

import com.miss.pojo.FinalEnum;
import com.miss.pojo.Token;
import com.miss.pojo.TokenResult;
import com.miss.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author MissLi
 * @create 2019/4/24 21:09
 */
@Component
public class TokenUtil {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisTemplate redisTemplate;
    public String getTokenResult(String userName){
        TokenResult result=new TokenResult();
        //检查token是否存在
        Token token=judgeToken(userName);
        String tokenString=null;
        if(token==null){
            //new token
            tokenString=createNewToken(userName);
            Token token1=new Token();
            token1.setUserName(userName);
            token1.setToken(tokenString);
            try {
                System.out.println("***"+token1);
                insertToken(token1);
            } catch (Exception e) {
                tokenString=null;
                System.out.println(e.getMessage());
            }
        }else{
            //判断是否过期
            long dbBuild = Long.valueOf(token.getTokenTime());
            long nowTome = System.currentTimeMillis();
            long sub = TimeUnit.MILLISECONDS.toSeconds(nowTome - dbBuild);
            if(sub>0&&sub<900000){
                tokenString=token.getToken();
            }else{
                tokenString=createNewToken(userName);
                token.setToken(tokenString);
                try {
                    insertToken(token);
                } catch (Exception e) {
                    tokenString=null;
                }
            }
        }
        return tokenString;
    }
    public void insertToken(Token token) throws Exception {
        token.setTokenTime(String.valueOf(System.currentTimeMillis()));
        if(!tokenService.saveToken(token)){
            throw new Exception("token 保存失败");
        }else{
            redisTemplate.opsForList().leftPush(FinalEnum.JWT, token);
        }
    }
    private String createNewToken(String userName) {
        Date now = new Date(System.currentTimeMillis());
        Date end = new Date(now.getTime() + 720000);
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(now)
                .setIssuer("online bulider")
                .setExpiration(end)
                .signWith(SignatureAlgorithm.HS256, "missli")
                .compact();
    }
    public Token judgeToken(String userName) {
        List<Token> allToken=redisTemplate.opsForList().range(FinalEnum.JWT,0,-1);
        Token token = null;
        boolean flag = true;
        for (Token tok : allToken) {
            if (userName.equals(tok.getUserName())) {
                flag = false;
                token = tok;
            }
        }
        if (flag) {
            token = tokenService.findTokenByUserName(userName);
        }
        return token;
    }
}
