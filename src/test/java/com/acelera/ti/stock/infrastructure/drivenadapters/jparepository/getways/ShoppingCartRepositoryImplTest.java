package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.getways;

import com.acelera.ti.stock.domain.model.exceptions.ShoppingCartNotFoundException;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartProductEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.StockEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.mapper.ShoppingCartMapper;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository.ShoppingCartJpaRepository;
import com.acelera.ti.stock.mock.cart.ShoppingCartMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShoppingCartRepositoryImplTest {
    @Mock
    private ShoppingCartJpaRepository shoppingCartJpaRepository;

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @InjectMocks
    private ShoppingCartRepositoryImpl shoppingCartRepositoryImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shoppingCartRepositoryImpl = new ShoppingCartRepositoryImpl(
                shoppingCartJpaRepository, shoppingCartMapper);
    }

    @Test
    @DisplayName("Given a valid user ID, when getShoppingCartByUserId is called, " +
            "then it should return the corresponding shopping cart")
    void testGetShoppingCartByUserId() {
        // Configurar
        Long userId = 1L;

        ShoppingCartEntity shoppingCartEntity = ShoppingCartEntity.builder()
                .id(1L)
                .idUser(userId)
                .lastUpdate(LocalDate.now())
                .build();

        ShoppingCart shoppingCart = ShoppingCartMocks.getShoppingCart(userId);

        when(shoppingCartJpaRepository.findByIdUser(userId)).thenReturn(Optional.of(shoppingCartEntity));
        when(shoppingCartMapper.shoppingCartEntityToShoppingCart(shoppingCartEntity)).thenReturn(shoppingCart);

        // Ejecutar
        ShoppingCart result = shoppingCartRepositoryImpl.getShoppingCartByUserId(userId);

        // Verificar
        assertNotNull(shoppingCart);
        assertEquals(shoppingCart, result);
    }

    @Test()
    @DisplayName("Given a valid user ID, when getShoppingCartByUserId is called," +
            "and ShoppingCart is empty, then it should throw a ShoppingCartNotFoundException")
    void testGetShoppingCartByUserIdNotFoundException() {
        //Configurar
        Long userId = 123L;
        when(shoppingCartJpaRepository.findByIdUser(userId)).thenReturn(Optional.empty());

        // Ejecutar y Verificar
        assertThrows(ShoppingCartNotFoundException.class, () ->
                shoppingCartRepositoryImpl.getShoppingCartByUserId(userId));
    }

    @Test
    @DisplayName("")
    void testRemoveProductFromCart() {
        // Configurar
        long productId = 1L;
        long cartId = 2L;
        long userId = 1L;

        ShoppingCartProductEntity shoppingCartProduct = ShoppingCartProductEntity.builder()
                .id(1L)
                .stock(StockEntity.builder()
                        .productId(productId)
                        .build())
                .build();

        Set<ShoppingCartProductEntity> productEntities = new HashSet<>();
        productEntities.add(shoppingCartProduct);

        ShoppingCartEntity expectedCart = ShoppingCartEntity.builder()
                .id(cartId)
                .idUser(userId)
                .products(productEntities)
                .lastUpdate(LocalDate.now())
                .build();

        when(shoppingCartJpaRepository.findById(cartId)).thenReturn(Optional.of(expectedCart));

        // Ejecutar
        shoppingCartRepositoryImpl.removeProductFromCart(productId, cartId);

        // Verificar
        assertEquals(0, expectedCart.getProducts().size());
    }
}
