package client.cutomcomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

public class TerrainButton extends JToggleButton{
	private static final long serialVersionUID = -7643764853278531773L;
	
	private String zoneName;
	
	public TerrainButton(String zoneName) {
		super();
		this.zoneName = zoneName;
		Icon icon = new ImageIcon(getClass().getResource("/TerrainHockey"+ zoneName + ".png"));
		this.setIcon(icon);
		this.setSize(icon.getIconWidth(), icon.getIconHeight());
		this.setText(zoneName);
		this.setHorizontalTextPosition(CENTER);
		this.setVerticalTextPosition(CENTER);
		
		this.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				updateAppearance();
			}
			
		});
		updateAppearance();
	}
	
	private void updateAppearance() {
		if(this.isSelected()) {
			this.setBorder(new LineBorder(Color.GREEN, 1));
			this.setForeground(Color.GREEN);
			this.setFont(new Font("Arial", Font.BOLD, 40));
		}
		else {
			this.setBorder(new LineBorder(Color.BLACK, 1));
			this.setForeground(Color.BLACK);
			this.setFont(new Font("Arial", Font.PLAIN, 40));
		}
	}

	public String getZoneName() {
		return zoneName;
	}
	
}
