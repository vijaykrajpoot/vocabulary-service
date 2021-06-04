package com.example.vocabulary.service.persistence.repositories;

import com.example.vocabulary.service.persistence.entities.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Archetect
 */
@Repository
public interface WorldRepository extends JpaRepository<WordEntity, Long> {

}