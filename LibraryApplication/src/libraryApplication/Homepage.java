package libraryApplication;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Homepage extends JPanel implements ItemListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel cards; //Panel for cards to be switched between
	
	
	public Homepage() throws Exception {
		super(new BorderLayout());
		String[] options = {"Search", "Profile"};
		
		JComboBox optionsCombo = new JComboBox(options);
		optionsCombo.setSelectedIndex(0);
		optionsCombo.addItemListener(this);
		
		add(optionsCombo, BorderLayout.NORTH);
		setBorder(new EmptyBorder(40, 40, 40, 40));
		
		JPanel searchReserveCard = new BookSearch();
		JPanel patronCard = new PatronProfile();
		cards = new JPanel(new CardLayout());
		cards.add(searchReserveCard, "Search");
		cards.add(patronCard, "Profile");

		add(cards, BorderLayout.CENTER);
		
	}
	
	//@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			CardLayout cardLayout = (CardLayout)(cards.getLayout());
			cardLayout.show(cards, (String)e.getItem());
			
		}
	
	private static void createAndShowGUI() throws Exception
	{
		JFrame frame = new JFrame("Library System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent contentPane = new Homepage();
		contentPane.setOpaque(true);
		frame.setContentPane(contentPane);

		frame.setSize(new Dimension(1000, 1000));
		//frame.pack();
		frame.setVisible(true);	
	}	

	/*
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		createAndShowGUI();

	}*/
















	

}
