package github.muhametshindenis.bitshop.modules.orders.service.impl;

import github.muhametshindenis.bitshop.modules.orders.dto.CreateOrderItemDto;
import github.muhametshindenis.bitshop.modules.orders.dto.OrderItemResponseDto;
import github.muhametshindenis.bitshop.modules.orders.entity.Order;
import github.muhametshindenis.bitshop.modules.orders.entity.OrderItem;
import github.muhametshindenis.bitshop.modules.orders.mapper.OrderItemMapper;
import github.muhametshindenis.bitshop.modules.orders.repository.OrderItemRepository;
import github.muhametshindenis.bitshop.modules.orders.service.OrderItemService;
import github.muhametshindenis.bitshop.modules.products.entity.Product;
import github.muhametshindenis.bitshop.modules.products.service.ProductService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
    }

    @Override
    public OrderItemResponseDto create(UserDetails userDetails, CreateOrderItemDto createOrderItemDto) {
        return null;
    }

    @Override
    public List<OrderItem> createAll(UserDetails userDetails, List<CreateOrderItemDto> createOrderItemDtos, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (var orderItemDto : createOrderItemDtos) {
            Product product = this.productService.findProductById(orderItemDto.productId());
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .name(product.getName())
                    .productId(product.getId())
                    .price(product.getPrice())
                    .quantity(orderItemDto.quantity())
                    .build();

            orderItems.add(this.orderItemRepository.save(orderItem));
        }
        return orderItems;
    }

    @Override
    public List<OrderItemResponseDto> findAll(UserDetails userDetails) {
        return List.of();
    }
}
