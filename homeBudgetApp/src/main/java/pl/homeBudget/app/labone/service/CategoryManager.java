package pl.homeBudget.app.labone.service;

import pl.homeBudget.app.labone.domain.Category;

import java.util.List;
import java.util.NoSuchElementException;

interface CategoryManager {

    public int createCategory(Category category);
    public boolean deleteCategory(Category category) throws NoSuchElementException;
    public boolean updateCategory(Category category) throws NoSuchElementException;
    public Category getCategory(int id) throws NoSuchElementException;
    public List<Category> getAllCategories();

}
