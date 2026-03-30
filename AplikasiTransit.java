import java.util.Scanner;
import java.util.ArrayList;
public class AplikasiTransit {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            ArrayList<kartutransit> databaseKartu = new ArrayList<>();

            databaseKartu.add(new kartutransit("Andi", "1198001", 100000, "1234"));
            databaseKartu.add(new kartutransit("Yanto", "2276002", 500000, "2345"));
            databaseKartu.add(new kartutransit("Siti", "3354003", 200000, "3456"));

            while (true) {
            System.out.println("\n--- TERMINAL TRANSPORTASI PINTAR ---");
            System.out.print("Tempelkan Kartu (Masukkan Nomor): ");
            String noInput = input.nextLine();

            kartutransit kartuAktif = null;
            for (kartutransit k : databaseKartu) {
                if (k.getNomorKartu().equals(noInput)) {
                    kartuAktif = k;
                    break;
                }
            }

            if (kartuAktif == null) {
                System.out.println("Kartu tidak dikenali oleh sistem.");
                continue;
            }

            boolean login = false;
            while (!login && !kartuAktif.isTerblokir()) {
                System.out.print("Masukkan PIN Keamanan: ");
                login = kartuAktif.autentikasi(input.nextLine());
            }

            if (login) {
                int menu = 0;
                while (menu != 3) {
                    System.out.println("\nMenu Kartu " + kartuAktif.getNamaPemilik());
                    System.out.println("1. Bayar Perjalanan\n2. Isi Saldo\n3. Selesai");
                    System.out.print("Pilih: ");
                    menu = input.nextInt();
                    input.nextLine(); 

                    switch (menu) {
                        case 1:
                            System.out.print("Masukkan Tarif Perjalanan: ");
                            double tarif = input.nextDouble();
                            input.nextLine();
                            kartuAktif.bayarPerjalanan(tarif);
                            break;
                        case 2:
                            System.out.print("Masukkan Nominal Top Up: ");
                            double topup = input.nextDouble();
                            input.nextLine();
                            kartuAktif.isiSaldo(topup);
                            break;
                        case 3:
                            System.out.println("Terima kasih, selamat sampai tujuan!");
                            break;
                        }
                    }
                }
            }
        }
    }
}