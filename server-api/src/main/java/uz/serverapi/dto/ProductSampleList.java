package uz.serverapi.dto;

import lombok.*;
import uz.sharedlibs.dto.ProductDtoSample;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSampleList {
    private List<ProductDtoSample> productDtoSamples;
}
