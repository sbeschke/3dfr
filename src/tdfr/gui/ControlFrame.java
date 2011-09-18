package tdfr.gui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tdfr.Main;
import tdfr.twitter.importer.JsonImporter;

public class ControlFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlFrame(Main main) {
		this.main = main;
		
		setTitle("Controls");
		setSize(180, 120);
		
		initUI();
	}
	
	public void initUI() {
		panel = new JPanel();
		getContentPane().add(panel);
		
		loadJsonButton = new JButton("Load Twitter data");
		loadJsonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadJsonButtonAction(e);
			}
		});
		panel.add(loadJsonButton);
	}
	
	public void loadJsonButtonAction(ActionEvent e) {
		FileDialog fc = new FileDialog((Frame)null);
		fc.setVisible(true);
		File file = new File(fc.getDirectory(), fc.getFile());
		main.importFrom(new JsonImporter(file));
	}
	
	private Main main;
	
	private JPanel panel;
	private JButton loadJsonButton;
}
