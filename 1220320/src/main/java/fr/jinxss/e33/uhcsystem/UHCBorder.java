package fr.jinxss.e33.uhcsystem;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;

public class UHCBorder {
	
	private final WorldBorder border;
	public double InitialBorderSize = 4000;
	public double MeetUpBorderSize = 1000;
	public long MeetUpReduceTime = 20l;
	
	public UHCBorder() {
		border = Bukkit.getWorld("world").getWorldBorder();
		InstanciateBorder();
	}
	
	private void InstanciateBorder() {
		
		border.setSize(InitialBorderSize);
		border.setCenter(new Location(Bukkit.getWorld("world"), 0, 60, 0 ) );
		
	}
	public void setBorderSize(double pSize, long pTime) {
		border.setSize(pSize, pTime*20);
	}
	
	public void ReduceToMeetUpSize() {
		setBorderSize(MeetUpBorderSize, MeetUpReduceTime);
	}
	
	public double getCurrentSize() {
	    return border.getSize()/2;
	}

}
