
import java.awt.Color;
import java.awt.Toolkit;


public class SplashScreen extends javax.swing.JFrame {

    public SplashScreen() {
        setUndecorated(true);
        initComponents();
        
        this.setSize(600,400);
        int wd=Toolkit.getDefaultToolkit().getScreenSize().width;
        int ht=Toolkit.getDefaultToolkit().getScreenSize().height;
        int x=(wd/2)-(this.getWidth()/2);
        int y=(ht/2)-(this.getHeight()/2);
        this.setLocation(x,y);
        this.getContentPane().setBackground(Color.black);
        this.setTitle("");
        this.setVisible(true);
        
        jpg.setStringPainted(true);
        // progress bar
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=100; i++)
                {
                    if(i<30)
                    {
                        downLb.setText("Initializing...");
                    }
                    else if(i<60)
                    {
                        downLb.setText("Reading Modules...");
                    }
                    else if(i<90)
                    {
                        downLb.setText("Loading Modules...");
                    }
                    else
                    {
                        downLb.setText("Starting Application...");
                    }
                    jpg.setValue(i);
                    jpg.setString(i+"%");
                    
                    try
                    {
                        Thread.sleep(100);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                SplashScreen.this.dispose();
                FetchAllRecipes obj=new FetchAllRecipes();
            }
        }).start();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        projectNameLb = new javax.swing.JLabel();
        jpg = new javax.swing.JProgressBar();
        downLb = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(null);

        projectNameLb.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        projectNameLb.setForeground(new java.awt.Color(0, 153, 0));
        projectNameLb.setText("A Recipe is a story that ends with good meal");
        getContentPane().add(projectNameLb);
        projectNameLb.setBounds(150, 290, 380, 40);

        jpg.setBackground(new java.awt.Color(0, 153, 0));
        jpg.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jpg);
        jpg.setBounds(10, 364, 570, 20);

        downLb.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        downLb.setForeground(new java.awt.Color(255, 255, 255));
        downLb.setText("initializing...");
        getContentPane().add(downLb);
        downLb.setBounds(10, 334, 260, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/recipesplash.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 600, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel downLb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jpg;
    private javax.swing.JLabel projectNameLb;
    // End of variables declaration//GEN-END:variables
}
