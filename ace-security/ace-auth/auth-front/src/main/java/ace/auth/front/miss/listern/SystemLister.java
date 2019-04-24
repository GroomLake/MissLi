package ace.auth.front.miss.listern;

import ace.auth.front.miss.dao.TokenDao;
import com.miss.pojo.FinalEnum;
import com.miss.pojo.Token;
import com.miss.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/24 21:12
 */
@WebListener
public class SystemLister implements ServletContextListener {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        List<Token> allToken=tokenService.findAllToken();
        redisTemplate.delete(FinalEnum.JWT);
        if(allToken.size()!=0){
            redisTemplate.opsForList().leftPushAll(FinalEnum.JWT,allToken);
            System.out.println("监听:"+allToken);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        redisTemplate.delete(FinalEnum.JWT);
    }
}
