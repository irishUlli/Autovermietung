/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Verbindung.DbVerbindung;
import java.util.Date;
import Klassen.Fahrzeug;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author irish
 */
public class UiAutovermietung extends javax.swing.JFrame {

    Fahrzeug Fahrzeug;
    DefaultTableModel model;
//    DefaultTableModel model;

    /**
     * Creates new form NewJFrame
     */
    public UiAutovermietung(Fahrzeug Fahrzeug) //DefaultTableModel model
    {
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(true);
        initComponents();
        SetIcon();
        SetLogo();
        Verbindungstest();
        EnableDesableItmes();
        this.Fahrzeug = Fahrzeug;
        this.jrbALLe.setSelected(true);
        LoadGrid();
        LadeFelder();
        this.jrbGeschichte.setEnabled(false);
        LadeAlleKennzeichen();
    }

    private void SetIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("car1.png")));
    }

    private void SetLogo() {
        this.lblLogo.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("car1.png")).getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
    }

    //Könnte auch vorher schon überprüft werden oder nur auf Klick zum Testen
    private void Verbindungstest() {
        if (DbVerbindung.VerbindungstestDb()) {
            this.btnVerbindung.setBackground(Color.GREEN);
            this.lblDBVerbindung.setText("Datenbankverbindung OK.");
            this.lblDBVerbindung.setForeground(Color.BLACK);
        } else {
            this.btnVerbindung.setBackground(Color.RED);
            this.lblDBVerbindung.setText("Datenbank nicht erreichbar!.");
            this.lblDBVerbindung.setForeground(Color.RED);
        }

    }

    private void EnableDesableItmes() {
        //DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dateFormat.format(date)
//        Date date = new Date();

        if (jrbRuecknahme.isSelected()) {
            this.cboBauart.setEnabled(false);
            this.cboNutzlast.setEnabled(false);
            this.ftxbMietbeginn.setEnabled(false);
            this.txbServiceBei.setEnabled(false);
            this.cboSitzplaetze.setEnabled(false);
            this.ftxbRueckgabedatum.setEnabled(true);
            this.ftxbRueckgabedatum.setValue(new Date()); //16.11.2016 formatterFactory in UI
            this.ftxbKmBeiRueckgabe.setEnabled(true);
            this.btnLaden.setEnabled(true);
            this.ftxbPreis.setEditable(false);
            this.btnAnlegen.setText("Rücknahme");

        }
        if (jrbVerleihen.isSelected()) {
            this.cboBauart.setEnabled(true);
            this.cboNutzlast.setEnabled(true);
            this.ftxbMietbeginn.setEnabled(true);
            this.txbServiceBei.setEnabled(true);
            this.cboSitzplaetze.setEnabled(true);
            this.ftxbRueckgabedatum.setEnabled(false);
            this.ftxbKmBeiRueckgabe.setEnabled(false);
            this.btnLaden.setEnabled(true);
            this.ftxbPreis.setEditable(true);
            this.btnAnlegen.setText("Verleihen");
        }
        if (jrbNeuAnlegen.isSelected()) {
            this.cboBauart.setEnabled(true);
            this.cboNutzlast.setEnabled(true);
            this.ftxbMietbeginn.setEnabled(false);
            this.txbServiceBei.setEnabled(true);
            this.cboSitzplaetze.setEnabled(true);
            this.ftxbRueckgabedatum.setEnabled(false);
            this.ftxbKmBeiRueckgabe.setEnabled(false);
            this.btnLaden.setEnabled(false);
            this.ftxbPreis.setEditable(false);
            this.btnAnlegen.setText("Anlegen");
        }
    }

    private void LoadGrid() //DefaultTableModel model
    {
        this.model = this.Fahrzeug.GibAlleFahrzeugeTabelle();
        if (model != null) {
            jTblAll.setModel(model);
        }
////        jTblAll.clearSelection();
////        jTblAll.setModel((TableModel) table);
////       DefaultTableModel model = (DefaultTableModel ) jTblAll.getModel();
//       //model.addColumn(table);
//        LadeFelder();
    }

    private void LadeFelder() {
        this.cboBauart.removeAllItems();
        this.cboBauart.addItem(this.Fahrzeug.getBauart());
        this.cboNutzlast.removeAllItems();
        this.cboNutzlast.addItem(this.Fahrzeug.getNutzlast().toString());
        this.ftxbMietbeginn.setValue(this.Fahrzeug.getMietbeginn());
        this.ftxbKM.setText(this.Fahrzeug.getKm1().toString());
        this.ftxbKmBeiRueckgabe.setValue(this.Fahrzeug.getKmRueckgabe());
//        this.ftxbServiceFaelligAm
//        this.txbServiceBei
        this.cboSitzplaetze.removeAllItems();
        this.cboSitzplaetze.addItem(this.Fahrzeug.getSitzplaetze().toString());
        this.ftxbRueckgabedatum.setValue(this.Fahrzeug.getVorrRueckgabe());
        this.ftxbPreis.setValue(this.Fahrzeug.getPreis());
    }

    private void LadeFahrzeuggeschichte() {
        try {
            // das Fahrzeug muss seine Eigenschaften aus der DB aktualisieren
            String kz = this.cboKennzeichen.getSelectedItem().toString();

            if (kz != null && !kz.isEmpty()) {
                this.Fahrzeug.EigenschaftenNeuLaden(kz);
            }

        } catch (ParseException ex) {
            Logger.getLogger(UiAutovermietung.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.LadeFelder();
        this.model = this.Fahrzeug.GibFahrzuegGeschichteTabelle(this.Fahrzeug.getKennzeichen());
        if (model != null) {
            jTblAll.setModel(model);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgVerleih = new javax.swing.ButtonGroup();
        bgBauart = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        jrbRuecknahme = new javax.swing.JRadioButton();
        jrbVerleihen = new javax.swing.JRadioButton();
        lblLogo = new javax.swing.JLabel();
        jrbNeuAnlegen = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        cboBauart = new javax.swing.JComboBox<>();
        cboKennzeichen = new javax.swing.JComboBox<>();
        cboNutzlast = new javax.swing.JComboBox<>();
        ftxbKmBeiRueckgabe = new javax.swing.JFormattedTextField();
        lblKennzeichen = new javax.swing.JLabel();
        lblBauart = new javax.swing.JLabel();
        lblNutzlast = new javax.swing.JLabel();
        lblKmRueckg = new javax.swing.JLabel();
        btnLaden = new javax.swing.JButton();
        lblServiceBeiKm = new javax.swing.JLabel();
        lblServiceFaelligAm = new javax.swing.JLabel();
        ftxbServiceFaelligAm = new javax.swing.JFormattedTextField();
        txbServiceBei = new javax.swing.JTextField();
        cboSitzplaetze = new javax.swing.JComboBox<>();
        lblRueckgabeDatum = new javax.swing.JLabel();
        lblSitzplaetze = new javax.swing.JLabel();
        lblPreis = new javax.swing.JLabel();
        ftxbPreis = new javax.swing.JFormattedTextField();
        ftxbMietbeginn = new javax.swing.JFormattedTextField();
        lblMietbeginn = new javax.swing.JLabel();
        ftxbRueckgabedatum = new javax.swing.JFormattedTextField();
        btnAnlegen = new javax.swing.JButton();
        ftxbKM = new javax.swing.JFormattedTextField();
        lblKmStand = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jrbLKW = new javax.swing.JRadioButton();
        jrbPKW = new javax.swing.JRadioButton();
        jrbALLe = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblAll = new javax.swing.JTable();
        jrbGeschichte = new javax.swing.JRadioButton();
        jpanelStatus = new javax.swing.JPanel();
        lblDBVerbindung = new javax.swing.JLabel();
        btnVerbindung = new javax.swing.JButton();
        lblVersion = new javax.swing.JLabel();

        bgVerleih.add(jrbRuecknahme);
        bgVerleih.add(jrbVerleihen);
        bgVerleih.add(jrbNeuAnlegen);

        bgBauart.add(jrbLKW);
        bgBauart.add(jrbPKW);
        bgBauart.add(jrbALLe);
        bgBauart.add(jrbGeschichte);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.SystemColor.window);
        setMaximumSize(new java.awt.Dimension(853, 719));
        setMinimumSize(new java.awt.Dimension(853, 719));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblHeader.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        lblHeader.setText("Autovermietung");

        jrbRuecknahme.setBackground(new java.awt.Color(204, 204, 204));
        jrbRuecknahme.setSelected(true);
        jrbRuecknahme.setText("Rücknahme");
        jrbRuecknahme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbRuecknahmeActionPerformed(evt);
            }
        });

        jrbVerleihen.setBackground(new java.awt.Color(204, 204, 204));
        jrbVerleihen.setText("Verleihen");
        jrbVerleihen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbVerleihenActionPerformed(evt);
            }
        });

        lblLogo.setText("LOGO");
        lblLogo.setMaximumSize(new java.awt.Dimension(1074, 487));
        lblLogo.setMinimumSize(new java.awt.Dimension(1074, 487));

        jrbNeuAnlegen.setBackground(new java.awt.Color(204, 204, 204));
        jrbNeuAnlegen.setText("Fahrzeug anlegen");
        jrbNeuAnlegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNeuAnlegenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbRuecknahme)
                    .addComponent(jrbVerleihen)
                    .addComponent(jrbNeuAnlegen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrbVerleihen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbRuecknahme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbNeuAnlegen)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jrbRuecknahme.getAccessibleContext().setAccessibleName("jrbRueknahme");
        jrbVerleihen.getAccessibleContext().setAccessibleName("JRBVerleihen");
        jrbVerleihen.getAccessibleContext().setAccessibleDescription("");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Fahrzeugdaten"));

        cboBauart.setEditable(true);
        cboBauart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PKW", "LKW" }));
        cboBauart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBauartActionPerformed(evt);
            }
        });

        cboKennzeichen.setEditable(true);
        cboKennzeichen.setToolTipText("Hier kann für die Suche auch ein Kennzeichen eingegeben werden");
        cboKennzeichen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cboKennzeichen.setFocusCycleRoot(true);

        cboNutzlast.setEditable(true);

        ftxbKmBeiRueckgabe.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        lblKennzeichen.setText("Kennzeichen");

        lblBauart.setText("Bauart");

        lblNutzlast.setText("Nutzlast");

        lblKmRueckg.setText("km-Stand bei Rückgabe");

        btnLaden.setText("Laden");
        btnLaden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLadenActionPerformed(evt);
            }
        });

        lblServiceBeiKm.setText("Service bei km");

        lblServiceFaelligAm.setText("Service fällig am");

        ftxbServiceFaelligAm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        cboSitzplaetze.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        lblRueckgabeDatum.setText("Rückgabedatum");

        lblSitzplaetze.setText("Sitzplätze");

        lblPreis.setText("Preis");

        ftxbPreis.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        ftxbMietbeginn.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftxbMietbeginn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        lblMietbeginn.setText("Mietbeginn");

        ftxbRueckgabedatum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        btnAnlegen.setText("Anlegen");
        btnAnlegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnlegenActionPerformed(evt);
            }
        });

        lblKmStand.setText("km");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ftxbKmBeiRueckgabe)
                    .addComponent(cboBauart, 0, 1, Short.MAX_VALUE)
                    .addComponent(cboKennzeichen, 0, 1, Short.MAX_VALUE)
                    .addComponent(cboNutzlast, 0, 1, Short.MAX_VALUE)
                    .addComponent(ftxbMietbeginn)
                    .addComponent(ftxbKM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblKennzeichen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLaden))
                            .addComponent(lblBauart)
                            .addComponent(lblNutzlast)
                            .addComponent(lblMietbeginn)
                            .addComponent(lblKmStand, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ftxbRueckgabedatum)
                            .addComponent(ftxbPreis)
                            .addComponent(txbServiceBei)
                            .addComponent(ftxbServiceFaelligAm)
                            .addComponent(cboSitzplaetze, 0, 91, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblKmRueckg)
                        .addGap(261, 261, 261)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblServiceFaelligAm)
                    .addComponent(lblServiceBeiKm)
                    .addComponent(lblSitzplaetze)
                    .addComponent(lblRueckgabeDatum)
                    .addComponent(lblPreis)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(btnAnlegen, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboKennzeichen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblKennzeichen))
                                    .addComponent(btnLaden, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboBauart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBauart)
                                    .addComponent(ftxbServiceFaelligAm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txbServiceBei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboSitzplaetze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSitzplaetze))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboNutzlast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNutzlast)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ftxbRueckgabedatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblRueckgabeDatum))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ftxbMietbeginn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMietbeginn))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lblServiceFaelligAm))
                    .addComponent(lblServiceBeiKm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ftxbPreis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPreis)
                        .addComponent(ftxbKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblKmStand, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnlegen)
                    .addComponent(ftxbKmBeiRueckgabe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKmRueckg))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Listenansicht"));

        jrbLKW.setBackground(new java.awt.Color(204, 204, 204));
        jrbLKW.setSelected(true);
        jrbLKW.setText("LKW");

        jrbPKW.setBackground(new java.awt.Color(204, 204, 204));
        jrbPKW.setText("PKW");

        jrbALLe.setBackground(new java.awt.Color(204, 204, 204));
        jrbALLe.setText("Alle");
        jrbALLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbALLeActionPerformed(evt);
            }
        });

        jTblAll.setBackground(new java.awt.Color(204, 204, 204));
        jTblAll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kennzeichen", "Marke", "km Stand", "km Stand bei Rückgabe", "Inspektionsintervall", "Service Datum", "Service km", "Mietbeginn", "Vorr. Rückgabe", "km bei Rückgabe", "Preis / Tag"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblAll.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTblAll.setAutoscrolls(false);
        jTblAll.setMinimumSize(new java.awt.Dimension(825, 128));
        jScrollPane2.setViewportView(jTblAll);

        jrbGeschichte.setBackground(new java.awt.Color(204, 204, 204));
        jrbGeschichte.setText("Geschichte");
        jrbGeschichte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbGeschichteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbALLe)
                .addGap(32, 32, 32)
                .addComponent(jrbPKW, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jrbLKW, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jrbGeschichte)
                .addGap(218, 218, 218))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbLKW)
                    .addComponent(jrbPKW)
                    .addComponent(jrbALLe)
                    .addComponent(jrbGeschichte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jrbLKW.getAccessibleContext().setAccessibleName("jrbLKW");
        jrbPKW.getAccessibleContext().setAccessibleName("jrbPKW");
        jrbALLe.getAccessibleContext().setAccessibleName("jrbAlle");

        jpanelStatus.setBackground(new java.awt.Color(204, 204, 204));

        lblDBVerbindung.setText("Datenbankverbindung...");

        btnVerbindung.setBackground(new java.awt.Color(51, 153, 0));

        lblVersion.setText("v 1.0");

        javax.swing.GroupLayout jpanelStatusLayout = new javax.swing.GroupLayout(jpanelStatus);
        jpanelStatus.setLayout(jpanelStatusLayout);
        jpanelStatusLayout.setHorizontalGroup(
            jpanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVerbindung, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDBVerbindung, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVersion)
                .addContainerGap())
        );
        jpanelStatusLayout.setVerticalGroup(
            jpanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelStatusLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jpanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelStatusLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jpanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDBVerbindung)
                            .addComponent(lblVersion)))
                    .addComponent(btnVerbindung, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnVerbindung.getAccessibleContext().setAccessibleName("btnVerbindung");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jpanelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboBauartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBauartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboBauartActionPerformed

    private void jrbRuecknahmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbRuecknahmeActionPerformed
        EnableDesableItmes();
    }//GEN-LAST:event_jrbRuecknahmeActionPerformed

    private void jrbVerleihenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbVerleihenActionPerformed
        EnableDesableItmes();
    }//GEN-LAST:event_jrbVerleihenActionPerformed

    private void btnLadenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLadenActionPerformed
        //        Dadurch wird die Fahrzeuggeschichte automatisch geladen
//       if (jrbGeschichte.isSelected())
        LadeFahrzeuggeschichte();
//       else
        this.jrbGeschichte.setSelected(true);
//       this.bgBauart.setSelected(jrbGeschichte.getModel(), true);

    }//GEN-LAST:event_btnLadenActionPerformed

    private void jrbNeuAnlegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNeuAnlegenActionPerformed
        EnableDesableItmes();
    }//GEN-LAST:event_jrbNeuAnlegenActionPerformed

    private void btnAnlegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnlegenActionPerformed
        if (this.btnAnlegen.getText() == "Anlegen") {
            //Speichere in DB
            JOptionPane.showMessageDialog(null, "Noch keine Funktion", "Test Titel", JOptionPane.OK_CANCEL_OPTION);
        }
        if (this.btnAnlegen.getText() == "Verleihen") {
        //  Speichere in DB
        JOptionPane.showMessageDialog(null, "Noch keine Funktion", "Test Titel", JOptionPane.OK_CANCEL_OPTION);
        }

        if (this.btnAnlegen.getText() == "Rücknahme") {
            //Speichere in DB
            JOptionPane.showMessageDialog(null, "Noch keine Funktion", "Test Titel", JOptionPane.OK_CANCEL_OPTION);
        }
    }//GEN-LAST:event_btnAnlegenActionPerformed

    private void jrbGeschichteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbGeschichteActionPerformed

    }//GEN-LAST:event_jrbGeschichteActionPerformed

    private void jrbALLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbALLeActionPerformed
        LoadGrid();
    }//GEN-LAST:event_jrbALLeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgBauart;
    private javax.swing.ButtonGroup bgVerleih;
    private javax.swing.JButton btnAnlegen;
    private javax.swing.JButton btnLaden;
    private javax.swing.JButton btnVerbindung;
    private javax.swing.JComboBox<String> cboBauart;
    private javax.swing.JComboBox<String> cboKennzeichen;
    private javax.swing.JComboBox<String> cboNutzlast;
    private javax.swing.JComboBox<String> cboSitzplaetze;
    private javax.swing.JFormattedTextField ftxbKM;
    private javax.swing.JFormattedTextField ftxbKmBeiRueckgabe;
    private javax.swing.JFormattedTextField ftxbMietbeginn;
    private javax.swing.JFormattedTextField ftxbPreis;
    private javax.swing.JFormattedTextField ftxbRueckgabedatum;
    private javax.swing.JFormattedTextField ftxbServiceFaelligAm;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTblAll;
    private javax.swing.JPanel jpanelStatus;
    private javax.swing.JRadioButton jrbALLe;
    private javax.swing.JRadioButton jrbGeschichte;
    private javax.swing.JRadioButton jrbLKW;
    private javax.swing.JRadioButton jrbNeuAnlegen;
    private javax.swing.JRadioButton jrbPKW;
    private javax.swing.JRadioButton jrbRuecknahme;
    private javax.swing.JRadioButton jrbVerleihen;
    private javax.swing.JLabel lblBauart;
    private javax.swing.JLabel lblDBVerbindung;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblKennzeichen;
    private javax.swing.JLabel lblKmRueckg;
    private javax.swing.JLabel lblKmStand;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMietbeginn;
    private javax.swing.JLabel lblNutzlast;
    private javax.swing.JLabel lblPreis;
    private javax.swing.JLabel lblRueckgabeDatum;
    private javax.swing.JLabel lblServiceBeiKm;
    private javax.swing.JLabel lblServiceFaelligAm;
    private javax.swing.JLabel lblSitzplaetze;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JTextField txbServiceBei;
    // End of variables declaration//GEN-END:variables

    private void LadeAlleKennzeichen() {
        
        ArrayList kz = new ArrayList();
        kz = (ArrayList) this.Fahrzeug.LadeAlleKennzeichen();
        
        for(Object k : kz)
        {
            this.cboKennzeichen.addItem((String) k);
        }
        
    }
}
