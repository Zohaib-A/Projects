package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

class TopScores
{
    String name;

    int score;

    public TopScores(String name, int score)
    {
        this.name = name;
        this.score = score;
    }
}

//nameCompare Class to compare the names

class nameCompare implements Comparator<TopScores>
{
    @Override
    public int compare(TopScores s1, TopScores s2)
    {
        return s1.name.compareTo(s2.name);
    }
}

//scoreCompare Class to compare the scores

class scoreCompare implements Comparator<TopScores>
{
    @Override
    public int compare(TopScores s1, TopScores s2)
    {
        return s2.score - s1.score;
    }
}

class SortTextFile
{
    public static void main(String[] args)throws IOException
    {
        //Creating BufferedReader object to read the input text file

        BufferedReader reader = new BufferedReader(new FileReader("quickest.txt"));

        //Creating ArrayList to hold high score objects

        ArrayList<TopScores> topScoresArrayList = new ArrayList<TopScores>();

        //Reading scores one by one

        String currentLine = reader.readLine();

        while (currentLine != null)
        {
            String[] scoreDetail = currentLine.split(",");

            String name = scoreDetail[0];

            int score = Integer.parseInt(scoreDetail[1]);

            //Creating topScores object for every player record and adding it to ArrayList

            topScoresArrayList.add(new TopScores(name, score));

            currentLine = reader.readLine();
        }

        //Sorting ArrayList topScores based on marks

        topScoresArrayList.sort(new scoreCompare());

        //Creating BufferedWriter object to write into output text file

        BufferedWriter writer = new BufferedWriter(new FileWriter("quickest.txt"));

        //Writing every topScores into output text file

        for (TopScores topScores : topScoresArrayList)
        {
            writer.write(topScores.name);

            writer.write(","+topScores.score);

            writer.newLine();
        }

        //Closing the resources

        reader.close();

        writer.close();
    }
}
