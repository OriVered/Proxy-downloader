package com.hac.IO;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Scanner;

/**
 * The IOManager class is a singleton utility class responsible for handling
 * file I/O operations such as reading, writing, and appending to files, as well
 * as streaming content from an HttpURLConnection to a file.
 */
public class IOManager {
    private static IOManager instance = null;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private IOManager() {}

    /**
     * @return The singleton instance of the IOManager class.
     */
    public static IOManager getInstance() {
        if (instance == null) {
            instance = new IOManager();
        }
        return instance;
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param fileName The name of the file to be read.
     * @return The content of the file as a string.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public String readFromFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append(line).append("\n");
            }
            scanner.close();
        return sb.toString();
    }

    /**
     * Writes the specified data string to a file with the specified name.
     *
     * @param fileName The name of the file to be written to.
     * @param data The data string to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeToFile(String fileName, String data) throws IOException {
            FileWriter writer = new FileWriter(fileName);
            writer.write(data);
            writer.close();
    }

    /**
     * Appends the specified data string to a file with the specified name.
     *
     * @param fileName The name of the file to be appended to.
     * @param data The data string to be appended to the file.
     * @throws IOException If an I/O error occurs while appending to the file.
     */
    public void appendToFile(String fileName, String data) throws IOException {
            FileWriter writer = new FileWriter(fileName, true); // true to append to the file
            String line = data + "\n";
            writer.write(line); // append the data string and a newline character
            writer.close();
    }

    /**
     * Streams the content of an HttpURLConnection to a file with the specified name.
     *
     * @param connection The HttpURLConnection to stream the content from.
     * @param outputFileName The name of the file to stream the content to.
     * @throws IOException If an I/O error occurs while streaming the content.
     */
    public void streamToFile(HttpURLConnection connection, String outputFileName) throws IOException {
        try (
                InputStream input = new BufferedInputStream(connection.getInputStream());
                OutputStream output = new BufferedOutputStream(new FileOutputStream(outputFileName))
        ) {
            int b;
            while ((b = input.read()) != -1) {
                output.write(b);
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
