package GUI;

import javax.swing.JDialog;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JCheckBox;

import code.Configs;

import javax.swing.JButton;

public class ConfigDeleteMenu extends JDialog{
	
	private static final long serialVersionUID = 1L;

	public ConfigDeleteMenu() {
		
		setSize(320,270);
		setLocationRelativeTo(null);
		setModal(true);
		
		//Scroll Pane
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		Configs configs = new Configs();
		
		String[] names = configs.getNames();
		
		JCheckBox [] checkBoxes = new JCheckBox[names.length];
		
		for (int i = 0; i < names.length; i++){
			checkBoxes[i] = new JCheckBox(names[i]);
			checkBoxes[i].setSelected(true);
			//Don't allow default to be deleted
			if (checkBoxes[i].getText().trim().equals("Default"))
				checkBoxes[i].setEnabled(false);
			panel.add(checkBoxes[i]);
		}
		
		panel.setLayout(new GridLayout(names.length, 0));
		
		//Buttons
		
		JButton btnSelectAll = new JButton("Select All");
		btnSelectAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkbox :checkBoxes)
					if (checkbox.getText().trim().equals("Default"))
						checkbox.setSelected(true);
			}
		});
		panel_1.add(btnSelectAll);
		
		JButton btnDeselectAll = new JButton("Deselect All");
		btnDeselectAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkbox :checkBoxes)
					if (checkbox.getText().trim().equals("Default"))
						checkbox.setSelected(false);
			}
		});
		panel_1.add(btnDeselectAll);
		
		JButton btnDeleteUnselected = new JButton("Delete Unselected");
		btnDeleteUnselected.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkbox :checkBoxes)
					if (!checkbox.isSelected())
						configs.deleteConfig(checkbox.getText());
			}
		});
		panel_1.add(btnDeleteUnselected);
		
		setVisible(true);
	}
	
}
