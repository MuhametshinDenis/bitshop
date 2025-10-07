package github.muhametshindenis.bitshop.modules.orders.service.impl;

import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.common.exception.UnauthorizedException;
import github.muhametshindenis.bitshop.modules.orders.dto.CreateOrderDto;
import github.muhametshindenis.bitshop.modules.orders.dto.OrderItemResponseDto;
import github.muhametshindenis.bitshop.modules.orders.dto.OrderResponseDto;
import github.muhametshindenis.bitshop.modules.orders.dto.UpdateOrderDto;
import github.muhametshindenis.bitshop.modules.orders.entity.Order;
import github.muhametshindenis.bitshop.modules.orders.entity.OrderItem;
import github.muhametshindenis.bitshop.modules.orders.entity.Status;
import github.muhametshindenis.bitshop.modules.orders.mapper.OrderMapper;
import github.muhametshindenis.bitshop.modules.orders.repository.OrderRepository;
import github.muhametshindenis.bitshop.modules.orders.service.OrderItemService;
import github.muhametshindenis.bitshop.modules.orders.service.OrderService;
import github.muhametshindenis.bitshop.modules.users.dto.UserDeliveryAddressResponseDto;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.service.UserDeliveryAddressService;
import github.muhametshindenis.bitshop.modules.users.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final UserDeliveryAddressService userDeliveryAddressService;

    public OrderServiceImpl(UserService userService, OrderRepository orderRepository, OrderItemService orderItemService, UserDeliveryAddressService userDeliveryAddressService) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.userDeliveryAddressService = userDeliveryAddressService;
    }

    @Override
    public OrderResponseDto create(UserDetails userDetails, CreateOrderDto createOrderDto) {
        User user = this.getUserFromUserDetails(userDetails);
        UserDeliveryAddressResponseDto userDeliveryAddressResponseDto = this.userDeliveryAddressService.findById(userDetails, createOrderDto.deliveryAddressId());
        Order order = Order.builder()
                .user(user)
                .deliveryAddress(userDeliveryAddressResponseDto.deliveryAddress())
                .status(Status.PENDING)
                .build();

        order = this.orderRepository.save(order);

        List<OrderItem> orderItems = this.orderItemService.createAll(userDetails, createOrderDto.orderItems(), order);

        order.setOrderItems(orderItems);

        this.orderRepository.save(order);

        return OrderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponseDto> findAll(UserDetails userDetails) {
        User user = this.getUserFromUserDetails(userDetails);
        List<Order> orders = this.orderRepository.findAllByUserId(user.getId());

        return orders.stream().map(OrderMapper::toResponse).toList();
    }

    @Override
    public OrderResponseDto findById(UserDetails userDetails, Long orderId) {
        User user = this.getUserFromUserDetails(userDetails);
        Optional<Order> order = this.orderRepository.findById(orderId);

        if (order.isPresent()) {
            if (Objects.equals(order.get().getUser().getId(), user.getId())) {
                return OrderMapper.toResponse(order.get());
            }
        }

        throw new NotFoundException("Заказ с ID: " + orderId + " не найден!");
    }

    @Override
    public OrderResponseDto update(UserDetails userDetails, Long orderId, UpdateOrderDto updateOrderDto) {
        return null;
    }

    private User getUserFromUserDetails(UserDetails userDetails) {
        String userEmail = userDetails.getUsername();

        if (userEmail == null || userEmail.isEmpty()) {
            throw new UnauthorizedException("Пользователь не авторизирован!");
        }

        return this.userService.findUserByEmail(userEmail).orElseThrow(() -> new NotFoundException("Пользователь с email: " + userEmail + " не найден"));
    }
}
