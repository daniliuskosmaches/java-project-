import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CrimeManager {
    private ArrayList<CrimeStats> records = new ArrayList<>();


    public void loadFromFile(String filename) {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 6) continue;

                String region = parts[0].trim();
                int total = Integer.parseInt(parts[1].trim());
                int year = Integer.parseInt(parts[2].trim());
                String type = parts[3].trim();
                int solved = Integer.parseInt(parts[4].trim());
                int unsolved = Integer.parseInt(parts[5].trim());

                records.add(new CrimeStats(region, year, type, total, solved, unsolved));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file " + filename);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing numbers in " + filename);
        }
    }

    public List<CrimeStats> searchByRegion(String region) {
        List<CrimeStats> result = new ArrayList<>();
        for (CrimeStats record : records) {
            if (record.getRegion().toLowerCase().contains(region.toLowerCase())) {
                result.add(record);
            }
        }
        return result;
    }

    public List<CrimeStats> searchByCrimeType(String type) {
        List<CrimeStats> result = new ArrayList<>();
        for (CrimeStats record : records) {
            if (record.getCrimeType().toLowerCase().contains(type.toLowerCase())) {
                result.add(record);
            }
        }
        return result;
    }

    public List<CrimeStats> filterByYear(int year) {
        List<CrimeStats> result = new ArrayList<>();
        for (CrimeStats record : records) {
            if (record.getYear() == year) {
                result.add(record);
            }
        }
        return result;
    }

    public void sortBySolvedCases(boolean ascending) {
        records.sort(Comparator.comparingInt(CrimeStats::getSolvedCases));
        if (!ascending) {
            Collections.reverse(records);
        }
    }

    public void sortByUnsolvedCases(boolean ascending) {
        records.sort(Comparator.comparingInt(CrimeStats::getUnsolvedCases));
        if (!ascending) {
            Collections.reverse(records);
        }
    }

    public void displayAll() {
        for (CrimeStats record : records) {
            System.out.println(record);
        }
    }
}
