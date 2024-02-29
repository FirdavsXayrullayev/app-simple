package uz.clientapi.service;

import uz.sharedlibs.dto.ProductDto;
import uz.sharedlibs.dto.ResponseDto;

public interface ClientProductService {
    ResponseDto<ProductDto> getProductByID(String id);

    ResponseDto<ProductDto> addNewProduct(ProductDto productDto);

    ResponseDto<ProductDto> update(ProductDto productDto);

    ResponseDto<ProductDto> deleteById(Integer id);
}
