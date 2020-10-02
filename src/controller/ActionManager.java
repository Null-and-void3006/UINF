package controller;

public class ActionManager {
	private static ActionManager instance = null;
	
	public static ActionManager getInstance(){
		if(instance==null){
			instance = new ActionManager();
		}
		return instance;
	}
	
	private AboutAction aboutAction;
	private NextAction nextAction;
	private PreviousAction previousAction;
	private CloseDialogAction closeDialogAction;
	private OpenMetaAction openMetaAction;
	private BrowseForMetaAction browseForMetaAction;
	public ActionManager()
	{
		aboutAction = new AboutAction();
		nextAction = new NextAction();
		previousAction = new PreviousAction();
		closeDialogAction = new CloseDialogAction();
		openMetaAction = new OpenMetaAction();
		browseForMetaAction = new BrowseForMetaAction();
	}
	
	public BrowseForMetaAction getBrowseForMetaAction() {
		return browseForMetaAction;
	}

	public OpenMetaAction getOpenMetaAction() {
		return openMetaAction;
	}

	public CloseDialogAction getCloseDialogAction() {
		return closeDialogAction;
	}

	public PreviousAction getPreviousAction() {
		return previousAction;
	}

	public AboutAction getAboutAction() {
		return aboutAction;
	}

	public NextAction getNextAction() {
		return nextAction;
	}
	

}
