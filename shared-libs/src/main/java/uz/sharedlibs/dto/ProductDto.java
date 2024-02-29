package uz.sharedlibs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Integer id;
    private String name;
    private Integer price;
    private Integer amount;
    private String description;
    private Integer createBy;
    private String createAt;
    private Integer updateBy;
    private String updateAt;
}
