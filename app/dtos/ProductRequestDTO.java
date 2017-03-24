package dtos;

/**
 * Created by rownak on 3/23/17.
 */
public class ProductRequestDTO {
    private String name;
    private String code;
    private String specification;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
