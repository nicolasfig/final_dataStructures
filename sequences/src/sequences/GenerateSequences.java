//TODO  pasar a python

package sequences;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateSequences {

    public static String nucleotide() {
        Random rd = new Random();
        
        switch(rd.nextInt(4)){
            case 0:
                return "A";
            case 1:
                return "C";
            case 2:
                return "G";
            case 3:
                return "T";
            default:return "";
        }
    }
    
    public static String sequence(int length) {
        if(length == 1){
            return nucleotide();
        }else{
            return nucleotide() + sequence(length - 1);
        }
    }

    public static String chromosome(){
        Random rd = new Random();
        
        return "chr" + (rd.nextInt(23) + 1);
    } 
    
    public static void saveToFile() throws IOException{
         try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("sequences.txt"));
            int datasize = 1000000;
            Random rd = new Random();
            String experimental_read;
         
            for (int i = 0; i < datasize; i++) {
              int length = rd.nextInt(50)+1;
              int start = rd.nextInt(1000);
              experimental_read = String.format("%s,%s,%d,%d\n", sequence(length), chromosome(), start, (start + length - 1));
              bw.write(experimental_read);
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public static void main(String[] args) throws IOException {
        saveToFile();
        Random rd = new Random();
        int length = rd.nextInt(50) + 1;
        int start = rd.nextInt(1000);
        System.out.printf("%s %s %d %d\n", sequence(length), chromosome(), start, (start + length - 1));
    }
}
