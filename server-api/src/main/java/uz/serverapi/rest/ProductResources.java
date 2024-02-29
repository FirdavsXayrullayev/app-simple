package uz.serverapi.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.serverapi.dto.ProductDto;
import uz.serverapi.dto.ResponseDto;
import uz.serverapi.servise.ProductService;
import uz.serverapi.servise.UserService;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class ProductResources {
    private final ProductService productService;

    @PreAuthorize("hasAnyAuthority('READ')")
    @GetMapping("get-by-id/{id}")
    public ResponseDto<ProductDto> getById(@PathVariable Integer id){
        return productService.getBYId(id);
    }

    @PostMapping("add-new-product")
    public ResponseDto<ProductDto> addNewProduct(@RequestBody ProductDto productDto){
        return productService.addNewProduct(productDto);
    }
    @PreAuthorize("hasAnyAuthority('UPDATE')")
    @PatchMapping("update")
    public ResponseDto<ProductDto> update(@RequestBody ProductDto productDto){
        return productService.update(productDto);
    }
    @PreAuthorize("hasAnyAuthority('DELETE')")
    @DeleteMapping("delete-product")
    public ResponseDto<ProductDto> deleteById(@RequestParam Integer id){
        return productService.deleteById(id);
    }
}
