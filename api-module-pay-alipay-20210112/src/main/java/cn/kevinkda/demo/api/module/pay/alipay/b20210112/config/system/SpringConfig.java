package cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Spring 配置信息获取组件
 *
 * @author Kevin KDA on 2021/1/13 18:07
 * @version 0.0.1
 * @project http-api-map-service-202101
 * @package cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.system
 * @classname SpringConfig
 * @apiNote
 * @implSpec
 * @implNote
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class SpringConfig {
    @Value("${spring.application.name}")
    public String name;
}
