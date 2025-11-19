package com.demo.chatApp.common.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class RequestLoggingFilter extends OncePerRequestFilter {
    private static final String TRACE_ID = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        // Ambil traceId dari header kalau sudah ada, atau buat baru
        String traceId = request.getHeader(TRACE_ID);
        if (traceId == null || traceId.isBlank()) {
            traceId = UUID.randomUUID().toString();
        }

        // Tambahkan MDC context
        MDC.put(TRACE_ID, traceId);
        MDC.put("method", request.getMethod());
        MDC.put("path", request.getRequestURI());

        try {
            // Log request masuk
            log.info("Incoming Request");

            // Jalankan filter berikutnya (controller)
            filterChain.doFilter(request, response);

            long duration = System.currentTimeMillis() - startTime;

            // Log response keluar
            log.info("Response status={} duration={}ms", response.getStatus(), duration);

            // Optional: kirim traceId balik ke response header
            response.setHeader(TRACE_ID, traceId);

        } finally {
            // Bersihkan MDC agar tidak bocor antar-thread
            MDC.clear();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/actuator") || path.startsWith("/swagger") || path.startsWith("/v3/api-docs");
    }
}
