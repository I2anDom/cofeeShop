package application.config;

import application.entity.CoffeeUser;
import application.repository.ShopRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetailsService {
    ShopRepository shopRepository;

    public UserDetails loadUserById(Long id){
        CoffeeUser user = shopRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        return UserPrincipal.create(user);
    }
}
