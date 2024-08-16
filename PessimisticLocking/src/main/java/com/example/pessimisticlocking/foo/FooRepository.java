package com.example.pessimisticlocking.foo;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FooRepository extends JpaRepository<Foo, Integer> {
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Foo a WHERE a.id = :id")
    Optional<Foo> findAndLockById(Integer id);

    Optional<Foo> findById(Integer id);

}
