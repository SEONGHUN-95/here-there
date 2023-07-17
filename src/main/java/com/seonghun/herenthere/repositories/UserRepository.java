package com.seonghun.herenthere.repositories;

import com.seonghun.herenthere.models.User;
import com.seonghun.herenthere.models.UserId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UserId> {
    boolean existsByEmail(String email);
}
