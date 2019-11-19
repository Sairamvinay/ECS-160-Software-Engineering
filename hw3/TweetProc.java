package edu.ucdavis.ecs160.hw3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;


public class TweetProc {
	private static int NAME_COL = 8;	//The tweeter name column is always the 8th column
	private static int TEXT_COL = 11;	//The text of the tweet is always 11th column
	private static String Taggee = "@";	//The String to match for finding the Taggees (especially for 2 and 6)
	private static String URL = "http://";	//The String to match for URLs especially for 3
	
	//Helper method to read the lines within the file
	//Returns a stream of all the lines within the file
	public static Stream<String> getLines(String FullPathName) throws IOException{
		
		Stream<String> lines = null;
		lines = Files.lines(Paths.get(FullPathName));
		return lines;
	}
	
	
	
	
	//Part 1 (DONE):
	//This function returns the map of the tweeter and the respective number of tweets posted by that tweeter
	
	public static Map <String, Long> getPerTweeterCount(String FullPathname){
		
		Stream<String> lines = null;	// a simple stream to accept all the lines inside the File
		try {
			lines = getLines(FullPathname);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Function<String[],String> getTweeterName = line->line[NAME_COL].toString(); //a simple lambda to obtain tweeter from fields
		//This is the logic to map all the tweeters with their tweet counts
		Map <String,Long> Counts = lines.skip(1)	//First line is header (remove it)
									.parallel()	//Do all in parallel
									.map(line -> line.split(","))	//get all the fields for each line
									.collect(Collectors.groupingBy(getTweeterName,Collectors.counting()));	//Collect the count for each tweeter
		
		lines.close();	//Close the stream
		
		return Counts;
		
	}
	//Part 2 (DONE)
	//The function to count number of distinct tweets taggees appear
	public static Map <String, Long> getPerTaggeeCount(String FullPathname){
		Stream<String> lines = null;
		try {
			lines = getLines(FullPathname);	// a simple stream to accept all the lines inside the File
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Map <String,Long> Counts = 
		lines.skip(1).parallel()	//Skip header and do in parallel
		.map(line -> line.split(","))	//get all the fields for each line
		.flatMap(line -> Arrays.stream(line[TEXT_COL].split("\\s+"))	//Get a stream of all the words inside the tweet
										.distinct().filter(x -> x.startsWith(Taggee))	//filter all distinct taggees alone within the stream of words
										.map(x -> x.substring(0)))	//get all the taggees
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting())) ;	//Get a mapping of their counts
		
		lines.close();	//Close the stream
		return Counts;
	}
	
	//Part 3 (DONE)
	//This function returns a map of count of tweets per user which has atleast one URL
	public static Map <String, Long> getTweeterURLTweetCount(String FullPathname){
		
		Stream<String> lines = null;
		try {
			lines = getLines(FullPathname);	// a simple stream to accept all the lines inside the File
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Function<String[],String> getTweeterName = line->line[NAME_COL].toString(); //a simple lambda to obtain tweeter from fields
		
		Map <String,Long> Counts =
					
					lines.skip(1).parallel().map(line -> line.split(","))	//Skip header and do in parallel and get the fields
					.filter(line -> Arrays.stream(line[TEXT_COL].split("\\s+")).anyMatch(x -> x.startsWith(URL)))	//filter all words within a stream of words inside the tweet to get the words which start with URL link
					.collect(Collectors.groupingBy(getTweeterName,Collectors.counting())); //get a count of all tweeters with num tweets with an URL
					
		lines.close();	//Close the stream
		return Counts;
		
			
	}
	
	
	//Part 4 (DONE)
	//This function returns a map of count of tweets per user which has atleast one instance of word inside the tweet
	public static Map <String, Long> getTweeterWordCount(String FullPathname, String word){
		Stream<String> lines = null;
		try {
			lines = getLines(FullPathname);	// a simple stream to accept all the lines inside the File
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Function<String[],String> getTweeterName = line->line[NAME_COL].toString(); //a simple lambda to obtain tweeter from fields
		Map <String,Long> counts =
				
				lines.skip(1).parallel()	//Skip header and do in parallel
				.map(line -> line.split(","))	//Get all the fields
				.filter(line -> Arrays.stream(line[TEXT_COL].split("\\s+")).anyMatch(x -> x.equals(word))) //filter stream of words inside tweets with words which contains atleast one instance of word (argument)
				.collect(Collectors.groupingBy(getTweeterName,Collectors.counting())); //map all counts of tweets which have atleast one instance of word
				
		
		lines.close();	//Close the stream
		return counts;
	}

	//Part 5 (DONE)
	//The function returns a map of the average word count of each tweeter
	public static Map <String, Double> getTweeterAverageVerbosity(String FullPathname){
		
		Stream<String> lines = null;
		try {
			lines = getLines(FullPathname);	// a simple stream to accept all the lines inside the File
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Function<String[],String> getTweeterName = line->line[NAME_COL].toString(); //a simple lambda to obtain tweeter from fields
		Map <String,Double> counts = 
		
				lines.skip(1).parallel()	//Skip header and do in parallel
				.map(line -> line.split(","))	//Get all the fields
				.collect(Collectors.groupingBy(getTweeterName,	//Get by tweeter
						 Collectors.averagingDouble(line -> Arrays.stream(line[TEXT_COL].split("\\s+")).count())));	
						
						//Find the average of the word count (count of stream of words in a tweet)
					
		lines.close();	//Close the stream
		return counts;
		
	}
	
	//PART 6 (DONE)
	//returns a map of tweeters with respect to their taggees within their tweets and the counts of those taggees
	public static Map <String, Map <String, Long>> getTweeterTaggeeCount(String FullPathname){
		Stream<String> lines = null;
		try {
			lines = getLines(FullPathname);	// a simple stream to accept all the lines inside the File
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Function<String[],String> getTweeterName = line->line[NAME_COL].toString(); //a simple lambda to obtain tweeter from fields
		Function<String[],Stream<String>> getTaggee = line-> Arrays.stream(line[TEXT_COL].split("\\s+")).filter(x -> x.startsWith(Taggee)).distinct();	//another lambda to return the stream of all distinct taggees
		
		Map <String,Map <String, Long>> counts  = 
				lines.skip(1).parallel()		//Skip header and do in parallel
				.map(line -> line.split(","))	//Get all the fields
				.collect(Collectors.groupingBy(getTweeterName,Collectors.flatMapping(getTaggee, Collectors.groupingBy(Function.identity(),Collectors.counting()))));
				
		//a double collect, group by the tweeter name and then a flatmap of all taggees and their counts within each tweet of the user
		
		lines.close();	//Close the stream
		
		
	
		return counts;
	}
	
	public static void main(String[] args) {
		
	
	}
}
