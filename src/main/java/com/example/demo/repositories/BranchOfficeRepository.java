package com.example.demo.repositories;

import com.example.demo.models.BranchOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchOfficeRepository extends JpaRepository<BranchOffice, UUID> {

    @Query(value = "SELECT * FROM branchoffice WHERE name =:name", nativeQuery = true)
    Optional<BranchOffice> findByName(String name);

    @Override
    List<BranchOffice> findAll();


}
