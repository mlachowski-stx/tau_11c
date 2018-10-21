package pl.homeBudget.app.labone.service;

import pl.homeBudget.app.labone.domain.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CategoryImpl implements CategoryManager {

    private ArrayList<Category> db;

    CategoryImpl(){
        this.db = new ArrayList<Category>();
    }

    public int createCategory(Category category){
        this.db.add(category);
        return category.getId();
    }

    public boolean deleteCategory(Category category){
        for (int i = 0; i < db.size(); i ++) {
            if (db.get(i).getId() == category.getId()){
                this.db.remove(i);
                return true;
            }
        }
        throw new NoSuchElementException();
    }

    public boolean updateCategory(Category category){
        for (int i = 0; i < db.size(); i ++) {
            if (db.get(i).getId() == category.getId()){
                this.db.set(i, category);
                return true;
            }
        }
        throw new NoSuchElementException();
    }

    public Category getCategory(int id) throws NoSuchElementException {
        for (Category c: db){
            if (c.getId() == id){
                return c;
            }
        }
        throw new NoSuchElementException();
    }

    public List<Category> getAllCategories(){
        return db;
    }
}
