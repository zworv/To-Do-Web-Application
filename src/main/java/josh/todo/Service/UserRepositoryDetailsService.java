package josh.todo.Service;

import josh.todo.Repository.UserRepository;
import josh.todo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    public UserRepositoryDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUserById(Integer id) {
        userRepo.deleteById(id);
    }
}
