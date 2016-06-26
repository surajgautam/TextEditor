package notepad;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public final class HelpFrame extends JFrame {
    private JPanel panel;
    private JLabel copyright;
    private JLabel queries;
    private JPanel queriespanel;
    private JLabel emailId;
    private JPanel emailpanel;
    private JLabel to;
    private JPanel topanel;
    private JTextArea toarea;
    private JTextArea emailarea;
    private JTextArea text;
    private JScrollPane scroll;
    private JButton send;
      HelpFrame(){
        initialize();
        setter();
        setTitle("HELP");
        setLocation(500, 100);
        setResizable(false);
        setLayout(null);//to use setBounds we have to do setLayout as null
        setSize(400,500);
        this.add(panel);
        panel.add(copyright);
        this.add(queriespanel);
        queriespanel.add(queries);
        this.add(emailpanel);
        this.add(topanel);
        emailpanel.add(emailId);
        topanel.add(to);
        this.add(toarea);
        this.add(emailarea);
        this.add(scroll);
        this.add(send);
        setVisible(true);
        
      }
      public void initialize(){
          //use html tags for breaking lines
          text = new JTextArea(20,20);
           //if text is initialized after scroll then textarea will be disabled
          scroll = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
          copyright = new JLabel("<html>Version:1.0.0 <br/> ©2016. All rights reserved</html>");
          panel = new JPanel();
          emailpanel = new JPanel();
          topanel = new JPanel();
          queries = new JLabel("If any queries, feel free to mail me");
          queriespanel = new JPanel();
          emailId = new JLabel("Email ID:");
          to = new JLabel("TO:");
          emailarea = new JTextArea();
          toarea = new JTextArea();  
          send = new JButton("Send");
          
      }
     public void setter(){
         copyright.setForeground(Color.RED);
         queries.setForeground(Color.RED);
         panel.setBounds(110,425,160,60);
         queriespanel.setBounds(110,5,190,20);
         emailpanel.setBounds(5,60,80,20);
         topanel.setBounds(5,100,80,20);
         toarea.setBounds(85,100,200,20);
         emailarea.setBounds(85,60,200,20);
         scroll.setBounds(85,140,200,200);
         send.setBounds(85,350,100,30);
         toarea.setText("teamwarrior200@gmail.com");
         toarea.setEditable(false);   
     }
     public void operation(){
         //email id of the receiver
         String to = "teamwarrior200@gmail.com";
         //email id of the sender
         String from = emailId.getText();
     }
}
