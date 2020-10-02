package model;

import java.util.ArrayList;

public class ForeignTableBundle {
	private String ime;
	private ArrayList<ColumnDescriptor> kolone;
	private ArrayList<String> redovi;
	public ForeignTableBundle(String ime, ArrayList<ColumnDescriptor> kolone) {
		this.ime = ime;
		this.kolone = kolone;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public ArrayList<ColumnDescriptor> getKolone() {
		return kolone;
	}
	public void setKolone(ArrayList<ColumnDescriptor> kolone) {
		this.kolone = kolone;
	}
	public ArrayList<String> getRedovi() {
		return redovi;
	}
	public void setRedovi(ArrayList<String> redovi) {
		this.redovi = redovi;
	}
	
	

}
