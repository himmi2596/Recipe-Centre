class VedioPan extends javax.swing.JPanel {

    public VedioPan() {
        initComponents();
        
        this.setSize(1250,200);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        thumbLb = new javax.swing.JLabel();
        titleLb = new javax.swing.JLabel();
        descLb = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new java.awt.Dimension(1250, 150));
        setLayout(null);

        thumbLb.setText("thumbnail");
        add(thumbLb);
        thumbLb.setBounds(14, 14, 220, 170);

        titleLb.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        titleLb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titleLb.setText("title");
        add(titleLb);
        titleLb.setBounds(254, 20, 980, 50);

        descLb.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        descLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descLb.setText("description");
        add(descLb);
        descLb.setBounds(260, 90, 950, 100);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel descLb;
    public javax.swing.JLabel thumbLb;
    public javax.swing.JLabel titleLb;
    // End of variables declaration//GEN-END:variables
}
