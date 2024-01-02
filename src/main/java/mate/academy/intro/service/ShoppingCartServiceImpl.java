package mate.academy.intro.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.intro.dto.shoppingcart.ShoppingCartDto;
import mate.academy.intro.exception.EntityNotFoundException;
import mate.academy.intro.mapper.CartItemMapper;
import mate.academy.intro.mapper.ShoppingCartMapper;
import mate.academy.intro.model.Book;
import mate.academy.intro.model.CartItem;
import mate.academy.intro.model.ShoppingCart;
import mate.academy.intro.model.User;
import mate.academy.intro.repository.book.BookRepository;
import mate.academy.intro.repository.shoppingcart.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void addItem(CreateCartItemRequestDto createCartItemRequestDto) {
        ShoppingCart shoppingCart = getShoppingCartForUser();
        Book book = bookRepository.findById(createCartItemRequestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find book with this id:"
                        + createCartItemRequestDto.getBookId()));
        CartItem cartItem = cartItemMapper.toCartItem(createCartItemRequestDto);
        cartItem.setBook(book);
        cartItem.setShoppingCart(shoppingCart);
        shoppingCart.getCartItems().add(cartItem);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartDto getShoppingCart() {
        ShoppingCart shoppingCart = getShoppingCartForUser();
        return shoppingCartMapper.toDoShoppingCart(shoppingCart);
    }

    private ShoppingCart getShoppingCartForUser() {
        User user = userService.getUserFromContext();
        if (shoppingCartRepository.existsShoppingCartByUser(user)) {
            return shoppingCartRepository.findShoppingCartByUser(user)
                    .orElseThrow(() -> new EntityNotFoundException("Can't find the shopping cart"));
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }
}
