package uz.serverapi.securityJwt;

import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.serverapi.dto.ResponseDto;
import uz.serverapi.dto.UserDto;
import uz.serverapi.model.User;

import java.io.IOException;

@RequiredArgsConstructor


@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final Gson gson;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String aut = request.getHeader("Authorization");
//        if (aut == null) {
//            response.getWriter().println(gson.toJson(ResponseDto.builder()
//                    .code(-2)
//                    .info("Token is not exists!")
//                    .build()));
//            response.setContentType("application/json");
////            response.setStatus(400);
//        } else {
            if (aut != null && aut.startsWith("Bearer ")) {
                String token = aut.substring(7);
                if (jwtService.expired(token)) {
                    response.getWriter().println(gson.toJson(ResponseDto.builder()
                            .code(-2)
                            .info("Token is expired!")
                            .build()));
                    response.setContentType("application/json");
                    response.setStatus(400);
                } else {
                    UserDto userDto = jwtService.getSubject(token);
                    System.out.println(userDto.getAuthorities());

                    UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDto, null, userDto.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        }
//    }
}
