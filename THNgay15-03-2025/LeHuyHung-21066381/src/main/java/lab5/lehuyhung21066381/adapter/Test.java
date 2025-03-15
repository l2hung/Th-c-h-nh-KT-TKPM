package lab5.lehuyhung21066381.adapter;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        XMLDataSystemOld xmlDataSystemOld = new XMLDataSystemOldImpl();
        JsonDataSystemNew adapter = new XMLToJsonAdapter(xmlDataSystemOld);

        try {
            String jsonData = adapter.getJsonData();
            System.out.println("Dữ liệu JSON sau khi chuyển đổi:");
            System.out.println(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

