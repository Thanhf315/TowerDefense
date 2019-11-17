import java.io.FileWriter;

public class FileWriterExample {
    public static void main(String args[]) {
        int count = 0;
        try {
            FileWriter fw = new FileWriter("C:/Users/Thanhf/Desktop/OOP/TowerDefense/src/line/smallline.txt");

            for (int i = 0; i <= 550; i += 24) {
                fw.write(i + " 600" + "\n");
                count++;
            }
            for (int i =600; i>=400; i-=24){
                fw.write("550 "+i + "\n");
            count++;
            }
            for (int i= 550; i<=950; i+=24){
                fw.write(i + " 400" + "\n");
         count++;
        }
            for (int i =400; i>=300; i-=24){
                fw.write("950 "+i + "\n");
            count++;
                }
            for (int i= 950; i<=1350; i+=24){
                fw.write(i + " 300" + "\n");
                    count++;
                    }
            for (int i =300; i>=0; i-=24){
                fw.write("1350 "+i + "\n");
                    count++;
                    }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(count);
    }
}