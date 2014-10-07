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
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/**
 * The {@code JBarGraph} class is a GUI component which displays a bar graph.
 * 
 * @author  Amichai Rothman
 * @since   2009-01-08
 */
public class JBarGraph extends JPanel {
    
    protected long[] data;
    protected long scale;
    protected String text;
    protected Color color;
    
    /**
     * Constructs a JBarGraph.
     */
    public JBarGraph() {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }
    
    /**
     * Sets the graph's data.
     * 
     * @param data the graph data
     * @param scale the graph scale (maximum height value)
     */
    public void setData(long[] data, long scale) {
        this.data = data;
        this.scale = scale;
    }
    
    /**
     * Sets the text to display in place of the data.
     * If null, the data is displayed, otherwise the text is displayed instead.
     * 
     * @param text the text to display
     * @param color the color to display in
     */
    public void setText(String text, Color color) {
        this.text = text;
        this.color = color;
    }
    
    /**
     * Returns the index within the data of the bar in the given X coordinate.
     * 
     * @param x an X coordinate (relative to this component)
     * @return the index within the data of the bar in the given X coordinate
     */
    public int getDataIndex(int x) {
        return (int)(data.length * (double)x / getWidth());
    }
    
    @Override
    public void paint(Graphics g) {
        // clear graph area
        int w = getWidth();
        int h = getHeight();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        if (text != null)
            paintMessage(g, text, color);
        else if (data != null)
            paintBars(g);
    }
    
    /**
     * Paints a message in the middle of the graphics context.
     * 
     * @param g the Graphics context in which to paint
     * @param message the message to paint
     * @param color the color to paint in
     */
    private void paintMessage(Graphics g, String message, Color color) {
        int w = getWidth();
        int h = getHeight();
        Rectangle2D r = g.getFontMetrics().getStringBounds(message, g);
        g.setColor(color);
        g.drawString(message,
            (int)(w - r.getWidth()) / 2, (int)(h - r.getHeight()) / 2);
    }

    /**
     * Paints the bars.
     * 
     * @param g the Graphics context in which to paint
     */
    private void paintBars(Graphics g) {
        long[] data = this.data;
        if (data == null || scale <= 0)
            return; // nothing to draw
        // draw bar graph
        int w = getWidth();
        int h = getHeight();
        double vgap = (double)h / scale;
        for (int x = 0; x < w; x++) {
            long val = data[getDataIndex(x)];
            int bh = val == 0 ? 0 : (int)Math.max(vgap * val, 1);
            g.setColor(new Color(0, 0, (int)(255f * val / scale)));
            g.drawLine(x, h, x, h - bh);
        }
    }

}
