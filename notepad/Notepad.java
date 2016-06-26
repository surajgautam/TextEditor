package notepad;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

//itemlistener for JCombobox, checkbox,menu
public class Notepad extends JFrame implements ActionListener {
    private static JTextArea area;
    private ImageIcon frameicon;
    private JMenu filemenu;
    private JMenu editmenu;
    private JMenu formatmenu;
    private JMenu helpmenu;
    private JScrollPane scroll;
    private Font font;
    private JMenuBar menubar;
    private JMenuItem newmenuitem;
    private JMenuItem openmenuitem;
    private JMenuItem savemenuitem;
    private JMenuItem saveasmenuitem;
    private JMenuItem exitmenuitem;
    private JMenuItem cutmenuitem;
    private JMenuItem copymenuitem;
    private JMenuItem pastemenuitem;
    private JMenuItem selectallmenuitem;
    //we can add JMenu inside JMenu 
    private JMenu fontmenu;
    private JMenuItem arial;
    private JMenuItem candara;
    private JMenuItem timesnewroman;
    private JMenuItem verdana;
    //font size
    private JMenu fontsize;
    private JMenuItem tenpx;
    private JMenuItem twentypx;
    private JMenuItem thirtypx;
    private JMenuItem fortypx;
    private JMenuItem fiftypx;
    private JMenuItem sixtypx;
    private JMenuItem seventypx;
    private JMenuItem thirtyfivepx;
    private JMenuItem fortyfivepx;
    private JMenuItem fiftyfivepx;
    private JMenuItem seventytwopx;
    private JMenuItem viewhelp;
    private int msg;
    private int returnVal;
    private final JFileChooser choose;
    private final JFileChooser save;
    private JMenu extras;
    private JMenuItem sourcecode;

    public Notepad() {
        initComponents();
        setComponents();

        setTitle("Hamro Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 100);
        setResizable(true);
        setSize(600, 600);

        //adding Event for Close button of JFrame
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFileChooser j = new JFileChooser();
                BufferedWriter br = null;
                FileNameExtensionFilter fnef = new FileNameExtensionFilter("TextFiles", "txt", "doc", "pdf", "html", "php");
                j.setFileFilter(fnef);
                msg = JOptionPane.showConfirmDialog(menubar, "DO you want to save changes?");
                if (msg == JOptionPane.YES_OPTION) {
                    int returnValue = j.showSaveDialog(area);
                    File file = j.getSelectedFile();
                    try {
                        if (file != null) {
                            //it must be done because if the user selects no file then file has value null
                            //which throws NULLPOINTER EXCEPTION
                            br = new BufferedWriter(new FileWriter(file));
                        }
                    } catch (IOException ex) {

                    }
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        try {
                            area.write(br);

                        } catch (IOException ex) {
                            Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            br.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(filemenu, "File Has been saved!!!", "Save Option", JOptionPane.INFORMATION_MESSAGE);
                    }

                } //if no clicked, the frame will exit
                else if (msg == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } //if cancel click it will do nothing but still run
                else if (msg == JOptionPane.CANCEL_OPTION) {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        setJMenuBar(menubar);

        helpmenu.add(viewhelp);
        menubar.add(filemenu);
        menubar.add(editmenu);
        menubar.add(formatmenu);
        menubar.add(helpmenu);
        menubar.add(extras);
        filemenu.add(newmenuitem);
        filemenu.add(openmenuitem);
        filemenu.add(savemenuitem);
        filemenu.add(saveasmenuitem);
        filemenu.add(exitmenuitem);
        editmenu.add(copymenuitem);
        editmenu.add(cutmenuitem);
        editmenu.add(pastemenuitem);
        editmenu.add(selectallmenuitem);
        formatmenu.add(fontmenu);
        //adding fonts to fontmenu
        fontmenu.add(arial);
        fontmenu.add(candara);
        fontmenu.add(verdana);
        fontmenu.add(timesnewroman);
        //adding sizes to fontsize menu
        formatmenu.add(fontsize);
        fontsize.add(tenpx);
        fontsize.add(twentypx);
        fontsize.add(thirtypx);
        fontsize.add(thirtyfivepx);
        fontsize.add(fortypx);
        fontsize.add(fortyfivepx);
        fontsize.add(fiftypx);
        fontsize.add(fiftyfivepx);
        fontsize.add(sixtypx);
        fontsize.add(seventypx);
        fontsize.add(seventytwopx);
        extras.add(sourcecode);
        choose = new JFileChooser("C:\\Users\\Suraj Gautam\\Desktop");
        //must be initialized before setVIsible
        save = new JFileChooser("C:\\Users\\Suraj Gautam\\Desktop");
        add(scroll);

        setIconImage(frameicon.getImage());
        setVisible(true);

    }

    public final void initComponents() {
        area = new JTextArea();
        //if area is initialized after scroll then textarea will be disabled
        scroll = new JScrollPane(area, //no need of add textArea when added in JScrollPane
          JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        menubar = new JMenuBar();
        filemenu = new JMenu("  File");
        editmenu = new JMenu("    Edit");
        formatmenu = new JMenu("    Format");
        helpmenu = new JMenu("    Help");
        newmenuitem = new JMenuItem("    New");
        openmenuitem = new JMenuItem("    Open");
        savemenuitem = new JMenuItem("    Save");
        saveasmenuitem = new JMenuItem("    Save As");
        exitmenuitem = new JMenuItem("    Exit");
        cutmenuitem = new JMenuItem("    Cut");
        copymenuitem = new JMenuItem("    Copy");
        selectallmenuitem = new JMenuItem("    SelectAll");
        pastemenuitem = new JMenuItem("    Paste");
        fontmenu = new JMenu("    Font");
        arial = new JMenuItem("    Arial");
        candara = new JMenuItem("    Candara");
        verdana = new JMenuItem("    Verdana");
        timesnewroman = new JMenuItem("    Times New Roman");
        //size initialization
        fontsize = new JMenu("    FontSize");
        tenpx = new JMenuItem("    10px");
        twentypx = new JMenuItem("    20px");
        thirtypx = new JMenuItem("    30px");
        thirtyfivepx = new JMenuItem("    35px");
        fortypx = new JMenuItem("    40px");
        fortyfivepx = new JMenuItem("    45px");
        fiftypx = new JMenuItem("    50px");
        fiftyfivepx = new JMenuItem("    55px");
        sixtypx = new JMenuItem("    60px");
        seventypx = new JMenuItem("    70px");
        seventytwopx = new JMenuItem("    72px");
        viewhelp = new JMenuItem("    View Help");
        font = new Font("Calibri", Font.PLAIN, 26);
        frameicon = new ImageIcon(getClass().getResource("/res/setting.png"));
        extras = new JMenu("    Extras");
        sourcecode = new JMenuItem("    SourceCode Generator");
        
    }

    public final void setComponents() {

        // area.setSize(600,600);
        area.setBackground(Color.WHITE);
        area.setFont(font);
        //adding ActionListener
        newmenuitem.addActionListener(this);
        exitmenuitem.addActionListener(this);
        openmenuitem.addActionListener(this);
        savemenuitem.addActionListener(this);
        saveasmenuitem.addActionListener(this);
        copymenuitem.addActionListener(this);
        cutmenuitem.addActionListener(this);
        copymenuitem.addActionListener(this);
        pastemenuitem.addActionListener(this);
        selectallmenuitem.addActionListener(this);
        arial.addActionListener(this);
        candara.addActionListener(this);
        verdana.addActionListener(this);
        timesnewroman.addActionListener(this);
        tenpx.addActionListener(this);
        twentypx.addActionListener(this);
        thirtypx.addActionListener(this);
        thirtyfivepx.addActionListener(this);
        fortypx.addActionListener(this);
        fortyfivepx.addActionListener(this);
        fiftypx.addActionListener(this);
        fiftyfivepx.addActionListener(this);
        sixtypx.addActionListener(this);
        seventypx.addActionListener(this);
        seventytwopx.addActionListener(this);
        viewhelp.addActionListener(this);
        sourcecode.addActionListener(this);
    }

    @Override

    public void actionPerformed(ActionEvent e) {
        //started work for file
        //if newmenuitemclicked
        if (e.getSource() == newmenuitem) {
            JFileChooser j = new JFileChooser();
            BufferedWriter br = null;
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("TextFiles", "txt", "doc", "pdf", "html", "php");
            j.setFileFilter(fnef);

            File file = j.getSelectedFile();

            if (!"".equals(area.getText().trim())) {
                msg = JOptionPane.showConfirmDialog(menubar, "DO you want to save changes?");
                if (msg == JOptionPane.YES_OPTION) {
                    int returnValue = j.showSaveDialog(area);
                    try {
                        if (file != null) {
                            //it must be done because if the user selects no file then file has value null
                            //which throws NULLPOINTER EXCEPTION
                            br = new BufferedWriter(new FileWriter(file));
                        }
                    } catch (IOException ex) {

                    }
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        try {
                            area.write(br);
                            br.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(filemenu, "File Has been saved!!!", "Save Option", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

                if (msg == JOptionPane.NO_OPTION) {
                    area.setText("");
                }

            }

        }

        if (e.getSource() == exitmenuitem) {
            msg = JOptionPane.showConfirmDialog(menubar, "Are you sure you want to exit?");
            if (msg == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        }
        if (e.getSource() == openmenuitem) {
            JFileChooser chooser = new JFileChooser("C:\\Users\\Suraj Gautam\\Desktop");
            String data;
            chooser.setFileFilter(new FileNameExtensionFilter("TextFiles", "txt", "doc", "pdf", "html", "bat", ".php"));
            returnVal = chooser.showOpenDialog(area.getParent());

            try {
                BufferedWriter brw = null;
                FileReader in = null;
                BufferedReader br = null;
                try {
                    File file = chooser.getSelectedFile();
                    in = new FileReader(file);
                    if (file != null) {
                        br = new BufferedReader(in);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }

                try {
                    if (area.getText().trim().equals("")) {
                        while ((data = br.readLine()) != null) {
                            area.append(data);
                            area.append("\n");
                        }
                    } else {
                        msg = JOptionPane.showConfirmDialog(menubar, "DO you want to save changes?");
                        if (msg == JOptionPane.YES_OPTION) {
                            int savedialog = chooser.showSaveDialog(area);
                            if (savedialog == JFileChooser.APPROVE_OPTION) {
                                File file = chooser.getSelectedFile();
                                if (file != null) {
                                    brw = new BufferedWriter(new FileWriter(file));
                                }
                                area.write(brw);
                                brw.close();
                                JOptionPane.showMessageDialog(filemenu, "File Has been saved!!!", "Save Option", JOptionPane.INFORMATION_MESSAGE);
                                area.setText("");
                                while ((data = br.readLine()) != null) {
                                    area.append(data);
                                    area.append("\n");
                                }
                            }

                        } else if (msg == JOptionPane.NO_OPTION) {
                            area.setText("");
                            while ((data = br.readLine()) != null) {
                                area.append(data);
                                area.append("\n");
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NullPointerException exx) {

            }
        }
        if (e.getSource() == savemenuitem) {
            FileNameExtensionFilter fne = new FileNameExtensionFilter("TextFiles", "txt", "doc", "pdf", "html", "bat", "php");
            save.setFileFilter(fne);
            int returnvalue = save.showSaveDialog(menubar);
            File f = save.getSelectedFile();
            BufferedWriter br = null;
            try {
                if (f != null) {
                    br = new BufferedWriter(new FileWriter(f));
                }
            } catch (IOException ex) {
                System.out.println(e);
            }

            if (returnvalue == JFileChooser.APPROVE_OPTION) {
                try {
                    area.write(br);
                } catch (IOException ex) {

                }
                try {
                    br.close();
                } catch (IOException ex) {

                }
               
                JOptionPane.showMessageDialog(filemenu, "File Has been saved!!!", "Save Option", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        if (e.getSource() == saveasmenuitem) {
            BufferedWriter br = null;
            JFileChooser j = new JFileChooser();
            //filechooser provides us dialog boxes such as Open, Save 
            FileNameExtensionFilter f = new FileNameExtensionFilter("TextFiles", "txt", "doc", "pdf", "html", "bat", "php");
            //to filter extensions like .txt,.doc we use FileNameExtensionFilter
            j.setFileFilter(f); //we have to set it
            int opt = j.showSaveDialog(area); //showSaveDialog will return integer and show Save Dialog box
            if (opt == JFileChooser.APPROVE_OPTION) { //If file choosed then it will return 0
                File file = j.getSelectedFile();
                int data = 0;
                String name = file.getName();
                boolean exist = file.exists();
                if (file != null) { //if file is not selected it returns null
                    //so prevent from NullPointerException use if condition
                    try {
                        br = new BufferedWriter(new FileWriter(file));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (exist == true) {
                    data = JOptionPane.showConfirmDialog(formatmenu, "File Exists! DO you want to replace it?", "Error", JOptionPane.YES_NO_OPTION);
                }
                if (data == JOptionPane.YES_OPTION) {
                    try {
                        area.write(br);
                    } catch (IOException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(formatmenu, "File Has Been Saved");
                }
            }

        }

//for edit menu
        if (e.getSource() == copymenuitem) {
            area.copy();
        }

        if (e.getSource() == cutmenuitem) {
            area.cut();
        }
        if (e.getSource() == selectallmenuitem) {
            area.selectAll();
        }
        if (e.getSource() == pastemenuitem) {
            area.paste();
        }
        //for format menu 
        if (e.getSource() == arial) {
            area.setFont(new Font("Arial", Font.PLAIN, 26));
        }
        if (e.getSource() == candara) {
            area.setFont(new Font("Candara", Font.PLAIN, 26));
        }
        if (e.getSource() == verdana) {
            area.setFont(new Font("Verdana", Font.PLAIN, 26));
        }
        if (e.getSource() == timesnewroman) {
            area.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        }
        //for font size
        //here getFont returns Font so getFontName() method will reuturn String name
        if (e.getSource() == tenpx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 10));
        }
        if (e.getSource() == twentypx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 20));
        }
        if (e.getSource() == thirtypx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 30));
        }
        if (e.getSource() == fortypx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 40));
        }
        if (e.getSource() == thirtyfivepx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 35));
        }
        if (e.getSource() == fortyfivepx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 45));
        }
        if (e.getSource() == fiftypx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 50));
        }
        if (e.getSource() == sixtypx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 60));
        }
        if (e.getSource() == fiftyfivepx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 55));
        }
        if (e.getSource() == seventypx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 70));
        }
        if (e.getSource() == seventytwopx) {
            Font s = area.getFont();
            area.setFont(new Font(s.getFontName(), Font.PLAIN, 72));
        }
        if (e.getSource() == viewhelp) {
            new HelpFrame();
        }
        if(e.getSource()==sourcecode){
            String value = JOptionPane.showInputDialog(area.getParent(), "Enter the URL \n format:http://gautamsuraj.com.np");
            if(value!=null){
               CodeGenerator(value.trim());
        }
            if(value == null){
                
            }
        }
       
    }
    public void CodeGenerator(String data){
        String ndata;
        URL u;
        URLConnection urlc = null;
        try {
            u = new URL(data);
            urlc = u.openConnection();
            InputStream in = urlc.getInputStream();
            //BufferedReader's constructor needs Reader i.e InputStreamReader and 
            //InputStreamReader needs InputStream
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while((ndata=br.readLine())!= null){
                area.append(ndata);
                area.append("\n");
            }
        } catch (MalformedURLException ex ) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
        
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = null;
        File file;
        String data;
        Notepad n = new Notepad();
        if(args.length>0){
            file = new File(args[0]);
            br = new BufferedReader(new FileReader(file));
            while((data = br.readLine())!=null){
                Notepad.area.append(data);
                Notepad.area.append("\n");
            }
        }
        
    }
}
