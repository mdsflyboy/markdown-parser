//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while (currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            // If indexOf can't find an opening bracket
            if (openBracket == -1) {
                // Then we are done!
                // Let's end this loop
                break;
            }

            int newLineAfterOpenBracket = markdown.indexOf("\n", openBracket);

            int closeBracket = markdown.indexOf("]", openBracket);

            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);

            // if there is a new line between the first bracket,
            // and the last parethesis
            // This is not a link
            if(newLineAfterOpenBracket < closeParen 
                    && newLineAfterOpenBracket > openBracket){
                currentIndex = closeParen + 1;
                continue;
            }

            // Let's check if this is an image!
            int closestImage = markdown.indexOf("Image", openBracket);

            // If this is not an image
            if (closestImage > closeBracket || closestImage < openBracket) {
                String link = markdown.substring(openParen + 1, closeParen);
                link = link.trim();
                toReturn.add(link);
                // if there is empty link, ignore
                if (link.isEmpty()) {
                    toReturn.remove(currentIndex);
                }
            }
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
        System.out.println(links);
        System.out.println("change");
    }
}
