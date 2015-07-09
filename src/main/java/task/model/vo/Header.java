package task.model.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * to put common header elements here
 * 
 * @author Roger Liu
 *
 */
public class Header {
	private Set<Javascript> js = new HashSet<Javascript>();
	private Set<Css> css = new HashSet<Css>();

	public Set<Javascript> getJs() {
		return js;
	}

	public void setJs(Set<Javascript> js) {
		this.js = js;
	}

	public Set<Css> getCss() {
		return css;
	}

	public void setCss(Set<Css> css) {
		this.css = css;
	}

}
