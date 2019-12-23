package com.user.management.repository;

import com.user.management.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}

