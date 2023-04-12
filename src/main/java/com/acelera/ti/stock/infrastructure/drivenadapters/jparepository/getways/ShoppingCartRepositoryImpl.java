package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.getways;

import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.ShoppingCartNotFoundException;
import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.mapper.ShoppingCartMapper;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository.ShoppingCartJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
    private final ShoppingCartJpaRepository shoppingCartJpaRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCart getShoppingCartByUserId(Long userId) {
        Optional<ShoppingCartEntity> shoppingCartEntity = shoppingCartJpaRepository.findByUserId(userId);
        return shoppingCartEntity.map(shoppingCartMapper::shoppingCartEntityToShoppingCart)
                .orElseThrow(ShoppingCartNotFoundException::new);
    }

    @Override
    public void removeProductFromCart(long productId, long userId) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);
        if (shoppingCart == null || shoppingCart.getProducts() == null) {
            throw new ProductNotFoundException();
        }
        shoppingCart.getProducts().removeIf(product -> product.getId().equals(productId));
        shoppingCartJpaRepository.save(shoppingCartMapper.shoppingCartToShoppingCartEntity(shoppingCart));
    }
}
