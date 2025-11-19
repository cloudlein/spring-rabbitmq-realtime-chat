package com.demo.chatApp.common.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class MdcFilter implements Filter {
    private static final String TRACE_ID = "traceId";
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            String traceId = UUID.randomUUID().toString();
            MDC.put(TRACE_ID, traceId);

            if (request instanceof HttpServletRequest req) {
                MDC.put("method", req.getMethod());
                MDC.put("path", req.getRequestURI());
            }

            chain.doFilter(request, response);
        } finally {
            MDC.clear(); // wajib agar tidak bocor di thread pool
        }
    }
}
