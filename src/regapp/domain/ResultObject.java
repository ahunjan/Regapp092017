package regapp.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultObject<XYZ> {
	private String error;
	
	private XYZ result;
	
	private int appStatus;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public XYZ getResult() {
		return result;
	}

	public void setResult(XYZ result) {
		this.result = result;
	}

	public int getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(int appStatus) {
		this.appStatus = appStatus;
	}
}