package com.mycommerce.wellcommerce.repository;

import com.mycommerce.wellcommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
