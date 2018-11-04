package pl.homeBudget.app.service;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import pl.homeBudget.app.domain.Category;
import pl.homeBudget.app.domain.TimeSource;
import pl.homeBudget.app.service.CategoryImpl;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    private CategoryImpl database;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        this.database = new CategoryImpl(new TimeSource());
    }

    @Given("^I have category named (.*) with id (\\d+) in my database$")
    public void I_have_category_in_database(String name, int id) throws Throwable {
        database.createCategory(new Category(id, name));
    }

    @Given("^I have (\\d+) example categories in my database$")
    public void I_have_example_categories_in_database(int count) throws Throwable {
        int size = database.getAllCategories().size();
        for (int i = size; i < size + count; i ++) {
            database.createCategory(new Category(i, "Test name"));
        }
    }

    @When("^I delete category with id (\\d+)$")
    public void I_delete_category_with_id(int id) throws Throwable {
        database.deleteCategory(database.getCategory(id));
    }

    @Then("^There should left (\\d+) categories$")
    public void there_should_left_num_categories(int count) throws Throwable {
        assertEquals(count, database.getAllCategories().size());
    }

    @Then("^There shouldn't be (\\d+) in database$")
    public void there_should_not_be_category_in_db(int id) throws Throwable {
        thrown.expect(NoSuchElementException.class);
        database.getCategory(id);
    }
}
