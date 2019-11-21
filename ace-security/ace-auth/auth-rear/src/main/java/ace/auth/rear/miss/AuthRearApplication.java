package ace.auth.rear.miss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ASUS
 * 后台服务类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthRearApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthRearApplication.class, args);
    }

}
