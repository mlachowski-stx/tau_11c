package pl.homeBudget.app.labone.service;

import pl.homeBudget.app.labone.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements CategoryManager {

    private ArrayList<Category> db;

    CategoryImpl(){
        this.db = new ArrayList<Category>();
    }

    public int createCategory(Category category){
        category.setId(this.db.size());
        this.db.add(category);
        return category.getId();
    }

    public boolean deleteCategory(Category category){
        return false;
    }

    public boolean updateCategory(Category category){
        return false;
    }

    public Category getCategory(int id){
        return db.get(id);
    }

    public List<Category> getAllCategories(){
        return db;
    }
}
