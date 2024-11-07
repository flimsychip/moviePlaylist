package P2MoviePlaylist;
import java.util.Scanner;

public class PlayList {
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);

        // initialize movie database; don't change next 2 lines
        Movie [] movieDb = new Movie[10];
        initMovieDb(movieDb);

        // Step 1: create playlist, a queue
        LinkedMovieQueue playlist = new LinkedMovieQueue();

        // Step 2: prompt user to enter IDs of movies to add to playlist
        // ID < 0 ends input
        System.out.print("Enter a string of IDs from the database to add a movie to the playlist. Type a negative number to end your input: ");
        int time = 0;
        int id = in.nextInt();
        while(id >= 0) {
            Movie m = new Movie(movieDb[id].getTitle(), movieDb[id].getDuration(), time);
            playlist.enqueue(m);
            time += m.getDuration();
            id = in.nextInt();
        }

        // Step 3: display the playlist
        System.out.println("Playlist:");
        playlist.display();
        System.out.println();

        // extra credit: removing an entry
        in.nextLine();
        System.out.print("Enter a movie title to remove it from the playlist: ");
        if(playlist.remove(in.nextLine())) {
            System.out.println("New playlist:");
        } else {
            System.out.println("Invalid entry.\nNothing removed from playlist:");
        }
        playlist.display();
        System.out.println();

        // Step 4: start current timestamp = 0
        int timestamp;
        System.out.println("Grab your popcorn, the movies are starting! Timestamp: 0 minutes");

        // Step 5: prompt user to enter next timestamp
        // if next timestamp <= current timestamp, end program
        outer: while(!playlist.isEmpty()) {
            System.out.print("Enter next timestamp in minutes: ");
            timestamp = in.nextInt();

        // Step 6, 7: for each timestamp entered, process playlist
        // dequeue all movies from playlist that are done playing
        // if current movie not done,
        //      report movie in progress, get next timestamp
        // if no more movies left, playlist done, exit
            while(timestamp >= (playlist.getFront().getStartTime() + playlist.getFront().getDuration())) {
                System.out.println(playlist.dequeue().getTitle() + " done");
                if(playlist.isEmpty()) {
                    break outer;
                }
            }
            System.out.println(playlist.getFront().getTitle() + " in progress\n");
        }
        System.out.print("\nPlaylist empty.\nAll done!");
    }

    // method to initialize movie database list; don't change this!
    public static void initMovieDb(Movie [] ml) {
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
            System.out.println(i + ": " + ml[i].getTitle() +
                    " | " + ml[i].getDuration() + " minutes");
        }
        System.out.println();
    }
}
