package client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import client.cutomcomponents.CageButton;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javax.swing.SwingConstants;
import client.cutomcomponents.TerrainButton;
import modele.gardien.Gardien;
import modele.match.Match;
import modele.tir.Tir;
import modele.user.User;
import modele.zonearret.ZoneArret;
import modele.zonetir.ZoneTir;
import servlethandler.ServletHandler;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaisieFrame extends JFrame {	
	private static final long serialVersionUID = 3859695681629256409L;
	
	private ServletHandler servletHandler;
	
	private JPanel contentPane;
	private JComboBox<Match> comboBoxMatch;
	private JComboBox<Gardien> comboBoxGardien;
	private List<CageButton> listCageButtons;
	private List<TerrainButton> listTerrainButtons;
	private JRadioButton rdbtnButOui;
	private JRadioButton rdbtnButNon;
	private ButtonGroup cageButtonGroup;
	private ButtonGroup terrainButtonGroup;
	private ButtonGroup butButtonGroup;
	
	private User user;

	/**
	 * Lancement frame de saisie
	 * @param user Utilisateur authentifié
	 * @param servletHandler Servlet handler initialisé par la frame précédente
	 */
	public SaisieFrame(User user, ServletHandler servletHandler) {
		this.servletHandler = servletHandler;
		this.user = user;
		
		setTitle("Hockey - Saisie");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1610, 961);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelCage = new JPanel();
		
		JSeparator separator = new JSeparator();
		
		JPanel panelInfos = new JPanel();
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblChoixCibleCage = new JLabel("Position du palet par rapport \u00E0 la cage");
		lblChoixCibleCage.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblChoixPositionTireur = new JLabel("Position du tireur");
		lblChoixPositionTireur.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoixPositionTireur.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JPanel panel = new JPanel();
		
		Icon icon = new ImageIcon(getClass().getResource("/TerrainHockeyFull.png"));
		JLabel lblImgTerrain = new JLabel(icon);
		
		JButton btnEnvoyer = new JButton("OK !");
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBeforeSend();
			}
		});
		btnEnvoyer.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		butButtonGroup = new ButtonGroup(); 
		rdbtnButOui = new JRadioButton("Oui");
		rdbtnButOui.setFont(new Font("Tahoma", Font.PLAIN, 20));
		butButtonGroup.add(rdbtnButOui);
		
		rdbtnButNon = new JRadioButton("Non");
		rdbtnButNon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		butButtonGroup.add(rdbtnButNon);
		
		JLabel lblBut = new JLabel("But ?");
		lblBut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(227)
									.addComponent(lblChoixPositionTireur, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 211, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblImgTerrain, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
									.addGap(110)))
							.addGap(17)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(121)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelCage, GroupLayout.PREFERRED_SIZE, 699, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblChoixCibleCage)
									.addGap(130))))
						.addComponent(panelInfos, GroupLayout.DEFAULT_SIZE, 1556, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 1556, Short.MAX_VALUE))
					.addGap(28))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(669)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtnButOui)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(rdbtnButNon))
						.addComponent(btnEnvoyer, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(762, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(728)
					.addComponent(lblBut)
					.addContainerGap(821, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelInfos, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(82)
							.addComponent(lblChoixCibleCage)
							.addGap(59)
							.addComponent(panelCage, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(75)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblChoixPositionTireur, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(60)
											.addComponent(panel, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(78)
											.addComponent(lblImgTerrain)))))))
					.addGap(18)
					.addComponent(lblBut)
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnButOui)
						.addComponent(rdbtnButNon))
					.addGap(18)
					.addComponent(btnEnvoyer, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		
		terrainButtonGroup = new ButtonGroup();
		listTerrainButtons = new ArrayList<TerrainButton>();
		TerrainButton terrainButton1 = new TerrainButton("1");
		terrainButtonGroup.add(terrainButton1);
		listTerrainButtons.add(terrainButton1);
		panel.add(terrainButton1);
		
		TerrainButton terrainButton2 = new TerrainButton("2");
		terrainButtonGroup.add(terrainButton2);
		listTerrainButtons.add(terrainButton2);
		panel.add(terrainButton2);
		
		TerrainButton terrainButton3 = new TerrainButton("3");
		terrainButtonGroup.add(terrainButton3);
		listTerrainButtons.add(terrainButton3);
		panel.add(terrainButton3);
		
		TerrainButton terrainButton4 = new TerrainButton("4");
		terrainButtonGroup.add(terrainButton4);
		listTerrainButtons.add(terrainButton4);
		panel.add(terrainButton4);
		
		TerrainButton terrainButton5 = new TerrainButton("5");
		terrainButtonGroup.add(terrainButton5);
		listTerrainButtons.add(terrainButton5);
		panel.add(terrainButton5);
		
		TerrainButton terrainButton6 = new TerrainButton("6");
		terrainButtonGroup.add(terrainButton6);
		listTerrainButtons.add(terrainButton6);
		panel.add(terrainButton6);
		
		JLabel lblBienvenue = new JLabel("Bienvenue " + user.getName());
		lblBienvenue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblChoixMatch = new JLabel("Choix match:");
		lblChoixMatch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		comboBoxMatch = new JComboBox();
		
		JLabel lblChoixGardien = new JLabel("Choix Gardien:");
		lblChoixGardien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		comboBoxGardien = new JComboBox();
		GroupLayout gl_panelInfos = new GroupLayout(panelInfos);
		gl_panelInfos.setHorizontalGroup(
			gl_panelInfos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInfos.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBienvenue)
					.addGap(316)
					.addComponent(lblChoixMatch)
					.addGap(18)
					.addComponent(comboBoxMatch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(81)
					.addComponent(lblChoixGardien)
					.addGap(18)
					.addComponent(comboBoxGardien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(602, Short.MAX_VALUE))
		);
		gl_panelInfos.setVerticalGroup(
			gl_panelInfos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInfos.createSequentialGroup()
					.addGroup(gl_panelInfos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInfos.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblBienvenue))
						.addGroup(gl_panelInfos.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelInfos.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblChoixMatch)
								.addComponent(comboBoxMatch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxGardien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblChoixGardien))))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panelInfos.setLayout(gl_panelInfos);
		
		listCageButtons = new ArrayList<CageButton>();
		cageButtonGroup = new ButtonGroup();
		
		CageButton cageButtonA = new CageButton("A");
		cageButtonGroup.add(cageButtonA);
		listCageButtons.add(cageButtonA);
		panelCage.add(cageButtonA);
		
		CageButton cageButtonB = new CageButton("B");
		cageButtonGroup.add(cageButtonB);
		listCageButtons.add(cageButtonB);
		panelCage.add(cageButtonB);
		
		CageButton cageButtonC = new CageButton("C");
		listCageButtons.add(cageButtonC);
		cageButtonGroup.add(cageButtonC);
		panelCage.add(cageButtonC);
		
		CageButton cageButtonD = new CageButton("D");
		listCageButtons.add(cageButtonD);
		cageButtonGroup.add(cageButtonD);
		panelCage.add(cageButtonD);
		
		CageButton cageButtonE = new CageButton("E");
		cageButtonGroup.add(cageButtonE);
		listCageButtons.add(cageButtonE);
		panelCage.add(cageButtonE);
		
		CageButton cageButtonF = new CageButton("F");
		cageButtonGroup.add(cageButtonF);
		listCageButtons.add(cageButtonF);
		panelCage.add(cageButtonF);
		
		CageButton cageButtonG = new CageButton("G");
		cageButtonGroup.add(cageButtonG);
		listCageButtons.add(cageButtonG);
		panelCage.add(cageButtonG);
		
		CageButton cageButtonH = new CageButton("H");
		panelCage.add(cageButtonH);
		listCageButtons.add(cageButtonH);
		cageButtonGroup.add(cageButtonH);
		
		CageButton cageButtonI = new CageButton("I");
		cageButtonGroup.add(cageButtonI);
		listCageButtons.add(cageButtonI);
		panelCage.add(cageButtonI);
		
		contentPane.setLayout(gl_contentPane);
		
		loadInfos();
	}
	
	
	/**
	 * Méthode permettant de remplir nos Combobox (Gardiens et Matchs)
	 */
	private void loadInfos() {
		DefaultComboBoxModel<Match> modelMatchs = new DefaultComboBoxModel<Match>();
		modelMatchs.addAll(servletHandler.getListeMatch(user));
		comboBoxMatch.setModel(modelMatchs);
		
		DefaultComboBoxModel<Gardien> modelGardiens = new DefaultComboBoxModel<Gardien>();
		modelGardiens.addAll(servletHandler.getListeGardien(user));
		comboBoxGardien.setModel(modelGardiens);
	}
	
	/**
	 * Fonction permettant d'obtenir le bouton de cage sélectionné
	 * @return CageButton sélectionné
	 */
	private CageButton getSelectedCageButton() {
		for(CageButton cageButton : listCageButtons) {
			if(cageButton.isSelected())
				return cageButton;
		}
		return null;
	}
	
	/**
	 * Fonction permettant d'obtenir le bouton de tir de départ sélectionné
	 * @return
	 */
	private TerrainButton getSelectedTerrainButton() {
		for(TerrainButton terrainButton : listTerrainButtons) {
			if(terrainButton.isSelected())
				return terrainButton;
		}
		return null;
	}
	
	/**
	 * Méthode vérifiant le remplissage des champs avant envoi des données au servlet
	 */
	private void checkBeforeSend() {
		Match match = (Match) comboBoxMatch.getSelectedItem();
		Gardien gardien = (Gardien) comboBoxGardien.getSelectedItem();
		ZoneArret zoneArret = new ZoneArret();
		zoneArret.setLibelle(getSelectedCageButton() == null ? "" : getSelectedCageButton().getZoneName());
		ZoneTir zoneTir = new ZoneTir();
		zoneTir.setLibelle(getSelectedTerrainButton() == null ? "" : getSelectedTerrainButton().getZoneName());
		
		if(match == null) {
			JOptionPane.showMessageDialog(this,
				    "Veuillez sélectionner un match !",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
		else if(gardien == null) {
			JOptionPane.showMessageDialog(this,
				    "Veuillez sélectionner un gardien !",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
		else if(zoneTir.getLibelle().isBlank()) {
			JOptionPane.showMessageDialog(this,
				    "Veuillez sélectionner une zone de tir !",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
		else if(zoneArret.getLibelle().isBlank()) {
			JOptionPane.showMessageDialog(this,
				    "Veuillez sélectionner une zone de la cage !",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
		else if(!rdbtnButOui.isSelected() && !rdbtnButNon.isSelected()){
			JOptionPane.showMessageDialog(this,
				    "Veuillez indiquer s'il y a but ou non !",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
		// Envoi des données si ok
		else {
			Tir tir = new Tir();
			tir.setMatch(match);
			tir.setGardien(gardien);
			tir.setZoneArret(zoneArret);
			tir.setZoneTir(zoneTir);
			if(rdbtnButOui.isSelected()) {
				tir.setNbBut(1);
			}
			else {
				tir.setNbTir(1);
			}
			servletHandler.envoiDonneesTir(tir, user);
			resetSaisie();
		}
	}
	
	
	/**
	 * Méthode réinitialisant la saisie pour prochain tir
	 * Appelée après saisie d'un tir
	 */
	private void resetSaisie() {
		cageButtonGroup.clearSelection();
		terrainButtonGroup.clearSelection();
		butButtonGroup.clearSelection();
	}
}
