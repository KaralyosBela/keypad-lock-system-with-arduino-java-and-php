package adatbaziskezelo;

import com.mysql.jdbc.Connection;
import com.sun.glass.ui.Cursor;
import java.awt.Color;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.SignStyle;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class felulet extends javax.swing.JFrame
{

    //progam inditasa utan lefuto dolgok
    public felulet()
    {
        initComponents();
        adatok_megjelenitese_tablaban();
    }

    //sql kapcsolódás
    public Connection getConnection()
    {
        Connection con;
        try
        {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/arduino", "root", "Karalyos1994");
            return con;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    //lista feltöltése sql-ból 0 paraméteres
    public ArrayList<felhasznalok> felhasznaloLista()
    {
        ArrayList<felhasznalok> felhasznaloLista = new ArrayList<>();
        Connection connection = getConnection();

        String lekerdezes = "select * from tabla";
        Statement st;
        ResultSet rs;

        try
        {
            st = connection.createStatement();
            rs = st.executeQuery(lekerdezes);
            felhasznalok felhasznalo;
            while (rs.next())
            {
                felhasznalo = new felhasznalok(rs.getString("nev"), rs.getString("id"), rs.getString("datum"));
                felhasznaloLista.add(felhasznalo);
            }

        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Sikertelen csatlakozás!");
        }
        return felhasznaloLista;
    }

    //lista feltöltése sql-ból 1 paraméteres
    public ArrayList<felhasznalok> felhasznaloLista(String lekerdezes)
    {
        ArrayList<felhasznalok> felhasznaloLista = new ArrayList<>();
        Connection connection = getConnection();

        Statement st;
        ResultSet rs;

        try
        {
            st = connection.createStatement();
            rs = st.executeQuery(lekerdezes);
            felhasznalok felhasznalo;
            while (rs.next())
            {
                felhasznalo = new felhasznalok(rs.getString("nev"), rs.getString("id"), rs.getString("datum"));
                felhasznaloLista.add(felhasznalo);
            }

        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Sikertelen csatlakozás!");
        }
        return felhasznaloLista;
    }

    //adat kiiratása Jtablában 0 paraméteres
    public void adatok_megjelenitese_tablaban()
    {
        tablazat.setRowHeight(20);
        ArrayList<felhasznalok> lista = felhasznaloLista();
        DefaultTableModel model = (DefaultTableModel) tablazat.getModel();
        Object[] sor = new Object[3];
        for (int i = 0; i < lista.size(); i++)
        {
            sor[0] = lista.get(i).getKod();
            sor[1] = lista.get(i).getTelj_nev();
            sor[2] = lista.get(i).getBelepes();
            model.addRow(sor);
        }
    }

    //adat kiiratása Jtablában 1 paraméteres
    public void adatok_megjelenitese_tablaban(String lekerdezes)
    {
        tablazat.setRowHeight(20);
        ArrayList<felhasznalok> lista = felhasznaloLista(lekerdezes);
        DefaultTableModel model = (DefaultTableModel) tablazat.getModel();
        Object[] sor = new Object[3];
        for (int i = 0; i < lista.size(); i++)
        {
            sor[0] = lista.get(i).getKod();
            sor[1] = lista.get(i).getTelj_nev();
            sor[2] = lista.get(i).getBelepes();
            model.addRow(sor);
        }
    }

    //SQL lekérdezés
    public void SQLlekerdezes(String lekerdezes, String uzenet, boolean hibauzenet)
    {
        Connection con = getConnection();
        Statement st;
        try
        {
            st = con.createStatement();
            if ((st.executeUpdate(lekerdezes)) == 1)
            {
                DefaultTableModel model = (DefaultTableModel) tablazat.getModel();
                model.setRowCount(0);
                adatok_megjelenitese_tablaban();
                for (int i = 0; i <= 100; i++)
                {
                    jProgressBar1.setValue(i);
                }

                if (hibauzenet)
                {
                    JOptionPane.showMessageDialog(null, "A " + uzenet + " sikeres!");
                }
                jProgressBar1.setValue(0);

            } else
            {
                if (hibauzenet)
                {
                    JOptionPane.showMessageDialog(null, "Sikertelen " + uzenet);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        vezeteknev_label = new javax.swing.JLabel();
        keresztnev_label = new javax.swing.JLabel();
        belepokod_label = new javax.swing.JLabel();
        vezetknev_text = new javax.swing.JTextField();
        keresztnev_text = new javax.swing.JTextField();
        belepokod_text = new javax.swing.JTextField();
        torol = new javax.swing.JButton();
        hozzaad = new javax.swing.JButton();
        frissit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablazat = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();
        reset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        rendezesBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        rendez = new javax.swing.JButton();
        keres = new javax.swing.JButton();
        keresesMezo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        vezeteknev_label.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        vezeteknev_label.setText("Vezetéknév:");

        keresztnev_label.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        keresztnev_label.setText("Keresztnév:");

        belepokod_label.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        belepokod_label.setText("Belépőkód:");

        vezetknev_text.setBackground(new java.awt.Color(204, 204, 204));
        vezetknev_text.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        vezetknev_text.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        vezetknev_text.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        vezetknev_text.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                vezetknev_textActionPerformed(evt);
            }
        });

        keresztnev_text.setBackground(new java.awt.Color(204, 204, 204));
        keresztnev_text.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        keresztnev_text.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        keresztnev_text.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        keresztnev_text.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                keresztnev_textActionPerformed(evt);
            }
        });

        belepokod_text.setBackground(new java.awt.Color(204, 204, 204));
        belepokod_text.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        belepokod_text.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        belepokod_text.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        torol.setBackground(new java.awt.Color(91, 175, 202));
        torol.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        torol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adatbaziskezelo/Close-icon.png"))); // NOI18N
        torol.setText("Törlés");
        torol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        torol.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                torolMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                torolMouseExited(evt);
            }
        });
        torol.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                torolActionPerformed(evt);
            }
        });

        hozzaad.setBackground(new java.awt.Color(91, 175, 202));
        hozzaad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        hozzaad.setForeground(new java.awt.Color(1, 1, 1));
        hozzaad.setIcon(new javax.swing.ImageIcon("C:\\Users\\Karaly\\Desktop\\ikonok\\Add-icon.png")); // NOI18N
        hozzaad.setText("Hozzáadás");
        hozzaad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hozzaad.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                hozzaadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                hozzaadMouseExited(evt);
            }
        });
        hozzaad.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                hozzaadActionPerformed(evt);
            }
        });

        frissit.setBackground(new java.awt.Color(91, 175, 202));
        frissit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        frissit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adatbaziskezelo/edit-icon.png"))); // NOI18N
        frissit.setText("Frissítés");
        frissit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        frissit.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                frissitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                frissitMouseExited(evt);
            }
        });
        frissit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                frissitActionPerformed(evt);
            }
        });

        tablazat.setBackground(new java.awt.Color(204, 204, 255));
        tablazat.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        tablazat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Belépőkód", "Teljes Név", "Belépés Dátuma"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tablazat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablazat.setGridColor(new java.awt.Color(0, 0, 0));
        tablazat.setSelectionBackground(new java.awt.Color(0, 204, 153));
        tablazat.setSelectionForeground(new java.awt.Color(240, 240, 240));
        tablazat.getTableHeader().setReorderingAllowed(false);
        tablazat.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tablazatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablazat);

        jProgressBar1.setForeground(new java.awt.Color(0, 204, 153));

        reset.setBackground(new java.awt.Color(91, 175, 202));
        reset.setText("Reset");
        reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reset.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                resetActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel1.setText("Rendezés:");

        rendezesBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Név <", "Név >", "Dátum <", "Dátum >" }));

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jLabel2.setText("Keresés:");

        rendez.setBackground(new java.awt.Color(91, 175, 202));
        rendez.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adatbaziskezelo/Data-Generic-Sorting-2-icon.png"))); // NOI18N
        rendez.setText("Rendez");
        rendez.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rendez.setPreferredSize(new java.awt.Dimension(43, 30));
        rendez.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rendezActionPerformed(evt);
            }
        });

        keres.setBackground(new java.awt.Color(91, 175, 202));
        keres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adatbaziskezelo/Very-Basic-Search-icon.png"))); // NOI18N
        keres.setText("Keres");
        keres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        keres.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                keresActionPerformed(evt);
            }
        });

        keresesMezo.setBackground(new java.awt.Color(204, 204, 204));
        keresesMezo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        keresesMezo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        keresesMezo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        keresesMezo.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                keresesMezoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(vezetknev_text, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(keresztnev_text, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(belepokod_text, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(keresztnev_label)
                        .addComponent(vezeteknev_label)
                        .addComponent(belepokod_label)
                        .addComponent(hozzaad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(frissit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(torol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rendezesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rendez, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keresesMezo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keres, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(vezeteknev_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vezetknev_text, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keresztnev_label)
                        .addGap(1, 1, 1)
                        .addComponent(keresztnev_text, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(belepokod_label)
                        .addGap(1, 1, 1)
                        .addComponent(belepokod_text, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hozzaad, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frissit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(torol, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rendez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(keres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(rendezesBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(keresesMezo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void keresActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_keresActionPerformed
    {//GEN-HEADEREND:event_keresActionPerformed
        /* String lekerdezes = "select * from tabla where nev like '%" + keresesMezo.getText() + "%'";
        DefaultTableModel model = (DefaultTableModel) tablazat.getModel();
        model.setRowCount(0);
        adatok_megjelenitese_tablaban(lekerdezes);*/
    }//GEN-LAST:event_keresActionPerformed

    private void rendezActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_rendezActionPerformed
    {//GEN-HEADEREND:event_rendezActionPerformed
        int rendezestipus = rendezesBox.getSelectedIndex();
        String lekerdezes = null;
        if (rendezestipus == 0)
        {
            lekerdezes = "select * from tabla order by nev desc";
        }
        if (rendezestipus == 1)
        {
            lekerdezes = "select * from tabla order by nev asc";
        }
        if (rendezestipus == 2)
        {
            lekerdezes = "select * from tabla order by datum desc";
        }
        if (rendezestipus == 3)
        {
            lekerdezes = "select * from tabla order by datum asc";
        }
        DefaultTableModel model = (DefaultTableModel) tablazat.getModel();
        model.setRowCount(0);
        adatok_megjelenitese_tablaban(lekerdezes);
    }//GEN-LAST:event_rendezActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_resetActionPerformed
    {//GEN-HEADEREND:event_resetActionPerformed
        vezetknev_text.setText("");
        keresztnev_text.setText("");
        belepokod_text.setText("");
        DefaultTableModel model = (DefaultTableModel) tablazat.getModel();
        model.setRowCount(0);
        adatok_megjelenitese_tablaban();
    }//GEN-LAST:event_resetActionPerformed

    private void tablazatMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tablazatMouseClicked
    {//GEN-HEADEREND:event_tablazatMouseClicked
        int kivalasztott_sor = tablazat.getSelectedRow();
        TableModel model = tablazat.getModel();
        String v_nev, k_nev, seged;
        String[] nevfelbontva;
        seged = model.getValueAt(kivalasztott_sor, 1).toString();
        nevfelbontva = seged.split("_");
        vezetknev_text.setText(nevfelbontva[0]);
        keresztnev_text.setText(nevfelbontva[1]);
        belepokod_text.setText(model.getValueAt(kivalasztott_sor, 0).toString());
    }//GEN-LAST:event_tablazatMouseClicked

    private void hozzaadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_hozzaadActionPerformed
    {//GEN-HEADEREND:event_hozzaadActionPerformed
        boolean hiba = false;
        StringBuilder ujsttring = new StringBuilder();
        ujsttring.append(vezetknev_text.getText());
        ujsttring.append("_");
        ujsttring.append(keresztnev_text.getText());
        String nul = null;
        String lekerdezes = "INSERT INTO tabla (id,nev,datum) VALUES ('" + belepokod_text.getText() + "','" + ujsttring + "'," + nul + ")";
        //JOptionPane.showMessageDialog(null, lekerdezes);
        if (vezetknev_text.getText().isEmpty() || keresztnev_text.getText().isEmpty() || belepokod_text.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Töltsd ki minden mezőt a beillesztés előtt!");
        } else
        {
            String kod = belepokod_text.getText();
            for (int i = 0; i < kod.length(); i++)
            {
                if (Character.isAlphabetic(kod.charAt(i)))
                {
                    hiba = true;
                }
            }
            if (hiba)
            {
                JOptionPane.showMessageDialog(null, "Nem lehet betű a kódban!");
            } else
            {
                SQLlekerdezes(lekerdezes, "beillesztés", true);
            }
        }
    }//GEN-LAST:event_hozzaadActionPerformed

    private void frissitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_frissitActionPerformed
    {//GEN-HEADEREND:event_frissitActionPerformed
        StringBuilder ujsttring = new StringBuilder();
        ujsttring.append(vezetknev_text.getText());
        ujsttring.append("_");
        ujsttring.append(keresztnev_text.getText());
        String lekerdezes = "UPDATE tabla SET id = '" + belepokod_text.getText() + "' WHERE nev = '" + ujsttring + "'";
        SQLlekerdezes(lekerdezes, "frissítés", true);
    }//GEN-LAST:event_frissitActionPerformed

    private void torolActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_torolActionPerformed
    {//GEN-HEADEREND:event_torolActionPerformed
        String lekerdezes = "delete from tabla where id = '" + belepokod_text.getText() + "'";
        SQLlekerdezes(lekerdezes, "törlés", true);
    }//GEN-LAST:event_torolActionPerformed

    private void keresztnev_textActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_keresztnev_textActionPerformed
    {//GEN-HEADEREND:event_keresztnev_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_keresztnev_textActionPerformed

    private void vezetknev_textActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_vezetknev_textActionPerformed
    {//GEN-HEADEREND:event_vezetknev_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vezetknev_textActionPerformed

    private void keresesMezoKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_keresesMezoKeyTyped
    {//GEN-HEADEREND:event_keresesMezoKeyTyped

        String lekerdezes = "select * from tabla where nev like '%" + keresesMezo.getText() + "%'";
        DefaultTableModel model = (DefaultTableModel) tablazat.getModel();
        model.setRowCount(0);
        adatok_megjelenitese_tablaban(lekerdezes);
    }//GEN-LAST:event_keresesMezoKeyTyped

    private void hozzaadMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_hozzaadMouseEntered
    {//GEN-HEADEREND:event_hozzaadMouseEntered
        hozzaad.setBorder(BorderFactory.createLineBorder(new Color(4,137,0), 3));
    }//GEN-LAST:event_hozzaadMouseEntered

    private void hozzaadMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_hozzaadMouseExited
    {//GEN-HEADEREND:event_hozzaadMouseExited
        hozzaad.setBorder(BorderFactory.createLineBorder(new Color(91,175,202),3));
    }//GEN-LAST:event_hozzaadMouseExited

    private void frissitMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_frissitMouseEntered
    {//GEN-HEADEREND:event_frissitMouseEntered
        frissit.setBorder(BorderFactory.createLineBorder(new Color(3, 0, 254), 3));
    }//GEN-LAST:event_frissitMouseEntered

    private void frissitMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_frissitMouseExited
    {//GEN-HEADEREND:event_frissitMouseExited
        frissit.setBorder(BorderFactory.createLineBorder(new Color(91,175,202), 3));
    }//GEN-LAST:event_frissitMouseExited

    private void torolMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_torolMouseEntered
    {//GEN-HEADEREND:event_torolMouseEntered
        torol.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 2), 3));
    }//GEN-LAST:event_torolMouseEntered

    private void torolMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_torolMouseExited
    {//GEN-HEADEREND:event_torolMouseExited
        torol.setBorder(BorderFactory.createLineBorder(new Color(91,175,202), 3));
    }//GEN-LAST:event_torolMouseExited

    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(felulet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(felulet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(felulet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(felulet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new felulet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel belepokod_label;
    private javax.swing.JTextField belepokod_text;
    private javax.swing.JButton frissit;
    private javax.swing.JButton hozzaad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton keres;
    private javax.swing.JTextField keresesMezo;
    private javax.swing.JLabel keresztnev_label;
    private javax.swing.JTextField keresztnev_text;
    private javax.swing.JButton rendez;
    private javax.swing.JComboBox<String> rendezesBox;
    private javax.swing.JButton reset;
    private javax.swing.JTable tablazat;
    private javax.swing.JButton torol;
    private javax.swing.JLabel vezeteknev_label;
    private javax.swing.JTextField vezetknev_text;
    // End of variables declaration//GEN-END:variables
}
