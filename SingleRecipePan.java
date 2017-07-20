
public class SingleRecipePan extends javax.swing.JPanel {

    public SingleRecipePan() {
        initComponents();
        
        this.setSize(250,250);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        picLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setLayout(null);

        picLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(picLabel);
        picLabel.setBounds(30, 20, 190, 160);

        titleLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(titleLabel);
        titleLabel.setBounds(40, 190, 190, 80);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel picLabel;
    public javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
