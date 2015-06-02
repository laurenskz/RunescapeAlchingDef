package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class View  
{
	
	public static final boolean VISIBLE_ON_START = true;
	public static final boolean FRAME_RESIZABLE = false;
	
	public static final String FRAME_TITLE = "Downloader";
	
	public static final String OPTIONS_TXT = "Options";
	public static final String OPTIONS_SETTINGS = "Settings";
	public static final String OPTIONS_CONNECT = "Connect";
	public static final String OPTIONS_DOWNLOADS = "Downloads";
	
	public static final int FRAME_WIDTH =  300;
	public static final int FRAME_HEIGHT = 300;
	
	public static final int LOCATION_X = 100;
	public static final int LOCATION_Y = 150;
	
	public static final int LIST_SELECTION_TYPE = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
	public static final int LIST_LAYOUT = JList.VERTICAL;
	
	public static final String SELECT_FILE_TXT = "Select file";
	public static final String SEND_FILE_TXT = "Send file";
	
	public static final String NO_FILE_SELECTED = "No file selected.";
	
	public static final String AC_SENDFILE = "sendfile";
	public static final String AC_SELECTFILE = "selectfile";
	
	public static final String AC_OPTIONS_CONNECT = "optionsconnect";
	public static final String AC_OPTIONS_SETTINGS = "optionssettings";
	public static final String AC_OPTIONS_DOWNLOADS = "optionsdownloads";

	private static JFrame frame = new JFrame(FRAME_TITLE);
	
	public static JLabel filelabel = new JLabel(NO_FILE_SELECTED, JLabel.CENTER);
	public static final String FILELABEL_FONT = "Terminal";
	public static final int FILELABEL_STYLE = Font.PLAIN;
	public static final int FILELABEL_SIZE = 12;
	public static final Color FILELABEL_COLOR = Color.BLACK;
	
	private static File selectedFile = null;
	
	public View()
	{
		
		createMenu();
		createList();
		createBottom();
		
		frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(FRAME_RESIZABLE);
		frame.setVisible(VISIBLE_ON_START);
		frame.setLocation(LOCATION_X, LOCATION_Y);
	}
	
	public static void main(String[] args)
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
		{
			e.printStackTrace();
		}
		
		View view = new View();
	}
	
	/**
	 * Creates the content list.
	 */
	private void createList()
	{
		// TODO dit verwijderen
		// tijdelijke lijstwaarden
		DefaultListModel listModel = new DefaultListModel();
		
		listModel.addElement("Hoi");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		listModel.addElement("doei");
		// einde tijdelijke waarden
		
		//listModel.addListDataListener(new ListSelectionListener());
		
		JList list = new JList(listModel);
		
		list.setSelectionMode(LIST_SELECTION_TYPE);
		list.setLayoutOrientation(LIST_LAYOUT);
		
		JScrollPane listScroller = new JScrollPane(list);
		// TODO aanpassen preferredSize zodat de onderste balk erbij kan.
		listScroller.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		
		frame.getContentPane().add(listScroller);
	}
	
	/**
	 * Innerclass for ListDataListener
	 * @author John
	 *
	 */
	/*class PersonalListener implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			// TODO Auto-generated method stub
			ListSelectionModel lsm = (ListSelectionModel) lse.getSource();
			
			int min = lsm.getMinSelectionIndex();
			int max = lsm.getMaxSelectionIndex();
			
			for (int i = min; i < max; i++)
			{
				if (lsm.isSelectedIndex(i))
				{
					lsm.
				}
			}
		}
		
	}*/
	
	/**
	 * Creates the bottom part of the view
	 */
	private void createBottom()
	{
		JPanel panel = new JPanel(new BorderLayout());
		
		// Selectfile button
		JButton selectfileButton = new JButton(SELECT_FILE_TXT);
		selectfileButton.setActionCommand(AC_SELECTFILE);
		panel.add(selectfileButton, BorderLayout.WEST);
		
		
		// Adding filelabel
		filelabel.setFont(new Font(FILELABEL_FONT, FILELABEL_STYLE, FILELABEL_SIZE));
		panel.add(filelabel, BorderLayout.CENTER);
		
		// Sendfile button
		JButton sendfileButton = new JButton(SEND_FILE_TXT);
		sendfileButton.setActionCommand(AC_SENDFILE);
		panel.add(sendfileButton, BorderLayout.EAST);
		
		
		frame.getContentPane().add(panel, BorderLayout.PAGE_END);
	}
	
	/**
	 * Creates the menu for the frame
	 */
	private void createMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		
		// Options menu
		JMenu optionsMenu = new JMenu(OPTIONS_TXT);
		
		// Settings
		JMenuItem settings = new JMenuItem(OPTIONS_SETTINGS);
		settings.setActionCommand(AC_OPTIONS_SETTINGS);;
		optionsMenu.add(settings);
		
		// Connect
		JMenuItem connect = new JMenuItem(OPTIONS_CONNECT);
		connect.setActionCommand(AC_OPTIONS_CONNECT);
		optionsMenu.add(connect);
		
		// Downloads
		JMenuItem downloads = new JMenuItem(OPTIONS_DOWNLOADS);
		downloads.setActionCommand(AC_OPTIONS_DOWNLOADS);
		optionsMenu.add(downloads);
		
		menuBar.add(optionsMenu);
		
		frame.setJMenuBar(menuBar);
	}
	
	/**
	 * Opens a fileselector. After a file has been selected, that file will be returned.
	 * @return File
	 */
	public File selectFile()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(frame);
		if (chooser.getSelectedFile() != null)
		{
			selectedFile = chooser.getSelectedFile();
			filelabel.setText(chooser.getSelectedFile().getName() + " : " + selectedFile.length() + " bytes");
			return chooser.getSelectedFile();
		}
		return null;
	}
	
	
	/**
	 * Saves a file to the preferred location.
	 * @param preffered The preferred name/directory.
	 * @return File
	 */
	public File saveFile(String preffered) {
		JFileChooser chooser = new JFileChooser();
		if(preffered!=null) {
			chooser.setSelectedFile( new File(preffered));
		}
		chooser.showSaveDialog(frame);
		File file = chooser.getSelectedFile();
		return file;
	}

	
	
}