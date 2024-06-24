package org.example.repository;


import org.example.model.DTO.TypeDTO;
import org.example.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    @Query(value = "select type.name as name , count(computer.type_id) as quantity from type left join computer on type.id = computer.type_id group by type.id",countQuery = "SELECT count(*) FROM type",nativeQuery = true)
    Page<TypeDTO> findQuantityInTypeByIdType(Pageable pageable);
}