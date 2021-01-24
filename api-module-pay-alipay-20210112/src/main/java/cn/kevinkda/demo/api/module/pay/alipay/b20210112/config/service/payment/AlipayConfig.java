package cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.service.payment;

import com.kevinkda.core.util.annotation.enumeration.VerifiedType;
import com.kevinkda.core.util.annotation.func.FuncVerification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Alipay 配置常量
 *
 * @author Kevin KDA on 2021/1/13 13:34
 * @version 0.0.1
 * @project api-module-pay-alipay-20210112
 * @package cn.kevinkda.demo.service.module.payment.gateway.b20210113.config.service.payment
 * @classname AlipayConfig
 * @apiNote
 * @implSpec
 * @implNote
 * @since 0.0.1
 */
@Slf4j
@Data
@Configuration
public class AlipayConfig {
    /**
     * 商户UID
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.app.uid}")
    public String uid;

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.app.app-id}")
    public String appId;


    /**
     * 签名方式
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.key.sign-type}")
    public String signType;
    /**
     * 商户私钥，您的PKCS8格式RSA2私钥，这些就是我们刚才设置的
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.key.rsa-private-key}")
    public String rsaPrivateKey;
    /**
     * 商户公钥，您的PKCS8格式RSA2私钥，这些就是我们刚才设置的
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.key.rsa-pubilc-key}")
    public String rsaPubilcKey;
    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
     * 对应APPID下的支付宝公钥。，这些就是我们刚才设置的
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.key.alipay-public-key}")
    public String alipayPublicKey;


    /**
     * 异步通知，再这里我们设计自己的后台代码
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.url.notify-url}")
    public String notifyUrl;
    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.url.return-url}")
    public String returnUrl;
    /**
     * 支付宝网关
     * https://openapi.alipaydev.com/gateway.do
     * https://openapi.alipay.com/gateway.do
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.url.gateway-url}")
    public String gatewayUrl;


    /**
     * 字符编码格式
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.config.charset}")
    public String charset;
    /**
     * 支付宝网关
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.log.log-path}")
    public String logPath;
    /**
     * 格式
     *
     * @since 0.0.1
     */
    @Value("${gateway.payment.alipay.log.format}")
    public String format;


    /**
     * 最大查询次数
     *
     * @since 0.0.1
     */
    @Value("#{new Integer('${gateway.payment.alipay.config.max-query-retry}')}")
    public Integer maxQueryRetry;
    /**
     * 查询间隔（毫秒）
     *
     * @since 0.0.1
     */
    @Value("#{new Long('${gateway.payment.alipay.config.query-duration}')}")
    public Long queryDuration;
    /**
     * 最大撤销次数
     *
     * @since 0.0.1
     */
    @Value("#{new Integer('${gateway.payment.alipay.config.max-cancel-retry}')}")
    public Integer maxCancelRetry;
    /**
     * 撤销间隔（毫秒）
     *
     * @since 0.0.1
     */
    @Value("#{new Long('${gateway.payment.alipay.config.cancel-duration}')}")
    public Long cancelDuration;



    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     * @return void
     * @author Kevin KDA on 2021/1/13 13:53
     * @description AlipayConfig / logResult
     * @version 1.0.0
     * @apiNote
     * @implSpec
     * @implNote
     * @since 1.0.0
     */
    @FuncVerification(version = "1.0.0", status = VerifiedType.Unverified, date = "2021/1/13 15:03")
    public void fileLogResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(logPath + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 写日志记录存入数据库，方便测试
     *
     * @param sWord 要写入日志里的文本内容
     * @return void
     * @author Kevin KDA on 2021/1/13 15:03
     * @description AlipayConfig / databaseLogResult
     * @version 1.0.0
     * @apiNote
     * @implSpec
     * @implNote
     * @since 1.0.0
     */
    @FuncVerification(version = "1.0.0", status = VerifiedType.Unverified, date = "2021/1/13 15:03")
    public void databaseLogResult(String sWord) {

    }
}
