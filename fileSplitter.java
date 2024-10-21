import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class fileSplitter {

    // Metode untuk membaca dan memotong file
    public static void splitFile(String filePath, int chunkSize) {
        Queue<String> queue = new LinkedList<>(); // Queue untuk menyimpan potongan
        StringBuilder currentChunk = new StringBuilder(); // Menyimpan potongan saat ini

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int totalChars = 0; // Menghitung total karakter yang dibaca

            while ((line = reader.readLine()) != null) {
                // Tambahkan baris ke potongan saat ini
                currentChunk.append(line).append("\n");
                totalChars += line.length() + 1; // +1 untuk newline

                // Jika potongan saat ini sudah mencapai ukuran yang diinginkan
                if (totalChars >= chunkSize) {
                    queue.add(currentChunk.toString()); // Masukkan ke dalam queue
                    currentChunk.setLength(0); // Reset potongan saat ini
                    totalChars = 0; // Reset total karakter
                }
            }

            // Menyimpan potongan terakhir jika ada
            if (currentChunk.length() > 0) {
                queue.add(currentChunk.toString());
            }

        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }

        // Menampilkan hasil potongan dari queue
        System.out.println("Potongan file:");
        while (!queue.isEmpty()) {
            System.out.println("----- Potongan -----");
            System.out.println(queue.poll()); // Mengeluarkan potongan dari queue
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input dari pengguna untuk file dan ukuran potongan
        System.out.print("Masukkan path file: ");
        String filePath = scanner.nextLine();

        System.out.print("Masukkan ukuran potongan (dalam karakter): ");
        int chunkSize = scanner.nextInt();

        // Memanggil metode untuk memotong file
        splitFile(filePath, chunkSize);

        scanner.close();
    }
}

