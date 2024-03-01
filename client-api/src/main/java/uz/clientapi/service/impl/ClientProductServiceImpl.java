package uz.clientapi.service.impl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
public class ClientProductServiceImpl implements ClientProductService {
    private final RestTemplate restTemplate;
    public final HttpHeaders headers;
    public static String token;
    @Override
    public ResponseDto<ProductDto> getProductByID(String id) {
        HttpEntity <String> entity = new HttpEntity<>(headers);
        if (token != null) {
            headers.set("Authorization", token);
        }
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return restTemplate.exchange("http://localhost:8000/product/get-by-id/" + id, HttpMethod.GET, entity, ResponseDto.class).getBody();

    }

    @Override
    public ResponseDto<ProductDto> addNewProduct(ProductDto productDto) {
        HttpEntity<ProductDto> entity = new HttpEntity<>(productDto, headers);
        if (token != null) {
            headers.set("Authorization", token);
        }
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return restTemplate.exchange("http://localhost:8000/product/add-new-product", HttpMethod.POST, entity, ResponseDto.class).getBody();
    }

    @Override
    public ResponseDto<ProductDto> update(ProductDto productDto) {
        HttpEntity<ProductDto> entity = new HttpEntity<>(productDto, headers);
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        if (token != null) {
            headers.set("Authorization", token);
        }
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate.exchange("http://localhost:8000/product/update" , HttpMethod.PATCH, entity, ResponseDto.class).getBody();
    }

    @Override
    public ResponseDto<ProductDto> deleteById(Integer id) {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        if (token != null) {
            headers.set("Authorization", token);
        }
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return restTemplate.exchange("http://localhost:8000/product/delete-product/" + id, HttpMethod.DELETE, entity, ResponseDto.class).getBody();
    }
}
