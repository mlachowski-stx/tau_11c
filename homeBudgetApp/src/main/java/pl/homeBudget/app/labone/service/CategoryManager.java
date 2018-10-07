package pl.homeBudget.app.labone.service;

import pl.homeBudget.app.labone.domain.Category;

import java.util.List;

interface CategoryManager {

    public int createCategory(Category category);
    public boolean deleteCategory(Category category);
    public boolean updateCategory(Category category);
    public Category getCategory(int id);
    public List<Category> getAllCategories();

}
