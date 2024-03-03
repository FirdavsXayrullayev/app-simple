package uz.serverapi.servise;

import com.itextpdf.text.DocumentException;
import uz.serverapi.dto.ProductDto;
import uz.serverapi.dto.ProductSampleList;
import uz.serverapi.dto.ResponseDto;
import uz.serverapi.model.ProductDtoSample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileService {
    ResponseDto<String> exelCreate() throws IOException;

    ResponseDto<String> pdfGeneration() throws DocumentException, FileNotFoundException;

    ResponseDto<String> postProducts(List<ProductDtoSample> productSampleList);
}
