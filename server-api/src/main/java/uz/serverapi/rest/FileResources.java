package uz.serverapi.rest;

import com.itextpdf.text.DocumentException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.serverapi.dto.ProductDto;
import uz.serverapi.dto.ProductSampleList;
import uz.serverapi.dto.ResponseDto;
import uz.serverapi.model.ProductDtoSample;
import uz.serverapi.repository.ProductRepository;
import uz.serverapi.servise.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class FileResources {
    private final FileService fileService;
    @GetMapping("excel")
    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseDto<String> exelGeneration() throws IOException {
        return fileService.exelCreate();
    }
    @GetMapping("pdf")
    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseDto<String> pdfGeneration() throws DocumentException, FileNotFoundException {
        return fileService.pdfGeneration();
    }
    @PostMapping("post-products")
    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseDto<String> postProducts(@RequestBody List<ProductDtoSample> productDtoSamples){
        return fileService.postProducts(productDtoSamples);
    }
}
