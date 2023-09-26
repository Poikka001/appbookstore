package mate.academy.intro.service;

import java.util.List;

import mate.academy.intro.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public User save(User user) {
        return productRepository.save(user);
    }
    @Override
    public List<User> findAll() {
        return productRepository.findAll();
    }
}
