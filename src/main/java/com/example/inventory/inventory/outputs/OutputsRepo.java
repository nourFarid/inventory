package com.example.inventory.inventory.outputs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputsRepo extends JpaRepository<Outputs,Long> {
}
