package task.model;


public class EmailConfig {
	private int id;
	private String dailyTime;//daily report send time
	private String noticeTime;
	private String host;
	private Integer port;
	private String userName;//auth email& from
	private String password;
	private Boolean startTls;
	private Boolean auth;
	private Boolean debug;//true:not send email
	private String dailyTo;
	private String dailyCc;
	private String dailyBcc;
	private String dailySubject;
	
	private String monthlyTo;
	private String monthlyCc;
	private String monthlyBcc;
	private String monthlySubject;
	
	private boolean hasChange;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDailyTime() {
		return dailyTime;
	}
	public void setDailyTime(String dailyTime) {
		this.dailyTime = dailyTime;
	}
	public String getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean isStartTls() {
		return startTls;
	}
	public void setStartTls(Boolean startTls) {
		this.startTls = startTls;
	}
	public Boolean isAuth() {
		return auth;
	}
	public void setAuth(Boolean auth) {
		this.auth = auth;
	}
	public Boolean isDebug() {
		return debug;
	}
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
	public String getDailyTo() {
		return dailyTo;
	}
	public String[] getDailyTos() {
		if(dailyTo!=null&&!"".equals(dailyTo)){
			return dailyTo.split(",");
		}else{
			return null;
		}
	}
	public void setDailyTo(String dailyTo) {
		this.dailyTo = dailyTo;
	}
	public String getDailyCc() {
		return dailyCc;
	}
	public String[] getDailyCcs() {
		if(dailyCc!=null&&!"".equals(dailyCc)){
			return dailyCc.split(",");
		}else{
			return null;
		}
	}
	public void setDailyCc(String dailyCc) {
		this.dailyCc = dailyCc;
	}
	public String getDailyBcc() {
		return dailyBcc;
	}
	public String[] getDailyBccs() {
		if(dailyBcc!=null&&!"".equals(dailyBcc)){
			return dailyBcc.split(",");
		}else{
			return null;
		}
	}
	public void setDailyBcc(String dailyBcc) {
		this.dailyBcc = dailyBcc;
	}
	
	public String getDailySubject() {
		return dailySubject;
	}
	public void setDailySubject(String dailySubject) {
		this.dailySubject = dailySubject;
	}
	public String getMonthlyTo() {
		return monthlyTo;
	}
	public String[] getMonthlyTos() {
		if(monthlyTo!=null&&!"".equals(monthlyTo)){
			return monthlyTo.split(",");
		}else{
			return null;
		}
	}
	public void setMonthlyTo(String monthlyTo) {
		this.monthlyTo = monthlyTo;
	}
	public String getMonthlyCc() {
		return monthlyCc;
	}
	public String[] getMonthlyCcs() {
		if(monthlyCc!=null&&!"".equals(monthlyCc)){
			return monthlyCc.split(",");
		}else{
			return null;
		}
	}
	public void setMonthlyCc(String monthlyCc) {
		this.monthlyCc = monthlyCc;
	}
	public String getMonthlyBcc() {
		return monthlyBcc;
	}
	public String[] getMonthlyBccs() {
		if(monthlyBcc!=null&&!"".equals(monthlyBcc)){
			return monthlyBcc.split(",");
		}else{
			return null;
		}
	}
	public void setMonthlyBcc(String monthlyBcc) {
		this.monthlyBcc = monthlyBcc;
	}
	public String getMonthlySubject() {
		return monthlySubject;
	}
	public void setMonthlySubject(String monthlySubject) {
		this.monthlySubject = monthlySubject;
	}
	
	public EmailConfig compare(EmailConfig old){
		EmailConfig n = new EmailConfig();
		n.setId(id);
		n.setDebug(debug);
		boolean change = false;
		if(!isSave(auth,old.isAuth())){
			n.setAuth(auth);
			change = true;
		}
		if(!isSave(port,old.getPort())){
			n.setPort(port);
			change = true;
		}
		if(!isSame(dailyBcc, old.getDailyBcc())){
			n.setDailyBcc(dailyBcc);
			change = true;
		}
		if(!isSame(dailyCc, old.getDailyCc())){
			n.setDailyCc(dailyCc);
			change = true;
		}
		if(!isSame(dailyTo, old.getDailyTo())){
			n.setDailyTo(dailyTo);
			change = true;
		}
		if(!isSame(dailySubject, old.getDailySubject())){
			n.setDailySubject(dailySubject);
			change = true;
		}
		if(!isSame(userName, old.getUserName())){
			n.setUserName(userName);
			change = true;
		}
		if(!isSame(password, old.getPassword())){
			n.setPassword(password);
			change = true;
		}
		if(!isSame(host, old.getHost())){
			n.setHost(host);
			change = true;
		}
		if(!isSave(startTls,old.isStartTls())){
			n.setStartTls(startTls);
			change = true;
		}
		if(!isSame(monthlyBcc, old.getMonthlyBcc())){
			n.setMonthlyBcc(monthlyBcc);
			change = true;
		}
		if(!isSame(monthlyTo, old.getMonthlyTo())){
			n.setMonthlyTo(monthlyTo);
			change = true;
		}
		if(!isSame(monthlyCc, old.getMonthlyCc())){
			n.setMonthlyCc(monthlyCc);
			change = true;
		}
		if(!isSame(monthlySubject, old.getMonthlySubject())){
			n.setMonthlySubject(monthlySubject);
			change = true;
		}
		if(!isSame(noticeTime, old.getNoticeTime())){
			n.setNoticeTime(noticeTime);
			change = true;
		}
		if(!isSame(dailyTime, old.getDailyTime())){
			n.setDailyTime(dailyTime);
			change = true;
		}
		n.setHasChange(change);
		return n;
	}
	
	private boolean isSame(String newv,String old){
		if("".equals(newv)){
			newv=null;
		}
		if(newv!=null){
			if(newv.equals(old)){
				return true;
			}else{
				return false;
			}
		}else{
			if(old==null||"".equals(old)){
				return true;
			}else{
				return false;
			}
		}
	}
	
	private boolean isSave(int n, int o){
		return n==o;
	}
	
	private boolean isSave(boolean n,  boolean o){
		return n==o;
	}
	public boolean isHasChange() {
		return hasChange;
	}
	public void setHasChange(boolean hasChange) {
		this.hasChange = hasChange;
	}
	@Override
	public String toString() {
		return "EmailConfig [id=" + id + ", dailyTime=" + dailyTime
				+ ", noticeTime=" + noticeTime + ", host=" + host + ", port="
				+ port + ", userName=" + userName + ", password=" + password
				+ ", startTls=" + startTls + ", auth=" + auth + ", debug="
				+ debug + ", dailyTo=" + dailyTo + ", dailyCc=" + dailyCc
				+ ", dailyBcc=" + dailyBcc + ", dailySubject=" + dailySubject
				+ ", monthlyTo=" + monthlyTo + ", monthlyCc=" + monthlyCc
				+ ", monthlyBcc=" + monthlyBcc + ", monthlySubject="
				+ monthlySubject + ", hasChange=" + hasChange + "]";
	}
}
