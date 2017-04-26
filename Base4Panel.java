import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Base4Panel extends JPanel implements ActionListener, ChangeListener {

	private Base4Calc calc = new Base4Calc();
	JButton zero, one, two, three, four, five, six, seven, eight, nine, 
	a, b, c, d, e, f, 
	plus, minus, multiply, divide, 
	clear, equals;
	
	//holds all numbers/operators entered
	String nums = new String("");
	
	//whether or not an operator exists in the string nums
	boolean op = false;
	
	int BASE_MIN = 2, BASE_MAX = 16, BASE_INIT = 10;
	JSlider base = new JSlider(JSlider.HORIZONTAL, BASE_MIN, BASE_MAX, BASE_INIT);
	JTextArea text = new JTextArea(1, 20);
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	//where the next number should be displayed in the JTextArea
	int pos = 0;

	Base4Panel() {

		this.setLayout(new BorderLayout()); 

		base.addChangeListener(this);
		base.setMajorTickSpacing(1);
		base.setPaintTicks(true);
		base.setPaintLabels(true);
		
		Font bigFont = new Font("serif", Font.BOLD, 28);
		text.setFont(bigFont);
		
		zero = new JButton("0"); 
		one = new JButton("1");
		two = new JButton("2"); 
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		a = new JButton("A");
		b = new JButton("B");
		c = new JButton("C");
		d = new JButton("D");
		e = new JButton("E");
		f = new JButton("F");
		plus = new JButton("+"); 
		minus = new JButton("-"); 
		multiply = new JButton("*"); 
		divide = new JButton("/"); 
		clear = new JButton("Clear"); 
		equals = new JButton("=");
		
		//add all buttons to ArrayList buttons
		buttons.add(zero);
		buttons.add(one);
		buttons.add(two);
		buttons.add(three);
		buttons.add(four);
		buttons.add(five);
		buttons.add(six);
		buttons.add(seven);
		buttons.add(eight);
		buttons.add(nine);
		buttons.add(a);
		buttons.add(b);
		buttons.add(c);
		buttons.add(d);
		buttons.add(e);
		buttons.add(f);

		JPanel digitPanel = new JPanel();
		digitPanel.add(f);
		digitPanel.add(e);
		digitPanel.add(d);
		digitPanel.add(c);
		digitPanel.add(b);
		digitPanel.add(a);
		digitPanel.add(nine);
		digitPanel.add(eight);
		digitPanel.add(seven);
		digitPanel.add(six);
		digitPanel.add(five);
		digitPanel.add(four);
		digitPanel.add(three);
		digitPanel.add(two);
		digitPanel.add(one);
		digitPanel.add(zero);

		JPanel opPanel = new JPanel();
		opPanel.setLayout(new BoxLayout(opPanel, BoxLayout.Y_AXIS));
		opPanel.add(divide);
		opPanel.add(multiply);
		opPanel.add(minus);
		opPanel.add(plus);
		opPanel.add(equals);
		opPanel.add(clear);
		
		add(BorderLayout.NORTH, text);
		add(BorderLayout.SOUTH, base);
		add(BorderLayout.CENTER, digitPanel);
		add(BorderLayout.EAST, opPanel);
		
		zero.addActionListener(this);
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		a.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		e.addActionListener(this);
		f.addActionListener(this);
		plus.addActionListener(this);
		minus.addActionListener(this);
		multiply.addActionListener(this);
		divide.addActionListener(this);
		clear.addActionListener(this);
		equals.addActionListener(this);
		
		a.setEnabled(false);
		b.setEnabled(false);
		c.setEnabled(false);
		d.setEnabled(false);
		e.setEnabled(false);
		f.setEnabled(false);
		
	}
	
	/**
	 * JSlider change method
	 * converts string num and/or display value to new base
	 * enables/disables buttons depending on base
	 */
	public void stateChanged(ChangeEvent event) {
		int baseInt = base.getValue();
		
		//make sure nums is just a string of numbers so it can be converted
		nums = calc.calculate(nums);
		
		//create newText string of converted oldNum and display it (unless oldNum is empty)
		if(!base.getValueIsAdjusting()) {
			String oldNum = nums;
			
			if(Integer.parseInt(oldNum) == 0) {
				text.setText("");
			}
			else {
				String newText = calc.convert(oldNum, baseInt);
				text.setText(newText);
			}
		}
		
		//disable all buttons, then only enable buttons for specific base selected
		for(JButton i: buttons) 
			i.setEnabled(false);
		
		for(int i = 0; i < baseInt; i++)
			buttons.get(i).setEnabled(true);
	}

	/**
	 * ActionListener event method
	 * different actions for operator buttons, digit buttons, clear and equals
	 */
	public void actionPerformed(ActionEvent event) {
		int baseInt = base.getValue();
		
		
		/*
		 * if button == +, -, *, /, reset position, clear JTextArea
		 * if operator already in string nums (op == true), perform calculation
		 * add operator to string nums and set op == true
		 */
		if(event.getSource() == plus) {
			pos = 0;
			text.setText("");
			if(op == true) {
				nums = calc.calculate(nums);
				op = false;
			}
			nums += '+';
			op = true;
		}
		else if(event.getSource() == minus) {
			pos = 0;
			text.setText("");
			if(op == true) {
				nums = calc.calculate(nums);
				op = false;
			}
			nums = nums + '-';
			op = true;
		}
		else if(event.getSource() == multiply) {
			pos = 0;
			text.setText("");
			if(op == true) {
				nums = calc.calculate(nums);
				op = false;
			}
			nums = nums + '*';
			op = true;
		}
		else if(event.getSource() == divide) {
			pos = 0;
			text.setText("");
			if(op == true) {
				nums = calc.calculate(nums);
				op = false;
			}
			nums = nums + '/';
			op = true;
		}
		
		/*
		 * if button is =, reset position and clear display
		 * calculate nums
		 * set numDisplay equal to nums converted to correct base and display in JTextArea
		 * set op == false 
		 */
		else if(event.getSource() == equals) {
			pos = 0;
			text.setText("");
			String numDisplay = new String("");
			if(op == true) {
				nums = calc.calculate(nums);
				numDisplay = calc.convert(nums, baseInt);
			}
			text.setText(numDisplay);
			op = false;
		}
		
		//reset position, clear display and nums, call clear(), set op == false
		else if(event.getSource() == clear) {
			pos = 0;
			text.setText("");
			nums = "";
			calc.clear();
			op = false;
		}
		
		/*
		 * if button == any digit/letter
		 * int val equals index of button in ArrayList buttons
		 * convert val to string and insert into JTextArea at correct position
		 * increment position and add val to string nums
		 */
		else {
			int val = buttons.indexOf(event.getSource());
			String valString = Integer.toString(val);
			
			text.insert(valString, pos);
			pos++;
			nums += val;
		}
	}

}
