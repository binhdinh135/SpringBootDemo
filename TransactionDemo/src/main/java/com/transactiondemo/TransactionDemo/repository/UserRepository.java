package com.transactiondemo.TransactionDemo.repository;

import com.transactiondemo.TransactionDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
