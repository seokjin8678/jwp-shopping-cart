package cart.service;

import cart.dto.cart.CartProductDto;
import cart.exception.ProductNotFoundException;
import cart.repository.CartDao;
import cart.repository.ProductDao;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartDao cartDao;
    private final ProductDao productDao;

    public CartService(CartDao cartDao, ProductDao productDao) {
        this.cartDao = cartDao;
        this.productDao = productDao;
    }

    public void addToCart(Long memberId, Long productId) {
        validateProductId(productId);
        cartDao.save(memberId, productId);
    }

    private void validateProductId(Long productId) {
        if (!productDao.existsById(productId)) {
            throw new ProductNotFoundException("존재하지 않는 상품의 ID 입니다.");
        }
    }

    public List<CartProductDto> findAllProducts(Long memberId) {
        return cartDao.findAllProductByMemberId(memberId);
    }

    public void deleteProduct(Long memberId, Long cartId) {
        validateCartId(cartId);
        cartDao.delete(memberId, cartId);
    }

    private void validateCartId(Long cartId) {
        if (!cartDao.existsById(cartId)) {
            throw new ProductNotFoundException("존재하지 않는 장바구니의 ID 입니다.");
        }
    }
}