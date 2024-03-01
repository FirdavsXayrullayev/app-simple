package uz.clientapi.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("product")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class ClientProductResources {
    private final ClientProductService productService;

    @GetMapping("get-by-id")
    public ResponseDto<ProductDto> getProduct(@RequestParam String id) {
        return productService.getProductByID(id);
    }
    @PostMapping("add-new-product")
    public ResponseDto<ProductDto> addNewProduct(@RequestBody ProductDto productDto){
        return productService.addNewProduct(productDto);
    }
    @PatchMapping("update")
    public ResponseDto<ProductDto> update(@RequestBody ProductDto productDto){
        return productService.update(productDto);
    }
    @DeleteMapping("delete-product")
    public ResponseDto<ProductDto> deleteById(@RequestParam Integer id){
        return productService.deleteById(id);
    }
}
