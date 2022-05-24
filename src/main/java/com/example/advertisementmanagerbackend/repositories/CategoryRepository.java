package com.example.advertisementmanagerbackend.repositories;

import com.example.advertisementmanagerbackend.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}