package id.dojo;

import java.io.*;
import java.util.Base64;

public class ObjectSaver{
    public static String fileName;

    public static void saveObject(Object object){
        try {
            //menulis data dalam bytearray
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //untuk menulis objek Java ke dalam stream
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            //Metode writeObject() dari ObjectOutputStream digunakan untuk menulis objek Java ke dalam stream.
            oos.writeObject(object);
            //untuk memastikan bahwa semua data yang ditulis ke stream sebenarnya ditulis ke ByteArrayOutputStream
            oos.flush();
            //mengubah data dalam bentuk byte array
            byte[] objectBytes = baos.toByteArray();

            String base64Data = Base64.getEncoder().encodeToString(objectBytes);

            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(base64Data);
            fileWriter.close();

            oos.close();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object retrieveObject(){
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String base64Data = bufferedReader.readLine();
            bufferedReader.close();
            fileReader.close();

            if (base64Data != null) {
                byte[] objectBytes = Base64.getDecoder().decode(base64Data);
                ByteArrayInputStream bais = new ByteArrayInputStream(objectBytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Object object = ois.readObject();
                ois.close();
                bais.close();

                return object;
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        public static byte[] serializeObject(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object deserializeObject(byte[] bytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object object = ois.readObject();
            ois.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

    // End of class
