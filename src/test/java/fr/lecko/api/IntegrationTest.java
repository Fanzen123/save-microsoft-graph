package fr.lecko.api;

import fr.lecko.AbstractTest;
import junit.framework.Assert;
import org.junit.Before;
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

/**
 * TODO add mock on endpoint (bad dependency with content returned from graph api)
 * TODO add tests on content returned
 * TODO add unit test on other class
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest extends AbstractTest {

    private Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

    private static final String URL_GET_MAIL = URL + "/mails";
    private static final String URL_SAVE_MAILS = URL + "/save";
    private static final String URL_INIT_CONNECTION = URL + "/init";

    private final String init_content =
            "{\"clientId\":\"dc516f84-9366-4edc-9c0a-8e038c26bd4b\"" +
            ",\"clientSecret\":\"SvU7Q~FDgVcXIhDY6yDvplb6mfAQWX7yyuuBy\"," +
            "\"tenant\":\"d5d99b8a-61b5-40bd-975a-923eca104608\"}";

    @Before
    public void before() throws Exception {
        final ResultActions result = this.mockMvc.perform(post(URL_INIT_CONNECTION)
                .content(init_content).contentType(MediaType.APPLICATION_JSON));
        Assert.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    public void shouldReturnHttpOkWhenGetMails() throws Exception {
        logger.info("GIVEN : an user want to get mails using the existing services available.");

        logger.info(String.format("WHEN : an user request on endpoint %s", URL_GET_MAIL));
        final ResultActions result = this.mockMvc.perform(get(URL_GET_MAIL).contentType(MediaType.APPLICATION_JSON));

        logger.info(String.format("THEN : an http code %d is returned.", HttpStatus.OK.value()));
        Assert.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    public void shouldReturnHttpCreatedWhenSaveMails() throws Exception {
        logger.info("GIVEN : an user want to save mails using the existing services available.");
        final String mail = "BrianJ@M365x762283.OnMicrosoft.com";
        final String userMailParam = "userMail";

        logger.info(String.format("WHEN : an user request on endpoint %s", URL_SAVE_MAILS));
        final ResultActions result =
                this.mockMvc.perform(get(URL_SAVE_MAILS)
                        .queryParam(userMailParam, mail)
                        .contentType(MediaType.APPLICATION_JSON));

        logger.info(String.format("THEN : an http code %d is returned.", HttpStatus.CREATED.value()));
        Assert.assertEquals(HttpStatus.CREATED.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    public void shouldSaveMails() throws Exception {
        logger.info("GIVEN : an user want to save mails using the existing services available.");
        final String mail = "BrianJ@M365x762283.OnMicrosoft.com";
        final String userMailParam = "userMail";

        logger.info(String.format("WHEN : an user request on endpoint %s", URL_SAVE_MAILS));
        this.mockMvc.perform(get(URL_SAVE_MAILS)
                        .queryParam(userMailParam, mail)
                        .contentType(MediaType.APPLICATION_JSON));
        ResultActions result = this.mockMvc.perform(get(URL_GET_MAIL).contentType(MediaType.APPLICATION_JSON));

        logger.info("THEN : mails must be saved");
        Assert.assertEquals("", result.andReturn().getResponse().getContentAsString());
    }
}
