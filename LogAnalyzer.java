/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Willen O. Leal
 * @version 2018.10.17
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    /**
     * @param filename file to be read.
     * Overloaded contructor, accepts filename. Create object 
     * to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    {
        hourCounts = new int[24];
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    /**
     * Prints the total number of accesses in a day.
     */
    public void numberOfAccesses()
    {
        analyzeHourlyData();
        int numOfAccesses = 0;
        
        for(int element: hourCounts)
        {
            numOfAccesses = numOfAccesses + element;
        }
        
        System.out.println("Number of accesses: " + numOfAccesses);
        
    }
    
    /**
     * Returns the busssiest hour in a day.
     */
    public int busiestHour()
    {
        analyzeHourlyData();
        int max = 0;
        int maxIndex = 0;
        
        for(int hour= 0; hour < hourCounts.length; hour++)
        {
            if(hourCounts[hour] > max)
            {
                max = hourCounts[hour];
                maxIndex = hour;
            }
        
        }
        
        return maxIndex;
    }
    
    /**
     * Returns the quietest hour in a day.
     */
    public int quietstHour()
    {
        analyzeHourlyData();
        int min = 100;
        int minIndex = 0;
    
        for(int hour= 0; hour < hourCounts.length; hour++)
        {
            if(hourCounts[hour] < min)
            {
                min = hourCounts[hour];
                minIndex = hour;
            }
        
        }    
    
        return minIndex;
    }
    
    /**
     * Returns the busiest two hours. 
     */
    public int busiestTwoHours()
    {
        analyzeHourlyData();
        int max = 0;
        int maxIndex = 0;
        
        for(int hour= 0; hour < hourCounts.length -1; hour++)
        {
            if((hourCounts[hour] + hourCounts[hour+1]) > max)
            {
                max = (hourCounts[hour] + hourCounts[hour+1]);
                maxIndex = hour;
            }
        
        }
        
        return maxIndex;
    }
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
