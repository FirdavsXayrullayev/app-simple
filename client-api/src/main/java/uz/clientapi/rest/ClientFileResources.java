package uz.clientapi.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import uz.clientapi.service.ClientFileService;
import uz.clientapi.service.ClientProductService;
import uz.sharedlibs.dto.ProductDto;
import uz.sharedlibs.dto.ProductDtoSample;
import uz.sharedlibs.dto.ResponseDto;

import java.io.IOException;

@RestController
@RequestMapping("file")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class ClientFileResources {
    private final ClientFileService clientFileService;

    @GetMapping("post-products-excel")
    public ResponseDto<String> postProducts() throws IOException, InvalidFormatException {
        return clientFileService.postProducts();
    }
}
