package controller;

import java.util.ArrayList;

public interface Observed {

	public void triggetChange();
	public void subscribe(Observer ob);
	public void unsubscribe(Observer ob);
}
