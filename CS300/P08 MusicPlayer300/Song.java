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
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is a representation of a single Song. Interfaces with the provided AudioUtility 
 * class, which uses the javax.sound.sampled package to play audio to your computer's audio 
 * output device
 * 
 * @author Ethan Yikai Yan
 */
public class Song {
    private String title; // The title of this song
    private String artist; // The artist of this song
    private int duration; // The duration of this song in number of seconds
    private AudioUtility audioClip; // This song's AudioUtility interface to javax.sound.sampled

    /**
     * Initializes all instance data fields according to the provided values
     * @param title    the title of the song, set to empty string if null
     * @param artist   the artist of this song, set to empty string if null
     * @param filepath the full relative path to the song file, begins with the "audio" directory 
     *                 for P08
     * @throws IllegalArgumentException if the song file cannot be read
     */
    public Song(String title, String artist, String filepath) throws IllegalArgumentException {
        /*Scanner input = null;
        try {
        input = new Scanner(new File(filepath));
        }
        catch(FileNotFoundException e) {
            System.out.println("DEBUG1");
            throw new IllegalArgumentException();
        }

        if (!filepath.substring(0, 5).equals("audio") || 
                !filepath.substring(filepath.length()-3, filepath.length()).equals("mid") ||
                filepath == null) {
            System.out.println("DEBUG2");
            throw new IllegalArgumentException();
        }*/

        try {
            audioClip = new AudioUtility(filepath);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        this.duration = this.audioClip.getClipLength();
        if (title == null) {
            this.title = "";
        } else {
            this.title = title;
        }
        if (artist == null) {
            this.artist = "";
        } else {
            this.artist = artist;
        }
    }

    /**
     * Tests whether this song is currently playing using the AudioUtility
     * @return true if the song is playing, false otherwise
     */
    public boolean isPlaying() {
        if (!audioClip.isRunning()) {
            return false;
        }
        return true;
    }

    /**
     * Accessor method for the song's title
     * @return the title of this song
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Accessor method for the song's artist
     * @return the artist of this song
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * Uses the AudioUtility to start playback of this song, reopening the clip for 
     * playback if necessary
     */
    public void play() {
        if (audioClip.isRunning()) {
            audioClip.stopClip();
            audioClip.reopenClip();
        }
        audioClip.startClip();
        System.out.println("Playing..." + this.toString());
    }

    /**
     * Uses the AudioUtility to stop playback of this song
     */
    public void stop() {
        audioClip.stopClip();
    }

    /**
     * Creates and returns a string representation of this Song, for example:
     * "Africa" (4:16) by Toto
     * The title should be in quotes, the duration should be split out into minutes and seconds 
     * (recall it is stored as seconds only!), and the artist should be preceded by the word "by".
     * It is intended for this assignment to leave single-digit seconds represented as 0:6, 
     * for example, but if you would like to represent them as 0:06, this is also allowed.
     * @return a formatted string representation of this Song
     */
    @Override
    public String toString() {
        int seconds = this.duration % 60;
        int minutes = this.duration / 60;
        return "\"" + title + "\"" + " (" + minutes + ":" + seconds + ") by " + artist;
    }
}
