
public class Bookstore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

public class Book {
	
	//book data members
	protected int bookId;
	protected String bookTitle;
	protected String bookAuthor;
	protected String bookPublication;
	protected String bookEdition;
	protected int bookYear;
	
	
	//constructor
	public Book() {
		this.bookId = 0;
		this.bookTitle = "no";
		this.bookAuthor = "";
		this.bookPublication = "";
		this.bookEdition = "";
		this.bookYear = 0;
		
	}
	
	public int getBookId() {
		return bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public String getBookPublication() {
		return bookPublication;
	}

	public String getBookEdition() {
		return bookEdition;
	}

	public int getBookYear() {
		return bookYear;
	}


	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public void setBookPublication(String bookPublication) {
		this.bookPublication = bookPublication;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

	public void setBookYear(int bookYear) {
		this.bookYear = bookYear;
	}

	

	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

	public void setBook(Book setBook,Book book) {
		setBook.bookId = book.getBookId();
		setBook.bookTitle = book.getBookTitle();
		setBook.bookAuthor = book.getBookAuthor();
		setBook.bookPublication = book.getBookPublication();
		setBook.bookEdition = book.getBookEdition();
		setBook.bookYear = book.getBookYear();
		
	}
	
	public Book getBook() {
		Book mybooks = new Book();
		mybooks.bookId = bookId;
		mybooks.bookTitle = bookTitle;
		mybooks.bookAuthor = bookAuthor;
		mybooks.bookPublication = bookPublication;
		mybooks.bookEdition = bookEdition;
		mybooks.bookYear = bookYear;
		 
		return mybooks;
	}
	
	@Override
	public String toString() {
		return "Book Title =" + bookTitle + ", Author=" + bookAuthor + ", Publication=" + bookPublication
				+ ", Edition=" + bookEdition + ", Year=" + bookYear ;
	}
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.sun.rowset.CachedRowSetImpl;

@SuppressWarnings("restriction")
public class SQLUtil {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/lib";
	private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    
    private static Connection conn = null;
    
    //connect to Database
    public void databaseconnect() throws SQLException, ClassNotFoundException{
    	try {
    		Class.forName(JDBC_DRIVER);
    	}catch(ClassNotFoundException e) {
    		System.out.println("Where is your Oracle JDBC Driver?");
    		e.printStackTrace();
    		throw e;
    	}
    	System.out.println("Oracle JDBC Driver Registered!");
    	
    	try {
    		conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    	}catch(SQLException e) {
    		System.out.println("Connection not etablished");
    		e.printStackTrace();
    		throw e;
    	}
    	System.out.println("Connected to database");
    }
    
    public String checkRegister(String name, String email, String user, String pass) throws SQLException, ClassNotFoundException{
    	if(usercheckname(user)) {
    		return "Username Exist";
    	}else if(usercheck(name,user)) {
			return "Already Exist";
		}else {
			register(name,email,user,pass);
			return "Registered";
		}
    }
    
    //registering the user 
    public String register(String name, String email, String user, String pass) throws SQLException, ClassNotFoundException{
		String sqlstatement = "INSERT INTO `user` (`NAME`, `USERNAME`, `PASSWORD`, `EMAILID`) VALUES ( '"+name+"', '"+user+"', '"+pass+"', '"+email+"')";
		Statement statement = null;
    	try {
			databaseconnect();
			statement = conn.createStatement();
    		statement.executeUpdate(sqlstatement);
    		statement.close();
    		conn.close();
		}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("Problem with registeration");
    		throw e;
    	}
    	return "";
	}
    
    //checking if the user already have registered to the system
    private boolean usercheck(String name, String user) throws ClassNotFoundException, SQLException {
		try {
			databaseconnect();
			PreparedStatement statement = conn.prepareStatement("SELECT NAME,USERNAME FROM user WHERE NAME = ? and USERNAME = ?");
			statement.setString(1, name);
    		statement.setString(2, user);
    		ResultSet reslt= statement.executeQuery();
    		if(reslt.next()) {
    			return true;
    		}
    		statement.close();
    		conn.close();
		}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("There is some problem while checking users");
    	}
    	return false;
	}

    //to check if username is already taken by someone
	private boolean usercheckname(String user) throws ClassNotFoundException, SQLException {
		try {
			databaseconnect();
			PreparedStatement statement = conn.prepareStatement("SELECT USERNAME FROM WHERE user USERNAME = ?");
    		statement.setString(1, user);
    		ResultSet reslt= statement.executeQuery();
    		if(reslt.next()) {
    			return true;
    		}
    		statement.close();
    		conn.close();
		}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("Problem while checking users");
    	}
		return false;
	}

	//validate login credentials 
    public boolean validate(String username, String password) throws SQLException, ClassNotFoundException{
    	try {
    		databaseconnect();
    		PreparedStatement statement = conn.prepareStatement("SELECT * FROM user WHERE USERNAME = ? and PASSWORD = ?");
    		statement.setString(1, username);
    		statement.setString(2, password);
    		ResultSet reslt= statement.executeQuery();
    		if(reslt.next()) {
    			return true;
    		}
    		statement.close();
    		conn.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("Some problem with login");
    		throw e;
    	}
		return false;
    }
    
    //run query in database using resultSet
    public ResultSet dbExecuteQuery(String querystatement) throws SQLException, ClassNotFoundException{
		Statement statement = null;
		ResultSet resltSet = null;
		CachedRowSetImpl cachrowset = new CachedRowSetImpl();
    	
		try {
			databaseconnect();
			statement  = conn.createStatement();
			resltSet = statement.executeQuery(querystatement);			
			cachrowset.populate(resltSet);
			
		}catch(SQLException e) {
			System.out.println("Problem occurred at executeQuery opration: " + e);
			throw e;
		}finally {
			if(resltSet != null) {
				resltSet.close();
			}
			if(statement != null) {
				statement.close();
			}
		}
    	return cachrowset;
    }
}

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bookdatabase {
	
	//search  books from the database 
	public ObservableList<Book> searchBook(String title) throws SQLException, ClassNotFoundException{
		String selectStatement = "SELECT * FROM books WHERE TITLE LIKE '%" + title + "%' OR AUTHOR LIKE '%" + title + "%' OR PUBLICATION LIKE '%" + title + "%'";
		
		try {
			SQLUtil sql = new SQLUtil();
			ResultSet rsBook = sql.dbExecuteQuery(selectStatement); 
			ObservableList<Book> bookList = getBookListFromResultSet(rsBook);
			return bookList;
		}catch(SQLException e) {
			System.out.println("Searching books , an error occured:" + e);
			throw e;
		}
	}
	
	//get list of all books with details from database
	public ObservableList<Book> getBookListFromResultSet(ResultSet result) throws SQLException{
		ObservableList<Book> booksList = FXCollections.observableArrayList();
		while(result.next()) {
			Book books = new Book();
			books.setBookId(result.getInt("BOOK_ID"));
			books.setBookTitle(result.getString("TITLE"));
			books.setBookAuthor(result.getString("AUTHOR"));
			books.setBookPublication(result.getString("PUBLICATION"));
			books.setBookEdition(result.getString("EDITION"));
			books.setBookYear(result.getInt("YEAR"));
			booksList.add(books);
		}
		return booksList;
	}
}
