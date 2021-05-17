import javax.sound.sampled.*;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Sounds {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }



}//End Class Sound

/**
 * Class Welcome
 * Imports audio file that signals the start of gameplay
 * Plays after user selects game mode and difficulty from menu
 * Audio: Rachel saying "Welcome to Boggle" with chime sound effects
 */
class Welcome {
    public Welcome()throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        //Import Audio file from file system
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("/Users/kellymak/Documents/java12/Boggle/welcome.wav"));
        Clip clip = AudioSystem.getClip();

        //Open and play audio
        clip.open(audio);
        clip.start();
    }
}//End Class Welcome

/**
 * Class Valid
 * Imports audio file that affirms a word's validity
 * Plays after user enters a valid word
 * Audio: Sarah saying "Valid, Congratulations" with clapping sound effects
 */
class Valid {
    public Valid()throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //Import Audio file from file system
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("/Users/kellymak/Documents/java12/Boggle/valid_word.wav"));
        Clip clip = AudioSystem.getClip();
        //Open and play audio
        clip.open(audio);
        clip.start();
    }
}//End audio Valid

/**
 * Class InvalidAudio
 * Imports audio file that affirms a word as invalid
 * Plays after user enters an invalid word
 * Audio: Kelly saying "Err err, Invalid"
 */
class InvalidAudio {
    public InvalidAudio()throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //Import Audio file from file system
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("/Users/kellymak/Documents/java12/Boggle/invalid.wav"));
        Clip clip = AudioSystem.getClip();
        //Open and play audio
        clip.open(audio);
        clip.start();
    }
}//End Class InvalidAudio

/**
 * Class endGame
 * Imports audio file that signals the end of a game
 * Connects to a timer that delays audio for 3s so it will play after the "Valid" sound at the end of a game
 */
class endGame
{
    public endGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        delaySound start = new delaySound();
        Timer timer = new Timer();
        timer.schedule(start, 3000);
    }
}//End Class Endgame

/**
 * Class delaySound
 * Timer class, connects to Class endGame to run the audio
 */
class delaySound extends TimerTask
{
    public void run()
    {
        try {
            //Call method playSound that will play the audio
            playSound();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * Method PlaySound
     * Imports audio file hat signals the end of a game
     * Plays after player or AI has won
     * Audio: Mortal Combat "Finished him"
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public void playSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("/Users/kellymak/Documents/java12/Boggle/finish_him.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
    }
}//End Timer Run

/**
 * Class Short
 * Imports audio file that informs player that their input word is too short
 * Plays after the player inputs a word that is below the set minimum length
 * Audio: Kelly saying the user's input word is too short
 */
class Short {
    public Short()throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //Import Audio File from File System
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("/Users/kellymak/Documents/java12/Boggle/too_short.wav"));
        Clip clip = AudioSystem.getClip();
        //Open and play audio
        clip.open(audio);
        clip.start();
    }
}//End Class Short

/**
 * Class EnterWord
 * Imports audio file that asks player to enter a word
 * Plays if a player enters "null"
 * Audio: Sarah saying that the user should enter a word
 */
class EnterWord {
    public EnterWord()throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //Import Audio File from File System
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("/Users/kellymak/Documents/java12/Boggle/please_enter.wav"));
        Clip clip = AudioSystem.getClip();
        //Open and play audio
        clip.open(audio);
        clip.start();
    }
}//End Class EnterWord

/**
 * Class found
 * Imports audio file that states a word has been repeated
 * Plays after user enters an already entered input
 * Audio: Rachel saying word has already been found
 */
class FoundWord {
    public FoundWord()throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //Import Audio File from File System
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("/Users/kellymak/Documents/java12/Boggle/duplicated_word.wav"));
        Clip clip = AudioSystem.getClip();
        //Open and play audio
        clip.open(audio);
        clip.start();
    }
}//End Class Found
