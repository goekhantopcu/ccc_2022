package de.algebros;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Common {
    public static void writeFile(List<String> lines, String fileName) {
        String path = "src/main/resources/solutions/" + fileName;
        try {
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String s : lines) {
                writer.write(s);
                writer.newLine(); // neue Zeile hinzufügen
            }
            writer.close();
            System.out.println("Die Datei wurde erfolgreich erstellt und mit Strings befüllt.");
        } catch (IOException e) {
            System.err.println("Fehler beim Erstellen der Datei: " + e.getMessage());
        }
    }

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
