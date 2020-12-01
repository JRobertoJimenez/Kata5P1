
package kata5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Roberto Jiménez
 */
public class MailListReader {
    public static List<String> read(String fileName){
        List<String> listMail=new ArrayList<>();
        try {
            BufferedReader br=new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                if (line.split("@").length==2){
                    listMail.add(line);
                }
                line=br.readLine();
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return listMail;
    }
}
