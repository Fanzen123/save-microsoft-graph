package fr.lecko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class AbstractTest {
    @Autowired
    protected MockMvc mockMvc;
    static protected final int PORT = 8980;
    static protected final String HOST = "localhost";
    protected static final String URL = String.format("http://%s:%d", HOST, PORT);
}
