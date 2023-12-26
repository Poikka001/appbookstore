package mate.academy.intro.service;

import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.shoppingcart.ShoppingCartDto;
import mate.academy.intro.mapper.ShoppingCartMapper;
import mate.academy.intro.repository.shoppingcart.ShoppingCartRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    //----------------/
    @Override
    public ShoppingCartDto getUserCart(Long userId) {
        return null;
    }
    //----------------/

    @Override
    public List<ShoppingCartDto> findAll(Pageable pageable) {
        return shoppingCartRepository.findAll(pageable).stream()
                .map(shoppingCartMapper::toDoShoppingCart)
                .collect(Collectors.toList());
    }
}