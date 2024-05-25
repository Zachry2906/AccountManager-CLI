package id.dojo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Base64;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static String filePass = "/home/zachry/Documents/accountManager/password.txt";
    static String fileHistory = "/home/zachry/Documents/accountManager/history.txt";
    public static boolean ulang = false;

    public static void main(String[] args) {
        do {
            menu();
            System.out.print("Apakah Anda Ingin Melanjutkan ? (Y/N) : ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next();
            if (answer.equals("Y") || answer.equals("y")) {
                ulang = true;
            } else {
                ulang = false;
            }
        } while (ulang == true);
    }

    public static void menu() {
        clearScreen();
        System.out.println("Welcome to Password Manager");
        System.out.println("1. Daftar Akun");
        System.out.println("2. Lihat Akun");
        System.out.println("3. Tambah Akun");
        System.out.println("4. Ubah Password");
        System.out.println("5. Riwayat Perubahan Password");
        System.out.println("6. Hapus Akun");
        System.out.println("7. Keluar");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Pilih Menu : ");
        int menu = scanner.nextInt();

        switch (menu) {
            case 1:
                tampilData();
                break;
            case 2:
                tampilDataTerpilih();
                break;
            case 3:
                tambahAkun();
                break;
            case 4:
                ubahPassword();
                break;
            case 5:
                riwayatPerubahanPassword();
                break;
            case 6:
                hapusAkun();
                break;
            case 7:
                System.out.println("Keluar");
                break;
            default:
                System.out.println("Menu tidak tersedia");
        }
    }

    public static void tampilData() {
        ObjectSaver.fileName = filePass;
        Object object = ObjectSaver.retrieveObject();
        if (object != null && object instanceof List) {
            List<Account> accounts = (List<Account>) object;
            TableFormatter.printTable(accounts);
        } else {
            System.out.println("Data Kosong");
        }
    }

    public static void tampilDataTerpilih() {
        System.out.println("Lihat Akun");
        System.out.print("Masukkan nama akun : ");
        Scanner scanner = new Scanner(System.in);
        String namaAkun = scanner.next();

        ObjectSaver.fileName = filePass;
        Object object3 = ObjectSaver.retrieveObject();
        if (object3 != null && object3 instanceof List) {
            List<Account> accounts = (List<Account>) object3;
            TableFormatter.printTable(accounts, namaAkun);
        } else {
            System.out.println("Data Kosong");
        }
    }

    public static void tambahAkun() {
        String password = "";
        boolean enkrip = false;
        System.out.println("Tambah Akun");
        Scanner scanner = new Scanner(System.in);
        ObjectSaver.fileName = filePass;

        System.out.print("Nama Akun : ");
        String accountName = scanner.next();
        System.out.print("Username : ");
        String username = scanner.next();
        System.out.print("Dalam setting password, apakah anda ingin menggunakan enkripsi vigene cipher ? (Y/N) : ");
        String enkripsi = scanner.next();
        if (enkripsi.equals("Y") || enkripsi.equals("y")) {
            System.out.print("Password yg akan dienkripsi : ");
            password = scanner.next();
            password = VigeneCipher.encrypt(password, "ZACHRY");
            enkrip = true;
        } else {
            System.out.print("Password : ");
            password = scanner.next();
        }
        System.out.print("Website : ");
        String website = scanner.next();

        Object objecttt = ObjectSaver.retrieveObject();
        List<Account> AccList = new ArrayList<>();
        if (objecttt != null && objecttt instanceof List) {
            List<Account> account1 = (List<Account>) objecttt;
            for (Account acc : account1) {
                Account account = new Account(acc.getAccountName(), acc.getUsername(), acc.getPassword(), acc.getUrl(), acc.getTime(), acc.getEncrypt());
                AccList.add(account);
            }
        }

        String time = LocalDateTime.now().toString();

        Account account = new Account(accountName, username, password, website, time, enkrip);
        AccList.add(account);
        ObjectSaver.saveObject(AccList);
    }

    public static void ubahPassword() {
        String pass = "";
        String ti = "";
        String akun = "";
        String us = "";
        int cnt = 0;
        boolean enkrip = false;
        String password1 = "";
        System.out.println("Ubah Password");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nama Akun : ");
        String accountName1 = scanner.next();

        ObjectSaver.fileName = filePass;
        Object object1 = ObjectSaver.retrieveObject();
        if (object1 instanceof List) {
            List<Account> account1 = (List<Account>) object1;
            for (Account acc : account1) {
                if (acc.getAccountName().equals(accountName1)) {
                    if (acc.getEncrypt()) {
                        System.out.print("Password Baru : ");
                        password1 = scanner.next();
                        password1 = VigeneCipher.encrypt(password1, "ZACHRY");
                    } else {
                        System.out.print("Password Baru : ");
                        password1 = scanner.next();
                    }
                    acc.setResetCount(1);
                    pass = VigeneCipher.decrypt(acc.getPassword(), "ZACHRY");
                    System.out.print(pass);
                    ti = acc.getTime();
                    akun = acc.getAccountName();
                    us = acc.getUsername();
                    cnt = acc.getResetCount();
                    enkrip = acc.getEncrypt();
                    acc.setPassword(password1);
                    acc.setTime(LocalDateTime.now().toString());
                }
            }
            ObjectSaver.saveObject(account1);

        }

        ObjectSaver.fileName = fileHistory;
        Object historyy = ObjectSaver.retrieveObject();
        List<History> HisList = new ArrayList<>();
        if (historyy != null && historyy instanceof List) {
            List<History> history = (List<History>) historyy;
            for (History acc : history) {
                History Historry = new History(acc.getAccountName(), acc.getUsername(), acc.getPassword(), acc.getTime(), acc.getResetCount(), acc.getEncrypt());
                HisList.add(Historry);
            }
        }

        History h = new History(akun, us, pass, ti, cnt, enkrip);
        HisList.add(h);
        ObjectSaver.saveObject(HisList);
    }

    public static void riwayatPerubahanPassword() {
        System.out.println("Riwayat Perubahan Password");
        ObjectSaver.fileName = fileHistory;
        Object historyyy = ObjectSaver.retrieveObject();
        if (historyyy != null && historyyy instanceof List) {
            List<History> history = (List<History>) historyyy;
            TableFormatter.printTableHistory(history);
        }
    }

    public static void hapusAkun() {
        System.out.println("Hapus Akun");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nama Akun : ");
        String hapus = scanner.next();

        ObjectSaver.fileName = filePass;
        Object objectHapus = ObjectSaver.retrieveObject();
        List<Account> Acclist = new ArrayList<>();
        if (objectHapus instanceof List) {
            List<Account> account1 = (List<Account>) objectHapus;
            for (Account acc : account1) {
                if (!acc.getAccountName().equals(hapus)) {
                    Account account2 = new Account(acc.getAccountName(), acc.getUsername(), acc.getPassword(), acc.getUrl(), acc.getTime(), acc.getEncrypt());
                    Acclist.add(account2);
                }
            }
        }
        ObjectSaver.saveObject(Acclist);
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }
}