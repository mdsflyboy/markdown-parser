
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
    public void thisTestNoLongerFails() {
        assertEquals(-1.0 / 12, 11.0 / 12 - 1.0, 0.1);
    }

    private ArrayList<String> getLinksTester(String fileName) throws IOException {
        Path file = Path.of(fileName);
        String content = Files.readString(file);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        return links;
    }

    @Test
    public void testFileTest() throws IOException {
        ArrayList<String> links = getLinksTester("test-file.md");
        List<String> expectedLinks = List.of(
                "https://something.com",
                "some-thing.html");
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }

    @Test
    public void breakingFileTest() throws IOException {
        ArrayList<String> links = getLinksTester("breaking-file.md");
        List<String> expectedLinks = List.of(
                "https://something.com",
                "some-thing.html");
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }

    @Test
    public void imageBugTester() throws IOException {
        ArrayList<String> links = getLinksTester("image-bug.md");
        List<String> expectedLinks = List.of(
                "https://something.com",
                "some-thing.html");
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }

    @Test
    public void spacedLinkTester() throws IOException {
        ArrayList<String> links = getLinksTester("spaced-link.md");
        List<String> expectedLinks = List.of();
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }

    @Test
    public void noLinksTester() throws IOException {
        ArrayList<String> links = getLinksTester("no-links.md");
        List<String> expectedLinks = List.of();
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }

    @Test
    public void spacedLinkTester2() throws IOException {
        ArrayList<String> links = getLinksTester("spaced-links-with-other-links.md");
        List<String> expectedLinks = List.of(
                "google.com");
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }

    @Test
    public void newFailedTest() throws IOException{
        ArrayList<String> links = getLinksTester("nestedParenthesisFailure.md");
        List<String> expectedLinks = List.of();
        assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }

    @Test
    public void group2Break() throws IOException {
        ArrayList<String> links = getLinksTester("group2-break.md");
        List<String> expectedLinks = List.of();
                assertArrayEquals(expectedLinks.toArray(), links.toArray());
    }
}
