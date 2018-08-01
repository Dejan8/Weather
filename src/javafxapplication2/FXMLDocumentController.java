/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author Dejan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField textName;
    @FXML
    private Label textCountry;
    @FXML
    private Label textCity;
    @FXML
    private Label textCondition;
    @FXML
    private Label textPreasure;
    @FXML
    private Label textHumidity;
    @FXML
    private ImageView imagew01d;
    @FXML
    private ImageView imagew01n;
    @FXML
    private ImageView imagew02d;
    @FXML
    private ImageView imagew02n;
    @FXML
    private ImageView imagew03d;
    @FXML
    private ImageView imagew03n;
    @FXML
    private ImageView imagew04d;
    @FXML
    private ImageView imagew04n;
    @FXML
    private ImageView imagew09d;
    @FXML
    private ImageView imagew09n;
    @FXML
    private ImageView imagew10d;
    @FXML
    private ImageView imagew10n;
    @FXML
    private ImageView imagew11d;
    @FXML
    private ImageView imagew11n;
    @FXML
    private ImageView imagew13d;
    @FXML
    private ImageView imagew13n;
    @FXML
    private ImageView imagew50d;
    @FXML
    private ImageView imagew50n;
    @FXML
    private Text textTempBig;
    
    @FXML
    private Label textWind;
    @FXML
    private ImageView imageBackground;
    
    
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    

    @FXML
    private void handleButtonActionEnterName(ActionEvent event) {
        
        
        label.setText("Can't find city ");
        
            // TODO add your handling code here:
        String cityName = textName.getText();
        textCity.setText(null);
        textCountry.setText(null);
        textCondition.setText(null);
        textHumidity.setText(null);
        textPreasure.setText(null);
        textWind.setText(null);
        
        imagew01d.setVisible(false);
        imagew02d.setVisible(false);
        imagew03d.setVisible(false);
        imagew04d.setVisible(false);
        imagew09d.setVisible(false);
        imagew10d.setVisible(false);
        imagew11d.setVisible(false);
        imagew13d.setVisible(false);
        imagew50d.setVisible(false);
        
        imagew01n.setVisible(false);
        imagew02n.setVisible(false);
        imagew03n.setVisible(false);
        imagew04n.setVisible(false);
        imagew09n.setVisible(false);
        imagew10n.setVisible(false);
        imagew11n.setVisible(false);
        imagew13n.setVisible(false);
        imagew50n.setVisible(false);
        imageBackground.setVisible(false);
        
        
        
        try {
      URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=ff36df026a63fbaf380e553d0ebbc65c");
      BufferedWriter writer;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                writer = new BufferedWriter(new FileWriter("data.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    writer.write(line);
                    writer.newLine();
                    
                    
                    Pattern p = Pattern.compile("\"description\":\"(.*?)\",\"icon\"");
                    Matcher m = p.matcher(line);
                    while (m.find()) {
                        
                        String desc = m.group(1);
                        System.out.println(desc);
                        textCondition.setText("Condition: " + desc);
                        
                        //Temperatura
                        Pattern pt = Pattern.compile("\"temp\":(.*?),\"pressure\"");
                        Matcher mt = pt.matcher(line);
                        while (mt.find()){
                            
                            String temp = mt.group(1);
                            
                            double d= Double.parseDouble(temp);
                            int a = (int) Math.round(d);
                            System.out.println("Trenutna temperatura je: " + (a-273) + "C");
                            int bTempCelsius = a - 273;
                            label.setText("Temp: " + (a-273) + "C");
                            //textTempBig.setText(desc);
                        }
                        
                         //High Temperature
                        Pattern pHt = Pattern.compile("\"temp_max\":(.*?)},\"visibility\"");
                        Matcher mHt = pHt.matcher(line);
                        while (mHt.find()){
                            
                            String hTemp = mHt.group(1);
                            
                            double d= Double.parseDouble(hTemp);
                            int a = (int) Math.round(d);
                            System.out.println("Maximalna temperatura je: " + (a-273) + "C");
                            int bTempCelsius = a - 273;
                            //highTemp.setText("High: " + (a-273) + "C");
                            //textTempBig.setText(desc);
                        }
                        
                        
                        
                        Pattern pPreasure = Pattern.compile("\"pressure\":(.*?),\"humidity\"");
                        Matcher mp = pPreasure.matcher(line);
                        while (mp.find()){
                            
                            String preasure = mp.group(1);
                            
                            System.out.println("Pritisak je: " + preasure);
                            textPreasure.setText("Preasure: " + preasure + " Bar");
                            
                            //Grad
                            Pattern pCity = Pattern.compile("\"name\":\"(.*?)\",\"cod\"");
                            Matcher mc = pCity.matcher(line);
                            while (mc.find()){
                                
                                String city = mc.group(1);
                                
                                System.out.println("Grad je: " + city);
                                textCity.setText(city);
                                
                            }
                            
                            //Country
                            Pattern pCountry = Pattern.compile("\"country\":\"(.*?)\",\"sunrise\"");
                            Matcher mk = pCountry.matcher(line);
                            while (mk.find()){
                                
                                String country = mk.group(1);
                                
                                System.out.println("Country: " + country);
                                textCountry.setText(country + ",");
                                
                            }
                             //Wind 
                            Pattern pWind = Pattern.compile("speed\":(.*?),\"deg");
                            Matcher mWind = pWind.matcher(line);
                            while (mWind.find()){
                                
                                String wind = mWind.group(1);
                                
                                System.out.println("Wind: " + wind);
                                textWind.setText("Wind: " + wind + "km/h");
                                
                            }
                            
                            
                            
                            //Time
                            Pattern pTime = Pattern.compile("\"sunrise\":(.*?),\"sunset\"");
                            Matcher mT = pTime.matcher(line);
                            while (mT.find()){
                                
                                String time = mT.group(1);
                                
                                Date date = new Date();
                                Instant instant = date.toInstant();
                                int eT= Integer.parseInt(time);
                                long epochSeconds =eT;
                                ZonedDateTime zonedDateTime = LocalDateTime.ofEpochSecond(epochSeconds, 0,
                                        OffsetDateTime.now(ZoneId.systemDefault()).getOffset()).atZone(ZoneId.systemDefault());
                                System.out.println("epochSeconds = " + epochSeconds);
                                System.out.println("ZonedDateTime = " + zonedDateTime);
                                
                                LocalDateTime dt = LocalDateTime.ofEpochSecond(epochSeconds, 0, ZoneOffset.UTC);
                                System.out.println(dt.toLocalTime());
                                
                                
                                
                                System.out.println("Casova: " + dt.toLocalTime());
                                
                                
                                
                                
                            }
                            
                            
                            
                             Pattern p1 = Pattern.compile(",\"icon\":\"(.*?)\"");
                    Matcher m1 = p1.matcher(line);
                    while (m1.find()) {
                       // image.setVisible(false);

                        String icn = m1.group(1);



                        String monthString = "";
                        switch (icn) {
                            case "01d":  imagew01d.setVisible(true);
                                break;
                            case "02d":  imagew02d.setVisible(true);
                                break;
                            case "03d":  imagew03d.setVisible(true);
                                break;
                            case "04d":  imagew04d.setVisible(true);
                                break;
                            case "09d":  imagew09d.setVisible(true);
                                break;
                            case "10d":  imagew10d.setVisible(true);
                                break;
                            case "11d":  imagew11d.setVisible(true);
                                break;
                            case "13d":  imagew13d.setVisible(true);
                                break;
                            case "50d":  imagew50d.setVisible(true);
                                break;
                            case "01n":  imagew01n.setVisible(true);
                                break;
                            case "02n":  imagew02n.setVisible(true);
                                break;
                            case "03n":  imagew03n.setVisible(true);
                                break;
                            case "04n":  imagew04n.setVisible(true);
                                break;
                            case "09n":  imagew09n.setVisible(true);
                                break;
                            case "10n":  imagew10n.setVisible(true);
                                break;
                            case "11n":  imagew11n.setVisible(true);
                                break;
                            case "13n":  imagew13n.setVisible(true);
                                break;
                            case "50n":  imagew50n.setVisible(true);
                                break;
                            default: imagew01n.setVisible(true);
                                break;
                            
                        }

                    }
                            
                            
                            //"humidity"
                            Pattern pHumidity = Pattern.compile("\"humidity\":(.*?),\"temp_min\"");
                            Matcher mH = pHumidity.matcher(line);
                            while (mH.find()){
                                
                                String humidity = mH.group(1);
                                
                                System.out.println("Vlaznost " + humidity);
                                textHumidity.setText("Humidity:  " + humidity +"%");
                                
                            }
                            
                        }
                    }
                    
                    
                }     
            }
      writer.close();
      
      
        } catch (MalformedURLException ex) {
            Logger.getLogger(JavaFXApplication2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaFXApplication2.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    } 
}

   
    

