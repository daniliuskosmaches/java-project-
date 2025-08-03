
import java.io.*;
import java.util.*;

public class CrimeRecord {
    private String region;
    private int year;
    private String crimetype;
    private int totalCases;
    private int solvedcases;
    private int unsolvedcases;

    public CrimeRecord(String region, int year, String crimetype, int totalCases, int solvedcases, int unsolvedcases) {
        this.region = region;
        this.year = year;
        this.crimetype = crimetype;
        this.totalCases = totalCases;
        this.solvedcases = solvedcases;
        this.unsolvedcases = unsolvedcases;
    }

    public String getRegion() { return region; }
    public int getYear() { return year; }
    public String getCrimeType() { return crimetype; }
    public int getTotalCases() { return totalCases; }
    public int getSolvedCases() { return solvedcases; }
    public int getUnsolvedCases() { return unsolvedcases; }

    @Override
    public String toString() {
        return String.format(
                "%-15s | %-4d | %-15s | Total: %-5d | Solved: %-5d | Unsolved: %-5d",
                region, year, crimetype, totalCases, solvedcases, unsolvedcases
        );
    }
}


