/*************************************************
 File: P3Playlist
 By: Allie Young
 Date: 11/19/24
 Description: takes user input to create a movie playlist and simulates
 "progression" through the playlist using user inputted timestamps. allows
 the user control over the position they want to add each movie to.
 slightly different from sample runs -- i changed some of the prompt text
 and added spaces after certain lines so it would be easier to read.
 *************************************************/

package P3MoviePlaylist;
import java.util.Scanner;

public class P3Playlist {
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);

        // initialize movie database; don't change next 2 lines
        Movie[] movieDb = new Movie[10];
        initMovieDb(movieDb);

        // Step 1: create playlist, a linked list
        LList<Movie> playlist = new LList<>();

        // Step 2: prompt user to enter positions and IDs of movies to add to playlist
        int position;
        while(true) {
            // position prompt w validity check
            System.out.print("Enter a position in the playlist where you want to add a movie. Type 0 or a negative number to exit: ");
            position = in.nextInt();
            if(position < 1 || position > (playlist.getLength() + 1)) { break; }

            // ID prompt w validity check, adding to and adjusting playlist
            System.out.print("Enter the ID of the movie you want to add: ");
            int id = in.nextInt();
            if(id < 0 || id > 9) {
                System.out.println("Invalid ID! Please try again.");
                continue;
            }
            Movie m = new Movie(movieDb[id].getTitle(), movieDb[id].getDuration());
            playlist.add(position, m);
            adjustStartTimes(playlist);

            // Step 3: display the playlist
            System.out.println("Playlist:");
            playlist.display();
            System.out.println();
        }

        // Step 4: removing an entry
        System.out.println("All done adding!\n");
        while(true) {
            try {
                System.out.print("Enter a position in the playlist to remove a movie. Type an invalid position to exit: ");
                playlist.remove(in.nextInt());
                adjustStartTimes(playlist);
                System.out.println("New playlist:");
                playlist.display();
                System.out.println();
            } catch(IndexOutOfBoundsException e) {
                break;
            }
        }

        // Step 5: start current timestamp = 0
        System.out.println("All done removing!\n\nFinal playlist:");
        playlist.display();
        System.out.println();
        int timestamp;
        System.out.println("Grab your popcorn, the movies are starting! Timestamp: 0 minutes");

        // Step 6: prompt user to enter next timestamp
        outer: while(!playlist.isEmpty()) {
            System.out.print("Enter next timestamp in minutes: ");
            timestamp = in.nextInt();

            // Step 6, 7: for each timestamp entered, process playlist
            // dequeue all movies from playlist that are done playing
            // if current movie not done, report movie in progress, get next timestamp
            // if no more movies left, playlist done, exit
            while(timestamp >= (playlist.getEntry(1).getStartTime() + playlist.getEntry(1).getDuration())) {
                System.out.println(playlist.remove(1).getTitle() + " done");
                if(playlist.isEmpty()) {
                    break outer;
                }
            }
            System.out.println(playlist.getEntry(1).getTitle() + " in progress\n");
        }
        System.out.print("\nPlaylist empty.\nAll done!");
    }

    // helper method to avoid rewriting code
    public static void adjustStartTimes(LList<Movie> list) {
        int time = 0;
        list.getEntry(1).setStartTime(0);
        for(int i = 2; i <= list.getLength(); i++) {
            time += list.getEntry(i-1).getDuration();
            list.getEntry(i).setStartTime(time);
        }
    }

    // method to initialize movie database list; don't change this!
    public static void initMovieDb(Movie[] ml) {
        ml[0] = new Movie("Get Out", 100);
        ml[1] = new Movie("Paprika", 115);
        ml[2] = new Movie("The Lost Boys", 85);
        ml[3] = new Movie("Blood Tea and Red String", 80);
        ml[4] = new Movie("Candyman", 105);
        ml[5] = new Movie("WALL-E", 120);
        ml[6] = new Movie("The Shining", 125);
        ml[7] = new Movie("Ghost in the Shell", 95);
        ml[8] = new Movie("Hellraiser", 90);
        ml[9] = new Movie("King Kong", 110);
        System.out.println("Movies in database:");
        for (int i=0; i<ml.length; i++) {
            System.out.println(i + ": " + ml[i].getTitle() + " | " + ml[i].getDuration() + " minutes");
        }
        System.out.println();
    }
}
