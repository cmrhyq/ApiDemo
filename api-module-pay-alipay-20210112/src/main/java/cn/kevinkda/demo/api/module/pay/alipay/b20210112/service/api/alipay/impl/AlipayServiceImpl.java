package cn.kevinkda.demo.api.module.pay.alipay.b20210112.service.api.alipay.impl;

import cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.service.payment.AlipayConfig;
import cn.kevinkda.demo.api.module.pay.alipay.b20210112.service.api.alipay.AlipayService;
import com.alipay.api.AlipayApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin KDA on 2021/1/13 14:19
 * @version 0.0.1
 * @project http-api-map-service-202101
 * @package cn.kevinkda.demo.api.module.pay.alipay.b20210112.service.api.alipay.impl
 * @classname
 * @apiNote
 * @implSpec
 * @implNote
 * @since 0.0.1
 */
@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService {
    /**
     * @since 0.0.1
     */
    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * 调取支付宝接口 web端支付
     *
     * @since 0.0.1
     */
    @Qualifier("getDefaultAlipayClient")
    @Autowired
    private DefaultAlipayClient alipayClient;

    /**
     * 调取支付宝接口app端支付
     *
     * @since 0.0.1
     */
    @Qualifier("getAlipayClient")
    @Autowired
    private AlipayClient alipayClients;

    @Override
    public String webPagePay(String outTradeNo, Integer totalAmount, String subject) throws Exception {

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        同步通知，支付完成后，支付成功页面
        alipayRequest.setReturnUrl(alipayConfig.returnUrl);
//        异步通知，支付完成后，需要进行的异步处理
        alipayRequest.setNotifyUrl(alipayConfig.notifyUrl);

        Map<String, String> bizContentMap = new HashMap<String, String>();
        bizContentMap.put("out_trade_no", outTradeNo);
        bizContentMap.put("total_amount", totalAmount + "");
        bizContentMap.put("subject", subject);
        bizContentMap.put("body", "商品名称");
        bizContentMap.put("timeout_express", "90m");
        bizContentMap.put("product_code", "FAST_INSTANT_TRADE_PAY");
//        String bizContentJson = AppFrameworkUtil.structureConfigParamsGroupJSONDataWithOutSimpleResultBean(bizContentMap);
//        alipayRequest.setBizContent(bizContentJson);

//        转换格式
        String result = alipayClient.pageExecute(alipayRequest).getBody().replace('\"', '\'').replace('\n', ' ');
        return result;
    }

    @Override
    public String refund(String outTradeNo, String refundReason, Integer refundAmount, String outRequestNo)
            throws AlipayApiException {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

//        调取接口
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\"," + "\"refund_amount\":\"" + refundAmount
                + "\"," + "\"refund_reason\":\"" + refundReason + "\"," + "\"out_request_no\":\"" + outRequestNo
                + "\"}");
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    @Override
    public String query(String outTradeNo) throws AlipayApiException {
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
//        请求接口
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\"," + "\"trade_no\":\"" + "" + "\"}");
//        转换格式
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    @Override
    public String close(String outTradeNo) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\"," + "\"trade_no\":\"" + "" + "\"}");

        String result = alipayClient.execute(alipayRequest).getBody();

        return result;
    }

    @Override
    public String refundQuery(String outTradeNo, String outRequestNo) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

//        请求接口
        alipayRequest.setBizContent(
                "{\"out_trade_no\":\"" + outTradeNo + "\"," + "\"out_request_no\":\"" + outRequestNo + "\"}");

//        格式转换
        String result = alipayClient.execute(alipayRequest).getBody();

        return result;
    }

    @Override
    public String appPagePay(String outTradeNo, Integer totalAmount, String subject) throws Exception {
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();

//        同步通知，支付完成后，支付成功页面
        alipayRequest.setReturnUrl(alipayConfig.returnUrl);
//        异步通知，支付完成后，需要进行的异步处理
        alipayRequest.setNotifyUrl(alipayConfig.notifyUrl);

//        销售产品码（固定）
        String productCode = "QUICK_WAP_WAY";

//        进行赋值
        AlipayTradeWapPayModel wapPayModel = new AlipayTradeWapPayModel();
        wapPayModel.setOutTradeNo(outTradeNo);
        wapPayModel.setSubject(subject);
        wapPayModel.setTotalAmount(totalAmount.toString());
        wapPayModel.setBody("商品名称");
        wapPayModel.setTimeoutExpress("2m");
        wapPayModel.setProductCode(productCode);
        alipayRequest.setBizModel(wapPayModel);

//        格式转换
        String result = alipayClients.pageExecute(alipayRequest).getBody().replace('\"', '\'').replace('\n', ' ');
        return result;
    }
}
