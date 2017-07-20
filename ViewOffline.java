
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


public class ViewOffline extends javax.swing.JFrame {

    ArrayList<Recipe> al=new ArrayList<>();
    TableModel tm;
    public ViewOffline() {
        initComponents();
        
        this.setSize(500,400);
        int wd=Toolkit.getDefaultToolkit().getScreenSize().width;
        int ht=Toolkit.getDefaultToolkit().getScreenSize().height;
        int x=(wd/2)-(this.getWidth()/2);
        int y=(ht/2)-(this.getHeight()/2);
        this.setLocation(x,y);
        this.setTitle("Offline Recipes");
        this.getContentPane().setBackground(Color.black);
        this.setVisible(true);
        tm=new TableModel();
        jTable.setModel(tm);
        
        try
        {
            // reading offline file
            FileInputStream fis=new FileInputStream("offline recipe.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            
            String s="";
            
            while(true)
            {
                String s1=br.readLine();
                if(s1==null)
                {
                    break;
                }
                s+=s1;
            }
            
            StringTokenizer st=new StringTokenizer(s,";");
            while(st.hasMoreTokens())
            {
                String sread=st.nextToken();
                
                StringTokenizer str=new StringTokenizer(sread,"~");
                String rid=str.nextToken();
                String title=str.nextToken();
                
                al.add(new Recipe(title,rid));
                tm.fireTableDataChanged();
            }
            
            fis.close(); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        viewBt = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        favMenu = new javax.swing.JMenu();
        offlineMenu = new javax.swing.JMenu();
        homeMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jTable);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 60, 410, 170);

        viewBt.setBackground(new java.awt.Color(255, 255, 255));
        viewBt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        viewBt.setText("View Recipe");
        viewBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtActionPerformed(evt);
            }
        });
        getContentPane().add(viewBt);
        viewBt.setBounds(330, 250, 130, 30);

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

    private void viewBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtActionPerformed
        int index=jTable.getSelectedRow();
        if(index==-1)
        {
            JOptionPane.showMessageDialog(ViewOffline.this,"    No Item Selected");
        }
        else
        {
            try
            {
                String rId=al.get(index).rid;
                File f1=new File("offline recipes"+File.separator+rId+".html");
                
                
                //  used to open a file in browser
                Desktop.getDesktop().open(f1);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_viewBtActionPerformed

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
                new ViewOffline().setVisible(true);
            }
        });
    }
    
    class TableModel extends AbstractTableModel
    {
        String name[]={"S.No","Title"};
        public String getColumnName(int index)
        {
            return(name[index]);
        }
        public int getColumnCount()
        {
            return(name.length);
        }

        public int getRowCount() 
        {
            return(al.size());
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) 
        {
            Recipe rep=al.get(rowIndex);
            if(columnIndex==0)
            {
                return(rowIndex+1);
            }
            else if (columnIndex==1)
            {
                return(al.get(rowIndex).title);
            }
            else
                return null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu favMenu;
    private javax.swing.JMenu homeMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JMenu offlineMenu;
    private javax.swing.JButton viewBt;
    // End of variables declaration//GEN-END:variables
}
