package com.st.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
