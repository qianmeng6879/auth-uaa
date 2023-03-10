package top.mxzero.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/6
 */
@RestController
public class TestController {

    @RequestMapping("/tests")
    public Object test() {
        return Map.of("message", "test");
    }
}
