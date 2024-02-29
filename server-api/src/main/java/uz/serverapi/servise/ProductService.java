package uz.serverapi.servise;

import uz.serverapi.dto.ProductDto;
import uz.serverapi.dto.ResponseDto;

public interface ProductService {
    ResponseDto<ProductDto> getBYId(Integer id);

    ResponseDto<ProductDto> addNewProduct(ProductDto productDto);

    ResponseDto<ProductDto> update(ProductDto productDto);

    ResponseDto<ProductDto> deleteById(Integer id);
}
