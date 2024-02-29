package uz.serverapi.servise;

import com.itextpdf.text.DocumentException;
import uz.serverapi.dto.ResponseDto;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {
    ResponseDto<String> exelCreate() throws IOException;

    ResponseDto<String> pdfGeneration() throws DocumentException, FileNotFoundException;
}
