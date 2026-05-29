package com.demo.chatApp.security;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtChannelInterceptor implements ChannelInterceptor{

    private final JwtProvider jwtProvider;
    private final JwtUserDetailService jwtUserDetailService;

    @Override
    public Message<?> preSend(
            @Nonnull Message<?> message,
            @Nonnull MessageChannel channel
    ) {
        StompHeaderAccessor accessor =  MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            List<String> authorization = accessor.getNativeHeader("Authorization");

            if (authorization != null && !authorization.isEmpty()) {
                String bearerToken = authorization.getFirst();

                if (bearerToken.startsWith("Bearer ")) {
                    String token = bearerToken.substring(7);
                    try {
                        if (jwtProvider.validateToken(token) && !jwtProvider.isTokenExpired(token)) {
                            String username = jwtProvider.getUsernameToken(token);
                            UserDetails userDetails = jwtUserDetailService.loadUserByUsername(username);
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                            accessor.setUser(authentication);
                        }else {
                            throw new IllegalArgumentException("token invalid or expired");
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid Authorization Header format");
                    }
                }else {
                    throw new IllegalArgumentException("no token provided");
                }
            }
        }
        return message;
    }
}
