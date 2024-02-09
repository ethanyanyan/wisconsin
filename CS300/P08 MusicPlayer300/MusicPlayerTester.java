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

/**
 * This class checks the correctness of the an implementation of cs300 Fall 2022 p08 MusicPlayer300
 * 
 * @author Ethan Yikai Yan
 */
public class MusicPlayerTester {
    /**
     * This method test and make use of the constructor with an invalid file, like one that doesn’t
     * exist or one of the provided .txt files (this should throw an IllegalArgumentException)
     * It also tests a valid file with toString() and getTitle() and getArtist() accessor methods
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testSongConstructor() {
        try {
            Song test1 = new Song(null, null, "audio/87.mid");
        }
        catch (IllegalArgumentException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            Song test2 = new Song(null, null, "midi_playlist.txt");
        }
        catch (IllegalArgumentException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            Song test3 = new Song(null, null, "audio/1.mid");
            if (!test3.getArtist().equals("")) {
                return false;
            }
            if (!test3.getTitle().equals("")) {
                return false;
            }

            Song test4 = new Song("Christmas", "Ethan", "audio/1.mid");
            if (!test4.getArtist().equals("Ethan")) {
                return false;
            }
            if (!test4.getTitle().equals("Christmas")) {
                return false;
            }
            if (!test4.toString().equals("\"Christmas\" (0:6) by Ethan")) {
                System.out.println("DEBUG");
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method test the other methods in Song that have to do with playback, making the audio 
     * file actually play or stop playing, and testing whether it is currently running. 
     */
    public static boolean testSongPlayback() {
        Song test1 = new Song("Christmas", "Ethan", "audio/toto-africa.mid");
        try {
            if (test1.isPlaying()) {
                return false;
            }
            test1.play();
            if (!test1.isPlaying()) {
                return false;
            }
            // To listen to song start
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test1.stop();
            if (test1.isPlaying()) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method test and make use of the SongNode constructor, an accessor (getter) method, 
     * and a mutator (setter) method defined in the LinkedNode class.
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testSongNode() {
        Song song1 = new Song("Christmas", "Ethan", "audio/toto-africa.mid");
        Song song2 = new Song("Halloween", "Chloe", "audio/2.mid");
        Song song3 = new Song("New Year", "Lucas", "audio/1.mid");

        // Test constructor 1
        try {
            SongNode testNode1 = new SongNode(null);
        }
        catch (IllegalArgumentException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // Test constructor 2
        SongNode node1 = new SongNode(song1); // node1
        try {
            SongNode testNode2 = new SongNode(null, node1);
        }
        catch (IllegalArgumentException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        SongNode node2 = new SongNode(song2); // node2
        SongNode node3 = new SongNode(song3, node2); // node3 -> node2 
        try {
            if (!node3.getNext().getSong().equals(song2)) {
                return false;
            }
            node1.setNext(node3); // node1 -> node3 -> node2
            if (!node1.getNext().getSong().equals(song3)) {
                System.out.println("DEBUG");
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method test and make use of the Playlist class, the constructor of the Playlist class, 
     * enqueue method and the accesors of the class.
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testEnqueue() {
        Song song1 = new Song("Christmas", "Ethan", "audio/toto-africa.mid");
        Song song2 = new Song("Halloween", "Chloe", "audio/2.mid");
        Song song3 = new Song("New Year", "Lucas", "audio/1.mid");
        Playlist tester = new Playlist();
        try {
            if (!tester.isEmpty()) {
                return false;
            }
            if (tester.peek() != null) {
                return false;
            }
            if (tester.size() != 0) {
                return false;
            }

            tester.enqueue(song1);
            if (tester.isEmpty()) {
                return false;
            }
            if (!tester.peek().equals(song1)) {
                return false;
            }
            if (tester.size() != 1) {
                return false;
            }

            tester.enqueue(song2);
            tester.enqueue(song3);
            if (!tester.peek().equals(song1)) {
                return false;
            }
            if (tester.size() != 3) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * This method test and make use of the Playlist class, the constructor of the Playlist class, 
     * dequeue method and the accesors of the class.
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testDequeue() {
        Song song1 = new Song("Christmas", "Ethan", "audio/toto-africa.mid");
        Song song2 = new Song("Halloween", "Chloe", "audio/2.mid");
        Song song3 = new Song("New Year", "Lucas", "audio/1.mid");
        Playlist tester = new Playlist();
        try {
            tester.enqueue(song1);
            tester.enqueue(song2);
            tester.enqueue(song3);

            if (!tester.peek().equals(song1)) {
                return false;
            }
            if (tester.size() != 3) {
                return false;
            }
            Song removedSong = tester.dequeue();
            if (!removedSong.equals(song1)) {
                return false;
            }
            if (tester.size() != 2) {
                return false;
            }
            if (!tester.dequeue().equals(song2)) {
                return false;
            }
            if (!tester.dequeue().equals(song3)) {
                return false;
            }
            if (tester.size() != 0) {
                return false;
            }
            if (tester.dequeue() != null) {
                return false;
            }
            if (tester.size() != 0) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println("testSongConstructor: " + testSongConstructor());
        System.out.println("testSongPlayback: " + testSongPlayback());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("testSongNode: " + testSongNode());
        System.out.println("testEnqueue: " + testEnqueue());
        System.out.println("testDequeue: " + testDequeue());
    }
}
