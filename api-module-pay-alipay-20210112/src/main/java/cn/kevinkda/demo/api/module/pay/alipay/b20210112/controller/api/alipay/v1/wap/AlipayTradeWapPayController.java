package cn.kevinkda.demo.api.module.pay.alipay.b20210112.controller.api.alipay.v1.wap;

import cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.service.payment.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kevin KDA on 2021/1/14 00:44
 * @version 0.0.1
 * @project http-api-map-service-202101
 * @package cn.kevinkda.demo.api.module.pay.alipay.b20210112.controller.api.alipay.v1.wap
 * @classname AlipayTradeWapPayController
 * @apiNote
 * @implSpec
 * @implNote
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/v1/payment/alipay/trade/wap/pay/2.0")
public class AlipayTradeWapPayController {
    @Autowired
    private AlipayConfig alipayConfig;
    /**
     * 调取支付宝接口app端支付
     *
     * @since 0.0.1
     */
    @Qualifier("getAlipayClient")
    @Autowired
    private AlipayClient alipayClient;
    /**
     * 设置请求参数
     *
     * @since 0.0.1
     */
    @Autowired
    private AlipayTradePagePayRequest alipayRequest;

    @ResponseBody
    @RequestMapping("/request")
    public String Pay(
            @RequestParam(value = "out_trade_no", required = false) String outTradeNo,
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "total_amount", required = false) String totalAmount,
            @RequestParam(value = "body", required = false) String body,
            @RequestParam(value = "time_express", required = false) String timeoutExpress,
            @RequestParam(value = "product_code", required = false) String productCode
    ) throws AlipayApiException {
//        alipayClient.set
        alipayRequest.setApiVersion("1.0");
        alipayRequest.setBizContent("");

//        封装 Rsa 签名方式
//        创建 Request 请求
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
//        封装传入参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(outTradeNo);
        model.setSubject(subject);
        model.setTotalAmount(totalAmount);
        model.setBody(body);
        model.setTimeoutExpress(timeoutExpress);
        model.setProductCode(productCode);

//        设置参数
        request.setBizModel(model);
//        设置异步回调地址
        request.setNotifyUrl(alipayConfig.notifyUrl);
//        设置同步回调地址
        request.setReturnUrl(alipayConfig.returnUrl);

//        生成表单
        String form = alipayClient.pageExecute(request).getBody();
        log.info("form = " + form);

        return form;
    }

}
