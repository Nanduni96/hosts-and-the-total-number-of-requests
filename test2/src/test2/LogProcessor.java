package test2;

import java.io.*;
import java.util.*;

public class LogProcessor {
    public static void main(String[] args) {
        try {
        
            //get file name as input
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("file name? ");
            String filename = scanner.nextLine();
            scanner.close();
        
            // Open input file for reading
            
            BufferedReader fp = new BufferedReader(new FileReader(filename));
            String file_content = "";
            String line = fp.readLine();
            while (line != null) {
                file_content += line + "\n";
                line = fp.readLine();
            }
            fp.close();

            // Split file content into lines and extract unique hosts
            
            String[] split_content = file_content.split("\n");
            ArrayList<String> host_list = new ArrayList<String>();
            for (int i = 0; i < split_content.length; i++) {
                String host = split_content[i].split("- -")[0];
                host_list.add(host);
            }
            Set<String> unique_host = new HashSet<String>(host_list);

            // Count occurrences of each unique host and write to output file
            
            ArrayList<String> output_list = new ArrayList<String>();
            for (String host : unique_host) {
                int count = Collections.frequency(host_list, host);
                output_list.add(host + " " + count);
            }
            BufferedWriter save_output = new BufferedWriter(new FileWriter("records "+filename));
            for (String item : output_list) {
                save_output.write(item + "\n");
            }
            save_output.close();

            // Open output file for reading and print its contents
            
            BufferedReader file_output = new BufferedReader(new FileReader("records "+filename));
            String output_content = "";
            line = file_output.readLine();
            while (line != null) {
                output_content += line + "\n";
                line = file_output.readLine();
            }
            file_output.close();
            System.out.println(output_content);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}