package com.Covid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;
import java.util.function.Consumer;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class App extends JComponent
{
	private static final long serialVersionUID = 1L;
	
	public static Vector<String> getNews() throws IOException {
		Vector<String> data = new  Vector<String>();
		//System.out.println("Fetching Data");
		String url = "https://www.worldometers.info/coronavirus/";
		Document doc = Jsoup.connect(url).get();
		//System.out.println(doc.title());
		//System.out.println(doc.body().html());
		
		//news date handle
		date = Instant.now().toString().substring(0, 10);
		String dateQuery = "#newsdate"+date;
		//dateQuery = "#newsdate2020-12-08";
		int i=1;
		while(i<2+10) {
			i++;
		//element.forEach((e) -> {
			//System.out.println(e.html());
			String value = doc.select(dateQuery+" > div:nth-child("+i+") > div > ul > li").text();
			//System.out.println(value);
			data.add(value);
		}//);
		return data;
	}
	
	public static Vector<String> getDataOverall() throws IOException {
		Vector<String> data = new  Vector<String>();
		//System.out.println("Fetching Data");
		String url = "https://www.worldometers.info/coronavirus/";
		Document doc = Jsoup.connect(url).get();
		//System.out.println(doc.title());
		//System.out.println(doc.body().html());
		Elements element = doc.select("#maincounter-wrap");
		//System.out.println(element.html());
		for(Element e : element) {
		//element.forEach((e) -> {
			//System.out.println(e.html());
			String text = e.select("h1").text();
			//System.out.print(text+" ");
			String value = e.select(".maincounter-number").text();
			//System.out.println(value);
			data.add(text+" "+value+" ");
		}//);
		// THIS IS THE HTML SELECTOR COPIED FROM INSPECT ELEMENT
		String active= doc.select("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(14) > div > div.panel-body > div > div.panel_front > div.number-table-main").text();
		//System.out.println(active.html());
		data.add("Active Cases:"+" "+active+" ");
		return data;
	}
	public static Vector<String> getDataCountry(String country) throws IOException {
		String result = "";
		Vector<String> data = new  Vector<String>();
		//System.out.println("Fetching Data");
		String url = "https://www.worldometers.info/coronavirus/country/" + country + "/";
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Mobile Safari/537.36").get();
		//System.out.println(doc.title());
		//System.out.println(doc.body().html());
		Elements element = doc.select("#maincounter-wrap");
		//System.out.println(element.html());
		//URL url1 = new URL(doc.select("body > div:nth-child(10) > div:nth-child(2) > div.col-md-8 > div > div:nth-child(5) > h1 > div > img").attr("abs:src"));
//		doc.select("img").forEach((img) ->{
//			System.out.println(img.attr("abs:src"));
//		});    
		for(Element e : element) {
		//element.forEach((e) -> {
			//System.out.println(e.html());
			String text = e.select("h1").text();
			//System.out.print(text+" ");
			String value = e.select(".maincounter-number").text();
			//System.out.println(value);
			data.add(text+" "+value+" ");
			result.concat(text+" "+value);
		}//);
		//System.out.println(result);
		
		// NOT PROVIDED BY THE WEBSITE <---
		//String active= doc.select("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(14) > div > div.panel-body > div > div.panel_front > div.number-table-main").text();
		//System.out.println(active.html());
		//data.add("Active Cases:"+" "+active+" ");
		//--->
		
		return data;
	}
	static int n=0;
	static String country = "___", date;
	static JLabel imgLabel = new JLabel();	//this is to fix cold start while removing previous iteration flag from the imglabel
   
	public static void main( String[] args ) throws IOException
    {
    	final Vector<String> validCountries = new Vector<String>();
    	//file handling to store all valid countries in list
    	try {
    		@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("validateCountry.txt"));
    		    while (br.ready()) {
    		        validCountries.add(br.readLine().toLowerCase());
    		    }
    		}
    		 catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
//    	//Initialisation
//    	Vector<String> dataCountry = new  Vector<String>();
//    	Vector<String> dataOverall = new  Vector<String>();
//    	System.out.println("Covid Data Tracker");
//    	
//    	//print overall data for COVID in console
//    	dataOverall = getDataOverall();
//    	dataOverall.forEach((d) -> {
//    		System.out.println(d);
//    	});
//    	
//    	//Data for the country as input
//    	Scanner sc = new Scanner(System.in);
//    	//new App().getDataCountry(); //if method is not static
//    	System.out.print("Enter the country: ");
//    	String country = sc.nextLine().toLowerCase();
//    	dataCountry = getDataCountry(country);
//    	System.out.println("Fetching Data");
//    	//System.out.print(data);
//    	dataCountry.forEach((d) -> {
//    		System.out.println(d);
//    	});
    	
    	//GUI
    	final JFrame frame = new JFrame("COVID'19 DATA");
    	final JTextField textField = new JTextField("Type Country Name...");
    	final JLabel label =new JLabel();
    	final JButton button = new JButton("FETCH");
    	final JButton resetButton = new JButton("RESET");
    	final JButton newsButton = new JButton("NEWS");   
    	//to remove text when searh bar is clicked
    	textField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                textField.setText("");
            }
        });
    	
    	button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!textField.getText().equals("") && !textField.getText().equals("Type Country Name...") && !country.equals(textField.getText().toLowerCase())) {
						label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
						label.setBorder(new EmptyBorder(0,0,20,0));//top,left,bottom,right
						//checking for valid country else break
						if(!validCountries.contains(textField.getText().toLowerCase())) {
							textField.setText("Type Country Name...");
							return;
						}
						//JLabel imgLabel = new JLabel();
						n++;
						if(n>1) {
//							frame.add(imgLabel, BorderLayout.SOUTH);
						}
						frame.remove(imgLabel);	//remove previous iteration image from frame
						StringBuffer sbr = new StringBuffer(); //to show text in frame
						sbr.append("<html>").append("<strong>").append(textField.getText().toUpperCase()).append("</strong>").append(":<br>");
						country = textField.getText().toLowerCase();
						for(String e : getDataCountry(textField.getText())) {
						//getDataCountry(textField.getText()).forEach((e) -> {
							sbr.append(e).append("<br>");
						}//);
						sbr.append("</html>");
						label.setText(sbr.toString());
						getFlag(textField.getText().toLowerCase());
						//label.add(image);
						// image icon has image that is to be added to component in a frame
						ImageIcon image = new ImageIcon(textField.getText().toLowerCase()+".gif");
						imgLabel = new JLabel(image);
						imgLabel.setBorder(new EmptyBorder(40, 0, 0, 0)); //border padding for alignment
						frame.add(imgLabel, BorderLayout.NORTH);
						//delete the downloaded flag
						File file = new File(textField.getText().toLowerCase()+".gif");
						file.delete();
						textField.setText("");	//reset search bar which is not really needed but just in case the mouse click does not function properly
					}
					else;
						//textField.setText("");
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
    		
    	});
    	
    	resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {	//removes image, resets the search bar and text displayed to global data
				if(!textField.getText().equals("Type Country Name...") || country.equals("___")) {
				label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
				label.setBorder(new EmptyBorder(0,0,20,0));//top,left,bottom,right
				frame.remove(imgLabel);
				textField.setText("Type Country Name...");
				StringBuffer sbr = new StringBuffer();
				sbr.append("<html>").append("<strong>").append("GLOBAL:").append("</strong>").append("<br>");
				try {
					for(String ele : getDataOverall()) {
					//getDataOverall().forEach((ele) -> {
						sbr.append(ele).append("<br>");
					}//);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				country="___";
				sbr.append("</html>");
				label.setText(sbr.toString());
			}}
    		
    	});
    	
    	newsButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {	//global news
				//get news
				Vector<String> someNews = new Vector<String>();
				try {
					someNews = getNews();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				frame.remove(imgLabel);
				textField.setText("Type Country Name...");
				label.setBorder(new EmptyBorder(0,50,50,10));//top,left,bottom,right
				StringBuffer sbr = new StringBuffer();
				sbr.append("<html>").append("<strong>").append("<h2>Live News: "+date+"</h2>").append("</strong>");
				label.setFont(new Font("TimesRoman", Font.PLAIN, 14));
				int i = 1;
				for(String news : someNews) {
				//getDataOverall().forEach((news) -> {
					while(news.charAt(news.length()-1) == ']') {
						news = news.substring(0, news.length()-9);
					}
					sbr.append(i+". "+news).append("<br>");
					i++;
				}//);
				sbr.append("</html>");
				label.setText(sbr.toString());
			}
    		
    	});
    	
    	
    	// setting up the frame - positioning of components
//		frame.add(imgLabel, BorderLayout.SOUTH);
    	//Font f =new Font("TimesRoman", Font.PLAIN, 10);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,500,500);
		frame.getContentPane().add(new App());
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setLayout(new BorderLayout());
		frame.add(imgLabel, BorderLayout.SOUTH);
		textField.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		textField.setHorizontalAlignment(JTextField.CENTER );
		label.setHorizontalAlignment(JTextField.CENTER );
		label.setBackground(Color.GRAY);
		button.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		button.setBounds(frame.getWidth()/2-150, 400, 90, 30);
		resetButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		resetButton.setBounds(frame.getWidth()/2-50, 400, 90, 30);
		newsButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		newsButton.setBounds(frame.getWidth()/2+50, 400, 90, 30);
		label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		// initial display of global data
		StringBuffer sbr = new StringBuffer();
		sbr.append("<html>").append("<strong>").append("GLOBAL:").append("</strong>").append("<br>");
		for(String e : getDataOverall()) {
		//getDataOverall().forEach((e) -> {
			sbr.append(e).append("<br>");
		}//);
		sbr.append("</html>");
		label.setText(sbr.toString());
		frame.add(button);
		frame.add(resetButton);
		frame.add(newsButton); 
		frame.add(textField, BorderLayout.NORTH);
		frame.add(label, BorderLayout.CENTER);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setVisible(true);
    }

	protected static void getFlag( String country ) throws IOException {
		String url = "https://www.worldometers.info/coronavirus/country/" + country + "/";
		//scrapping again
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Mobile Safari/537.36").get();
		//now the downloading procedure begins
		String imgURL = doc.select("img").get(1).attr("abs:src");
		System.out.println(imgURL);
		//System.out.println(doc.select("body > div:nth-child(10) > div:nth-child(2) > div.col-md-8 > div > div:nth-child(5) > h1 > div > img").attr("abs:src"));
            
            //open the stream from URL
			URL urlImage = new URL(imgURL);
            byte[] buffer = new byte[1];
            URLConnection urlConnection = urlImage.openConnection();
            // IMPORTANT- we need to mimic the browser request which can be found out in networking tab 
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Mobile Safari/537.36");
            urlConnection.connect();
            DataInputStream di = new DataInputStream(urlConnection.getInputStream()); //read
            FileOutputStream fo = new FileOutputStream(country+".gif"); //write
            while (-1 != di.read(buffer, 0, 1))
              fo.write(buffer, 0, 1);
            di.close();
            fo.close();
	}
//	public void paint(Graphics g)
//	{
//		repaint();
//	}
}
