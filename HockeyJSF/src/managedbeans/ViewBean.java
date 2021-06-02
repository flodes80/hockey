package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.richfaces.model.StringChartDataModel;
import org.richfaces.model.ChartDataModel;

import chart.TirChartRecord;
import controleur.EjbLocator;
import modele.gardien.Gardien;
import modele.match.Match;
import modele.tir.TirManager;
import modele.zonearret.ZoneArret;
import modele.zonetir.ZoneTir;

@Named("viewBean")
@SessionScoped
public class ViewBean implements Serializable{

	private static final long serialVersionUID = 5756063312510059823L;
	
	
	private Match selectedMatch;
	private Gardien selectedGardien;
	
	private List<TirChartRecord> lancersParZoneTir;
	private List<TirChartRecord> lancersParZoneArret;
	private int nbTotalLancers;
	private List<TirChartRecord> arretsParZoneTir;
	private List<TirChartRecord> arretsParZoneArret;
	private int nbTotalArrets;
	private StringChartDataModel pie;
	
	private List<TirChartRecord> globalLancersParZoneTir;
	private List<TirChartRecord> globalLancersParZoneArret;
	private int globalNbTotalLancers;
	private List<TirChartRecord> globalArretsParZoneTir;
	private List<TirChartRecord> globalArretsParZoneArret;
	private int globalNbTotalArrets;
	private StringChartDataModel globalPie;
	
	private TirManager tirManager;
	
	public ViewBean() {
		this.tirManager = EjbLocator.getLocator().getTirManager();
		this.lancersParZoneTir = new ArrayList<TirChartRecord>();
		this.lancersParZoneArret = new ArrayList<TirChartRecord>();
		this.arretsParZoneTir = new ArrayList<TirChartRecord>();
		this.arretsParZoneArret = new ArrayList<TirChartRecord>();
		this.pie = new StringChartDataModel(ChartDataModel.ChartType.pie);
		this.globalArretsParZoneArret = new ArrayList<TirChartRecord>();
		this.globalArretsParZoneTir = new ArrayList<TirChartRecord>();
		this.globalLancersParZoneArret = new ArrayList<TirChartRecord>();
		this.globalLancersParZoneTir = new ArrayList<TirChartRecord>();
		this.globalPie = new StringChartDataModel(ChartDataModel.ChartType.pie);
	}
	
	public void updateCharts() {
		clearCharts();
		
		if(selectedGardien != null && selectedMatch != null) {
			// Lancers par zone de tir
			for(Object[] data : tirManager.getNbLancersByZoneTir(selectedMatch, selectedGardien)) {
				ZoneTir zoneTir = (ZoneTir) data[1];
				lancersParZoneTir.add(new TirChartRecord(Integer.valueOf(String.valueOf(data[0])), "Zone tir " + zoneTir.getLibelle()));
			}
			
			// Lancers par zone d'arrêt
			for(Object[] data : tirManager.getNbLancersByZoneArret(selectedMatch, selectedGardien)) {
				ZoneArret zoneArret = (ZoneArret) data[1];
				lancersParZoneArret.add(new TirChartRecord(Integer.valueOf(String.valueOf(data[0])), "Zone Arret " + zoneArret.getLibelle()));
			}
			
			// Nb total de lancers
			nbTotalLancers = tirManager.getTotalNbLancers(selectedMatch, selectedGardien);
			
			
			// Lancers par zone de tir
			for(Object[] data : tirManager.getNbArretsByZoneTir(selectedMatch, selectedGardien)) {
				ZoneTir zoneTir = (ZoneTir) data[1];
				arretsParZoneTir.add(new TirChartRecord(Integer.valueOf(String.valueOf(data[0])), "Zone tir " + zoneTir.getLibelle()));
			}
			
			// Lancers par zone d'arrêt
			for(Object[] data : tirManager.getNbArretsByZoneArret(selectedMatch, selectedGardien)) {
				ZoneArret zoneArret = (ZoneArret) data[1];
				arretsParZoneArret.add(new TirChartRecord(Integer.valueOf(String.valueOf(data[0])), "Zone Arret " + zoneArret.getLibelle()));
			}
			
			// Nb total de lancers
			nbTotalArrets = tirManager.getTotalNbArrets(selectedMatch, selectedGardien);
			
			// Pourcentages
			pie.put("Pourcentage arrêts", nbTotalArrets);
			pie.put("Pourcentage buts", nbTotalLancers);
		}
		
		if(selectedGardien != null) {
			// Lancers par zone de tir
			for(Object[] data : tirManager.getNbLancersByZoneTir(selectedGardien)) {
				ZoneTir zoneTir = (ZoneTir) data[1];
				globalLancersParZoneTir.add(new TirChartRecord(Integer.valueOf(String.valueOf(data[0])), "Zone tir " + zoneTir.getLibelle()));
			}
			
			// Lancers par zone d'arrêt
			for(Object[] data : tirManager.getNbLancersByZoneArret(selectedGardien)) {
				ZoneArret zoneArret = (ZoneArret) data[1];
				globalLancersParZoneArret.add(new TirChartRecord(Integer.valueOf(String.valueOf(data[0])), "Zone Arret " + zoneArret.getLibelle()));
			}
			
			// Nb total de lancers
			globalNbTotalLancers = tirManager.getTotalNbLancers(selectedGardien);
			
			
			// Lancers par zone de tir
			for(Object[] data : tirManager.getNbArretsByZoneTir(selectedGardien)) {
				ZoneTir zoneTir = (ZoneTir) data[1];
				globalArretsParZoneTir.add(new TirChartRecord(Integer.valueOf(String.valueOf(data[0])), "Zone tir " + zoneTir.getLibelle()));
			}
			
			// Lancers par zone d'arrêt
			for(Object[] data : tirManager.getNbArretsByZoneArret(selectedGardien)) {
				ZoneArret zoneArret = (ZoneArret) data[1];
				globalArretsParZoneArret.add(new TirChartRecord(Integer.valueOf(String.valueOf(data[0])), "Zone Arret " + zoneArret.getLibelle()));
			}
			
			// Nb total de lancers
			globalNbTotalArrets = tirManager.getTotalNbArrets(selectedGardien);
			
			// Pourcentages
			globalPie.put("Pourcentage arrêts", nbTotalArrets);
			globalPie.put("Pourcentage buts", nbTotalLancers);
		}
	}
	
	private void clearCharts() {
		lancersParZoneTir.clear();
		lancersParZoneArret.clear();
		nbTotalLancers = 0;
		arretsParZoneTir.clear();
		arretsParZoneArret.clear();
		nbTotalArrets = 0;
		pie.getData().clear();
		
		globalLancersParZoneTir.clear();
		globalLancersParZoneArret.clear();
		globalNbTotalLancers = 0;
		globalArretsParZoneTir.clear();
		globalArretsParZoneArret.clear();
		globalNbTotalArrets = 0;
		globalPie.getData().clear();
	}
	
	public Match getSelectedMatch() {
		return selectedMatch;
	}
	
	public void setSelectedMatch(Match selectedMatch) {
		this.selectedMatch = selectedMatch;
	}
	
	public Gardien getSelectedGardien() {
		return selectedGardien;
	}
	
	public void setSelectedGardien(Gardien selectedGardien) {
		this.selectedGardien = selectedGardien;
	}
	
	public List<TirChartRecord> getLancersParZoneTir(){
		return lancersParZoneTir;
	}
	
	public List<TirChartRecord> getLancersParZoneArret(){
		return lancersParZoneArret;
	}

	public int getNbTotalLancers() {
		return nbTotalLancers;
	}

	public List<TirChartRecord> getArretsParZoneTir() {
		return arretsParZoneTir;
	}

	public List<TirChartRecord> getArretsParZoneArret() {
		return arretsParZoneArret;
	}

	public int getNbTotalArrets() {
		return nbTotalArrets;
	}

	public StringChartDataModel getPie() {
		return pie;
	}

	public StringChartDataModel getGlobalPie() {
		return globalPie;
	}

	public void setGlobalPie(StringChartDataModel globalPie) {
		this.globalPie = globalPie;
	}

	public List<TirChartRecord> getGlobalLancersParZoneTir() {
		return globalLancersParZoneTir;
	}

	public List<TirChartRecord> getGlobalLancersParZoneArret() {
		return globalLancersParZoneArret;
	}

	public int getGlobalNbTotalLancers() {
		return globalNbTotalLancers;
	}

	public List<TirChartRecord> getGlobalArretsParZoneTir() {
		return globalArretsParZoneTir;
	}

	public List<TirChartRecord> getGlobalArretsParZoneArret() {
		return globalArretsParZoneArret;
	}

	public int getGlobalNbTotalArrets() {
		return globalNbTotalArrets;
	}
	
	
}
