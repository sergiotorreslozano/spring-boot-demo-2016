package com.st.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st.domain.Ticks;

public interface TicksRepository extends JpaRepository<Ticks, Long> {

}
