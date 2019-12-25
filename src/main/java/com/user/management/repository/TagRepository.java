package com.user.management.repository;

import com.user.management.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    void deleteByName(String name);

    Optional<Tag> findByName(String name);

}

