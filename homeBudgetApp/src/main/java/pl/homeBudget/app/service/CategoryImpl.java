package pl.homeBudget.app.service;

import pl.homeBudget.app.domain.Category;
import pl.homeBudget.app.domain.TimeSource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CategoryImpl implements CategoryManager {

    private ArrayList<Category> db;
    private TimeSource ts;
    private boolean saveCreated = true;
    private boolean saveLastRead = true;
    private boolean saveLastModified = true;

    CategoryImpl(TimeSource ts){
        this.db = new ArrayList<Category>();
        this.ts = ts;
    }

    public int createCategory(Category category){
        if(saveCreated) {
            category.setCreated(this.ts.getCurrentTime());
        }
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
                if(saveLastModified) {
                    category.setLastModified(ts.getCurrentTime());
                }
                this.db.set(i, category);
                return true;
            }
        }
        throw new NoSuchElementException();
    }

    public Category getCategory(int id) throws NoSuchElementException {
        for (Category c: db){
            if (c.getId() == id){
                if(saveLastRead) {
                    c.setLastRead(ts.getCurrentTime());
                }
                return c;
            }
        }
        throw new NoSuchElementException();
    }

    public List<Category> getAllCategories(){
        return db;
    }

    public void setSaveCreated(boolean saveCreated) {
        this.saveCreated = saveCreated;
    }

    public void setSaveLastRead(boolean saveLastRead) {
        this.saveLastRead = saveLastRead;
    }

    public void setSaveLastModified(boolean saveLastModified) {
        this.saveLastModified = saveLastModified;
    }
}
