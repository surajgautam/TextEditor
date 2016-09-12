package notepad;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public final class HelpFrame extends JFrame implements ActionListener{
    private JPanel passpanel;
    private JPasswordField password;
    private JLabel passlabel;
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

    HelpFrame() {
        initialize();
        setter();
        setTitle("HELP");
        setLocation(500, 100);
        setResizable(false);
        setLayout(null);//to use setBounds we have to do setLayout as null
        setSize(400, 500);
        this.add(panel);
        panel.add(copyright);
        this.add(queriespanel);
        queriespanel.add(queries);
        this.add(emailpanel);
        this.add(topanel);
        emailpanel.add(emailId);
        topanel.add(to);
        this.add(passpanel);
        passpanel.add(passlabel);
        this.add(password);
        this.add(toarea);
        this.add(emailarea);
        this.add(scroll);
        this.add(send);
        setVisible(true);

    }

    public void initialize() {
        //use html tags for breaking lines
        text = new JTextArea(20, 20);
        //if text is initialized after scroll then textarea will be disabled
        scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        copyright = new JLabel("<html>Version:1.0.0 <br/> ©2016. All rights reserved</html>");
        panel = new JPanel();
        emailpanel = new JPanel();
        topanel = new JPanel();
        queries = new JLabel("If any queries, feel free to mail me");
        queriespanel = new JPanel();
        emailId = new JLabel("Email ID:");
        passlabel = new JLabel("Password:");
        password = new JPasswordField();
        passpanel = new JPanel();
        to = new JLabel("TO:");
        emailarea = new JTextArea();
        toarea = new JTextArea();
        send = new JButton("Send");
        send.addActionListener(this);

    }

    public void setter() {
        copyright.setForeground(Color.RED);
        queries.setForeground(Color.RED);
        panel.setBounds(110, 425, 160, 60);
        queriespanel.setBounds(110, 5, 190, 20);
        emailpanel.setBounds(5, 60, 80, 20);
        passpanel.setBounds(5,100,100,20);
        password.setBounds(105,100,80,20);
        topanel.setBounds(5, 140, 80, 20);
        toarea.setBounds(85, 140, 200, 20);
        emailarea.setBounds(85, 60, 200, 20);
        scroll.setBounds(85, 180, 200, 200);
        send.setBounds(85, 390, 100, 30);
        toarea.setText("teamwarrior200@gmail.com");
        toarea.setEditable(false);
    }

    public void operation() {
        //email id of the receiver
        String to = "teamwarrior200@gmail.com";
        //email id of the sender
        String from = emailId.getText();
    }

    public void sendMail() {
        Properties props = System.getProperties();
        props.put("mail.smtp.user", emailarea.getText());
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailarea.getText().trim(), new String(password.getPassword()));
            }

        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailarea.getText().trim()));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress("teamwarrior200@gmail.com"));

            message.setSubject("");

            message.setText(text.getText());

            Transport.send(message);

            System.out.println("Successfully sent");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==send){
            sendMail();
        }
    }
}
