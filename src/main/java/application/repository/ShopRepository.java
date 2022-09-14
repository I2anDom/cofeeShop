package application.repository;

import application.entity.CoffeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopRepository extends JpaRepository<CoffeeUser, Long> {
    CoffeeUser findCoffeeUserByEmailAndPassword(String email, String password);
}
