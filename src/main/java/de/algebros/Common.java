package de.algebros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Common {
    public static List<String> readFile(String name) {
        try {
            final InputStream in = Common.class.getClassLoader().getResourceAsStream(name);
            if (in == null) {
                System.err.println("Could not read level file");
                return Collections.emptyList();
            }
            final InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            final BufferedReader bufferedReader = new BufferedReader(reader);
            final List<String> lines = new ArrayList<>();
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
