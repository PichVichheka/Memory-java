package com.MyMemory.MyMemory.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.MyMemory.MyMemory.Enitity.category;
import com.MyMemory.MyMemory.Repository.CategoryRepository;


@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedCategories(CategoryRepository categoryRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {

                categoryRepository.save(new category(null, "Technology"));
                categoryRepository.save(new category(null, "Education"));
                categoryRepository.save(new category(null, "Health"));
                categoryRepository.save(new category(null, "Sports"));

                System.out.println("✅ Categories seeded");
            }
        };
    }
}