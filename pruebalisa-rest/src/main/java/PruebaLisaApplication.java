import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.example")
public class PruebaLisaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PruebaLisaApplication.class, args);
    }
}