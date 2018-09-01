package libraryApplication;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class GuestSearch extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField searchField;
	private JButton searchButton;
	private List<BookData> searchResults;
	private SpringLayout layout;
	private JRadioButton author, publisher, title;
	private JRadioButton main, pleasureIsland, myrtleGrove, northEast, invisible, all;
	private JTable table;
	private JScrollPane scrollPane;
	
	
	
	public GuestSearch() throws Exception {
		table = new JTable();
		layout = new SpringLayout();
		setLayout(layout);
		
		
		
		 author = new JRadioButton("Author");
		 publisher = new JRadioButton("Publisher");
		 title = new JRadioButton("Title");
		 
		 ButtonGroup group = new ButtonGroup();
		 
		 group.add(author);
		 group.add(publisher);
		 group.add(title);
		 
		 
		 
		 
		 //positioning author radio button
		 layout.putConstraint(SpringLayout.WEST, author, 15, SpringLayout.WEST, this);
		 layout.putConstraint(SpringLayout.NORTH, author, 30, SpringLayout.NORTH, this);
		 add(author);
		 
		 
		//positioning publisher radio button
		layout.putConstraint(SpringLayout.WEST, publisher, 10, SpringLayout.EAST, author);
		layout.putConstraint(SpringLayout.NORTH, publisher, 0, SpringLayout.NORTH, author);
		add(publisher);
		
		//positioning  title radio button
		layout.putConstraint(SpringLayout.WEST, title, 10, SpringLayout.EAST, publisher);
		layout.putConstraint(SpringLayout.NORTH, title, 0, SpringLayout.NORTH, publisher);
		add(title);
		
		//positioning search label
		JLabel searchFieldLabel = new JLabel("Search");
		layout.putConstraint(SpringLayout.WEST, searchFieldLabel, 0, SpringLayout.WEST, author);
		layout.putConstraint(SpringLayout.NORTH, searchFieldLabel, 8, SpringLayout.SOUTH, author);
		//add(searchFieldLabel);
		
		//positioning search text box
		searchField = new JTextField(20); //"Search our catalog                              "
		layout.putConstraint(SpringLayout.WEST, searchField, 0, SpringLayout.WEST, author);
		layout.putConstraint(SpringLayout.NORTH, searchField, 8, SpringLayout.SOUTH, author);
		//layout.putConstraint(SpringLayout.WEST, searchField, 30, SpringLayout.EAST, searchFieldLabel);
		//layout.putConstraint(SpringLayout.NORTH, searchField, 0, SpringLayout.NORTH, searchFieldLabel);
		add(searchField);
		
		
		//library branch buttons
		
		main = new JRadioButton("Main");
		pleasureIsland = new JRadioButton("Pleasure Island");
		myrtleGrove = new JRadioButton("Myrtle Grove");
		northEast = new JRadioButton("North East");
		all = new JRadioButton("All Branches");
		invisible=new JRadioButton();
		
		ButtonGroup branchGroup = new ButtonGroup();
		
		branchGroup.add(main);
		branchGroup.add(pleasureIsland);
		branchGroup.add(myrtleGrove);
		branchGroup.add(northEast);
		branchGroup.add(invisible);
		branchGroup.add(all);
		
		main.setSelected(true);
		
		//positioning main radio button
		 layout.putConstraint(SpringLayout.WEST, main, 15, SpringLayout.WEST, this);
		 layout.putConstraint(SpringLayout.NORTH, main, 8, SpringLayout.SOUTH, searchField);
		 add(main);
		 
		//positioning pleasure island radio button
		 layout.putConstraint(SpringLayout.WEST, pleasureIsland, 10, SpringLayout.EAST, main);
		 layout.putConstraint(SpringLayout.NORTH, pleasureIsland, 0, SpringLayout.NORTH, main);
		 add(pleasureIsland);
		 
		 
		//positioning myrtleGrove radio button
		 layout.putConstraint(SpringLayout.WEST, myrtleGrove, 10, SpringLayout.EAST, pleasureIsland);
		 layout.putConstraint(SpringLayout.NORTH, myrtleGrove, 0, SpringLayout.NORTH, pleasureIsland);
		 add(myrtleGrove);
		 
		//positioning north east radio button
		 layout.putConstraint(SpringLayout.WEST, northEast, 10, SpringLayout.EAST, myrtleGrove);
		 layout.putConstraint(SpringLayout.NORTH, northEast, 0, SpringLayout.NORTH, myrtleGrove);
		 add(northEast);
		 
		 JLabel instructions = new JLabel("<html>To see a list of books in the entire library system select <br>All Branches and leave the search box empty.<br><br>Selecting a branch and leaving search box empty<br> will display available books at that branch</html>");
		 layout.putConstraint(SpringLayout.WEST, instructions, 0, SpringLayout.WEST, main);
		 layout.putConstraint(SpringLayout.NORTH, instructions, 10, SpringLayout.SOUTH, main);
		 add(instructions);
		 
		//positioning all radio button
		 layout.putConstraint(SpringLayout.WEST, all, 0, SpringLayout.WEST, instructions);
		 layout.putConstraint(SpringLayout.NORTH, all, 10, SpringLayout.SOUTH, instructions);
		 add(all);

		
		searchButton = new JButton("Search");
		layout.putConstraint(SpringLayout.WEST, searchButton, 0, SpringLayout.WEST, all);
		layout.putConstraint(SpringLayout.NORTH, searchButton, 10, SpringLayout.SOUTH, all);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String branchNum=null;
				String branchName = null;
				
				if (main.isSelected()) {
					branchNum = "9812";
					branchName = "Main";
				}
				
				else if (pleasureIsland.isSelected()) {
					branchNum = "9745";
					branchName = "Pleasure Island";
				}
				
				else if (myrtleGrove.isSelected()) {
					branchNum = "9632";
					branchName = "Myrtle Grove";
				}
				else if (northEast.isSelected()) {
					branchNum = "9124";
					branchName = "North East";
				}
				
				try {
					String searchParam = searchField.getText();

				
					System.out.println(searchParam);

					if (searchParam != null && searchParam.trim().length() > 0) { 
						if (author.isSelected()) {
							searchResults=Operations.searchBookbyAuthor(searchParam, branchNum);
							Viewer.text.setText("Searching by author name " + searchParam + " at " + branchName);
						}
						
						else if (title.isSelected()) {
							searchResults=Operations.searchBookbyTitle(searchParam, branchNum);
							Viewer.text.setText("Searching by title " + searchParam + " at " + branchName);
						}
						
						else if(publisher.isSelected()) {
							searchResults=Operations.searchBookbyPublisher(searchParam, branchNum);
							Viewer.text.setText("Searching by publisher " + searchParam + " at " + branchName);
						}	
					} 
					else {
						if (main.isSelected()) {
							searchResults = Operations.getAllBooksfromBranch(branchNum);
							Viewer.text.setText("Showing all available books from " + branchName);
						}

						else if (pleasureIsland.isSelected()) {
							searchResults = Operations.getAllBooksfromBranch(branchNum);
							Viewer.text.setText("Showing all available books from " + branchName);
						}

						else if (myrtleGrove.isSelected()) {
							searchResults = Operations.getAllBooksfromBranch(branchNum);
							Viewer.text.setText("Showing all available books from " + branchName);
						}
						else if (northEast.isSelected()) {
							searchResults = Operations.getAllBooksfromBranch(branchNum);
							Viewer.text.setText("Showing all available books from " + branchName);
						}
						else if (all.isSelected()){
							searchResults = Operations.getAllBooks();
							Viewer.text.setText("Showing all books in library system");
						}	
						
					}
					
					// create the model and update the "table"
					BookTable model = new BookTable(searchResults);
					
					table.setModel(model);
					table.setAutoCreateRowSorter(true);
					
					
				} catch (Exception e1) {
					e1.printStackTrace(); 
				}
				
			}		
				
				
		});
				
		add(searchButton);
		
		scrollPane = new JScrollPane(table);
		
		
		layout.putConstraint(SpringLayout.WEST, scrollPane, 175, SpringLayout.EAST, northEast);
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 30, SpringLayout.NORTH, this);
		add(scrollPane);
		
		JLabel reserveInstructions = new JLabel("<html> To reserve a book please log in.</html>");
		
		layout.putConstraint(SpringLayout.WEST, reserveInstructions, 0, SpringLayout.WEST, scrollPane);
		layout.putConstraint(SpringLayout.NORTH, reserveInstructions, 10, SpringLayout.SOUTH, scrollPane);
		add(reserveInstructions);
		
		reserveInstructions.setFont(new Font("Dialog", Font.BOLD, 18));
		
		
	}
	

}
