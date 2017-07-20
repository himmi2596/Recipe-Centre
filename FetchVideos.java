
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class FetchVideos extends javax.swing.JFrame {

    String recipetitle;
    int wd,ht;
    public FetchVideos(String recipeTitle) {
        initComponents();
        
        this.recipetitle=recipeTitle;
        
        titleLb.setText(recipeTitle);
        wd=Toolkit.getDefaultToolkit().getScreenSize().width;
        ht=Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(wd,ht);
        //System.out.println(wd+" "+ht);
        
        
        new Thread(new Runnable()
        {
            public void run()
            {
                mainPanel.removeAll();
                mainPanel.repaint();
                try{
                    recipetitle = recipetitle.replaceAll(" ", "%20");   // because space will give error as in url, space is encoded as %20
                // fetch info from json format
                URL videoUrl=new URL("https://www.googleapis.com/youtube/v3/search?key="+Credentials.YOUTUBE_API+"&part=snippet&q="+recipetitle+"&maxResults=20&type=video");
                // we have to give type=video so that channels will not come
                
                URLConnection conn=videoUrl.openConnection();

                conn.setRequestProperty
                            ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

                BufferedReader br=new BufferedReader(
                               new InputStreamReader(conn.getInputStream())
                );

                String str;
                String s="";
                while(true)
                {
                    str=br.readLine();
                    if(str==null)
                    {
                        break;
                    }
                    s=s+str;
                }

                JSONParser jp=new JSONParser();
                JSONObject job=(JSONObject)jp.parse(s);

                JSONArray itemArr=(JSONArray) job.get("items");

                VedioPan vp[]=new VedioPan[itemArr.size()];
                mainPanel.setPreferredSize(new Dimension(1000,5000));
                int y=20;
                for(int i=0; i<itemArr.size(); i++)
                {
                    String videoId;
                    String title;
                    String description;
                    JSONObject arobj=(JSONObject) itemArr.get(i);
                    JSONObject idobj=(JSONObject) arobj.get("id");
                    videoId=(String) idobj.get("videoId");


                    JSONObject spobj=(JSONObject) arobj.get("snippet");
                    title=(String) spobj.get("title");
                    description=(String) spobj.get("description");


                    /*System.out.println("VEDIO_ID   "+videoId);
                    System.out.println("TITLE   "+title);
                    System.out.println("DESC   "+description);
                    System.out.println("***************************************************");
                    */
                    //                   for getting thumbnail
                     vp[i]=new VedioPan();

                    vp[i].titleLb.setText("<html>"+title+"</html>");
                    vp[i].descLb.setText("<html>"+description+"</html>");
                    try
                    {
                        URL urlImage=new URL("https://i.ytimg.com/vi/"+videoId+"/mqdefault.jpg");
                        //System.out.println(videoId);
                        URLConnection conn1=urlImage.openConnection();

                        conn1.setRequestProperty
                            ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

                        BufferedImage im=ImageIO.read(conn1.getInputStream());
                        im=FetchAllRecipes.resize(im,220,170);
                        vp[i].thumbLb.setIcon(new ImageIcon(im));

                    }

                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }



                    vp[i].setBounds(20,y,1250,200);

                    mainPanel.add(vp[i]);
                    mainPanel.repaint();
                    
                    vp[i].addMouseListener(new MouseAdapter()
                    {
                        public void mouseClicked(MouseEvent e)
                        {
                            if(e.getClickCount()==2)
                            {
                                YoutubeDetail obj=new YoutubeDetail(videoId,recipeTitle);
                            }
                        }
                    });

                    y=y+220;
                }

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start(); 
        
        this.setTitle("Youtube Videos");
        this.getContentPane().setBackground(Color.white);
        this.setVisible(true);
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        titleLb = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        favMenu = new javax.swing.JMenu();
        offlineMenu = new javax.swing.JMenu();
        homeMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));
        mainPanel.setLayout(null);
        sp.setViewportView(mainPanel);

        getContentPane().add(sp);
        sp.setBounds(20, 110, 1300, 550);

        titleLb.setBackground(new java.awt.Color(255, 255, 255));
        titleLb.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        titleLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(titleLb);
        titleLb.setBounds(24, 20, 1290, 70);

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
                new FetchVideos("").setVisible(true);
            }
        });
    }

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu favMenu;
    private javax.swing.JMenu homeMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenu offlineMenu;
    private javax.swing.JScrollPane sp;
    private javax.swing.JLabel titleLb;
    // End of variables declaration//GEN-END:variables
}
