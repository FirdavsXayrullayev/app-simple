package uz.sharedlibs.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSampleList {
    private List<ProductDtoSample> productDtoSamples;
}
