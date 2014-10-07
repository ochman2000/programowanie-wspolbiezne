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

import javax.swing.JLabel;

/**
 * The {@code JLabeledText} class is a simple extension of a JLabel,
 * allowing the text to be split into two parts - a constant
 * prefix (label) and a modifiable suffix (text).
 */
public class JLabeledText extends JLabel {
    
    String label;
    
    /**
     * Constructs a {@code JLabelText} with the given label and empty text.
     * 
     * @param label the label
     */
    public JLabeledText(String label) {
        this(label, null);
    }
    
    /**
     * Constructs a {@code JLabelText} with the given label and text.
     * 
     * @param label the label
     * @param text the text
     */
    public JLabeledText(String label, String text) {
        this.label = label + " ";
        setText(text);
    }

    /**
     * Defines the text this component will display (following the
     * constant label). If the value of text is null or empty string,
     * only the label is displayed.
     * 
     * @param text the text to display
     */
    @Override
    public void setText(String text) {
        super.setText(label + (text != null ? text : ""));
    }
}
