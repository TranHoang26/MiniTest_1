package org.example.service;


import org.example.model.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IComputerService extends IService<Computer>{
    Page<Computer> findByNameContainingPageAndSort(String name , Pageable pageable);
    Page<Computer> findAllPageAndSort(Pageable pageable);
}
