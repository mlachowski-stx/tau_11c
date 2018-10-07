package pl.homeBudget.app.labone.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.homeBudget.app.labone.domain.Category;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CategoryImplTest {

    private CategoryImpl database;

    @Before
    public void before(){
        CategoryImpl database = new CategoryImpl();
    }

    @Test
    public void checkCorrectDatabaseInitialization(){
        assertNotNull(database);
    }

    @Test
    public void checkCorrectCategoryCreation(){
        database.createCategory(new Category("Cat 1"));
        assertEquals(1, database.getAllCategories().size());
    }

    @Test
    public void checkCorrectCategoryGet(){
        long cat1 = database.createCategory(new Category("Cat 1"));
        long cat2 = database.createCategory(new Category("Cat 2"));
        assertEquals("Cat 2", database.getCategory(cat2).getName());
    }
}