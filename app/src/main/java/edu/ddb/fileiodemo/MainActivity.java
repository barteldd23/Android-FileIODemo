package edu.ddb.fileiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String FILENAME = "data.txt";
    ArrayList<Actor> actors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createActors();
    }

    private void createActors()
    {
        actors = new ArrayList<Actor>();
        actors.add(new Actor(1,"Jennifer", "Aniston"));
        actors.add(new Actor(2,"Mathew", "Perry"));
        actors.add(new Actor(3,"Matt", "LeBlanc"));
        actors.add(new Actor(4,"Courtney", "Cox"));
        actors.add(new Actor(5,"David", "Schwimmer"));
        actors.add(new Actor(6,"Lisa", "Kudrow"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.action_readtext)
        {
            Log.d(TAG, "onOptionsItemSelected: " + item.getTitle());
            ReadTextFile();

        } else if (id == R.id.action_writetext){
            Log.d(TAG, "onOptionsItemSelected: " + item.getTitle());
            WriteTextFile();

        } else if (id == R.id.action_readxml){
            Log.d(TAG, "onOptionsItemSelected: " + item.getTitle());

        } else{
            Log.d(TAG, "onOptionsItemSelected: " + item.getTitle());

        }

        return super.onOptionsItemSelected(item);
    }

    private void WriteTextFile()
    {
        try {

            FileIO fileIO = new FileIO();
            int counter = 0;
            String[] data = new String[actors.size()];

            for(Actor actor : actors)
            {
                data[counter++] = actor.toString();
            }
            fileIO.writeFile(FILENAME, this, data);

        }
        catch(Exception e)
        {
            Log.d(TAG, "WriteTextFile: " + e.getMessage());
        }
    }

    private void ReadTextFile()
    {
        try {
            FileIO fileIO = new FileIO();
            ArrayList<String> strData = fileIO.readFile(FILENAME, this);

            actors = new ArrayList<Actor>();



            for(String s : strData)
            {
                String[] data = s.split("\\|");
                actors.add(new Actor(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2]));
                Log.d(TAG, "ReadTextFile: " + actors.get(actors.size()-1).getFirstName());
            }
            Log.d(TAG, "ReadTextFile: " + actors.size());

        }
        catch(Exception e){
            Log.d(TAG, "ReadTextFile: " + e.getMessage());
        }
    }

}