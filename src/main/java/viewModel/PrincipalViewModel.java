package viewModel;

import org.uqbar.commons.utils.Observable;

@Observable
public class PrincipalViewModel {

	private String path;

	public String getPath() {
		return path;//.replace("\\", "\\\\");
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
