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
    public void checkCreate(){
        assertNotNull(database);
    }
}