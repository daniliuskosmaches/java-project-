
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BOLD = "\u001B[1m";

    public static void printStyledBox(String title, List<String> lines) {
        final int width = 90;
        String border = "═".repeat(width - 2);

        System.out.println(CYAN + "╔" + border + "╗" + RESET);
        System.out.printf(CYAN + "║" + BOLD + "%-" + (width - 2) + "s" + RESET + CYAN + "║%n", center(title, width - 2));
        System.out.println(CYAN + "╠" + border + "╣" + RESET);

        if (lines.isEmpty()) {
            String msg = "⚠ No records found.";
            System.out.printf(CYAN + "║" + "%-" + (width - 2) + "s" + CYAN + "║%n", center(msg, width - 2));
        } else {
            for (String line : lines) {
                if (line.length() > width - 4) line = line.substring(0, width - 7) + "...";
                System.out.printf(CYAN + "║ %-"+ (width - 4) + "s ║%n", line);
            }
        }

        System.out.println(CYAN + "╚" + border + "╝" + RESET);
    }

    public static String center(String text, int width) {
        int pad = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, pad)) + text;
    }

    public static void main(String[] args) {
        CrimeManager manager = new CrimeManager();

        Scanner scanner = new Scanner(System.in);

        while (true) {


            System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║                            🚔 CRIME DATABASE COMMAND CENTER                            ║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║  1. 📂 Load data from file (crimes.csv)                                                ║" + RESET);
            System.out.println(CYAN + "║  2. 📋 Display all records                                                             ║" + RESET);
            System.out.println(CYAN + "║  3. 🌍 Search by region                                                                ║" + RESET);
            System.out.println(CYAN + "║  4. 🔍 Search by crime type                                                            ║" + RESET);
            System.out.println(CYAN + "║  5. 📆 Filter by year                                                                  ║" + RESET);
            System.out.println(CYAN + "║  6. ✅ Sort by solved cases                                                            ║" + RESET);
            System.out.println(CYAN + "║  7. ❌ Sort by unsolved cases                                                          ║" + RESET);
            System.out.println(CYAN + "║  8. 🚪 Exit                                                                            ║" + RESET);
            System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════════════════════════════╝" + RESET);
            System.out.print(BOLD + YELLOW + "Choose an option (1–8): " + RESET);

            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    System.out.println("Working directory: " + System.getProperty("user.dir"));
                    manager.loadFromFile("data/crimes.csv");
                    System.out.println("📂 Data loaded from crimes.csv");
                    break;

                case "2":
                    manager.displayAll();
                    break;

                case "3":
                    System.out.print("Enter region to search: ");
                    String region = scanner.nextLine();
                    List<CrimeStats> byRegion = manager.searchByRegion(region);
                    List<String> regionLines = byRegion.stream().map(CrimeStats::toString).toList();
                    printStyledBox("🌍 SEARCH RESULTS FOR REGION: " + region.toUpperCase(), regionLines);
                    break;

                case "4":
                    System.out.print("Enter crime type to search: ");
                    String crimeType = scanner.nextLine();
                    List<CrimeStats> byCrimeType = manager.searchByCrimeType(crimeType);
                    List<String> crimeTypeLines = byCrimeType.stream().map(CrimeStats::toString).toList();
                    printStyledBox("🔍 CRIME TYPE: " + crimeType.toUpperCase(), crimeTypeLines);
                    break;

                case "5":
                    System.out.print("Enter year to filter: ");
                    try {
                        int year = Integer.parseInt(scanner.nextLine());
                        List<CrimeStats> byYear = manager.filterByYear(year);
                        List<String> yearLines = byYear.stream().map(CrimeStats::toString).toList();
                        printStyledBox("📆 FILTERED BY YEAR: " + year, yearLines);
                    } catch (NumberFormatException e) {
                        printStyledBox("❌ INVALID INPUT", List.of("Please enter a valid year (e.g., 2020)"));
                    }
                    break;

                case "6":
                    System.out.print("Sort by solved cases (asc/desc): ");
                    String ascSolved = scanner.nextLine().toLowerCase();
                    manager.sortBySolvedCases(ascSolved.equals("asc"));
                    printStyledBox("✅ SORTED BY SOLVED CASES", List.of("Order: " + (ascSolved.equals("asc") ? "Ascending" : "Descending")));
                    break;

                case "7":
                    System.out.print("Sort by unsolved cases (asc/desc): ");
                    String ascUnsolved = scanner.nextLine().toLowerCase();
                    manager.sortByUnsolvedCases(ascUnsolved.equals("asc"));
                    printStyledBox("❌ SORTED BY UNSOLVED CASES", List.of("Order: " + (ascUnsolved.equals("asc") ? "Ascending" : "Descending")));
                    break;

                case "8":
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
