/*
 *  (c) copyright 1998-2009 Amichai Rothman
 *
 *  This file is part of the Java ByteStat utility.
 *
 *  The Java ByteStat utility is free software; you can redistribute
 *  it and/or modify it under the terms of the GNU General Public License as
 *  published by the Free Software Foundation; either version 2 of the License,
 *  or (at your option) any later version.
 *
 *  The Java ByteStat utility is distributed in the hope that it
 *  will be useful, but WITHOUT ANY WARRANTY; without even the implied
 *  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package net.freeutils.bytestat;

import java.io.*;

/**
 * The {@code Statistics} class encapsulates the file statistics.
 * 
 * @author  Amichai Rothman
 * @since   2009-01-08
 */
public class Statistics {
    
    protected static final int BUFFER_SIZE = 64 * 1024;

    protected String filename = ""; // init to empty name
    protected long fileLength;
    protected long[] histogram = new long[256]; // init to empty histogram
    protected long maxCount;
    
    /**
     * Returns the maximum value over the given array.
     * 
     * @param arr an array
     * @return the maximum value over the array
     */
    public static long max(long[] arr) {
        long max = Long.MIN_VALUE;
        for (long i : arr)
            if (i > max)
                max = i;
        return max;
    }
    
    /**
     * Sets the filename to analyze.
     * 
     * @param filename the filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
        
    /**
     * Returns the filename.
     * 
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }
    
    /**
     * Returns the file length.
     * 
     * @return the file length
     */
    public long getFileLength() {
        return fileLength;
    }
    
    /**
     * Returns the previously calculated byte histogram.
     * 
     * @return the previously calculated byte histogram.
     */
    public long[] getHistogram() {
        return histogram;
    }
    
    /**
     * Returns the maximum number of occurrences across all byte values,
     * i.e. the maximum of the histogram values.
     * 
     * @return the maximum count
     */
    public long getMaxCount() {
        return maxCount;
    }
    
    /**
     * Performs the analysis on the current file.
     * 
     * @throws IOException if an error occurs
     */
    public void analyze() throws IOException {
        fileLength = new File(filename).length();
        histogram = calculateHistogram();
        maxCount = max(histogram);
    }

    /**
     * Calculates the byte-histogram for the file.
     * 
     * @return the histogram
     * @throws IOException if an IO error occurs
     */
    private long[] calculateHistogram() throws IOException {
        long[] stats = new long[256];
        FileInputStream in = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int count;
        try {
            in = new FileInputStream(filename);
            while ((count = in.read(buf)) != -1)
                for (int i = 0; i < count; i++)
                    stats[buf[i] & 0xFF]++;
        } finally {
            if (in != null)
                in.close();
        }
        return stats;
    }

}
