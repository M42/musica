import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

// A stereo sample made up with a left and a right sample. 
class Sample {
    public Short left;
    public Short right;
    public Sample(Short left, Short right) {
        this.left = left;
        this.right = right;
    }
}

// A wave is a list of samples.
// The wave class contains methods to write using the Wav and Riff formats.
class Wave {
    private ArrayList<Sample> audioSamples = new ArrayList<Sample>();
    private final int sampleRate = 44100;
    private final int numChannels = 2;
    private final int bytesize = 4;
    private final int headersize = 36;
    private final int bitsPerSample = 16;
    private final int byteRate = sampleRate * numChannels * bitsPerSample / 8;
    private final int blockAlign = bitsPerSample * numChannels / 8;
    private final int maxAmplitude = 32767;

    // The complete header of a wav file, using little endian.
    // Consisting of:
    private byte[] header() {
        // Writes an int as four bytes
        byte[] header = new byte[44];
        int samplesSize = audioSamples.size() * bytesize;
        int totalSize = (int) (headersize + samplesSize);

        // RIFF
        header[0] = 'R';
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        // Total size
        header[4] = (byte) ((totalSize >>  0) & 0xff);
        header[5] = (byte) ((totalSize >>  8) & 0xff);
        header[6] = (byte) ((totalSize >> 16) & 0xff);
        header[7] = (byte) ((totalSize >> 24) & 0xff);
        // WAVE
        header[8]  = 'W';
        header[9]  = 'A';
        header[10] = 'V';
        header[11] = 'E';
        // fmt
        header[12] = 'f';
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        // subchunk size
        header[16] = 16;
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        // PCM format
        header[20] = 1;
        header[21] = 0;
        // Number of channels
        header[22] = (byte) ((numChannels >> 0) & 0xff);
        header[23] = (byte) ((numChannels >> 8) & 0xff);
        // Sample rate
        header[24] = (byte) ((sampleRate >> 0) & 0xff);
        header[25] = (byte) ((sampleRate >> 8) & 0xff);
        header[26] = (byte) ((sampleRate >>16) & 0xff);
        header[27] = (byte) ((sampleRate >>24) & 0xff);
        // Byte rate
        header[28] = (byte) ((byteRate >> 0) & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >>16) & 0xff);
        header[31] = (byte) ((byteRate >>24) & 0xff);
        // Block align
        header[32] = (byte) ((blockAlign >> 0) & 0xff);
        header[33] = (byte) ((blockAlign >> 8) & 0xff);
        // Bit depth
        header[34] = (byte) ((bitsPerSample >> 0) & 0xff);
        header[35] = (byte) ((bitsPerSample >> 1) & 0xff);
        // Subchunk header
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        // Sunchunk size
        header[40] = (byte) ((samplesSize >> 0) & 0xff);
        header[41] = (byte) ((samplesSize >> 8) & 0xff);
        header[42] = (byte) ((samplesSize >>16) & 0xff);
        header[43] = (byte) ((samplesSize >>24) & 0xff);

        return header;
    }


    // Writes a sine wave
    // Frequency and amplitude are given
    public void generateSine(int samples, double amp, double freq) {
        short amplitude = (short) (maxAmplitude * Math.abs(amp));
        
        for (int k=0; k < samples; k++) {
            double candidate = Math.sin(2*Math.PI*k*freq*1.0/sampleRate);
            short aout = (short) (candidate * amplitude);
            Sample sample = new Sample(aout,aout);
            audioSamples.add(sample);
        }
    }


    // Creates a file writing there the RIFF header and writes after
    // all the byte samples using a file output stream.
    public boolean saveSamplesToDisk(String filename) {
        // Creates a new file if it does not exists yet
        File file = new File(filename);
        try {
            if (!file.exists()) file.createNewFile();
        } 
        catch (IOException exc) {
            return false;
        }

        // Writes the bytes on an array
        byte[] bytes = new byte[audioSamples.size() * 4];
        for (int i=0; i<audioSamples.size(); i++) {
            bytes[4*i+0] = (byte)((audioSamples.get(i).left  >> 0) & 0xff);
            bytes[4*i+1] = (byte)((audioSamples.get(i).left  >> 8) & 0xff);
            bytes[4*i+2] = (byte)((audioSamples.get(i).right >> 0) & 0xff);
            bytes[4*i+3] = (byte)((audioSamples.get(i).right >> 8) & 0xff);
        }

        // Writes the byte array on the file and the header
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(header());
            fos.write(bytes);
            fos.close();
        } 
        catch (IOException exc) {
            return false;
        }

        return true;
    }
}


public class Audio {
    public static void main(String[] args) {
        // RAW OUTPUT FORMAT.
        // * PCM 16bit-signed. 
        // * Stereo 2 Channels. 
        // * Little-endian.
        Wave wave = new Wave();
        wave.generateSine(44100, 0.3, 220);
        wave.saveSamplesToDisk("test.wav");
    }
}
