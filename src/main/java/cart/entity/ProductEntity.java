package cart.entity;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductEntity {
    private final Long id;
    private final String name;
    private final int price;
    private final String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductEntity)) {
            return false;
        }
        ProductEntity that = (ProductEntity) o;
        return price == that.price && Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, imageUrl);
    }
}
