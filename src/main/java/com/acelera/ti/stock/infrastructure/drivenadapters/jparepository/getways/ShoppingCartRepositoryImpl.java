package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.getways;

import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.ShoppingCartNotFoundException;
import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartProductEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.mapper.ShoppingCartMapper;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository.ShoppingCartJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
    private final ShoppingCartJpaRepository shoppingCartJpaRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCart getShoppingCartByUserId(Long userId) {
        return shoppingCartMapper.shoppingCartEntityToShoppingCart(
                shoppingCartJpaRepository.findByIdUser(userId).orElseThrow(ShoppingCartNotFoundException::new));
    }

    @Override
    @Transactional
    public void removeProductFromCart(long productId, long cartId) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartJpaRepository.findById(cartId).orElseThrow(
                ShoppingCartNotFoundException::new);
        ShoppingCartProductEntity productEntity = shoppingCartEntity.getProducts()
                .stream()
                .filter(product -> product.getStock().getProductId() == productId)
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
        shoppingCartEntity.getProducts().remove(productEntity);
        shoppingCartJpaRepository.save(shoppingCartEntity);
    }

    @Override
    @Transactional
    public ShoppingCart saveShoppingCart(ShoppingCart cart) {
        ShoppingCartEntity cartEntity = shoppingCartMapper.shoppingCartToShoppingCartEntity(cart);
        return shoppingCartMapper.shoppingCartEntityToShoppingCart(shoppingCartJpaRepository.save(cartEntity));
    }
}
