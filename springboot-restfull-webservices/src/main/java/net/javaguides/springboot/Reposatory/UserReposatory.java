package net.javaguides.springboot.Reposatory;

import net.javaguides.springboot.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserReposatory extends JpaRepository<User,Long> {
}
