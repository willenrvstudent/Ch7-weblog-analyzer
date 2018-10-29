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
    private int[] dayCounts;
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader readerH;
    private LogfileReader readerD;
    private LogfileReader readerM;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[29];
        monthCounts = new int[13];
        // Create the reader to obtain the data.
        readerH = new LogfileReader();
        readerD = new LogfileReader();
        readerM = new LogfileReader();
    }
    
    /**
     * @param filename file to be read.
     * Overloaded contructor, accepts filename. Create object 
     * to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    {
        hourCounts = new int[24];
        dayCounts = new int[29];
        monthCounts = new int[13];
        readerH = new LogfileReader(filename);
        readerD = new LogfileReader(filename);
        readerM = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(readerH.hasNext()) {
            LogEntry entry = readerH.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
     /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        while(readerM.hasNext()) {
            LogEntry entry = readerM.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }
    
    
    /**
     * Analyze the monthly acess data from log file.
     */
    public void analyzeDailyData()
    {
       while(readerD.hasNext())
       {
           LogEntry entry = readerD.next();
           int day = entry.getDay();
           dayCounts[day]++;
       }
    
    }
    
    /**
     * print the daily counts
     */
    public void printDailyCounts()
    {
        System.out.println("Day: Count");
        for(int day = 1; day < dayCounts.length; day++) {
            System.out.println(day + ": " + dayCounts[day]);
        }
    }
    
    /**
     * Return the quietest day in log file
     * @return minIndex
     */
    public int quietestDay()
    {
        analyzeDailyData();
        int min = 100;
        int minIndex = 0;
    
        for(int day = 1; day < dayCounts.length; day++)
        {
            if(dayCounts[day] < min)
            {
                min = dayCounts[day];
                minIndex = day;
            }
        
        }    
    
        return minIndex;
    }
    
    /**
     * return the busiest day on log file.
     * @return maxIndex
     */
    public int busiestDay()
    {
        analyzeDailyData();
        int max = 0;
        int maxIndex = 0;
        
        for(int day= 1; day < dayCounts.length; day++)
        {
            if(dayCounts[day] > max)
            {
                max = dayCounts[day];
                maxIndex = day;
            }
        
        }
        
        return maxIndex;
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
     * Prints the total number of accesses in a month.
     */
    public void totalAccessesPerMonth()
    {
        analyzeMonthlyData();
        System.out.println("Month: Count");
        for(int month = 1; month < monthCounts.length; month++) {
            System.out.println(month + ": " + monthCounts[month]);
        }
        
    }
    
       /**
     * Prints the total number of accesses in a month.
     */
    public void averageAccessesPerMonth()
    {
        analyzeMonthlyData();
        System.out.println("Month: Count");
        for(int month = 1; month < monthCounts.length; month++) {
            System.out.println(month + ": " + (monthCounts[month]/2));
        }
        
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
     * Returns the busssiest month in a day.
     */
    public int busiestMonth()
    {
        analyzeMonthlyData();
        int max = 0;
        int maxIndex = 0;
        
        for(int month = 0; month < monthCounts.length; month++)
        {
            if(monthCounts[month] > max)
            {
                max = monthCounts[month];
                maxIndex = month;
            }
        
        }
        
        return maxIndex;
    }
    
    /**
     * Returns the quietest hour in a day.
     * @return minIndex
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
     * Returns the quietest hour in a day.
     * @return minIndex
     */
    public int quietstMonth()
    {
        analyzeMonthlyData();
        int min = 100;
        int minIndex = 0;
    
        for(int month= 1; month < monthCounts.length; month++)
        {
            if(monthCounts[month] < min)
            {
                min = monthCounts[month];
                minIndex = month;
            }
        
        }    
    
        return minIndex;
    }
    
    /**
     * Returns the busiest two hours. 
     * @return maxIndex
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
        readerD.printData();
    }
}
