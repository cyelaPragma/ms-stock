package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.getways;

import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.ShoppingCartNotFoundException;
import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartProductEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.mapper.ShoppingCartMapper;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository.ShoppingCartJpaRepository;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository.ShoppingCartProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
    private final ShoppingCartJpaRepository shoppingCartJpaRepository;
    private final ShoppingCartProductJpaRepository shoppingCartProductJpaRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCart getShoppingCartByUserId(Long userId) {
        return shoppingCartMapper.shoppingCartEntityToShoppingCart(
                shoppingCartJpaRepository.findByUserId(userId).orElseThrow(ShoppingCartNotFoundException::new));
    }

    @Override
    public void removeProductFromCart(long productId, long cartId) {
        System.out.println("productId :: " + productId + " cartId::"  + cartId);

        ShoppingCartEntity shoppingCartEntity = shoppingCartJpaRepository.findById(cartId).orElseThrow();

        System.out.println("shoppingCartEntity  size::: "   +  shoppingCartEntity.getProducts().size());

        if (shoppingCartEntity.getProducts() == null) {
            throw new ProductNotFoundException();
        }
        Set<ShoppingCartProductEntity> shoppingCartProductEntity = shoppingCartEntity.getProducts().stream()
                        .filter(f -> f.getShoppingCart().getId().equals(cartId)
                                && f.getStock().getProductId() == productId
                        ).collect(Collectors.toSet());

        if (!shoppingCartProductEntity.isEmpty()){
            System.out.println("idShoppingCart:: " + shoppingCartProductEntity.stream().findFirst().get().getId());
            shoppingCartProductJpaRepository.deleteById(shoppingCartProductEntity.stream().findFirst().get().getId());
        }

        shoppingCartProductEntity.forEach(System.out::println);

        /*
        shoppingCartEntity.getProducts().removeIf(product -> product.getId().equals(productId));

        System.out.println("shoppingCartEntity  size::: "   +  shoppingCartEntity.getProducts().size());



        shoppingCartJpaRepository.save(shoppingCartEntity);

         */
    }
}
