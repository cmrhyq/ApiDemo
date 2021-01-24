package cn.kevinkda.demo.api.module.pay.alipay.b20210112.service.api.alipay;

import com.alipay.api.AlipayApiException;
import com.kevinkda.core.util.annotation.enumeration.VerifiedType;
import com.kevinkda.core.util.annotation.func.FuncVerification;

/**
 * @author Kevin KDA on 2021/1/13 14:18
 * @version 0.0.1
 * @project http-api-map-service-202101
 * @package cn.kevinkda.demo.api.module.pay.alipay.b20210112.service.api.alipay
 * @classname AlipayService
 * @apiNote
 * @implSpec
 * @implNote
 * @since 0.0.1
 */
public interface AlipayService {
    /**
     * Web端订单支付
     *
     * @param outTradeNo  订单编号（唯一）
     * @param totalAmount 订单价格
     * @param subject     商品名称
     * @return java.lang.String
     * @throws Exception
     * @author Kevin KDA on 2021/1/13 14:19
     * @description AlipayService / webPagePay
     * @version 1.0.0
     * @apiNote
     * @implSpec
     * @implNote
     * @since 1.0.0
     */
    @FuncVerification(version = "1.0.0", status = VerifiedType.Unverified, date = "2021/1/13 14:19")
    String webPagePay(String outTradeNo, Integer totalAmount, String subject) throws Exception;

    /**
     * app端订单支付
     *
     * @param outTradeNo  订单编号
     * @param totalAmount 订单价格
     * @param subject     商品名称
     * @return java.lang.String
     * @throws Exception
     * @author Kevin KDA on 2021/1/13 14:21
     * @description AlipayService / appPagePay
     * @version 1.0.0
     * @apiNote
     * @implSpec
     * @implNote
     * @since 1.0.0
     */
    @FuncVerification(version = "1.0.0", status = VerifiedType.Unverified, date = "2021/1/13 14:21")
    String appPagePay(String outTradeNo, Integer totalAmount, String subject) throws Exception;

    /**
     * 退款
     *
     * @param outTradeNo   订单编号
     * @param refundReason 退款原因
     * @param refundAmount 退款金额
     * @param outRequestNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     * @return java.lang.String
     * @throws AlipayApiException
     * @author Kevin KDA on 2021/1/13 14:21
     * @description AlipayService / refund
     * @version 1.0.0
     * @apiNote
     * @implSpec
     * @implNote
     * @since 1.0.0
     */
    @FuncVerification(version = "1.0.0", status = VerifiedType.Unverified, date = "2021/1/13 14:21")
    String refund(String outTradeNo, String refundReason, Integer refundAmount, String outRequestNo) throws AlipayApiException;

    /**
     * 交易查询
     *
     * @param outTradeNo 订单编号（唯一）
     * @return java.lang.String
     * @throws AlipayApiException
     * @author Kevin KDA on 2021/1/13 14:23
     * @description AlipayService / query
     * @version 1.0.0
     * @apiNote
     * @implSpec
     * @implNote
     * @since 1.0.0
     */
    @FuncVerification(version = "1.0.0", status = VerifiedType.Unverified, date = "2021/1/13 14:23")
    String query(String outTradeNo) throws AlipayApiException;

    /**
     * 交易关闭
     *
     * @param outTradeNo 订单编号（唯一）
     * @return java.lang.String
     * @throws AlipayApiException
     * @author Kevin KDA on 2021/1/13 14:24
     * @description AlipayService / close
     * @version 1.0.0
     * @apiNote
     * @implSpec
     * @implNote
     * @since 1.0.0
     */
    @FuncVerification(version = "1.0.0", status = VerifiedType.Unverified, date = "2021/1/13 14:24")
    String close(String outTradeNo) throws AlipayApiException;

    /**
     * 退款查询
     *
     * @param outTradeNo   订单编号（唯一）
     * @param outRequestNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     * @return java.lang.String
     * @throws AlipayApiException
     * @author Kevin KDA on 2021/1/13 14:24
     * @description AlipayService / refundQuery
     * @version 1.0.0
     * @apiNote
     * @implSpec
     * @implNote
     * @since 1.0.0
     */
    @FuncVerification(version = "1.0.0", status = VerifiedType.Unverified, date = "2021/1/13 14:24")
    String refundQuery(String outTradeNo, String outRequestNo) throws AlipayApiException;
}
