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
        this.database = new CategoryImpl();
    }

    @Test
    public void checkCorrectInitializeDatabase(){
        assertNotNull(database);
    }

    @Test
    public void checkCorrectCreateCategory(){
        database.createCategory(new Category("Cat 1"));
        assertEquals(1, database.getAllCategories().size());
    }

    @Test
    public void checkCorrectGetCategory(){
        int id = database.createCategory(new Category("Cat 1"));
        assertEquals("Cat 1", database.getCategory(id).getName());
        assertEquals(id, database.getCategory(id).getId());
    }

    @Test
    public void checkCorrectGetAll(){
        database.createCategory(new Category("Cat 1"));
        database.createCategory(new Category("Cat 2"));
        assertEquals(2, database.getAllCategories().size());
    }

    @Test
    public void checkCorrectUpdate(){
        int id = database.createCategory(new Category("Cat 1"));
        Category createdCategory = database.getCategory(id);
        createdCategory.setName("New name");
        boolean result = database.updateCategory(createdCategory);

        assertTrue(result);
        assertEquals("New name", database.getCategory(id).getName());
    }

}