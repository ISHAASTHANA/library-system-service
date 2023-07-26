package com.target.ready.library.system.service.LibrarySystemService.Service;

import com.target.ready.library.system.service.LibrarySystemService.Entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;



import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CategoryServiceTest.class})
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    BookCategoryRepository bookCategoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    public void findAllCategoriesTest(){
        List<Category> myCategories = new ArrayList<Category>();

        myCategories.add(new Category(1,"Horror"));
        myCategories.add(new Category(2, "Adventure"));

        when(categoryRepository.findAll()).thenReturn(myCategories);
        assertEquals(2,categoryService.getAllCategories().size());
    }

    @Test
    public void addCategoryTest(){
        Category category=new Category();
        category.setCategoryName("Horror");
        when(categoryRepository.save(category)).thenAnswer(invocation -> {
            Category category1 = invocation.getArgument(0);
            category1.setCategoryId(1);
            return category1;
        });
        assertNotNull(categoryService.addCategory(category));
        assertEquals(1,categoryService.addCategory(category).getCategoryId());
        assertEquals("Horror", categoryService.addCategory(category).getCategoryName());

    }

    @Test
    public void findByCategoryNameTest(){
        Category category=new Category();
        category.setCategoryName("Adventure");
        when(categoryRepository.findBycategoryName(category.getCategoryName())).thenReturn(category);
        assertNotNull(categoryService.findByCategoryName(category.getCategoryName()));
        assertEquals("Adventure",categoryService.findByCategoryName(category.getCategoryName()).getCategoryName());
    }

    @Test
    public void addBookCategoryTest(){
        BookCategory bookCategory=new BookCategory();
        bookCategory.setBookId(1);
        bookCategory.setCategoryName("Horror");
        when(bookCategoryRepository.save(bookCategory)).thenAnswer(invocation -> {
            BookCategory bookCategory1=invocation.getArgument(0);
            bookCategory1.setId(2);
            return bookCategory1;
        });
        assertNotNull(categoryService.addBookCategory(bookCategory));
        assertEquals(1,categoryService.addBookCategory(bookCategory).getBookId());
        assertEquals("Horror",categoryService.addBookCategory(bookCategory).getCategoryName());
        assertEquals(2,categoryService.addBookCategory(bookCategory).getId());
    }





}
