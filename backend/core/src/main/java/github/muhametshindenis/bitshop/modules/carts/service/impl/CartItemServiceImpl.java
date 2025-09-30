package github.muhametshindenis.bitshop.modules.carts.service.impl;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.common.exception.UnauthorizedException;
import github.muhametshindenis.bitshop.modules.carts.dto.CartItemResponseDto;
import github.muhametshindenis.bitshop.modules.carts.dto.CreateCartItemDto;
import github.muhametshindenis.bitshop.modules.carts.dto.UpdateCartItemDto;
import github.muhametshindenis.bitshop.modules.carts.entity.Cart;
import github.muhametshindenis.bitshop.modules.carts.entity.CartItem;
import github.muhametshindenis.bitshop.modules.carts.mapper.CartItemMapper;
import github.muhametshindenis.bitshop.modules.carts.repository.CartItemRepository;
import github.muhametshindenis.bitshop.modules.carts.service.CartItemService;
import github.muhametshindenis.bitshop.modules.carts.service.CartService;
import github.muhametshindenis.bitshop.modules.products.entity.Product;
import github.muhametshindenis.bitshop.modules.products.service.ProductService;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 23.09.2025 September 2025
 */
@Service
public class CartItemServiceImpl implements CartItemService {
    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;

    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
    }

    @Override
    public CartItemResponseDto create(UserDetails userDetails, CreateCartItemDto createCartItemDto) {
        User user = this.getUserFromUserDetails(userDetails);
        Product product = this.productService.findProductById(createCartItemDto.productId());

        if (this.cartItemRepository.findByUserIdAndProductId(user.getId(), createCartItemDto.productId()).isPresent()) {
            throw new ConflictException("Продукт с ID: " + createCartItemDto.productId() + " уже добавлен в корзину!");
        }

        if (product.getQuantity() < createCartItemDto.quantity()) {
            throw new BadRequestException("Указанное количество товара превышает существующее!");
        }

        if (createCartItemDto.quantity() <= 0) {
            throw new BadRequestException("Указанное количество товара должно быть больше 0!");
        }

        Cart cart = this.cartService.findCartByUser(user);

        CartItem cartItem = CartItem.builder()
                .quantity(createCartItemDto.quantity())
                .product(product)
                .cart(cart)
                .build();

        return CartItemMapper.toResponse(this.cartItemRepository.save(cartItem));
    }

    @Override
    public CartItemResponseDto findByProductId(UserDetails userDetails, Long productId) {
        User user = this.getUserFromUserDetails(userDetails);
        Product product = this.productService.findProductById(productId);

        CartItem cartItem = this.cartItemRepository.findByUserIdAndProductId(user.getId(), product.getId()).orElseThrow(() ->
                new NotFoundException("Продукт с ID: " + productId + " не добавлен в корзину!"));

        return CartItemMapper.toResponse(cartItem);
    }

    @Override
    public CartItemResponseDto update(UserDetails userDetails, UpdateCartItemDto updateCartItemDto, Long productId) {
        User user = this.getUserFromUserDetails(userDetails);
        Product product = this.productService.findProductById(productId);

        CartItem cartItem = this.cartItemRepository.findByUserIdAndProductId(user.getId(), product.getId()).orElseThrow(() ->
                new NotFoundException("Продукт с ID: " + productId + " не добавлен в корзину!"));

        if (cartItem.getProduct().getQuantity() < updateCartItemDto.quantity()) {
            throw new ConflictException("Указанное количество продуктов слишком велико!");
        }

        cartItem.setQuantity(updateCartItemDto.quantity());

        return CartItemMapper.toResponse(this.cartItemRepository.save(cartItem));
    }

    @Override
    public void delete(UserDetails userDetails, Long productId) {
        User user = this.getUserFromUserDetails(userDetails);
        Product product = this.productService.findProductById(productId);

        CartItem cartItem = this.cartItemRepository.findByUserIdAndProductId(user.getId(), product.getId()).orElseThrow(() ->
                new NotFoundException("Продукт с ID: " + productId + " не добавлен в корзину!"));

        this.cartItemRepository.delete(cartItem);
    }

    @Override
    public List<CartItemResponseDto> findAll(UserDetails userDetails) {
        User user = this.getUserFromUserDetails(userDetails);

        List<CartItem> cartItems = this.cartItemRepository.findAllByUserId(user.getId());
        return this.cartItemRepository.findAllByUserId(user.getId())
                .stream()
                .map(CartItemMapper::toResponse)
                .collect(Collectors.toList());
    }

    private User getUserFromUserDetails(UserDetails userDetails) {
        String userEmail = userDetails.getUsername();

        if (userEmail == null || userEmail.isEmpty()) {
            throw new UnauthorizedException("Пользователь не авторизирован!");
        }

        return this.userService.findUserByEmail(userEmail).orElseThrow(() -> new NotFoundException("Пользователь с email: " + userEmail + " не найден"));
    }

    private BigDecimal calculateTotalPrice(CartItem cartItems) {
        return cartItems.getProduct().getPrice().multiply(new BigDecimal(cartItems.getQuantity()));
    }
}
