package uz.clientapi.service.impl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.clientapi.service.ClientProductService;
import uz.sharedlibs.dto.ProductDto;
import uz.sharedlibs.dto.ResponseDto;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientProductServiceImpl extends OncePerRequestFilter implements ClientProductService {
    private final RestTemplate restTemplate;
    public static String token = "";
    @Override
    public ResponseDto<ProductDto> getProductByID(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        headers.add("Authorization", token);
        headers.setBearerAuth(token);
        System.out.println(headers);
        return restTemplate.getForEntity("http://localhost:8000/product/get-by-id/" + id, ResponseDto.class).getBody();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        token = request.getHeader("Authorization");
        filterChain.doFilter(request, response);
    }
}
