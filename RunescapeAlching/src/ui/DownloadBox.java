package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

import ui.View;

public class DownloadBox extends Box
{
	public static final boolean PAINT_STRING = false;
	public static final Color BAR_COLOR = Color.GREEN;
	
	public static final int STANDARD_STYLE = BoxLayout.Y_AXIS;
	
	public static final Color BORDER_COLOR = Color.GRAY;
	public static final int BORDER_THICKNESS = 1;
	public static final Border BORDER = BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS);
	
	public static final String FILE_TXT = "File: ";
	public static final String RESTART_TXT = "Restart";
	
	public static final String AC_CANCEL = "cancel";
	
	private String filename;
	private int downloadpercentage;
	private JProgressBar bar;
	private JButton restart;
	
	public DownloadBox(int orientation, String filename, int min, int max) 
	{
		super(orientation);
		this.filename = filename;
		downloadpercentage = 0;
		
		Box fileBox = Box.createHorizontalBox();
		
		fileBox.add(new JLabel(FILE_TXT));
		fileBox.add(new JLabel(filename));
		
		this.add(fileBox);
		
		bar = new JProgressBar(min, max);
		bar.setString(downloadpercentage +  "%");
		bar.setStringPainted(PAINT_STRING);
		bar.setForeground(BAR_COLOR);
		updateString();
		this.add(bar);
		
		restart = new JButton(RESTART_TXT);
		this.add(restart);
		
		this.setBorder(BORDER);
	}
	
	public DownloadBox(String filename, int min, int max)
	{
		this(STANDARD_STYLE, filename, min, max);
	}
	
	public DownloadBox(String filename)
	{
		this(STANDARD_STYLE, filename, 0, 100);
	}
	
	public void update(int downloadpercentage)
	{
		this.downloadpercentage = downloadpercentage;
		bar.setValue(downloadpercentage);
		updateString();
	}
	
	public JButton getRestart() {
		return restart;
	}
	
	
	private void updateString()
	{
		bar.setString(downloadpercentage + "%");
	}

}
