package cn.kevinkda.demo.api.module.pay.alipay.b20210112.controller.api.alipay;

import cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.consts.ConstsConfig;
import com.alibaba.fastjson.JSONObject;
import com.kevinkda.core.util.web.servlet.request.RequestService;
import com.kevinkda.core.util.web.servlet.request.RequestServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin KDA on 2021/1/13 18:44
 * @version 0.0.1
 * @project http-api-map-service-202101
 * @package cn.kevinkda.demo.api.module.pay.alipay.b20210112.controller.api.alipay
 * @classname CallbackController
 * @apiNote
 * @implSpec
 * @implNote
 * @since 0.0.1
 */
@Slf4j
@Controller
@RequestMapping("/v1/payment/alipay")
public class CallbackController {
    @RequestMapping({"/gateway"})
    public ModelAndView notifyInfo(
            @RequestParam(value = "charset", required = false) String charset,  //编码格式
            @RequestParam(value = "out_trade_no", required = false) String outTradeNo,  //商品订单号
            @RequestParam(value = "method", required = false) String method,  //接口名称
            @RequestParam(value = "total_amount", required = false) String totalAmount,  //金额
            @RequestParam(value = "sign", required = false) String sign,  //商品请求参数的签名串
            @RequestParam(value = "trade_no", required = false) String tradeNo,  //支付宝交易号
            @RequestParam(value = "auth_app_id", required = false) String authAppId,  //
            @RequestParam(value = "version", required = false) String version,  //调用接口版本
            @RequestParam(value = "app_id", required = false) String appId,  //用户id
            @RequestParam(value = "sign_type", required = false) String signType,  //签名算法类型
            @RequestParam(value = "seller_id", required = false) String sellerId,  //收款支付宝账号对应的支付宝唯一用户号。
            @RequestParam(value = "timestamp", required = false) String timestamp  //发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put(ConstsConfig.FIELD_REQUEST_TIME, new Date().toString());
        return new ModelAndView("return", map);
    }

    @RequestMapping({"/callback"})
    public ModelAndView returnInfo(HttpServletRequest request) {
        RequestService requestServices = new RequestService();
        log.info(JSONObject.toJSONString(requestServices.getRequestBody(request)));
        Map<String, Object> map = new HashMap<>();
        map.put("request", JSONObject.toJSONString(requestServices.getRequestBody(request)));
        map.put(ConstsConfig.FIELD_REQUEST_TIME, new Date().toString());
        return new ModelAndView("return", map);
    }
}
