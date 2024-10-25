package model;

import java.io.Serializable;
import java.util.Random;

public class TournamentData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int lastRacePos;
    private String swimTime;    
    private String CyclingTime;
    private String RunningTime;
    private int finishedAndWon = 0;
    private int AbandonedRaces = 0;
	private int SwmmWin = 0;
	private int CycWin = 0;
	private int PedsWon = 0;
	private int ChampiosnshipPoints = 0;
	private int ChampiosnshipPosition = 0;
	private int finalUDistance = 0;
	private int discipline = 0;
	private boolean neoprene;

	public TournamentData() {
		super();
		setNeoprene();
	}
	public boolean isNeoprene() {
		return neoprene;
	}
	public void setNeoprene() {
		Random random = new Random();
		this.neoprene = random.nextBoolean();
	}
	public int getDiscipline() {
		return discipline;
	}

	public void setDiscipline(int discipline) {
		this.discipline = discipline;
	}

	public void setTimePerDiscipline(int x, String time, boolean finished) {
		if (finished == false) {
			if(x > 400 && discipline == 0) {
				setSwimTime(time);
				discipline = 1 ;
			}
			else if(x > 650 && discipline == 1) {
				setCyclingTime(time);
				discipline = 2 ;
			}else if(x >= 900 && discipline == 2) {
				setRunningTime(time);
				discipline = 3;
			}
		}else {
			if (discipline == 0) {
				setSwimTime("");
				setCyclingTime("");
				setRunningTime("");
			}else if (discipline == 1) {
				setCyclingTime("");
				setRunningTime("");
			}
				else
					setRunningTime("");
			setDiscipline(0);
		}
	}
	
	public int getPosition() {
		return lastRacePos;
	}
	public void setFinalPosition(int position) {
		this.lastRacePos = position;
		this.setChampiosnshipPoints(position);
	}
	public String getSwimTime() {
		return swimTime;
	}
	public void setSwimTime(String swimTime) {
		this.swimTime = swimTime;
	}
	public String getCyclingTime() {
		return CyclingTime;
	}
	public void setCyclingTime(String cyclingTime) {
		CyclingTime = cyclingTime;
	}
	public String getRunningTime() {
		return RunningTime;
	}
	public void setRunningTime(String runningTime) {
		RunningTime = runningTime;
	}
	public int getFinishedAndWon() {
		return finishedAndWon;
	}
	public void setFinishedAndWon(boolean finishedAndWon) {
		this.finishedAndWon += 1;
	}
	public int getAbandonedRaces() {
		return AbandonedRaces;
	}
	public void setAbandonedRaces(int abandonedRaces) {
		AbandonedRaces = abandonedRaces;
	}
	public int getSwmmWin() {
		return SwmmWin;
	}
	public void setSwmmWin(int swmmWin) {
		SwmmWin = swmmWin;
	}
	public int getCycWin() {
		return CycWin;
	}
	public void setCycWin(int cycWin) {
		CycWin = cycWin;
	}
	public int getPedsWon() {
		return PedsWon;
	}
	public void setPedsWon(int pedsWon) {
		PedsWon = pedsWon;
	}
	public int getChampiosnshipPoints() {
		return ChampiosnshipPoints;
	}
	public void setChampiosnshipPoints(int pos) {
		if (pos == 1)
			ChampiosnshipPoints+= 5;
		else if (pos > 1 && pos <= 5)
				ChampiosnshipPoints+= 4;
			else if (pos > 5 && pos <= 10)
				ChampiosnshipPoints+= 2;
			else if (pos > 10 && pos <= 15)
				ChampiosnshipPoints+= 1;
	}
	public int getChampiosnshipPosition() {
		return ChampiosnshipPosition;
	}
	public void setChampiosnshipPosition(int champiosnshipPosition) {
		ChampiosnshipPosition = champiosnshipPosition;
	}
	public int getFinalUDistance() {
		return finalUDistance;
	}
	public void setFinalUDistance(int finalUDistance) {
		this.finalUDistance = finalUDistance;
	}
}
