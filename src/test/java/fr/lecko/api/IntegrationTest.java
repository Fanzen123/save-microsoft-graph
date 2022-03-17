package fr.lecko.api;

import fr.lecko.AbstractTest;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest extends AbstractTest {

    private Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

    private static final String URL = String.format("http://%s:%d", HOST, PORT);

    private static final String URL_ENTRY = "/save";
    private static final String URL_GET_ENTRIES = URL + "/save";

    @Test
    public void shouldReturnHttpOkWhenGetEmails() throws Exception {
        logger.info("GIVEN : an user want to get emails using the existing services available.");

        logger.info(String.format("WHEN : an user request service by calling the service on endpoint %s", URL_GET_ENTRIES));
        final ResultActions result = this.mockMvc.perform(get(URL_ENTRY).contentType(MediaType.APPLICATION_JSON));

        logger.info("THEN : an http code 200 is returned");
        Assert.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    public void shouldReturnEmailsWhenGetEmails() throws Exception {
        logger.info("GIVEN : an user want to get emails using the existing services available.");

        String expected = "[{\"name\":\"Example\",\"from\":\"Dupont\",\"content\":\"Bonjour, comment allez vous ? Cordialement\"," +
                "\"date\":\"2022-03-17T19:18:00.064+01:00\"}]";

        logger.info(String.format("WHEN : an user request service by calling the service on endpoint %s", URL_GET_ENTRIES));
        final ResultActions result = this.mockMvc.perform(get(URL_ENTRY).contentType(MediaType.APPLICATION_JSON));

        logger.info("THEN : emails returned");

        Assert.assertEquals(expected, result.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void shouldReturnHttpCreatedWhenSaveEmails() throws Exception {
        logger.info("GIVEN : an user want to save emails using the existing services available.");
        final String request = "{\"clientId\":\"dc516f84-9366-4edc-9c0a-8e038c26bd4b\"" +
                ",\"clientSecret\":\"SvU7Q~FDgVcXIhDY6yDvplb6mfAQWX7yyuuBy\"," +
                "\"tenant\":\"d5d99b8a-61b5-40bd-975a-923eca104608\"}";

        logger.info(String.format("WHEN : an user request service by calling the service on endpoint %s with content = %s", URL_GET_ENTRIES, request));
        final ResultActions result = this.mockMvc.perform(post(URL_ENTRY).content(request).contentType(MediaType.APPLICATION_JSON));

        logger.info("THEN : an http code 201 is returned");
        Assert.assertEquals(HttpStatus.CREATED.value(), result.andReturn().getResponse().getStatus());

    }

    @Test
    public void shouldSaveEmails() throws Exception {
        logger.info("GIVEN : an user want to save emails using the existing services available.");
        final String request = "{\"clientId\":\"dc516f84-9366-4edc-9c0a-8e038c26bd4b\"" +
                ",\"clientSecret\":\"SvU7Q~FDgVcXIhDY6yDvplb6mfAQWX7yyuuBy\"," +
                "\"tenant\":\"d5d99b8a-61b5-40bd-975a-923eca104608\"}";
        String expected = "[{\"name\":\"Example\",\"from\":\"Dupont\",\"content\":\"Bonjour, comment allez vous ? Cordialement\"," +
                "\"date\":\"2022-03-17T19:18:00.064+01:00\"}]";

        logger.info(String.format("WHEN : an user request service by calling the service on endpoint %s with content = %s", URL_GET_ENTRIES, request));
        this.mockMvc.perform(post(URL_ENTRY).content(request).contentType(MediaType.APPLICATION_JSON));

        ResultActions result = this.mockMvc.perform(get(URL_ENTRY).contentType(MediaType.APPLICATION_JSON));

        logger.info("THEN : emails returned");

        Assert.assertEquals(expected, result.andReturn().getResponse().getContentAsString());
    }
}
