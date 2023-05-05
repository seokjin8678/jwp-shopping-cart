package cart.service;

import cart.dto.cart.CartProductDto;
import cart.exception.CartNotFoundException;
import cart.exception.ProductNotFoundException;
import cart.repository.CartDao;
import cart.repository.ProductDao;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CartService {
    private final CartDao cartDao;
    private final ProductDao productDao;

    public CartService(CartDao cartDao, ProductDao productDao) {
        this.cartDao = cartDao;
        this.productDao = productDao;
    }

    @Transactional
    public void addToCart(Long memberId, Long productId) {
        validateProductId(productId);
        cartDao.save(memberId, productId);
    }

    private void validateProductId(Long productId) {
        if (!productDao.existsById(productId)) {
            throw new ProductNotFoundException();
        }
    }

    public List<CartProductDto> findAllCartProducts(Long memberId) {
        return cartDao.findAllCartProductByMemberId(memberId);
    }

    @Transactional
    public void deleteProduct(Long memberId, Long cartId) {
        validateCartId(memberId, cartId);
        cartDao.delete(memberId, cartId);
    }

    private void validateCartId(Long memberId, Long cartId) {
        if (!cartDao.existsByIdAndMemberId(cartId, memberId)) {
            throw new CartNotFoundException();
        }
    }
}
