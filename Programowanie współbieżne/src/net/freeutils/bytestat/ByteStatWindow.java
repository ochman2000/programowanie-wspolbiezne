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

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.swing.*;

/**
 * The <b>ByteStatWindow</b> class is the main application GUI Window.
 *
 * @author  Amichai Rothman
 * @since   2009-01-08
 */
public class ByteStatWindow extends JFrame
        implements ActionListener, MouseMotionListener {
    
    enum State { NONE, PROCESSING, DISPLAYING }
    
    /**
     * A {@code TransferHandler} which handles dragged and dropped files.
     */
    private TransferHandler transferHandler = new TransferHandler() {
        
        @Override
        public boolean canImport(TransferSupport ts) {
            return ts.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean importData(TransferSupport ts) {
            if (!canImport(ts))
                return false;
            try {
                java.util.List<File> list =
                    (java.util.List<File>)ts.getTransferable()
                    .getTransferData(DataFlavor.javaFileListFlavor);
                if (!list.isEmpty())
                    processFile(list.get(0).getAbsolutePath());
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    };
    
    Statistics stats;
    State state;
    
    JLabel jlFilename;
    JButton jbBrowse;
    JButton jbRefresh;
    JBarGraph jbgGraph;
    JFileChooser chooser;
    
    JLabeledText jltCharDec;
    JLabeledText jltCharHex;
    JLabeledText jltCharGlyph;
    JLabeledText jltCharCount;
    JLabeledText jltCharPercent;
    JLabeledText jltFileSize;
    
    /**
     * Constructs a ByteStatWindow, with statistics handled via the
     * given Statistics instance.
     * 
     * @param stats the Statistics used by this window
     */
    public ByteStatWindow(Statistics stats) {
        this.stats = stats;
        init();
    }
    
    /**
     * Initializes the GUI.
     */
    private void init() {
        setTitle("ByteStat");
        setResizable(false);
        setTransferHandler(transferHandler);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // loads the application icon
        URL url = ClassLoader.getSystemResource("resources/bytestat.png");
        if (url != null)
            setIconImage(new ImageIcon(url).getImage());
        setLayout(new GridBagLayout());
        getContentPane().setPreferredSize(new Dimension(660,382));
        chooser = new JFileChooser();
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets.bottom = 10; 
        
        c.gridx = 0; c.gridy = 0; c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        add(new JLabel(
            "ByteStat \u00a9 copyright 1998-2009 by Amichai Rothman"), c);
        
        c.gridx = 0; c.gridy = 1; c.gridwidth = 4;
        jlFilename = new JLabeledText("File: ", null);
        jlFilename.setPreferredSize(new Dimension(400, 16));
        add(jlFilename, c);
        
        c.gridx = 4; c.gridy = 1; c.gridwidth = 1;
        jbBrowse = new JButton("Browse...");
        jbBrowse.addActionListener(this);
        add(jbBrowse, c);
        
        c.gridx = 5; c.gridy = 1; c.gridwidth = 1;
        jbRefresh = new JButton("Refresh");
        jbRefresh.addActionListener(this);
        add(jbRefresh, c);
        
        c.gridx = 0; c.gridy = 2; c.gridwidth = GridBagConstraints.REMAINDER;
        jbgGraph = new JBarGraph();
        jbgGraph.setPreferredSize(new Dimension(624, 286));
        jbgGraph.setBackground(Color.WHITE);
        jbgGraph.addMouseMotionListener(this);
        add(jbgGraph, c);
        
        c.gridx = 0; c.gridy = 3; c.gridwidth = 1;
        c.insets.right = 10; c.weightx = 1.0 / 6;
        jltCharDec = new JLabeledText("Dec:");
        jltCharDec.setPreferredSize(new Dimension(55, 16));
        add(jltCharDec, c);
        
        c.gridx++;
        jltCharHex = new JLabeledText("Hex:");
        jltCharHex.setPreferredSize(new Dimension(55, 16));
        add(jltCharHex, c);
        
        c.gridx++;
        jltCharGlyph = new JLabeledText("Glyph:");
        jltCharGlyph.setPreferredSize(new Dimension(60, 16));
        add(jltCharGlyph, c);
        
        c.gridx++;
        jltCharCount = new JLabeledText("Count:");
        jltCharCount.setPreferredSize(new Dimension(100, 16));
        add(jltCharCount, c);
        
        c.gridx++;
        jltCharPercent = new JLabeledText("");
        jltCharPercent.setPreferredSize(new Dimension(70, 16));
        add(jltCharPercent, c);
        
        c.gridx++;
        jltFileSize = new JLabeledText("File size:");
        jltFileSize.setPreferredSize(new Dimension(140, 16));
        add(jltFileSize, c);
        
        pack();
        setState(State.NONE);
        setVisible(true);
        processFile(stats.getFilename());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbBrowse) {
            browseFilename();
        } else if (e.getSource() == jbRefresh) {
            processFile(stats.getFilename());
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        if (state == State.DISPLAYING)
            showCharInfo(jbgGraph.getDataIndex(e.getX()));
    }
    
    /**
     * Sets the application state.
     * 
     * @param state the application state
     */
    private void setState(State state) {
        this.state = state;
        boolean browse = false;
        boolean refresh = false;
        switch (state) {
            case NONE:
                browse = true;
                refresh = false;
                jbgGraph.setText("No file selected", Color.GRAY);
                break;
            case DISPLAYING:
                browse = refresh = true;
                jbgGraph.setText(null, null);
                jbgGraph.setData(
                    stats.getHistogram(), stats.getMaxCount());
                break;
            case PROCESSING:
                browse = refresh = false;
                jbgGraph.setText("Processing file...", Color.BLACK);
                break;
        }
        jbBrowse.setEnabled(browse);
        jbRefresh.setEnabled(refresh);
        jltCharDec.setText(null);
        jltCharHex.setText(null);
        jltCharGlyph.setText(null);
        jltCharCount.setText(null);
        jltCharPercent.setText(null);
        repaint();
    }

    /**
     * Shows an open file browse dialog and updates the selected file.
     */
    private void browseFilename() {
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION)
           processFile(chooser.getSelectedFile().getAbsolutePath());
    }
    
    /**
     * Processes the given file, calculating its statistics.
     * Note: this operation can take a while for large files.
     * 
     * @param filename the file to update
     */
    private void processFile(final String filename) {
        if (filename.isEmpty())
            return;
        final File f = new File(filename); 
        if (!f.exists()) {
            error("error opening file " + filename);
            return;
        }
        setState(State.PROCESSING);
        new SwingWorker<Object, Void>() {

            @Override
           public Object doInBackground() throws IOException {
               stats.setFilename(filename);
               stats.analyze();
               return null;
           }

            @Override
           protected void done() {
                try {
                    get(); // throws exception if one was raised
                    jlFilename.setText(filename);
                    chooser.setCurrentDirectory(f.getParentFile());
                    jltFileSize.setText(Long.toString(f.length()));
                } catch (ExecutionException ee) {
                    error("error analyzing: " + ee.getCause());
                } catch (InterruptedException ignore) {}
                setState(State.DISPLAYING);
           }
       }.execute();
    }
    
    /**
     * Shows information and statistics for the given character.
     * 
     * @param ch the character (byte value)
     */
    private void showCharInfo(int ch) { 
        // calculate values
        long count = stats.getHistogram()[ch];
        long fileLength = stats.getFileLength();
        double percent = fileLength == 0 ? 0 : 100d * count / fileLength;
        // display glyph using system charset
        String text = new String(new byte[] { (byte)ch });
        // update display
        jltCharDec.setText(Integer.toString(ch));
        jltCharHex.setText(Integer.toString(ch, 16).toUpperCase());
        jltCharGlyph.setText(text);
        jltCharCount.setText(Long.toString(count));
        jltCharPercent.setText(String.format("(%05.2f%%)", percent));
    }

    /**
     * Shows an error message box.
     * 
     * @param message the message to display
     */
    private void error(String message) {
        JOptionPane.showMessageDialog(this, message,
            "Error", JOptionPane.ERROR_MESSAGE);
    }

}
