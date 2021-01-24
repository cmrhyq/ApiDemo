package cn.kevinkda.demo.api.module.pay.alipay.b20210112.controller.system.version;

import cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.consts.ConstsConfig;
import cn.kevinkda.demo.api.module.pay.alipay.b20210112.config.system.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目版本页控制器
 *
 * @author Kevin KDA on 2021/1/13 17:53
 * @version 0.0.1
 * @project http-api-map-service-202101
 * @package cn.kevinkda.demo.api.module.pay.alipay.b20210112.controller.system.version
 * @classname VersionController
 * @apiNote
 * @implSpec
 * @implNote
 * @since 0.0.1
 */
@Slf4j
@Controller
@RequestMapping({"/"})
public class VersionController {
    @Autowired
    private SpringConfig springConfig;
    @Value("${version.main}")
    private String main;
    @Value("${version.sub}")
    private String sub;
    @Value("${version.pom}")
    private String pom;
    private static final String DATE = new Date().toString();


    @RequestMapping({"/", "index", "version"})
    public ModelAndView index() {
        Map<String, Object> map = new HashMap<>();
        map.put(ConstsConfig.FIELD_APPLICATION_NAME, springConfig.name);
        map.put(ConstsConfig.FIELD_VERSION_MAIN, main);
        map.put(ConstsConfig.FIELD_VERSION_SUB, sub);
        map.put(ConstsConfig.FIELD_VERSION_POM, pom);
        map.put(ConstsConfig.FIELD_VERSION_RESTART_DATE, DATE);
        map.put(ConstsConfig.FIELD_REQUEST_TIME, new Date().toString());
        log.info(map.toString());
        return new ModelAndView("version", map);
    }

}
