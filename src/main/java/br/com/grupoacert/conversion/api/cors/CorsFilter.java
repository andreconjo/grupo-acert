package br.com.grupoacert.conversion.api.cors;


import br.com.grupoacert.conversion.api.model.ResourceLog;
import br.com.grupoacert.conversion.api.service.ResourceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Autowired
    ResourceLogService resourceLogService;

    private String allowOrigin = "*"; //Configure for distinct environments TODO: Change when have site published

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        response.setHeader("Access-Control-Allow-Credentials", "true");

//        if ("OPTIONS".equals(request.getMethod()) && allowOrigin.equals(request.getHeader("Origin"))) TODO: Change when have site published
        if ("OPTIONS".equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            ResourceLog log = new ResourceLog();
            log.setPath(request.getRequestURI());
            log.setDate(new Date());

            chain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
