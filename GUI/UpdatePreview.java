package GUI;

import java.util.List;

import javax.swing.SwingWorker;

public class UpdatePreview extends SwingWorker<String, String>{

	private SettingsMenu settingsMenu;

	public UpdatePreview(SettingsMenu settingsMenu) {
		this.settingsMenu = settingsMenu;
	}

	protected void process(List<String> items) {
		settingsMenu.updatePreview();
	}

	@Override
	protected String doInBackground() throws Exception {
		
		while (true){
			publish();
			
			Thread.sleep(1000);
			
			if (Thread.currentThread().isInterrupted()){
				return null;
			}
		}
	}
}
