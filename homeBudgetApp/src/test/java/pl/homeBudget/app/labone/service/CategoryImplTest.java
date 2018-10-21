package pl.homeBudget.app.labone.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import pl.homeBudget.app.labone.domain.Category;
import pl.homeBudget.app.labone.domain.TimeSource;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CategoryImplTest {

    private CategoryImpl database;

    @Mock
    TimeSource timeSource;

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
        database.createCategory(new Category(1,"Cat 1"));
        assertEquals(1, database.getAllCategories().size());
    }

    @Test
    public void checkCorrectSetCreatedDate(){
        Date created = new Date();
        when(timeSource.getCurrentTime()).thenReturn(created);

        int id = database.createCategory(new Category(1,"Cat 1"));
        Category c = database.getCategory(id);

        assertEquals(created, c.getCreated());
    }

    @Test
    public void checkCorrectGetCategory(){
        int id = database.createCategory(new Category(1,"Cat 1"));
        assertEquals("Cat 1", database.getCategory(id).getName());
        assertEquals(id, database.getCategory(id).getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void checkGetCategoryThrowsExceptionForNonExistingCategory(){
        database.getCategory(1);
    }

    @Test
    public void checkCorrectSetLastReadDate(){
        Date lastRead = new Date();
        when(timeSource.getCurrentTime()).thenReturn(lastRead);

        int id = database.createCategory(new Category(1,"Cat 1"));
        Category c = database.getCategory(id);

        assertEquals(lastRead, c.getLastRead());
    }

    @Test
    public void checkCorrectGetAll(){
        database.createCategory(new Category(1,"Cat 1"));
        database.createCategory(new Category(1,"Cat 2"));
        assertEquals(2, database.getAllCategories().size());
    }

    @Test
    public void checkCorrectUpdate(){
        int id = database.createCategory(new Category(1,"Cat 1"));
        Category createdCategory = database.getCategory(id);
        createdCategory.setName("New name");
        boolean result = database.updateCategory(createdCategory);

        assertTrue(result);
        assertEquals("New name", database.getCategory(id).getName());
    }

    @Test(expected = NoSuchElementException.class)
    public void checkUpdateThrowsExceptionForNonExistingCategory(){
        Category c = new Category(1,"Cat 1");
        database.updateCategory(c);
    }

    @Test
    public void checkCorrectSetLastModifiedDate(){
        Date lastModified = new Date();
        when(timeSource.getCurrentTime()).thenReturn(lastModified);

        int id = database.createCategory(new Category(1,"Cat 1"));
        Category createdCategory = database.getCategory(id);
        createdCategory.setName("New name");
        database.updateCategory(createdCategory);
        Category c = database.getCategory(id);

        assertEquals(lastModified, c.getLastModified());
    }

    @Test
    public void checkCorrectDelete(){
        int id = database.createCategory(new Category(1, "Cat 1"));
        Category createdCategory = database.getCategory(id);
        createdCategory.setName("New name");
        boolean result = database.deleteCategory(createdCategory);

        assertTrue(result);
        assertEquals(0, database.getAllCategories().size());
    }

    @Test
    public void checkCorrectDeleteWithMultipleCategoriesInDb(){
        int idFirst = database.createCategory(new Category(1, "Cat 1"));
        int idSecond = database.createCategory(new Category(2, "Cat 2"));
        Category createdCategory = database.getCategory(idFirst);
        createdCategory.setName("New name");
        boolean result = database.deleteCategory(createdCategory);

        assertTrue(result);
        assertEquals(1, database.getAllCategories().size());
        assertEquals("Cat 2", database.getCategory(idSecond).getName());
    }

    @Test(expected = NoSuchElementException.class)
    public void checkDeleteCategoryThrowsExceptionForNonExistingCategory() {
        database.deleteCategory(new Category(1, "Cat 1"));
    }
}