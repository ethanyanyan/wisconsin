//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Music Player 300
// Course:   CS 300 Fall 2022
//
// Author:   Ethan Yikai Yan
// Email:    eyyan@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    NONE
// Partner Email:   NONE
// Partner Lecturer's Name: NONE
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A linked-queue based music player which plays Actual Music Files based on keyboard input in 
 * an interactive console method. This music player can load playlists of music or add individual 
 * song files to the queue.
 */
public class MusicPlayer300 {
    private Playlist playlist; // The current playlist of Songs
    private boolean filterPlay; // Whether the current playback mode should be filtered by artist; 
                                // false by default
    private String filterArtist; // The artist to play if filterPlay is true; should be null 
                                 // otherwise

    /**
     * Creates a new MusicPlayer300 with an empty playlist
     */
    public MusicPlayer300() {
        this.playlist = new Playlist();
        this.filterArtist = null;
        this.filterPlay = false;
    }

    /**
     * Stops any song that is playing and clears out the playlist
     */
    public void clear() {
        if (playlist.peek().isPlaying()) {
            playlist.peek().stop();
        }
        while (!playlist.isEmpty()) {
            playlist.dequeue();
        }
    }

    /**
     * Loads a playlist from a provided file, skipping any individual songs which cannot be loaded. 
     * Note that filenames in the provided files do NOT include the audio directory, and will need 
     * that added before they are loaded. Print "Loading" and the song's title in quotes before you 
     * begin loading a song, and an "X" if the load was unsuccessful for any reason.
     * @param file the File object to load
     * @throws FileNotFoundException if the playlist file cannot be loaded
     */
    public void loadPlaylist(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        String[] songArray = new String[10];
        Song insertSong;
        while (input.hasNextLine()) {
            songArray = input.nextLine().split(",");
            if (songArray.length != 3) {
                System.out.println("X");

            }
            if (songArray.length == 3) {
                try {
                    String audioDirectory = "audio/";
                    songArray[2] = audioDirectory.concat(songArray[2]);
                    insertSong = new Song(songArray[0], songArray[1], songArray[2]);
                    System.out.println("Loading " + "\"" + insertSong.getTitle() + "\"");
                    playlist.enqueue(insertSong);
                }
                catch (IllegalArgumentException e) {
                    System.out.println("X");
                }
            }
        }
    }

    /**
     * Loads a single song to the end of the playlist given the title, artist, and filepath. 
     * Filepaths for P08 must refer to files in the audio directory.
     * @param title the title of the song
     * @param artist the artist of this song
     * @param filepath the full relative path to the song file, begins with the "audio" directory 
     *                 for P08
     * @throws IllegalArgumentException if the song file cannot be read
     */
    public void loadOneSong(String title, String artist, String filepath) {
        Song insertSong = new Song(title, artist, filepath);
        this.playlist.enqueue(insertSong);
    }

    /**
     * Provides a string representation of all songs in the current playlist
     * @return a string representation of all songs in the current playlist
     */
    public String printPlaylist() {
        return this.playlist.toString();
    }

    /**
     * Creates and returns the menu of options for the interactive console program.
     * @return the formatted menu String
     */
    public String getMenu() {
        String returnString = "";
        returnString = "[A <filename>] to enqueue a new song file to the end of this playlist\n" +
                "[F <filename>] to load a new playlist from the given file\n" +
                "[L] to list all songs in the current playlist\n" +
                "[P] to start playing ALL songs in the playlist from the beginning\n" +
                "[P -t <Title>] to play all songs in the playlist starting from <Title>\n" +
                "[P -a <Artist>] to start playing only the songs in the playlist by Artist\n" +
                "[N] to play the next song\n[Q] to stop playing music and quit the program";
        return returnString;
    }

    /**
     * Stops playback of the current song (if one is playing) and advances to the next song in the 
     * playlist.
     * @throws IllegalStateException if the playlist is null or empty, or becomes empty at any time 
     *                               during this method
     */
    public void playNextSong() throws IllegalStateException {
        if (playlist.isEmpty() || playlist == null) {
            throw new IllegalStateException();
        }
        if (playlist.peek().isPlaying()) {
            playlist.peek().stop();
        }
        if (this.filterPlay) {
            playlist.dequeue();
            if (playlist.isEmpty()) {
                throw new IllegalStateException();
            }
            while (playlist.size() != 0) {
                if (playlist.peek().getArtist().equals(this.filterArtist)) {
                    break;
                } else {
                    playlist.dequeue();
                    if (playlist.isEmpty()) {
                        throw new IllegalStateException();
                    }
                }
            }
        }
        if (!this.filterPlay) {
            playlist.dequeue();
            if (playlist.isEmpty()) {
                throw new IllegalStateException();
            }
        }
        playlist.peek().play();
    }

    /**
     * Method to run the musicPlayer300. This is the method that begins the interactive menu loop 
     * and processes user input. Its only argument is a Scanner hooked up to System.in. This method 
     * should loop continuously, and do the following, using other methods from MusicPlayer300 
     * wherever possible: Display the menu,
     * Prompt the user for input (use a "> ")
     * Save the next line of input and parse out the option the user has selected:
     * A – add a song to the end of the playlist. You will need to further prompt the user for the
     *      title and artist of this song, and then add the new song to the playlist.
     * F – load in a new playlist from the given file.
     * L – display all the songs remaining in the current playlist.
     * P – begin playing the songs in the playlist, but first! Check to see if there was a modifier.
     *      -t – begin playback at the song with the given title.
     *      -a – play only the songs by the given artist (hint: use the filterPlay and 
     *           filterArtist data fields for assistance here)
     * N – stop the current song and move to the next song in the playlist. Songs should only be 
     *     dequeued from the playlist when you’re ready to move to the next song; otherwise, you
     *     won’t be able to stop their audio!
     * Q – clear out the queue and end the method. Print a "Goodbye!" message.
     * Anything else – print out "I don't know how to do that." and go back to the beginning.
     */
    public void runMusicPlayer300(Scanner in) {
        System.out.println(getMenu());
        System.out.print(">");
        String input =  in.nextLine();
        String[] details = new String[5];
        for (int i = 0; i < details.length; i ++) {
            details[i] = null;
        }
        details = input.split(" ");
        // [A <filename>] to enqueue a new song file to the end of this playlist
        if (details[0].equals("A") && details.length == 2) {
            System.out.print("Title: ");
            String inputTitle =  in.nextLine();
            System.out.print("Artist: ");
            String inputArtist = in.nextLine();
            try {
                playlist.enqueue(new Song(inputTitle, inputArtist, details[1]));
            }
            catch (IllegalArgumentException e) {
                System.out.println("Unable to load that song");
            }
        }
        // [F <filename>] to load a new playlist from the given file
        if (details[0].equals("F") && details.length == 2) {
            try {
                loadPlaylist(new File(details[1]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        // [L] to list all songs in the current playlist
        if (details[0].equals("L") && details.length == 1) {
            System.out.println(this.playlist.toString());
        }
        // [P] to start playing ALL songs in the playlist from the beginning
        if (details[0].equals("P")) {
            if (details.length > 1) {
                // [P -t <Title>] to play all songs in the playlist starting from <Title>
                if (details[1].equals("-t") && details.length == 3) {
                    while (!playlist.peek().getTitle().equals(details[2])) {
                        playlist.dequeue();
                    }
                    if (playlist.peek().getTitle().equals(details[2])) {
                        playlist.peek().play();

                    }
                }
                // [P -a <Artist>] to start playing only the songs in the playlist by Artist
                if (details[1].equals("-a") && details.length == 3) {
                    this.filterPlay = true;
                    this.filterArtist = details[2];
                    while (playlist.size() != 0 && this.filterPlay) {
                        if (playlist.peek().getArtist().equals(this.filterArtist)) {
                            playlist.peek().play();
                            break;
                        } else {
                            playlist.dequeue();
                        }
                    }
                }
            }
            if (details.length == 1) {
                if (this.playlist.isEmpty()) {
                    System.out.println("No more songs left ):");
                }
                if (!this.playlist.isEmpty()) {
                    playlist.peek().play();
                }
            }
        }
        // [N] to play the next song
        if (details[0].equals("N") && details.length == 1) {
            try {
                playNextSong();
            }
            catch (IllegalStateException e) {
                System.out.println("No songs left ):");
            }
            if (!this.playlist.isEmpty()) {
                if (!this.playlist.peek().isPlaying()) {
                    this.playlist.peek().play();
                }
            }

        }
        // [Q] to stop playing music and quit the program
        if (details[0].equals("Q") && details.length == 1) {
            if (playlist.peek() == null) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            if (playlist.peek().isPlaying()) {
                playlist.peek().stop();
            }
            clear();
            System.out.println("Goodbye!");
            System.exit(0);
        }
        if (!details[0].equals("Q") && !details[0].equals("N") && 
                !details[0].equals("P") && !details[0].equals("L") && 
                !details[0].equals("F") && !details[0].equals("A")) {
            System.out.println("I don't know how to do that.");
        }
    }

    public static void main(String[] args) {
        MusicPlayer300 tester = new MusicPlayer300();
        while (true) {
            tester.runMusicPlayer300(new Scanner(System.in));
        }
    }
}
