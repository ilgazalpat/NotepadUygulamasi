import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Main{

    public static void main(String[] args) {
        // Dosya adı ve yolu belirtin
        String dosyaAdi = "notepadDosyasi.txt";

        // Daha önce kaydedilmiş metni oku
        String oncekiMetin = dosyadanOku(dosyaAdi);
        System.out.println("Önceki kaydedilen metin: " + oncekiMetin);

        // Kullanıcıdan yeni metin girişi al
        Scanner scanner = new Scanner(System.in);
        System.out.print("Yeni metin giriniz: ");
        String yeniMetin = scanner.nextLine();

        // Yeni metni dosyaya kaydet
        dosyayaKaydet(dosyaAdi, yeniMetin);
        System.out.println("Yeni metin dosyaya kaydedildi.");

        // En son kaydedilen metni ekrana yazdır
        String enSonKayitliMetin = dosyadanOku(dosyaAdi);
        System.out.println("En son kaydedilen metin: " + enSonKayitliMetin);
    }

    private static void dosyayaKaydet(String dosyaAdi, String metin) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(dosyaAdi)))) {
            writer.println(metin);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Dosyaya yazma hatası: " + e.getMessage());
        }
    }

    private static String dosyadanOku(String dosyaAdi) {
        StringBuilder metinBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                metinBuilder.append(satir).append("\n");
            }
        } catch (IOException e) {
            // Dosya henüz oluşturulmamışsa bu hatayı bekleyebilirsiniz.
            // e.printStackTrace();
            // System.err.println("Dosyadan okuma hatası: " + e.getMessage());
        }
        return metinBuilder.toString();
    }
}