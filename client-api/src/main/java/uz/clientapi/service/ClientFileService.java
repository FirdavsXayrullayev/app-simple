package uz.clientapi.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import uz.sharedlibs.dto.ProductDtoSample;
import uz.sharedlibs.dto.ResponseDto;

import java.io.IOException;

public interface ClientFileService {
    ResponseDto<String> postProducts() throws IOException, InvalidFormatException;
}
