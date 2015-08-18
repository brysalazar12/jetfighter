/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.stream.FileCacheImageInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class MakeSound {

    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
	private static MakeSound instance;
	private static String fileName;

	public static void play(String fileName)
	{
		MakeSound.fileName = fileName;
		if(MakeSound.instance == null)
			MakeSound.instance = new MakeSound();

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				MakeSound.instance.playSound(MakeSound.fileName);
			}
		});
		t.start();
	}

    /**
     * @param filename the name of the file that is going to be played
     */
    public void playSound(String filename){

		URL strFilename = this.getClass().getClassLoader().getResource(filename);
//		InputStream in = this.getClass().getResourceAsStream(filename);
//		in.
//        try {
//			System.out.println(strFilename + " ============");
////            soundFile = new File(strFilename);
//			soundFile = new File(getClass().getClassLoader().getResource(filename).getFile());
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }

        try {
//			AudioSystem.getAudioInputStream(null)
            audioStream = AudioSystem.getAudioInputStream(strFilename);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }
}