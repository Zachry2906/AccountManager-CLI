package id.dojo;

public class History implements java.io.Serializable {
    private String accountName;
    private String username;
    private String password;
    private String time;
    private int resetCount;
    boolean encrypt = false;

    public History(String accountName, String username, String password, String time, int resetCount, boolean encrypt) {
        this.accountName = accountName;
        this.username = username;
        this.password = password;
        this.time = time;
        this.resetCount = resetCount;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getResetCount() {
        return resetCount;
    }

    public void setResetCount(int cnt) {
        this.resetCount += cnt ;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public boolean getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }
}
