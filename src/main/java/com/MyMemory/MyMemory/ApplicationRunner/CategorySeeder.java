package com.MyMemory.MyMemory.ApplicationRunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.MyMemory.MyMemory.Enitity.category;
import com.MyMemory.MyMemory.Repository.CategoryRepository;

import java.util.List;

@Component
public class CategorySeeder implements ApplicationRunner {

    private final CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @SuppressWarnings("null")
	@Override
    public void run(ApplicationArguments args) {
        if (categoryRepository.count() == 0) {

            List<category> categories = List.of(
                new category(null, "Technology"),
                new category(null, "Education"),
                new category(null, "Health"),
                new category(null, "Sports")
            );

            categoryRepository.saveAll(categories);
            System.out.println("✅ Categories seeded");
        }
    }
}
