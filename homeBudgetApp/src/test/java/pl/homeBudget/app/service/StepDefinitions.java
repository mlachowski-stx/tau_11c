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

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StepDefinitions {

    private CategoryImpl database;
    private List<Category> searchResult;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        this.database = new CategoryImpl(new TimeSource());
    }

    @Given("The following categories:")
    public void the_following_categories(List<Integer> ids) throws Throwable {
        for (Integer id : ids) {
            database.createCategory(new Category(id, "Example name"));
        }
    }

    @Given("I have the following categories names:")
    public void the_following_categories_names(List<String> names) throws Throwable {
        for (int i = 0; i < names.size(); i ++) {
            database.createCategory(new Category(i+1, names.get(i)));
        }
    }

    @When("I delete following categories:")
    public void I_delete_following_categories(List<Integer> ids) throws Throwable {
        database.deleteCategories(ids);
    }

    @When("^I search pattern (.*)$")
    public void I_search_pattern(String pattern) throws Throwable {
        this.searchResult = database.search(pattern);
    }

    @Then("^I should get (\\d+) record")
    public void I_should_get_num_records(int count) throws Throwable {
        assertEquals(count, this.searchResult.size());
    }

    @Then("^There should left (\\d+) categories$")
    public void there_should_left_num_categories(int count) throws Throwable {
        assertEquals(count, database.getAllCategories().size());
    }

    @Then("There shouldn't be following categories in database:")
    public void there_should_not_be_following_categories_in_db(List<Integer> ids) throws Throwable {

        for (Integer id : ids) {
            try {
                database.getCategory(id);
            } catch (NoSuchElementException e) {

            }
        }
    }

    @Then("There should be following categories in database:")
    public void there_should_be_following_categories_in_db(List<Integer> ids) throws Throwable {

        for (Integer id : ids) {
            database.getCategory(id);
        }
    }
}
