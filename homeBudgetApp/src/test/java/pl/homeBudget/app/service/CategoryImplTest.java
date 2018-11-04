package pl.homeBudget.app.service;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.homeBudget.app.domain.Category;
import pl.homeBudget.app.domain.TimeSource;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryImplTest {

    private CategoryImpl database;
    private int testTimestamp = 1540118425;

    @Mock TimeSource ts;

    @Before
    public void before(){
        this.database = new CategoryImpl(ts);
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
        when(ts.getCurrentTime()).thenReturn(created);

        int id = database.createCategory(new Category(1,"Cat 1"));
        Category c = database.getCategory(id);

        assertEquals(created, c.getCreated());
    }

    @Test
    public void checkNotSetCreatedDate(){
        database.setSaveCreated(false);
        Date oldCreated = new Date(testTimestamp);
        Date created = new Date();
        when(ts.getCurrentTime()).thenReturn(created);

        Category cc = new Category(1,"Cat 1");
        cc.setCreated(oldCreated);
        int id = database.createCategory(cc);
        Category c = database.getCategory(id);

        assertEquals(oldCreated, c.getCreated());
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
        when(ts.getCurrentTime()).thenReturn(lastRead);

        int id = database.createCategory(new Category(1,"Cat 1"));
        Category c = database.getCategory(id);

        assertEquals(lastRead, c.getLastRead());
    }

    @Test
    public void checkNotSetLastReadDate(){
        database.setSaveLastRead(false);
        Date oldLastRead = new Date(testTimestamp);
        Date lastRead = new Date();
        when(ts.getCurrentTime()).thenReturn(lastRead);

        Category cc = new Category(1,"Cat 1");
        cc.setLastRead(oldLastRead);
        int id = database.createCategory(cc);
        Category c = database.getCategory(id);

        assertEquals(oldLastRead, c.getLastRead());
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
        when(ts.getCurrentTime()).thenReturn(lastModified);

        Category c = createdAndModifyCategory(null);

        assertEquals(lastModified, c.getLastModified());
    }

    @Test
    public void checkNotSetLastModifiedDate(){
        database.setSaveLastModified(false);
        Date oldLastModified = new Date(testTimestamp);
        Date lastModified = new Date();
        when(ts.getCurrentTime()).thenReturn(lastModified);
        Category c = createdAndModifyCategory(oldLastModified);

        assertEquals(oldLastModified, c.getLastModified());
    }

    private Category createdAndModifyCategory(Date date){
        Category cc = new Category(1,"Cat 1");
        if(date != null){
            cc.setLastModified(date);
        }
        int id = database.createCategory(cc);
        Category createdCategory = database.getCategory(id);
        createdCategory.setName("New name");
        database.updateCategory(createdCategory);
        return database.getCategory(id);
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