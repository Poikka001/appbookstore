package mate.academy.intro;

import java.math.BigDecimal;

import mate.academy.intro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IntroApplication {
    private final ProductService productService;

    @Autowired
    public IntroApplication(ProductService productService) {
        this.productService = productService;
    }

    public static void main(String[] args) {
        SpringApplication.run(IntroApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            User iPhone = new User();
            iPhone.setName("iPhone 10");
            iPhone.setPrice(BigDecimal.valueOf(999));

            productService.save(iPhone);

            System.out.println(productService.findAll());
        };
    }
}
