package cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.spring;

import cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.service.payment.AlipayConfig;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kevin KDA on 2021/1/13 14:26
 * @version 0.0.1
 * @project http-api-map-service-202101
 * @package cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.spring
 * @classname ConfigBean
 * @apiNote
 * @implSpec
 * @implNote
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class ConfigBean {
    @Autowired
    private AlipayConfig alipayConfig;

    @Bean
    public DefaultAlipayClient getDefaultAlipayClient() {
        return new DefaultAlipayClient(
                alipayConfig.gatewayUrl,
                alipayConfig.appId,
                alipayConfig.rsaPrivateKey,
                alipayConfig.format,
                alipayConfig.charset,
                alipayConfig.alipayPublicKey,
                alipayConfig.signType
        );
    }

    @Bean
    public AlipayClient getAlipayClient() {
        return new DefaultAlipayClient(
                alipayConfig.gatewayUrl,
                alipayConfig.appId,
                alipayConfig.rsaPrivateKey,
                alipayConfig.format,
                alipayConfig.charset,
                alipayConfig.alipayPublicKey,
                alipayConfig.signType
        );
    }

    @Bean
    public AlipayTradePagePayRequest getAlipayTradePagePayRequest() {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(alipayConfig.returnUrl);
        request.setNotifyUrl(alipayConfig.notifyUrl);
        return request;
    }

}
