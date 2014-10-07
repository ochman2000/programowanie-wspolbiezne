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

import javax.swing.*;

/**
 * The <b>ByteStat</b> class is the main entry point of the ByteStat utility.
 *
 * @author  Amichai Rothman
 * @since   2009-01-08
 */
public class ByteStat {
    
    /**
     * The main application entry point.
     * 
     * @param args the command line arguments
     * @throws Exception if an error occurs
     */
    public static void main(String[] args) throws Exception {
        final Statistics stats = new Statistics();
        if (args.length > 0)
            stats.setFilename(args[0]);
        // swing initialization code must occur in the event dispatcher thread
        // (see http://java.sun.com/docs/books/tutorial/uiswing/concurrency/)
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                // set the system native look and feel
                String lafClass = UIManager.getSystemLookAndFeelClassName();
                try {
                    UIManager.setLookAndFeel(lafClass);
                } catch (Exception ignore) {}
                // start the GUI
                new ByteStatWindow(stats);
            }
        });
    }
    
}
