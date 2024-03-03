package uz.clientapi.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.clientapi.service.ClientFileService;
import uz.sharedlibs.dto.ProductDtoSample;

import uz.sharedlibs.dto.ResponseDto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        File file = new File("C:\\Users\\Firdavs\\IdeaProjects\\app-simple\\products.xlsx");

        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();


//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.execute(() -> {
//
            for (int n = 1; n <= 100000; n++) {
                Row row = sheet.getRow(n);
//                int i = row.getFirstCellNum();
                ProductDtoSample foodInfo = new ProductDtoSample();

                foodInfo.setId(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0))));
                foodInfo.setName(dataFormatter.formatCellValue(row.getCell(1)));
                foodInfo.setPrice(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(2))));

                list.add(foodInfo);
            }
//        });
//        executorService.shutdown();
//        for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
//            Row row = sheet.getRow(n);
//            int i = row.getFirstCellNum();
//
//            foodInfo.setId(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(i))));
//            foodInfo.setName(dataFormatter.formatCellValue(row.getCell(++i)));
//            foodInfo.setPrice(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(++i))));
//
//            list.add(foodInfo);
//        }
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<List<ProductDtoSample>> entity = new HttpEntity<>(list, headers);
        System.out.println(list.size());
        if (token != null) {
            headers.set("Authorization", token);
        }
        return restTemplate.exchange("http://localhost:8000/file/post-products", HttpMethod.POST, entity, ResponseDto.class).getBody();
    }
}
