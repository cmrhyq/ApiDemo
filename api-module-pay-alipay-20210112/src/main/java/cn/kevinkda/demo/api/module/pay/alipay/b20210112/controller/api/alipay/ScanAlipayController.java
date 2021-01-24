package cn.kevinkda.demo.api.module.pay.alipay.b20210112.controller.api.alipay;

import cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.service.payment.AlipayConfig;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/payment/alipay/scan/")
public class ScanAlipayController {

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


    /**
     * 快捷支付调用支付宝支付接口
     *
     * @param model，id，payables，
     * @return Object
     */
    @RequestMapping("/alipaySum")
    public String alipayIumpSum(Model model, String WIDout_trade_no, String WIDsubject, String WIDtotal_amount, String WIDbody) throws Exception {
        log.info("请求进来" + WIDtotal_amount);
        log.info(WIDout_trade_no);
        String payables = WIDout_trade_no;
        // 订单名称，必填(必须是数字)
        String subject = WIDsubject;
        // 付款金额，必填
        String totalFee = WIDtotal_amount;
        // 商品描述，可空
        String body = WIDbody;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String outTradeNo = sdf.format(new Date());
        // 付款金额，必填
        String totalAmount = payables.replace(",", "");

        Map<String, String> trade = new HashMap<>();
        trade.put("out_trade_no", outTradeNo);
//        trade.put("total_amount", totalAmount);
        trade.put("total_amount", WIDout_trade_no);
        trade.put("subject", subject);
        trade.put("body", body);
        trade.put("product_code", "FAST_INSTANT_TRADE_PAY");


//        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
//                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
//                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        alipayRequest.setBizContent(JSONObject.toJSONString(trade));

        // 请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        log.info(result);
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

        // 记录支付日志
//        alipayConfig.fileLogResult(result);
        return result;
    }

    @ResponseBody
    @RequestMapping("/ScanPay")
    public String Pay(
            @RequestParam(value = "out_trade_no", required = false) String outTradeNo,
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "total_amount", required = false) String totalAmount,
            @RequestParam(value = "body", required = false) String body,
            @RequestParam(value = "time_express", required = false) String timeoutExpress,
            @RequestParam(value = "product_code", required = false) String productCode
    ) throws AlipayApiException {
//        封装 Rsa 签名方式
//        创建 Request 请求
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
//        封装传入参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(outTradeNo);
        model.setSubject(subject);
        model.setTotalAmount(totalAmount);
        model.setBody(body);
        model.setTimeoutExpress(productCode);
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
