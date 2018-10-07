package pl.homeBudget.app.labone.service;

import pl.homeBudget.app.labone.domain.Category;
import pl.homeBudget.app.labone.service.CategoryManager;

import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements CategoryManager {

    public long createCategory(Category category){
        return 0;
    }

    public boolean deleteCategory(Category category){
        return false;
    }

    public boolean updateCategory(Category category){
        return false;
    }

    public Category getCategory(long id){
        return new Category("");
    }

    public List<Category> getAllCategories(){
        return new ArrayList<Category>();
    }
}
