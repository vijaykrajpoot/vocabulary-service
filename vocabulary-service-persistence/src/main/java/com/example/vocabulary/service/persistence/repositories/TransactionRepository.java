package com.example.vocabulary.service.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Archetect
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

}