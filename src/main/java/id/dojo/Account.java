package id.dojo;

import java.io.Serializable;
import java.util.HashMap;
import java.time.LocalDateTime;

public class Account implements Serializable {
    private String accountName;
    private String username;
    private String password;
    private String url;
    private String time;
    private int resetCount = 0;
    private boolean encrypt = false;

    public Account(String accountName, String username, String password, String url, String time, boolean encrypt) {
        this.accountName = accountName;
        this.username = username;
        this.password = password;
        this.url = url;
        this.time = time;
        this.encrypt = encrypt;
    }

    public String getUsername() {
        return username;
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        this.resetCount += cnt;
    }

    public boolean getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

}
