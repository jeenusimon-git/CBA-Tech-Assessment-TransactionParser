package au.com.parser;

import java.util.*;
import java.io.*;
import org.supercsv.io.*;
import org.supercsv.prefs.CsvPreference;

public class App {
    public static final String START_ROW = "SZ  [record";
    public static final String END_ROW = "]SZ";
    public static final String IGNORE_ROW = "[record";
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Need target directory for CSV output");
        } else {
            String target = args[0];
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    App.class.getResourceAsStream("/q1.test_data")));
            List<Map<String, String>> records = parseRecords(reader);
            if (records.isEmpty()) {
                System.out.println("Unable to parse records");
            } else {
                int count = 1;
                for (Map<String, String> record : records) {
                    ICsvMapWriter mapWriter =
                            new CsvMapWriter(new FileWriter(target + "/out" + count + ".csv"), CsvPreference.STANDARD_PREFERENCE);
                    String[] header = record.keySet().toArray(new String[0]);
                    mapWriter.writeHeader(header);
                    mapWriter.write(record, header);
                    count++;
                    mapWriter.close();
                }
            }
        }
    }

    public static List<Map<String, String>> parseRecords(BufferedReader reader) throws Exception {
        List<Map<String, String>> records = new ArrayList<Map<String, String>>();
        String line = "";
        while ((line = reader.readLine()) != null) { 
            if (line.trim().startsWith(START_ROW)) {
                Map<String, String> record = parseRecord(reader);
                 if (!record.isEmpty()) {
                     records.add(record);
                 }
            }
        }
        return records;
    }

    public static Map<String, String> parseRecord(BufferedReader reader) throws Exception {
        String line = "";
        Boolean reachedEnd = false;
        Map<String, String> record = new LinkedHashMap<String, String>();
        while (!reachedEnd && (line = reader.readLine()) != null) {
            if (line.trim().endsWith(END_ROW)) {
                reachedEnd = true;
                line = line.substring(0, line.indexOf(END_ROW));
            }
            if (line.trim().contains(IGNORE_ROW)) {
                continue;
            }
            if (line.trim().endsWith("\"]")) {
                line = line.substring(0, line.indexOf("]"));
            }
            line = line.trim();
            String[] cols = null;
            if (line.contains("[void")) {
                cols = line.split("\\[void");
                cols[1] = "[void" + cols[1];
            } else {
                cols = line.split(" +");
                if (cols[1].trim().equals("\"")) {
                    cols[1] = "\"\"";
                }
            }
            System.out.println(cols[0].trim() + " = " + cols[1].trim());
            record.put(cols[0].trim(), cols[1].trim());
        }
        return record;
    }
}
