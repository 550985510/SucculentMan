package com.tangdou.succulent.manager.config;

import com.tangdou.succulent.manager.bean.ResponseResult;
import com.tangdou.succulent.manager.bean.RestResultEnum;
import com.tangdou.succulent.manager.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常拦截器
 *
 * @author fengqiang
 */
@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    final Logger logger = LoggerFactory.getLogger(getClass());


    @Value("${server.context-path}")
    private String requestPath;

    /**
     * 判断是不是 REST + JSON 请求
     *
     * @param request 请求体
     * @return 是否是 json 请求
     */
    private boolean isJsonRequest(HttpServletRequest request) {
        return request.getHeader("Accept").contains("application/json");
    }


    /**
     * 自定义异常处理
     *
     * @param e 异常信息
     * @return 处理结果
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = CustomException.class)
    public ResponseResult<Object> handleCupidException(HttpServletRequest request, CustomException e) {
        ResponseResult<Object> result = new ResponseResult<>();
        result.setRetcode(RestResultEnum.ERROR.getKey());
        result.setMsg(e.getMessage());
        return result;
    }
    /**
     * 未知异常处理
     *
     * @param e 异常信息
     * @return 处理结果
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<String> handleException(HttpServletRequest request, Exception e) {
        ResponseResult<String> responseResult = new ResponseResult<>();

        logger.error("{}", e);

        responseResult.setRetcode(RestResultEnum.UNKNOWN_ERROR.getKey());
        responseResult.setMsg(e.getMessage());
        return responseResult;
    }


    /**
     * 404 信息处理
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("/404")
    public void handleNotFoundException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isJsonRequest(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSONUtil.toJson(new ResponseResult(RestResultEnum.NOT_FOUND_ERROR)));
            response.getWriter().flush();
            response.getWriter().close();
        } else {
            response.sendRedirect(requestPath + "/error_404");
        }
    }


    /**
     * 500 错误信息
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping("/500")
    public void handleException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isJsonRequest(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSONUtil.toJson(new ResponseResult(RestResultEnum.UNKNOWN_ERROR)));
            response.getWriter().flush();
            response.getWriter().close();
        } else {
            response.sendRedirect(requestPath + "/error_500");
        }
    }


    /**
     * 403 错误信息
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @RequestMapping("/403")
    public void handleForbiddenException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isJsonRequest(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSONUtil.toJson(new ResponseResult(RestResultEnum.FORBIDDEN_ERROR)));
            response.getWriter().flush();
            response.getWriter().close();
        } else {
            response.sendRedirect(requestPath + "/error_403");
        }
    }


}
