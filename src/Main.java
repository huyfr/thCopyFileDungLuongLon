import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {

    private static void copyFileUsingJava7Files(File source, File dest) {
        try {
            Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void copyFileUsingStream(File source, File dest) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        byte[] buffer;
        int length;

        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(dest);
            buffer = new byte[1024];

            while ((length = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                fileOutputStream.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter source life: ");
        String sourcePath = sc.nextLine();
        System.out.println("Enter destination life: ");
        String destPath = sc.nextLine();

        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        long startTime = System.nanoTime();
        copyFileUsingStream(sourceFile, destFile);
        long endTime = System.nanoTime();
        System.out.println("Time: "+(endTime-startTime));
        System.out.println();
        startTime = System.nanoTime();
        copyFileUsingJava7Files(sourceFile, destFile);
        endTime = System.nanoTime();
        System.out.println("Time: "+(endTime-startTime));
    }
}
