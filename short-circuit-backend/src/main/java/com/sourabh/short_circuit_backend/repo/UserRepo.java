package com.sourabh.short_circuit_backend.repo;

import com.sourabh.short_circuit_backend.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

}
