package com.zhs.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;


/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 09:47
 * @package: com.zhs.filter
 * @description:
 *
 * 基于webFilter组就配置
 * 再启动类上面加上@ServletComponentScan
 * 但是这样无法指定过滤器得顺序
 */
@javax.servlet.annotation.WebFilter(filterName = "ironFilter",urlPatterns = "/*")
@Slf4j
@Order(1)
public class IronFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter处理请求前面");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("filter销毁");
    }
}
