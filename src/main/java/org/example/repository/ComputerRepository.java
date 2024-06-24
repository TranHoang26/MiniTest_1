package org.example.repository;


import org.example.model.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer,Long> {
    boolean existsByCode(String code);
    Page<Computer> findAll(Pageable pageable);
    Page<Computer> findByNameContaining(String name, Pageable pageable);
}