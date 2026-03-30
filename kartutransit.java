public class kartutransit {
    private String namaPemilik;
    private String nomorKartu;
    private String pin;
    private double saldo;
    private int inputSalah = 0;
    private boolean terblokir = false;

    private static final String KODE_PELAJAR = "11";
    private static final String KODE_UMUM = "22";
    private static final String KODE_LANSIA = "33";

    public kartutransit(String nama, String nomor, double saldoAwal, String pin) {
        this.namaPemilik = nama;
        this.nomorKartu = nomor;
        this.saldo = saldoAwal;
        this.pin = pin;
    }

    public boolean autentikasi(String cekPin) {
        if (terblokir) {
            System.out.println("Kartu atas nama " + namaPemilik + " sudah tidak aktif (Blokir).");
            return false;
        }

        if (this.pin.equals(cekPin)) {
            inputSalah = 0;
            return true;
        } else {
            inputSalah++;
            System.out.println("PIN Keliru! Kesempatan tersisa: " + (3 - inputSalah));
            if (inputSalah >= 3) {
                terblokir = true;
                System.out.println("Keamanan aktif: Kartu Anda diblokir otomatis.");
            }
            return false;
        }
    }

    public void isiSaldo(double jumlah) {
        this.saldo += jumlah;
        System.out.printf("Isi saldo sukses. Total saldo sekarang: Rp%,.0f\n", this.saldo);
    }

    public void bayarPerjalanan(double tarifDasar) {
        if (tarifDasar > this.saldo) {
            System.out.println("Gagal: Saldo tidak cukup untuk perjalanan ini.");
            return;
        }

        String tipe = nomorKartu.substring(0, 2);
        double potonganHarga = 0;

        if (tarifDasar > 50000) { 
            if (tipe.equals(KODE_PELAJAR)) potonganHarga = 0.20 * tarifDasar; 
            else if (tipe.equals(KODE_UMUM)) potonganHarga = 0.10 * tarifDasar; 
            else if (tipe.equals(KODE_LANSIA)) potonganHarga = 0.30 * tarifDasar; 
        } else { 
            if (tipe.equals(KODE_PELAJAR)) potonganHarga = 0.10 * tarifDasar;
            else if (tipe.equals(KODE_LANSIA)) potonganHarga = 0.20 * tarifDasar;
        }

        double totalBayar = tarifDasar - potonganHarga;
        
        if ((this.saldo - totalBayar) < 10000) { 
            System.out.println("Gagal: Sisa saldo minimal Rp.10.000 Bang");
        } else {
            this.saldo -= totalBayar;
            System.out.println("=== TAPPING BERHASIL ===");
            System.out.printf("Diskon Perjalanan : Rp%,.0f\n", potonganHarga);
            System.out.printf("Biaya Terpotong   : Rp%,.0f\n", totalBayar);
            System.out.printf("Sisa Saldo        : Rp%,.0f\n", this.saldo);
        }
    }
    public String getNomorKartu() { return nomorKartu; }
    public boolean isTerblokir() { return terblokir; }
    public String getNamaPemilik() { return namaPemilik; }
}