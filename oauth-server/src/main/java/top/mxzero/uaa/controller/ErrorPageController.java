package top.mxzero.uaa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.PrintWriter;
import java.util.Map;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/7
 */
@Controller
@RequestMapping("/error/*")
public class ErrorPageController {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<String, Object> error401 = Map.of("error", "Unauthorized");
    private static final Map<String, Object> error403 = Map.of("error", "Forbidden");

    @RequestMapping("401")
    public void error401(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(error401));
            writer.close();
        }
    }

    @RequestMapping("403")
    public void error403(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(error403));
            writer.close();
        } else {
            request.getRequestDispatcher("/error/403").forward(request, response);
        }
    }

    @RequestMapping("404")
    public String error404() {
        return "error/404";
    }

    @RequestMapping("500")
    public String error500() {
        return "error/500";
    }
}
