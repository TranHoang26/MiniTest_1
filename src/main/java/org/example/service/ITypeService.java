package org.example.service;



import org.example.model.DTO.TypeDTO;
import org.example.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITypeService extends IService<Type>{
    Page<TypeDTO> findQuantityInTypeByIdType(Pageable pageable);
}