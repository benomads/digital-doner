package doners.data;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
     UserDetails loadUserByName(String username) throws UsernameNotFoundException;
}
