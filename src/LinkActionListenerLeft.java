/**
 * @(#)GraphicalStuff.java
 *
 *
 * @author
 * @version 1.00 2012/3/19
 */
	import javax.swing.*;
	import java.awt.event.*;
public class LinkActionListenerLeft extends AbstractAction{

	public Link link;

	public LinkActionListenerLeft(Link theLink) {
		super();
		this.link = theLink; //Attaches this listener to link
	}

	//@Override
	public void actionPerformed (ActionEvent e){

		if (!this.link.hurt && !this.link.window.intro && this.link.linkExit.equals("n")){
			this.link.direction = "l";//Changes Link's direction when the key bound to it is pressed
			this.link.sDirection = "l";
		}

	}
}
