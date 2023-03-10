package top.mxzero.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/6
 */
@RestController
public class ResourceController {
    @RequestMapping("/resource")
    public Object resource() {
        return Map.of(
                "name", "zero",
                "age", 20,
                "id", 100242,
                "role", List.of("ROLE_USER", "ROLE_STAFF")
        );
    }
}
