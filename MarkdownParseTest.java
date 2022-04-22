
// Imports tools from junit
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

// Class definition
public class MarkdownParseTest {
    // Defines following function as a Test
    @Test
    // Method definition
    public void addition() {
        // Checks that 1+1 = 2
        assertEquals(2, 1 + 1);
    }

    @Test
    public void getLinksTest() throws IOException {
        Path file = Path.of("test-file.md");
        String content = Files.readString(file);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        List<String> expectedLinks = List.of(
                "https://something.com",
                "some-thing.html");
        assertEquals(expectedLinks.toArray(), links.toArray());
    }
}
