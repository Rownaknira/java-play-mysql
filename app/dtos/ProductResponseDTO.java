package dtos;

/**
 * Created by rownak on 3/23/17.
 */
public class ProductResponseDTO extends ProductRequestDTO {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
