
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.search;

public class YoutubeDetail extends javax.swing.JFrame {

    String videoId;
    ArrayList<quality> al = new ArrayList<>();
    int vindex;
    public YoutubeDetail(String videoId,String title) {
        initComponents();
        
        int wd=Toolkit.getDefaultToolkit().getScreenSize().width;
        int ht=Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(wd,ht);
        
        titleLb.setText(title);
        this.getContentPane().setBackground(Color.white);
        successLb.setVisible(false);
        this.videoId = videoId;
        // to add component browser
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        this.add(view, BorderLayout.CENTER);
        browser.loadURL("https://www.youtube.com/embed/" + videoId);
        // this url is particular for video only 

        new Thread(new fetch_urls()).start();

        jpg.setStringPainted(true);
        
        panel.setPreferredSize(new Dimension(5000,50));
        view.setBounds(20, 50, 1300, 300);
        this.setTitle("Recipe Vedio");
        this.getContentPane().setBackground(Color.white);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        downBt = new javax.swing.JButton();
        jpg = new javax.swing.JProgressBar();
        successLb = new javax.swing.JLabel();
        titleLb = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        favMenu = new javax.swing.JMenu();
        offlineMenu = new javax.swing.JMenu();
        homeMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        panel.setBackground(new java.awt.Color(0, 0, 0));
        panel.setForeground(new java.awt.Color(255, 255, 255));
        panel.setMinimumSize(new java.awt.Dimension(2000, 100));
        panel.setLayout(null);
        sp.setViewportView(panel);

        getContentPane().add(sp);
        sp.setBounds(20, 420, 1300, 100);

        downBt.setBackground(new java.awt.Color(0, 0, 0));
        downBt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        downBt.setForeground(new java.awt.Color(255, 255, 255));
        downBt.setText("Download Now");
        downBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downBtActionPerformed(evt);
            }
        });
        getContentPane().add(downBt);
        downBt.setBounds(580, 590, 170, 40);

        jpg.setBackground(new java.awt.Color(0, 153, 0));
        jpg.setForeground(new java.awt.Color(0, 0, 0));
        jpg.setPreferredSize(new java.awt.Dimension(1300, 14));
        getContentPane().add(jpg);
        jpg.setBounds(20, 550, 1300, 14);

        successLb.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        successLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        successLb.setText("Congratulations! File Downloaded Successfully");
        getContentPane().add(successLb);
        successLb.setBounds(470, 540, 390, 30);

        titleLb.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        titleLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLb.setText("jLabel1");
        getContentPane().add(titleLb);
        titleLb.setBounds(20, 0, 1300, 40);

        jMenuBar1.setPreferredSize(new java.awt.Dimension(198, 30));

        favMenu.setText("MyFavourites");
        favMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                favMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(favMenu);

        offlineMenu.setText("OfflineRecipes");
        offlineMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                offlineMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(offlineMenu);

        homeMenu.setText("Home");
        homeMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(homeMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void downBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downBtActionPerformed
        new Thread(new Runnable()
        {
            public void run()
            {
                String vname=JOptionPane.showInputDialog(null,"Enter the name of the video");
                String urlpath=al.get(vindex).url;
                try {
                    
                    FileOutputStream fos=new FileOutputStream(System.getProperty("user.home")+File.separator+"Downloads"+File.separator+vname+".mp4");
                    URL url=new URL(urlpath);
                    URLConnection conn=url.openConnection();    

                    conn.setRequestProperty
                        ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

                    BufferedReader br=new BufferedReader(
                           new InputStreamReader(conn.getInputStream())
                    );
                    long size=conn.getContentLength();
                    InputStream is=conn.getInputStream();
                    
                    byte b[]=new byte[50000];
                    long count=0;
                    while(true)
                    {
                        int r=is.read(b,0,50000);
                        
                        if(r==-1)
                            break;
                        
                        count+=r;
                        int per=(int)((count*100)/size);
                        
                        jpg.setValue(per);
                        jpg.setString("File Downloaded:"+per+"%");
                        
                        fos.write(b, 0, r);
                        //System.out.println(r);
                    }
                    jpg.setVisible(false);
                    successLb.setVisible(true);
                    downBt.setEnabled(false);
                    //System.out.println("file downloaded");
                    fos.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"This quality not available\n    Try some other.");
                    Logger.getLogger(YoutubeDetail.class.getName()).log(Level.SEVERE, null, ex);
                } 
                
            }
        }).start();
    }//GEN-LAST:event_downBtActionPerformed

    private void favMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_favMenuMouseClicked
        File f=new File("favourite.txt");
        if(!f.exists())
        {
            JOptionPane.showMessageDialog(null,"   No recipe added to favourites");
        }
        else
        {
            ViewMyFavourite ob=new ViewMyFavourite();
        }
    }//GEN-LAST:event_favMenuMouseClicked

    private void offlineMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_offlineMenuMouseClicked
        File f=new File("offline recipes");
        if(f.exists())
        {
            ViewOffline ob=new ViewOffline();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"   No recipe saved offline");
        }
    }//GEN-LAST:event_offlineMenuMouseClicked

    private void homeMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMenuMouseClicked
        FetchAllRecipes ob=new FetchAllRecipes();
    }//GEN-LAST:event_homeMenuMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new YoutubeDetail("","").setVisible(true);
            }
        });
    }

    class fetch_urls implements Runnable {

        @Override
        public void run() {

            try {
                //Set URL
                URL url = new URL("https://www.youtube.com/watch?v=" + videoId + "");
                URLConnection spoof = url.openConnection();
                
                // Spoof the connection so we look like a web browser
                spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
                BufferedReader br = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
                String strLine = "";
                String gets = "";
                //Loop through every line in the source
                while ((strLine = br.readLine()) != null) {
                    //Prints each line to the console
                    if (strLine.contains("<script>var ytplayer")) {
                        if (gets.contains("</script>")) {
                            break;
                        }
                        gets = gets + strLine;
                    }
                }
                //System.out.println(gets);
                int index = 0;
                gets = gets.substring(gets.indexOf("url=") + 4);

                while (true) {

                    if (gets.indexOf("url=") == -1) {
                        break;
                    }
                    index = gets.indexOf("url=");
                    String gets1 = gets.substring(0, index);
                    String[] word
                            = {
                                "%2F", "%3A", "%3F", "%3D", "%26", "%252"
                            };
                    String[] word1
                            = {
                                "/", ":", "?", "=", "&", "%2"
                            };

                    for (int i = 0; i < word.length; i++) {
                        gets1 = gets1.replaceAll(word[i], word1[i]);
                    }

                    //System.out.println(gets1);
                    //System.out.println("====================================");
                    //     for(int j=0;j<videotype.length;j++)
                    //   {
                    String q[] = {"hd", "medium", "small"};
                    if (gets1.contains("signature") && gets1.contains("quality=")) {
                        String type = gets1.substring(gets1.indexOf("type=video/") + 11, gets1.indexOf("%", gets1.indexOf("type=video/")));

                        String type1 = gets1.substring(gets1.indexOf("quality=") + 8, gets1.length());
                        gets1 = gets1.substring(0, gets1.indexOf("\\u0026"));
                        for (int i = 0; i < q.length; i++) {

                            if (type1.contains(q[i])) {
                                type1 = q[i];
                                break;
                            }

                        }

                        //System.out.println(gets1);
                        //System.out.println(type + " " + type1);
                        //System.out.println("------------------------------------------------------");
                        String t=type + " " + type1;
                        t=t.trim();
                        if(!t.isEmpty())
                        {
                            al.add(new quality(type + "(" + type1 + ")", gets1));
                        }

                    }

                    //System.out.println("added");
                    // }
                    gets = gets.substring(index + 4);
                }

                
                CheckboxGroup cbg = new CheckboxGroup();
                Checkbox cb[] = new Checkbox[al.size()];
                
                int x = 10;
                for (int i = 0; i < al.size(); i++) 
                {
                    final int j=i;
                    cb[i] = new Checkbox(al.get(i).type, cbg, false);
                    cb[i].setBounds(x, 20, 100, 40);
                        
                    cb[i].addItemListener(new ItemListener()
                    {
                        public void itemStateChanged(ItemEvent e)
                        {
                            vindex=j;
                        }
                    });
                    
                    panel.add(cb[i]);
                    panel.repaint();
                    x = x + 150;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    class quality {

        String type, url;

        public quality(String type, String url) {
            this.type = type;
            this.url = url;
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downBt;
    private javax.swing.JMenu favMenu;
    private javax.swing.JMenu homeMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JProgressBar jpg;
    private javax.swing.JMenu offlineMenu;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane sp;
    private javax.swing.JLabel successLb;
    private javax.swing.JLabel titleLb;
    // End of variables declaration//GEN-END:variables
}
