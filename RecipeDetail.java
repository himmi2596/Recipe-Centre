
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RecipeDetail extends javax.swing.JFrame {

    String title,imageUrl,rid;
    String sourceUrl;
    String procedure;
    String ingredients;
    int wd,ht;
    public RecipeDetail(String title,String imageUrl,String rid) {
        initComponents();
        
        this.title=title;
        this.imageUrl=imageUrl;
        this.rid=rid;
        
        lbTitle.setText("<html>"+title+"</html>");
        
        webBt.setVisible(false);
        unlikeBt.setVisible(false);
        
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                URL urlImage=new URL(imageUrl);
                URLConnection conn=urlImage.openConnection();

                conn.setRequestProperty
                    ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

                BufferedImage im=ImageIO.read(conn.getInputStream());
                im=FetchAllRecipes.resize(im,280,120);

                lbImage.setIcon( new ImageIcon(im));
                }
                catch(Exception ex)
                {
                    Logger.getLogger(RecipeDetail.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        
        
        new Thread( new FetchDetail()).start();
                         
        wd=Toolkit.getDefaultToolkit().getScreenSize().width;
        ht=Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(wd,ht);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.setTitle(title+" Details");
        
        
        
        // to check if recipe present in favourites or not
        // read file
        try
        {
            File f=new File("favourite.txt");
            if(f.exists())
            {
                FileInputStream fis=new FileInputStream(f);
                BufferedReader br=new BufferedReader(new InputStreamReader(fis));
                
                String fileContent="";
                while(true)
                {
                    String s=br.readLine();
                    if(s==null)
                    {
                        break;
                    }
                    fileContent+=s;
                }
                
                if(fileContent.contains("~"+rid+"~"))   // the whole string is given bcoz otherwise it may consider a wrong is like if we search for 14 then it may give 414 as result
                {
                    likeBt.setVisible(false);
                    unlikeBt.setVisible(true);
                }
                else
                {
                    likeBt.setVisible(true);
                    unlikeBt.setVisible(false);
                }
                fis.close();
            }
            else
            {
                likeBt.setVisible(true);
                unlikeBt.setVisible(false);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleimgPanel = new javax.swing.JPanel();
        lbImage = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        likeBt = new javax.swing.JButton();
        unlikeBt = new javax.swing.JButton();
        offlineBt = new javax.swing.JButton();
        videoBt = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        lbIng = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        sp = new javax.swing.JScrollPane();
        ingPanel = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        ProPanel = new javax.swing.JPanel();
        proLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        proEditor = new javax.swing.JEditorPane();
        webBt = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        favMenu = new javax.swing.JMenu();
        offlineMenu = new javax.swing.JMenu();
        homeMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(null);

        titleimgPanel.setBackground(new java.awt.Color(255, 255, 255));
        titleimgPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleimgPanel.setLayout(null);
        titleimgPanel.add(lbImage);
        lbImage.setBounds(10, 11, 280, 120);

        lbTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTitle.setText("title");
        titleimgPanel.add(lbTitle);
        lbTitle.setBounds(300, 20, 560, 100);

        likeBt.setBackground(new java.awt.Color(0, 0, 0));
        likeBt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        likeBt.setForeground(new java.awt.Color(255, 255, 255));
        likeBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/unlike.png"))); // NOI18N
        likeBt.setText("Like");
        likeBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                likeBtActionPerformed(evt);
            }
        });
        titleimgPanel.add(likeBt);
        likeBt.setBounds(1060, 10, 160, 30);

        unlikeBt.setBackground(new java.awt.Color(0, 0, 0));
        unlikeBt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        unlikeBt.setForeground(new java.awt.Color(255, 255, 255));
        unlikeBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/like.png"))); // NOI18N
        unlikeBt.setText("Unlike");
        unlikeBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unlikeBtActionPerformed(evt);
            }
        });
        titleimgPanel.add(unlikeBt);
        unlikeBt.setBounds(1060, 10, 160, 30);

        offlineBt.setBackground(new java.awt.Color(0, 0, 0));
        offlineBt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        offlineBt.setForeground(new java.awt.Color(255, 255, 255));
        offlineBt.setText("Save Offline");
        offlineBt.setEnabled(false);
        offlineBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offlineBtActionPerformed(evt);
            }
        });
        titleimgPanel.add(offlineBt);
        offlineBt.setBounds(1060, 50, 160, 40);

        videoBt.setBackground(new java.awt.Color(0, 0, 0));
        videoBt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        videoBt.setForeground(new java.awt.Color(255, 255, 255));
        videoBt.setText("View Video");
        videoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                videoBtActionPerformed(evt);
            }
        });
        titleimgPanel.add(videoBt);
        videoBt.setBounds(1060, 100, 160, 30);

        getContentPane().add(titleimgPanel);
        titleimgPanel.setBounds(10, 10, 1300, 140);

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));
        mainPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mainPanel.setLayout(null);

        lbIng.setBackground(new java.awt.Color(255, 255, 255));
        lbIng.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbIng.setForeground(new java.awt.Color(255, 255, 255));
        lbIng.setText(" Ingredients");
        lbIng.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel.add(lbIng);
        lbIng.setBounds(40, 0, 1280, 40);

        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/iii.png"))); // NOI18N
        lbIcon.setText("jLabel1");
        lbIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mainPanel.add(lbIcon);
        lbIcon.setBounds(0, 0, 40, 40);

        ingPanel.setBackground(new java.awt.Color(255, 255, 255));
        ingPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ingPanel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ingPanel.setLayout(null);

        sidePanel.setBackground(new java.awt.Color(0, 0, 0));
        sidePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1994, Short.MAX_VALUE)
        );

        ingPanel.add(sidePanel);
        sidePanel.setBounds(0, 0, 40, 2000);

        sp.setViewportView(ingPanel);

        mainPanel.add(sp);
        sp.setBounds(0, 40, 1300, 200);

        getContentPane().add(mainPanel);
        mainPanel.setBounds(10, 170, 1300, 240);

        ProPanel.setBackground(new java.awt.Color(0, 0, 0));
        ProPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ProPanel.setLayout(null);

        proLabel.setBackground(new java.awt.Color(255, 255, 255));
        proLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        proLabel.setForeground(new java.awt.Color(255, 255, 255));
        proLabel.setText("  Procedure");
        ProPanel.add(proLabel);
        proLabel.setBounds(0, 0, 220, 40);

        proEditor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        proEditor.setContentType("text/html"); // NOI18N
        proEditor.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(proEditor);

        ProPanel.add(jScrollPane1);
        jScrollPane1.setBounds(8, 42, 1280, 210);

        webBt.setBackground(new java.awt.Color(255, 255, 255));
        webBt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        webBt.setText("View On Browser");
        webBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webBtActionPerformed(evt);
            }
        });
        ProPanel.add(webBt);
        webBt.setBounds(270, 10, 190, 20);

        getContentPane().add(ProPanel);
        ProPanel.setBounds(10, 430, 1300, 260);

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

    private void webBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webBtActionPerformed
            try {
                Desktop.getDesktop().browse(       // to get browser info on our desktop 
                        new URI(sourceUrl)
                );
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(RecipeDetail.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (URISyntaxException ex)
            {
            Logger.getLogger(RecipeDetail.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_webBtActionPerformed

    private void likeBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_likeBtActionPerformed

        try
        {
            FileOutputStream fos=new FileOutputStream("favourite.txt",true);  // file created in project only
            PrintWriter pw=new PrintWriter(fos);
            
            pw.println("~"+title+"~"+imageUrl+"~"+rid+"~"+";"); // seperator is needed to see the new string
            pw.flush();
            
            fos.close();
            
            likeBt.setVisible(false);
            unlikeBt.setVisible(true);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        JOptionPane.showMessageDialog(RecipeDetail.this,"Recipe added to your favourites");
    }//GEN-LAST:event_likeBtActionPerformed

    private void unlikeBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unlikeBtActionPerformed

        try
        {
            FileInputStream fis=new FileInputStream("favourite.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            
            FileOutputStream fos=new FileOutputStream("favtemp.txt");    //copy the main file here except the one we unlike
            PrintWriter pw=new PrintWriter(fos);
            
            while(true)
            {
                String s=br.readLine();
                if(s==null)
                {
                    break;
                }
                else if(s.contains("~"+rid+"~"))
                {
                }
                else
                {
                    pw.println(s);
                }
            }
            
            pw.flush();
            fis.close();
            fos.close();
            
            File f=new File("favourite.txt");
            f.delete();
            
            File f1=new File("favtemp.txt");      // again make the file with same name 
            File f2=new File("favourite.txt");
            
            f1.renameTo(f2);                 // rename temp file to favourite 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        likeBt.setVisible(true);
        unlikeBt.setVisible(false);
    }//GEN-LAST:event_unlikeBtActionPerformed

    private void offlineBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offlineBtActionPerformed

            File folder=new File("offline recipes");      // folder name
            if(!folder.exists())
            {
                folder.mkdir();                 // make directory or folder
            }

            // CHECK WHETHER THE FILE ALREADY EXITS IN THE OFFLINE FOLDER
            int count=0;
            File f_n[]=folder.listFiles();

            for(int i=0; i<f_n.length; i++)
            {
                if(f_n[i].getName().equals(rid+".html"))
                {
                    int ans=JOptionPane.showConfirmDialog(RecipeDetail.this,"Already Saved!\nDo you want to save again","Confirm",JOptionPane.YES_NO_OPTION);
                    if(ans==JOptionPane.YES_OPTION)
                    {
                        count=1;
                    }
                    else
                    {
                        count=2;
                    }
                }
            }
            if (count==0 || count==1)
            {
                try {
                    FileOutputStream fos=new FileOutputStream(folder+File.separator+rid+".html");       // save html pages in a offline folder acc to their id
                    PrintWriter pw=new PrintWriter(fos);

                    String code=generate_offline_page(ingredients,procedure,title);

                    //   Downloading image too 
                    URL url2=new URL(imageUrl);
                    URLConnection conn2=url2.openConnection();

                    conn2.setRequestProperty
                       ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

                    InputStream is=conn2.getInputStream();  // inputstream is base class of all inputstreams we can use dis too
                    FileOutputStream fos2=new FileOutputStream(folder+File.separator+rid+".jpg");  // this seperator will automatically detect which seperator is needed

                    byte b[]=new byte[10000];

                    while(true)
                    {
                        int r=is.read(b,0,10000);
                        if(r==-1)
                            break;
                        fos2.write(b,0,r);
                    }
                    fos2.close();

                    pw.println(code);

                    pw.flush();
                    fos.close();

                    if(count==0)
                    {
                        // making a file which holds info about the recipes saved offline
                        FileOutputStream fos3=new FileOutputStream("offline recipe.txt",true);
                        PrintWriter pw3=new PrintWriter(fos3);

                        pw3.println("~"+rid+"~"+title+";");
                        pw3.flush();
                        fos3.close();
                        
                    }
                    JOptionPane.showMessageDialog(RecipeDetail.this,"Recipe saved offline successfully");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(RecipeDetail.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    }//GEN-LAST:event_offlineBtActionPerformed

    private void videoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_videoBtActionPerformed
        FetchVideos obj=new FetchVideos(title);
    }//GEN-LAST:event_videoBtActionPerformed

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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RecipeDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecipeDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecipeDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecipeDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecipeDetail("","","").setVisible(true);
            }
        });
    }

        class FetchDetail implements Runnable
        {
            public void run()
            {
                try
                {
                    URL url=new URL("http://food2fork.com/api/get?key="+Credentials.FOOD_TO_FORK_API+"&rId="+rid);
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
                     //get object
                     JSONObject objp= (JSONObject) jp.parse(s);
                     //get recipe object
                     JSONObject robj=(JSONObject)objp.get("recipe");
                     //get source url
                     sourceUrl=(String) robj.get("source_url");
                     //get ingredients array
                     JSONArray ing=(JSONArray) robj.get("ingredients");
                     // label array
                     JLabel lb[]=new JLabel[ing.size()];
                     
                     // for numbering
                     JLabel lbNum[]=new JLabel[ing.size()];
                     ingPanel.setPreferredSize(new Dimension(500,2000));
                     
                     // get ingredients
                     int y=10;
                     for(int i=0; i<ing.size(); i++)
                     {
                         String stIng=(String)ing.get(i);
                         ingredients+=stIng+"<br>";              // get ingredients  // as it has to save as html so for new linw there is break
                         lb[i]=new JLabel();
                         lb[i].setText(stIng);
                         lb[i].setBounds(43,y,800,30);
                         lb[i].setFont(new Font("Tahoma",Font.PLAIN,13));
                         lb[i].setForeground(Color.black);
                         ingPanel.add(lb[i]);
                         ingPanel.repaint();
                         
                         // for numbering
                         lbNum[i]=new JLabel();
                         lbNum[i].setText((i+1)+".");
                         lbNum[i].setBounds(10,y,30,30);
                         lbNum[i].setFont(new Font("Tahoma",Font.BOLD,13));
                         lbNum[i].setForeground(Color.white);
                         sidePanel.add(lbNum[i]);
                         sidePanel.repaint();
                         
                         y=y+40;
                     }
                     
                    procedure=recipeDescription(sourceUrl);
                    //System.out.println(procedure);
                    proEditor.setForeground(Color.cyan);
                    proEditor.setText(procedure);
                    
                    if(!procedure.equals("Data Not available"))
                    {
                        offlineBt.setEnabled(true);
                    }
                    
                }
                catch(Exception ex)
                {
                    Logger.getLogger(RecipeDetail.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
        String recipeDescription(String sourceUrl)
        {
            String finalProcedure="";
            try
            {
                URL url=new URL(sourceUrl);
                URLConnection conn=url.openConnection();    

                conn.setRequestProperty
                    ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

                BufferedReader br=new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
                );
                    String str;
                    String procedure="";
                    while(true)
                    {
                        str=br.readLine();
                        if(str==null)
                        {
                            break;
                        }
                        procedure+=str;
                    }           
                                  // read procedure from source url
                    int start,end;
                    start=procedure.indexOf("<h4 class=\"panel-title\">Instructions</h4>");
                    if(start==-1)
                    {
                        finalProcedure = "Data Not available";
                        webBt.setVisible(true);
                    }
                    else
                    {
                        start=procedure.indexOf("<div class=\"panel-body\">",start);

                        end=procedure.indexOf("</div>",start);  // here we get index of 

                        finalProcedure=procedure.substring(start, end+6);  //otherwise > will not come if we take end only
                    }
                    
            }
            catch(Exception ex)
            {
                Logger.getLogger(RecipeDetail.class.getName()).log(Level.SEVERE, null, ex);
            }
            return finalProcedure;
        }
        
        
        // offline saving of recipe
        
        String generate_offline_page(String ing, String procedure, String title) 
        {
            String offline = "<html>\n"
                    + "    <head>\n"
                    + "        <title>" + title + "</title>\n"
                    + "      \n"
                    + "    </head>\n"
                    + "    <body style=\"background: #ccccff\">\n"
                    + "        \n"
                    + "        <div style=\"border: solid 1px black; border-radius: 5px;background: white; padding: 20px \">\n"
                    + "            <img src=\""+rid+".jpg\" width=\"100\" height=\"100\" />\n"
                    + "            \n"
                    + "            <label style=\"font-style: italic; font-size: 25px; position: relative; top: -50px;left: 50px\"><b>" + title + "</b></label>\n"
                    + "            \n"
                    + "        </div>\n"
                    + "        \n"
                    + "        <div style=\"border: solid 1px black; border-radius: 5px;background: white; padding: 20px; margin-top: 10px \">\n"
                    + "            <label style=\"font-size: 25px; \">Ingredients</label><br>\n" + ing
                    + "                       \n"
                    + "        </div>\n"
                    + "        \n"
                    + "        <div style=\"border: solid 1px black; border-radius: 5px;background: white; padding: 20px; margin-top: 10px \">\n"
                    + "            <label style=\"font-size: 25px; \">Procedure</label><br>\n" + procedure
                    + "                   \n"
                    + "        </div>\n"
                    + "        \n"
                    + "        \n"
                    + "    </body>\n"
                    + "</html>\n"
                    + "";

            return offline;
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ProPanel;
    private javax.swing.JMenu favMenu;
    private javax.swing.JMenu homeMenu;
    private javax.swing.JPanel ingPanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbIng;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JButton likeBt;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton offlineBt;
    private javax.swing.JMenu offlineMenu;
    private javax.swing.JEditorPane proEditor;
    private javax.swing.JLabel proLabel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JScrollPane sp;
    private javax.swing.JPanel titleimgPanel;
    private javax.swing.JButton unlikeBt;
    private javax.swing.JButton videoBt;
    private javax.swing.JButton webBt;
    // End of variables declaration//GEN-END:variables
}
