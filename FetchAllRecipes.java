
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FetchAllRecipes extends javax.swing.JFrame {

    int wd,ht;
    public FetchAllRecipes() {
        
        
        initComponents();
       
        wd=Toolkit.getDefaultToolkit().getScreenSize().width;
        ht=Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(wd,ht);
        //this.setSize(800,700);
        this.setTitle("Welcome to Recipe Center");
        this.getContentPane().setBackground(Color.white);
        this.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        tf1 = new javax.swing.JTextField();
        fetchBt = new javax.swing.JButton();
        sp = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        favMenu = new javax.swing.JMenu();
        offlineMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lb1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb1.setText("Enter Recipe Name");
        getContentPane().add(lb1);
        lb1.setBounds(20, 20, 200, 30);
        getContentPane().add(tf1);
        tf1.setBounds(20, 50, 200, 30);

        fetchBt.setBackground(new java.awt.Color(0, 0, 0));
        fetchBt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        fetchBt.setForeground(new java.awt.Color(255, 255, 255));
        fetchBt.setText("Fetch");
        fetchBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fetchBtActionPerformed(evt);
            }
        });
        getContentPane().add(fetchBt);
        fetchBt.setBounds(250, 50, 80, 30);

        MainPanel.setBackground(new java.awt.Color(0, 0, 0));
        MainPanel.setLayout(null);
        sp.setViewportView(MainPanel);

        getContentPane().add(sp);
        sp.setBounds(20, 110, 1300, 500);

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

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fetchBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fetchBtActionPerformed
        new Thread(new Fetch()).start();
        
        // thread.interrupt() so that it will not interrupt
        // thread.stop();
    }//GEN-LAST:event_fetchBtActionPerformed

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

        
     class Fetch implements Runnable
     {
         public void run()
         {
             try
            {
                 MainPanel.setPreferredSize(new Dimension(1400,5000));  // object is passed in preferred sizeof Pannel added on scroll pane so that scroll pane will be visible

                 MainPanel.removeAll();
                 MainPanel.repaint();   // so that at every click previous data remove and new visible
                 
                String search=tf1.getText();
                URL url=new URL("http://food2fork.com/api/search?key="+Credentials.FOOD_TO_FORK_API+"&q="+search);
                URLConnection conn=url.openConnection();    

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
               
                // read Json format
                JSONParser jp=new JSONParser();

                JSONObject objp= (JSONObject) jp.parse(s);   // here we parse the string obtained and put it in object
                // type casting is done because by default it returns object type

                long count=(long)objp.get("count");
//                System.out.println("COUNT: "+count);
//                System.out.println("");

                JSONArray rar=(JSONArray)objp.get("recipes");  // array in json object
                
                SingleRecipePan srp[]=new SingleRecipePan[(int)count];  //array of panel of image n title
                
                int x=20,y=20;
                for(int i=0; i<rar.size(); i++)
                    {
                        JSONObject obrar=(JSONObject)rar.get(i);   // extract objects from array
                        String title=(String)obrar.get("title");
                        String imageUrl=(String)obrar.get("image_url");
                        String rid=(String)obrar.get("recipe_id");
                        //on console
                     /*   System.out.println("TITLE: "+obrar.get("title"));
                        System.out.println("IMAGE URL: "+obrar.get("image_url"));
                        System.out.println("PUBLISHER: "+obrar.get("publisher"));
                        System.out.println("RECIPE ID: "+obrar.get("recipe_id"));
                        System.out.println("-------------------------------------"); */
                        
                        srp[i]=new SingleRecipePan();   // everytime new panel is created dynamically
                        
                        srp[i].titleLabel.setText("<html> "+title+" </html>");   //adjust label automatically
                        srp[i].picLabel.setText(imageUrl);
                        
                        
                        URL urlImage=new URL(imageUrl);
                        URLConnection conn1=urlImage.openConnection();
                        
                        conn1.setRequestProperty
                        ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
                                   
                        /*
                        Image im=ImageIO.read(conn1.getInputStream());  //ImageIO is a class and read is static function
                        im=im.getScaledInstance(srp[i].picLabel.getWidth(),srp[i].picLabel.getHeight(),Image.SCALE_SMOOTH);  //similar to resize
                        */
                                 //image to buffer
                        BufferedImage im=ImageIO.read(conn1.getInputStream());
                        BufferedImage im1=FetchAllRecipes.resize(im,190,160);
                        srp[i].picLabel.setIcon(new ImageIcon(im1));
                        
                        
                        srp[i].setBounds(x,y,250,250);
                        
                        // add Listener before adding panel to main frame
                        
                        // work on double click on panel
                        srp[i].addMouseListener(new MouseAdapter()
                        {
                            public void mouseClicked(MouseEvent e)
                            {
                                if(e.getClickCount()==2)
                                {
                                    RecipeDetail rd=new RecipeDetail(title,imageUrl,rid);
                                    FetchAllRecipes.this.dispose();
                                }
                            }
                        });
                        
                        MainPanel.add(srp[i]);
                        
                        MainPanel.repaint();
                        
                        if(x<1300)
                        {
                            x=x+280;
                        }
                        else
                        {
                            y=y+280;
                            x=20;
                        }
                    }
            }
            catch(Exception ex)
            {
                Logger.getLogger(Fetch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FetchAllRecipes().setVisible(true);
            }
        });
    }

    
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
//        System.out.println(bi);
        return bi;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JMenu favMenu;
    private javax.swing.JButton fetchBt;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lb1;
    private javax.swing.JMenu offlineMenu;
    private javax.swing.JScrollPane sp;
    private javax.swing.JTextField tf1;
    // End of variables declaration//GEN-END:variables
}
