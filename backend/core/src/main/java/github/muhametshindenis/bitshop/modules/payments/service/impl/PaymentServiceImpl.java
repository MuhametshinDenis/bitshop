package github.muhametshindenis.bitshop.modules.payments.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.modules.carts.dto.CartItemResponseDto;
import github.muhametshindenis.bitshop.modules.carts.dto.CartResponseDto;
import github.muhametshindenis.bitshop.modules.carts.service.CartItemService;
import github.muhametshindenis.bitshop.modules.carts.service.CartService;
import github.muhametshindenis.bitshop.modules.payments.dto.InitiatePaymentDto;
import github.muhametshindenis.bitshop.modules.payments.dto.PaymentResponseDto;
import github.muhametshindenis.bitshop.modules.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 28.09.2025 September 2025
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${stripe.secret-key}")
    private String STRIPE_SECRET_KEY;

    private final CartService cartService;
    private final CartItemService cartItemService;

    public PaymentServiceImpl(CartService cartService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @Override
    public PaymentResponseDto initiate(UserDetails userDetails, InitiatePaymentDto initiatePaymentDto) {
        Stripe.apiKey = STRIPE_SECRET_KEY;

        CartResponseDto cartResponseDto = this.cartService.findCart(userDetails);

        if (cartResponseDto.totalPrice().equals(BigDecimal.ZERO)) {
            throw new BadRequestException("Корзина пользователя пустая!");
        }

        List<CartItemResponseDto> cartItemResponseDto = this.cartItemService.findAll(userDetails);

        List<SessionCreateParams.LineItem> lineItemList = cartItemResponseDto.stream().map(cartItem -> {
            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()
                    .setName(cartItem.name())
                    .build();

            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                    .setCurrency(String.valueOf(cartResponseDto.currency()))
                    .setUnitAmount(cartItem.price().multiply(BigDecimal.valueOf(100)).longValue())
                    .setProductData(productData)
                    .build();

            return SessionCreateParams.LineItem.builder()
                    .setQuantity(Long.valueOf(cartItem.quantity()))
                    .setPriceData(priceData)
                    .build();
        }).toList();

        SessionCreateParams params = SessionCreateParams.builder().setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success")
                .setCancelUrl("http://localhost:8080/cancel")
                .addAllLineItem(lineItemList)
                .build();

        Session session = null;

        try {
            session = Session.create(params);
        } catch (StripeException e) {
            throw new BadRequestException("Ошибка при оплате: " + e.getMessage());
        }

        return new PaymentResponseDto(
                "SUCCESS",
                "Успешная генерация для оплаты",
                session.getId(),
                session.getUrl()
        );
    }
}
