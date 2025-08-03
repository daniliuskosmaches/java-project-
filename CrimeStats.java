
class CrimeStats extends CrimeRecord {
    public CrimeStats(String region, int year, String crimetype, int totalCases, int solvedcases, int unsolvedcases) {
        super(region, year, crimetype, totalCases, solvedcases, unsolvedcases);
    }

    public double getSolveRate() {
        if (getTotalCases() == 0) return 0.0;
        return ((double) getSolvedCases() / getTotalCases()) * 100;
    }
}