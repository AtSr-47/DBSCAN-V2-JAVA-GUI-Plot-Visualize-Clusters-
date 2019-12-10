package dbscan;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import static org.jfree.chart.ChartColor.LIGHT_BLUE;
import static org.jfree.chart.ChartColor.LIGHT_GREEN;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

/**
 * @author Athmane.Srai *
 */
public class Accueil extends JFrame {

    public static int minpoints;
    public static double tdistance;
    public static Boolean a;
    public static int x1;
    public static int y1;
    public static int x2;
    public static int y2;
    public static Vector<Point> pointesList = new Vector<Point>();
    public static Vector<Point> clusters = new Vector<Point>();
    public static Vector<Point> bruit = new Vector<Point>();
    public static Vector<List> trl = new Vector<List>();
    static ArrayList<Point> temp = new ArrayList<Point>();
    private final static String newline = "\n";
    static Boolean Y = false;
    private JFreeChart pointesChart;
    private JFreeChart clustersChart;
    private XYPlot pointesPlot;
    private XYPlot clustersPlot;
    private XYDataset dataset;
    private XYDataset dataset2;
    private XYSeriesCollection pointesDataset = new XYSeriesCollection();
    private XYSeriesCollection clustersDataset = new XYSeriesCollection();
    private XYSeries ClustersSereis;
    private XYSeries PointeSeries;
    private JFileChooser jfc = new JFileChooser();
    private static int refresh = 0;

    public Accueil() {
        initComponents();

        jScrollPane2.setBorder(null);
        jLabel3.setVisible(false);
        jfc.setDialogTitle("Choose a directory to save your file: ");
        jfc.setAcceptAllFileFilterUsed(true);
        FileNameExtensionFilter filterCSV = new FileNameExtensionFilter("Fichier csv", "csv");
        FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("Fichier texte", "txt", "text");
        jfc.addChoosableFileFilter(filterCSV);
        jfc.addChoosableFileFilter(filterTXT);

        // Create dataset
        dataset = pointesDataset;
        dataset2 = clustersDataset;

        // Create chart
        pointesChart = ChartFactory.createScatterPlot(
                "",
                "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        pointesChart.getTitle().setFont(new java.awt.Font("Segoe UI", 0, 20));
        pointesChart.getTitle().setPaint(Color.GRAY);

        clustersChart = ChartFactory.createScatterPlot(
                "",
                "X", "Y", dataset2, PlotOrientation.VERTICAL, true, true, false);

        LegendTitle legend = clustersChart.getLegend();
        legend.setPosition(RectangleEdge.LEFT);
        //clustersChart.removeLegend();
        legend.setItemFont(new Font("Segoe UI", Font.PLAIN, 9));

        clustersChart.getTitle().setFont(new java.awt.Font("Segoe UI", 0, 20));
        clustersChart.getTitle().setPaint(Color.GRAY);
        // 5x5 red pixel circle
        Shape circle = new Ellipse2D.Double(0, 0, 5, 5);

        pointesPlot = (XYPlot) pointesChart.getPlot();
        pointesPlot.setBackgroundPaint(Color.WHITE);
        pointesPlot.setDomainGridlinesVisible(true);
        pointesPlot.setDomainCrosshairVisible(true);
        pointesPlot.setRangeCrosshairVisible(true);
        pointesPlot.setDomainGridlinePaint(Color.lightGray);
        pointesPlot.setRangeGridlinePaint(Color.lightGray);
        XYItemRenderer renderer = (XYItemRenderer) pointesPlot.getRenderer();
        renderer.setBaseShape(circle);
        renderer = pointesPlot.getRenderer();
        renderer.setSeriesShape(0, circle);
        renderer.setSeriesPaint(0, LIGHT_BLUE);
        renderer.setSeriesVisibleInLegend(0, false);
        pointesPlot.setRenderer(renderer);

        clustersPlot = clustersChart.getXYPlot();
        clustersPlot.setBackgroundPaint(Color.GRAY);
        clustersPlot.setDomainGridlinesVisible(true);
        clustersPlot.setDomainCrosshairVisible(true);
        clustersPlot.setRangeCrosshairVisible(true);
        clustersPlot.setDomainGridlinePaint(Color.lightGray);
        clustersPlot.setRangeGridlinePaint(Color.lightGray);
        XYItemRenderer renderer2 = (XYItemRenderer) clustersPlot.getRenderer();
        renderer2.setBaseShape(circle);
        renderer2 = clustersPlot.getRenderer();
        renderer2.setSeriesShape(0, circle);
        //renderer2.setSeriesPaint(0, LIGHT_BLUE);
        //renderer2.setShape(circle);
        //clustersPlot.setRenderer(renderer2);    

        // Create Panel
        ChartPanel chartPanel = new ChartPanel(pointesChart);
        ChartPanel chartPanel2 = new ChartPanel(clustersChart);
        jPanel3.add(chartPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 520, 300));
        jPanel3.add(chartPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 374, 520, 326));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pointes = new javax.swing.JTextArea();
        epField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        minpointField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        browsFile = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        clusterArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        path = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        NbrPointes = new javax.swing.JLabel();
        TimeCl = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ALGORITHME DBSCAN (Density-Based Spatial Clustering of Applications with Noise) ");
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 750));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 750));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(500, 700));
        jPanel3.setMinimumSize(new java.awt.Dimension(500, 700));
        jPanel3.setPreferredSize(new java.awt.Dimension(520, 700));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ext.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 40, 30));

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Pointes Plot");
        jLabel9.setPreferredSize(new java.awt.Dimension(500, 33));
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 390, 30));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Clusters Plot");
        jLabel10.setPreferredSize(new java.awt.Dimension(500, 33));
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 390, 30));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ext.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 40, 30));

        jSeparator5.setOpaque(true);
        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setPreferredSize(new java.awt.Dimension(164, 10));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 331, 520, 5));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 520, 700));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.setMaximumSize(new java.awt.Dimension(500, 700));
        jPanel4.setMinimumSize(new java.awt.Dimension(500, 700));
        jPanel4.setPreferredSize(new java.awt.Dimension(500, 700));

        pointes.setColumns(20);
        pointes.setLineWrap(true);
        pointes.setRows(5);
        pointes.setToolTipText("");
        pointes.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pointes.setMaximumSize(new java.awt.Dimension(164, 94));
        pointes.setMinimumSize(new java.awt.Dimension(164, 94));
        pointes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pointesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(pointes);

        epField.setMinimumSize(new java.awt.Dimension(6, 15));
        epField.setPreferredSize(new java.awt.Dimension(50, 25));
        epField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                epFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("epsilon (ε)  :");

        minpointField.setMinimumSize(new java.awt.Dimension(6, 15));
        minpointField.setPreferredSize(new java.awt.Dimension(50, 25));
        minpointField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minpointFieldActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Min Point   :");

        browsFile.setFont(new java.awt.Font("Yu Gothic Light", 1, 14)); // NOI18N
        browsFile.setText("Importer");
        browsFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        browsFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browsFileActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Exemple : x1,x1  x2,y2  . . . ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ALGORITHME DBSCAN");
        jLabel6.setPreferredSize(new java.awt.Dimension(500, 14));

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator1.setPreferredSize(new java.awt.Dimension(500, 10));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Entrez les coordonnées des points ici :");

        clusterArea.setBackground(new java.awt.Color(0, 0, 0));
        clusterArea.setColumns(20);
        clusterArea.setForeground(new java.awt.Color(255, 255, 255));
        clusterArea.setRows(5);
        clusterArea.setBackground(Color.BLACK);
        clusterArea.setForeground(LIGHT_GREEN);
        clusterArea.setEditable(false);
        clusterArea.setToolTipText("");
        clusterArea.setBorder(null);
        clusterArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        clusterArea.setMaximumSize(new java.awt.Dimension(164, 94));
        clusterArea.setMinimumSize(new java.awt.Dimension(164, 94));
        clusterArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clusterAreaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(clusterArea);

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("C L U S T E R S");
        jLabel3.setPreferredSize(new java.awt.Dimension(500, 33));

        jButton2.setFont(new java.awt.Font("Yu Gothic Light", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 0));
        jButton2.setText("Appliquer");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        path.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        path.setForeground(new java.awt.Color(51, 51, 51));
        path.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("OU");

        jSeparator2.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator2.setPreferredSize(new java.awt.Dimension(164, 10));

        jSeparator3.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator3.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator3.setPreferredSize(new java.awt.Dimension(164, 10));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 204));

        NbrPointes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        NbrPointes.setForeground(new java.awt.Color(51, 102, 255));

        TimeCl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TimeCl.setForeground(new java.awt.Color(255, 153, 0));
        TimeCl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 51, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(75, 75, 75))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(epField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(minpointField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2))
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(browsFile, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(5, 5, 5)
                                .addComponent(NbrPointes, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TimeCl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browsFile))
                .addGap(13, 13, 13)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(epField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minpointField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TimeCl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(NbrPointes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pathActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        pointesList.clear();
        bruit.clear();
        clusters.clear();
        trl.clear();
        int nbrP = 0;
        long start;
        long end;

        String line = null;
        String x = "";
        String y = "";
        String[] s = null;
        if (refresh != 0) {
            clustersDataset.removeAllSeries();
            pointesDataset.removeAllSeries();
            PointeSeries.clear();
            ClustersSereis.clear();
        }
        if (minpointField.getText().equals("") || epField.getText().equals("") && (pointes.getText().equals("") || path.getText().equals(""))) {
            {
                JOptionPane.showMessageDialog(null, "Completé les champs !!");
            }
        } else if (!pointes.getText().equals("") && !path.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez insérer juste en une fois (zone de texte ou importer)");
        } else if (pointes.getText().equals("") && path.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Completé les champs !!");
        } else {
            jPanel4.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            jButton2.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (!pointes.getText().equals("")) {
                String t = pointes.getText();
                BufferedReader br = new BufferedReader(new StringReader(t)); // Initialize a buffered reader reading from that text file.

                try {
                    while ((line = br.readLine()) != null) {
                        //System.out.println(line);
                        s = line.split("[, \n]");
                        for (int j = 0; j < s.length; j += 2) {
                            x = s[j];
                            y = s[j + 1];
                            pointesList.add(new Point(Double.parseDouble(x), Double.parseDouble(y)));
                            //System.out.println(x+","+y);
                        }
                        //hset.add(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!path.getText().equals("")) {
                File dataSetText = new File(path.getText());
                try {
                    BufferedReader br;
                    br = new BufferedReader(new FileReader(dataSetText));
                    while ((line = br.readLine()) != null) {
                        //System.out.println(line);
                        s = line.split("[, \n]");

                        for (int i = 0; i < s.length; i += 2) {
                            x = s[i];
                            y = s[i + 1];
                            pointesList.add(new Point(Double.parseDouble(x), Double.parseDouble(y)));
                            //System.out.println(x + "," + y);
                        }

                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
                }
            }
            minpoints = Integer.parseInt(minpointField.getText());
            tdistance = Double.parseDouble(epField.getText());
            //System.out.println(minpoints+"  "+tdistance);
            PointeSeries = new XYSeries("Pointe");
            for (Point p : pointesList) {
                nbrP++;
                PointeSeries.add(p.getX(), p.getY());
                //System.out.println(p.getX() + "," +p.getY());

            }
            jLabel12.setText("Nombre de Pointes  :");
            NbrPointes.setText("" + nbrP);
            pointesDataset.addSeries(PointeSeries);
            dataset = pointesDataset;
            // pointLabel.setText(s);

            trl.clear();
            dbscan.e = tdistance;
            dbscan.minpt = minpoints;
            start = System.currentTimeMillis();
            trl.addAll(dbscan.applyDbscan());
            end = System.currentTimeMillis();
            //System.out.println(dbscan.e+""+dbscan.minpt);
            String cls = "";
            int index1 = 0;
            for (List l : trl) {
                Iterator<Point> j = l.iterator();
                while (j.hasNext()) {
                    Point w = j.next();
                    clusters.add(new Point(w.getX(), w.getY()));
                    //System.out.println(w.getX() + "," + w.getY());
                }
            }
            for (Point p : pointesList) {
                if (!clusters.contains(new Point(p.getX(), p.getY()))) {
                    bruit.add(p);
                }
            }
            ClustersSereis = new XYSeries("Bruit", true);
            for (Point b : bruit) {
                ClustersSereis.add(b.getX(), b.getY());
                //System.out.println(b.getX() + "," + b.getY());
            }
            clustersDataset.addSeries(ClustersSereis);

            for (List l : trl) {
                cls = cls + "Cluster " + (index1 + 1) + " :     [   ";
                ClustersSereis = new XYSeries("C " + (index1 + 1));
                Iterator<Point> j = l.iterator();
                while (j.hasNext()) {
                    Point w = j.next();
                    cls = cls + "(" + (w.getX() + "," + w.getY()) + ")  ";

                    ClustersSereis.add(w.getX(), w.getY());
                }
                clustersDataset.addSeries(ClustersSereis);
                dataset2 = clustersDataset;
                cls = cls + "] \n";
                index1++;
                refresh++;

            }
            jLabel3.setVisible(true);
            //jLabel3.setForeground(Color.WHITE);
            //clusterArea.revalidate();
            clusterArea.setText(cls);
            pointesList.clear();
            trl.clear();
            jPanel4.setCursor(Cursor.getDefaultCursor());
            jButton2.setCursor(Cursor.getDefaultCursor());
            //clustersDataset.removeAllSeries();
            /*String bruit = "";
            for (Point p : hset) {
                if (!cls.contains("(" + p.getX() + "," + p.getY() + ")"));
                {
                    bruit = bruit + "(" + p.getX() + "," + p.getY() + ") ";
                }
                //System.out.println(p.getX() + "," + p.getY());
            }
            System.out.println(bruit);*/

            //System.out.println("Applay takes " +(end - start) + "ms"); 
            jLabel15.setText("Temp de Calcul :");
            TimeCl.setText(" " + (end - start) + " ms");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void clusterAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clusterAreaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_clusterAreaMouseClicked

    private void browsFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browsFileActionPerformed
        //System.out.println(pointes.getText());
        clusterArea.setText(null);
        if (pointes.getText().equals("")) {
            int returnValue = jfc.showOpenDialog(null);
            // int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                path.setText(selectedFile.getAbsolutePath());
                pointes.disable();

            }
        } else {
            JOptionPane.showMessageDialog(null, "             Supprimer les données de la zone de texte \n Veuillez insérer juste en une fois (zone de texte ou importer)");
        }
    }//GEN-LAST:event_browsFileActionPerformed

    private void minpointFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minpointFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minpointFieldActionPerformed

    private void epFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_epFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_epFieldActionPerformed

    private void pointesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pointesMouseClicked
        if (!path.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "             Supprimer le chemin de fichier importer \n Veuillez insérer juste en une fois (zone de texte ou importer)");
        } else {
            pointes.enable();
        }
    }//GEN-LAST:event_pointesMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        JFrame f1 = new JFrame();
        f1.setSize(800, 500);
        f1.setLocationRelativeTo(null);
        JFreeChart pointesChartCopie = pointesChart;
        //pointesChartCopie.setTitle("Pointes Plot");
        ChartPanel chartPanel = new ChartPanel(pointesChartCopie);
        f1.add(chartPanel, BorderLayout.CENTER);
        f1.setVisible(true);

    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        JFrame f1 = new JFrame();
        f1.setSize(800, 500);
        f1.setLocationRelativeTo(null);
        ChartPanel chartPanel = new ChartPanel(clustersChart);
        f1.add(chartPanel, BorderLayout.CENTER);
        f1.setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Accueil().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NbrPointes;
    private javax.swing.JLabel TimeCl;
    private javax.swing.JButton browsFile;
    private javax.swing.JTextArea clusterArea;
    private javax.swing.JTextField epField;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField minpointField;
    private javax.swing.JTextField path;
    private javax.swing.JTextArea pointes;
    // End of variables declaration//GEN-END:variables
}
