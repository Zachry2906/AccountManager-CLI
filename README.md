# Java CLI Password Manager

A secure command-line interface (CLI) password manager implemented in Java. This application provides a simple yet effective way to manage your passwords with encryption for enhanced security.


## Technologies Used

- **Language**: Java
- **Interface**: Command Line Interface (CLI)
- **Encryption Methods**: 
  - [Vigenere Cipher](https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher) for password encryption
  - [StringBase64](https://www.base64encode.org/) encoding for file encryption

## Features

The Password Manager offers the following functionalities:

1. Registered Account
2. View Account with password
3. Add Account
4. Change Password
5. View Password Change History
6. Delete Account
7. Exit

## Setup and Running
### Prerequisites

- Java JDK 8 or higher
- IntelliJ IDEA (strongly recommended)

### Steps to Run

1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Set directory where you want to save your account data in main.java line 16
4. Run the main application file

if you want to create jar file from this program, make sure you have installed [maven dependency manager](https://maven.apache.org/download.cgi) (if you using IntelijI IDEA, you don't need to), inside PasswordManager directory open terminal then run
``` bash
mvn install
```
then jar file will be created in the target folder


## Security Measures

- **Password Encryption**: All passwords are encrypted using the Vigenère cipher before storage.
- **File Encryption**: The data file storing account information is encrypted using Base64 encoding.

## Security Note

While this application uses encryption methods to enhance security, please note that it's primarily an educational project. For highly sensitive data, consider using professionally audited password management solutions.

## Contributing

Feel free to fork this project and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

