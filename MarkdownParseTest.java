
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

    @Test
    public void snippet1Test() throws IOException {
        ArrayList<String> links = getLinksTester("snippet-1.md");
        List<String> expectedLinks = List.of(
            "`google.com",
            "google.com",
            "ucsd.edu"
        );
        System.out.println(links.toArray());
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }

    @Test
    public void snippet2Test() throws IOException {
        ArrayList<String> links = getLinksTester("snippet-2.md");
        List<String> expectedLinks = List.of(
            "a.com",
            "a.com(())",
            "example.com"
        );
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }

    @Test
    public void snippet3Test() throws IOException {
        ArrayList<String> links = getLinksTester("snippet-3.md");
        List<String> expectedLinks = List.of(
            "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule"
        );
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }

    private ArrayList<String> getLinksTester(String fileName) throws IOException {
        Path file = Path.of(fileName);
        String content = Files.readString(file);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        return links;
    }
}
