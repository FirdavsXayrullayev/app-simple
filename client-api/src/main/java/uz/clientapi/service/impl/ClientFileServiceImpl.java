package uz.clientapi.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.clientapi.service.ClientFileService;
import uz.sharedlibs.dto.ProductDtoSample;

import uz.sharedlibs.dto.ResponseDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static uz.clientapi.service.impl.ClientProductServiceImpl.token;

@Service
@RequiredArgsConstructor
public class ClientFileServiceImpl implements ClientFileService {
    private final RestTemplate restTemplate;
    public final HttpHeaders headers;
    public final Gson gson;
    @Override
    public ResponseDto<String> postProducts() throws IOException, InvalidFormatException {
        List<ProductDtoSample> list = new ArrayList<>();
        File file = new File("C:\\Users\\User\\IdeaProjects\\sample-app\\products.xlsx");

        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        ProductDtoSample foodInfo = new ProductDtoSample();
        for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
            Row row = sheet.getRow(n);
            int i = row.getFirstCellNum();

            foodInfo.setId(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(i))));
            foodInfo.setName(dataFormatter.formatCellValue(row.getCell(++i)));
            foodInfo.setPrice(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(++i))));

            list.add(foodInfo);
        }
        HttpEntity entity = new HttpEntity<>(gson.toJson(list), headers);
        if (token != null) {
            headers.set("Authorization", token);
        }
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return restTemplate.exchange("http://localhost:8000/product/post-products", HttpMethod.POST, entity, ResponseDto.class).getBody();
    }
}
