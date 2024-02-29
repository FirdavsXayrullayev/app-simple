package uz.clientapi.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import uz.clientapi.service.ClientProductService;
import uz.sharedlibs.dto.ProductDto;
import uz.sharedlibs.dto.ResponseDto;

import java.util.List;


@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientProductResources {
    private final ClientProductService productService;

    @GetMapping("get-by-id")
    public ResponseDto<ProductDto> getProduct(@RequestParam String id) {
        return productService.getProductByID(id);
    }
}