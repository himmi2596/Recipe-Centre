
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ViewMyFavourite extends javax.swing.JFrame {

    ArrayList<Recipe> al=new ArrayList<>();
    
    public ViewMyFavourite() {
        initComponents();
        
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    FileInputStream fis=new FileInputStream("favourite.txt");
                    BufferedReader br=new BufferedReader(new InputStreamReader(fis));
                    
                    String str="";
                    while(true)
                    {
                        String s=br.readLine();
                        if(s==null)
                            break;
                        str+=s;
                    }
                    StringTokenizer st=new StringTokenizer(str,";");
                    while(st.hasMoreTokens())
                    {
                        String sread=st.nextToken();
                        StringTokenizer subst=new StringTokenizer(sread,"~");
                        String title=subst.nextToken();
                        String imageUrl=subst.nextToken();
                        String rid=subst.nextToken();
                        
                        al.add(new Recipe(title,imageUrl,rid));
                       
                    }
                    
                    SingleRecipePan srp[]=new SingleRecipePan[al.size()];
                    MainPanel.setPreferredSize(new Dimension(1000,5000));
                    
                    int x=20,y=20;
                    for(int i=0; i<al.size(); i++)
                    {
                        String title=al.get(i).title;
                        String imageUrl=al.get(i).imageUrl;
                        String rid=al.get(i).rid;
                        
                        srp[i]=new SingleRecipePan();   // everytime new panel is created dynamically
                        
                        srp[i].titleLabel.setText("<html> "+title+" </html>");   //adjust label automatically
                        srp[i].picLabel.setText(imageUrl);
                        
                        
                        URL urlImage=new URL(imageUrl);
                        URLConnection conn1=urlImage.openConnection();
                        
                        conn1.setRequestProperty
                        ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
                         
                        BufferedImage im=ImageIO.read(conn1.getInputStream());
                        BufferedImage im1=FetchAllRecipes.resize(im,190,160);
                        srp[i].picLabel.setIcon(new ImageIcon(im1));
                        
                        
                        srp[i].setBounds(x,y,250,250);
                        
                        srp[i].addMouseListener(new MouseAdapter()
                        {
                            public void mouseClicked(MouseEvent e)
                            {
                                if(e.getClickCount()==2)
                                {
                                    RecipeDetail rd=new RecipeDetail(title,imageUrl,rid);
                                }
                            }
                        });
                        
                        MainPanel.add(srp[i]);
                        
                        MainPanel.repaint();
                        
                        if(x<528)
                        {
                            x=x+280;
                        }
                        else
                        {
                            y=y+280;
                            x=20;
                        }
                    }
                    fis.close();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }).start();
        
        this.setSize(800,700);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.setTitle("My Favourites");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        sp = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();
        clearBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("My Favourites");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(29, 7, 740, 40);

        MainPanel.setBackground(new java.awt.Color(0, 0, 0));
        MainPanel.setLayout(null);
        sp.setViewportView(MainPanel);

        getContentPane().add(sp);
        sp.setBounds(20, 80, 750, 500);

        clearBt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        clearBt.setText("Clear All");
        clearBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtActionPerformed(evt);
            }
        });
        getContentPane().add(clearBt);
        clearBt.setBounds(680, 50, 90, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtActionPerformed
        // deleting file
        File f=new File("favourite.txt");
        f.delete();
        
        //clear arraylist
        al.clear();
        
        //remove pannels
        MainPanel.removeAll();
        MainPanel.repaint();
        
        JOptionPane.showMessageDialog(ViewMyFavourite.this,"Favourite list cleared");
    }//GEN-LAST:event_clearBtActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewMyFavourite().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton clearBt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
