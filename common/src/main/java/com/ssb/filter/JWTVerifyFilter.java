package com.ssb.filter;

import com.ssb.constants.Constants;
import com.ssb.exception.BusinessException;
import com.ssb.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JWTVerifyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )throws ServletException, IOException
    {
        // 获取请求头
        String header = request.getHeader(Constants.HTTP_HEADER_AUTHORIZATION);

        // 查看有没有token
        if (null == header || !header.startsWith(Constants.HTTP_HEADER_PREFIX)){
            filterChain.doFilter(request,response);
            return;
        }

        // 提取token
        String token = header.substring(Constants.HTTP_HEADER_PREFIX.length()).trim();

        try {
            Claims claims = JwtUtil.getDecodedPayload(token);

            // 看看Token过期没有
            if (claims.getExpiration().before(new Date())){
                throw new BusinessException("Token 无效或已过期");
            }

            // 提出token在主题中存储的用户ID
            String subject = claims.getSubject();
            Long userID = Long.parseLong(subject);

            // 获取权限信息
            List<String> resources = JwtUtil.getAuthorities(claims);

            // 构建Authentication
            List<GrantedAuthority> grantedAuthorities = resources.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userID, null, grantedAuthorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request,response);

        } catch (Exception e){
            throw new BusinessException("Token 解析失败: " + e.getMessage());
        }
    }
}
