package top.yekongle.shopmanager.filter;

/**
 * @author Yekongle
 * @date 2020/10/24 18:58
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.yekongle.shopmanager.common.CommonEnum;
import top.yekongle.shopmanager.common.JwtConstant;
import top.yekongle.shopmanager.exception.BizException;
import top.yekongle.shopmanager.utils.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token过滤器，用于每次外部对接口请求时的Token处理
 *
 * */
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(JwtConstant.HEADER_STRING);
        log.info("Auth header:{}", authHeader);
        if (authHeader != null && authHeader.startsWith( JwtConstant.TOKEN_PREFIX )) {
            final String authToken = authHeader.substring( JwtConstant.TOKEN_PREFIX.length() );
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }/* else {
                    throw new BizException(CommonEnum.SIGNATURE_NOT_MATCH.getResultCode(), CommonEnum.SIGNATURE_NOT_MATCH.getResultMsg());
                }*/
            }
        }
        chain.doFilter(request, response);
    }
}
