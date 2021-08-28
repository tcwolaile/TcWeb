package TcWeb.DataBase;

import java.io.*;

public class Json {
    public static boolean createJsonFile(String jsonString, String filePath, String fileName) {
        boolean flag = true;
        String fullPath = filePath + "/" + fileName + ".json";
        try {

            File file = new File(fullPath);

            if (!file.getParentFile().exists()) {

                file.getParentFile().mkdirs();

            }

            if (file.exists()) {

                file.delete();

            }

            file.createNewFile();
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");

            write.write(jsonString);

            write.flush();

            write.close();

        } catch (Exception e) {
            flag = false;

            e.printStackTrace();

        }
        return flag;
    }
    public static String readJson(String path) {
        String str;
        String json = new String();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
            BufferedReader in = new BufferedReader(isr);
            while ((str = in.readLine()) != null) {
                json += str;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return json;
    }
}
