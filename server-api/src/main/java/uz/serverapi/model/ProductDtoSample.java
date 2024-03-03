package uz.serverapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductDtoSample {
    @Id
//    @GeneratedValue(generator = "productSampleIdSeq")
//    @SequenceGenerator(name = "productSampleIdSeq", sequenceName = "product_sample_id_seq")
    private Integer id;
    private String name;
    private Integer price;
}
