package uz.sharedlibs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDtoSample {
    private Integer id;
    private String name;
    private Integer price;
}
