package id.dojo;
import java.util.List;

public class TableFormatter {
    private static final String HORIZONTAL_LINE = "+---------------------------+--------------------------+---------------------------+--------------------------------+";
    private static final String HORIZONTAL_LINE2 = "+-----------------+-----------------+-----------------+-----------------+-------------------------------+";

    public static void printTable(List<Account> accounts) {
        System.out.println(HORIZONTAL_LINE);
        System.out.printf("| %-25s | %-25s | %-25s | %-24s |%n", "Nama Akun", "Username", "Website", "Waktu");
        System.out.println(HORIZONTAL_LINE);

        for (Account acc : accounts) {
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |%n",
                    acc.getAccountName(), acc.getUsername(), acc.getUrl(), acc.getTime());
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTable(List<Account> accounts, String accountName) {
        System.out.println(HORIZONTAL_LINE2);
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-20s |%n", "Nama Akun", "Username", "Password", "Website", "Waktu");
        System.out.println(HORIZONTAL_LINE2);

        for (Account acc : accounts) {
            if (acc.getAccountName().equals(accountName)) {
                if (acc.getEncrypt()) {
                    System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-20s |%n",
                    acc.getAccountName(), acc.getUsername(), VigeneCipher.decrypt(acc.getPassword(),"ZACHRY"), acc.getUrl(), acc.getTime());
                } else {
                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-20s |%n",
                acc.getAccountName(), acc.getUsername(), acc.getPassword(), acc.getUrl(), acc.getTime());
                }
            }
        }

        System.out.println(HORIZONTAL_LINE2);
    }

    public static void printTableHistory(List<History> history) {
        System.out.println(HORIZONTAL_LINE2);
        System.out.printf("| %-15s | %-15s | %-20s | %-15s | %-20s |%n", "Nama Akun", "Username", "Password Sebelumnya", "Index Ganti", "Waktu");
        System.out.println(HORIZONTAL_LINE2);

        for (History acc : history) {
            System.out.printf("| %-15s | %-15s | %-20s | %-15s | %-20s |%n",
            acc.getAccountName(), acc.getUsername(), acc.getPassword(), acc.getResetCount(), acc.getTime());
        }

        System.out.println(HORIZONTAL_LINE2);
    }
}