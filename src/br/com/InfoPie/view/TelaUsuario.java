/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.view;

import java.sql.*;
import br.com.InfoPie.connection.ConnectionFactory;
import br.com.InfoPie.model.beans.Usuarios;
import br.com.InfoPie.modelDAO.UsuarioDao;
import br.com.InfoPie.modelDAO.UsuarioTableModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author debs
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    UsuarioTableModel tableModel = new UsuarioTableModel();

    /**
     * Creates new form TelaUsuario
     */
    //construtor
    public TelaUsuario() {
        initComponents();
        conexao = ConnectionFactory.getConnection();
        //Colocando dados detro da tabela
        DefaultTableModel model = (DefaultTableModel) tbUsuarios.getModel();
        tbUsuarios.setRowSorter(new TableRowSorter(model));
        readJtableUsuarios();

    }

    public void readJtableUsuarios() {
        DefaultTableModel model = (DefaultTableModel) tbUsuarios.getModel();
        model.setNumRows(0);
        UsuarioDao usuario = new UsuarioDao();
        usuario.readUsuarios().forEach((user) -> {
            model.addRow(new Object[]{
                //Chama os objetos (é com virgula pq é objeto de array) dentro de [].
                user.getIdUser(),
                user.getNomeUsuario(),
                user.getTelefoneUsuario(),
                user.getLogin(),
                user.getSenha(),
                user.getPerfil()});
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUsuarioNome = new javax.swing.JTextField();
        txtUsuarioPass = new javax.swing.JTextField();
        txtUsuarioFone = new javax.swing.JFormattedTextField();
        txtUsuarioLogin = new javax.swing.JTextField();
        cboUsuarioPerfil = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnUsuarioCreate = new javax.swing.JButton();
        btnUsuarioUp = new javax.swing.JButton();
        btnUsuarioDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtFinder = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jLabel7.setText("jLabel7");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(929, 562));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de usuários ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel9.setText("* campos obrigatórios");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("*Nome:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Celular:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("*Login:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("*Senha:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText(" Perfil:");

        txtUsuarioNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsuarioNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioNomeActionPerformed(evt);
            }
        });

        txtUsuarioPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsuarioPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioPassActionPerformed(evt);
            }
        });

        try {
            txtUsuarioFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtUsuarioFone.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtUsuarioFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioFoneActionPerformed(evt);
            }
        });

        txtUsuarioLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsuarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioLoginActionPerformed(evt);
            }
        });

        cboUsuarioPerfil.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboUsuarioPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin\t", "user" }));
        cboUsuarioPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuarioPerfilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsuarioPass, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuarioFone, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(40, 40, 40)
                                .addComponent(cboUsuarioPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(txtUsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUsuarioPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboUsuarioPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnUsuarioCreate.setText("Adicionar Usuario");
        btnUsuarioCreate.setToolTipText("adicionar");
        btnUsuarioCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsuarioCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuarioCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioCreateActionPerformed(evt);
            }
        });

        btnUsuarioUp.setText("Editar Usuario");
        btnUsuarioUp.setToolTipText("editar");
        btnUsuarioUp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsuarioUp.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuarioUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioUpActionPerformed(evt);
            }
        });

        btnUsuarioDelete.setText("Remover usuarios");
        btnUsuarioDelete.setToolTipText("remover");
        btnUsuarioDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsuarioDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuarioDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioDeleteActionPerformed(evt);
            }
        });

        jButton1.setText("Limpar Campos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnUsuarioCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnUsuarioDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnUsuarioUp, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuarioCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuarioDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuarioUp, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuários  Existentes no Sistema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID USUARIO", "NOME USUARIO", "Telefone", "LOGIN ", "SENHA", "PERFIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuariosMouseClicked(evt);
            }
        });
        tbUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbUsuariosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbUsuarios);

        jLabel1.setText("Nome do usuario:");

        jButton2.setText("pesquisar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFinder, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFinder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        setBounds(0, 0, 984, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioNomeActionPerformed

    private void cboUsuarioPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuarioPerfilActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboUsuarioPerfilActionPerformed

    private void btnUsuarioCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioCreateActionPerformed

        Usuarios usuario = new Usuarios();
        UsuarioDao dao = new UsuarioDao();
        //Adiciona o usuario com base dos dados que serão escritos pelo usuario
        usuario.setNomeUsuario(txtUsuarioNome.getText());
        usuario.setTelefoneUsuario(txtUsuarioFone.getText());
        usuario.setLogin(txtUsuarioLogin.getText());
        usuario.setSenha(txtUsuarioPass.getText());
        usuario.setPerfil(cboUsuarioPerfil.getSelectedItem().toString());
        dao.InsertUser(usuario);
        //Tira os textos dos campos
        txtUsuarioNome.setText("");
        txtUsuarioLogin.setText("");
        txtUsuarioPass.setText("");
        txtUsuarioFone.setText("");
        

        readJtableUsuarios();
    }//GEN-LAST:event_btnUsuarioCreateActionPerformed

    private void btnUsuarioUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioUpActionPerformed
        if (tbUsuarios.getSelectedRow() != -1) {         //SE a tabela estiver selecionada

            Usuarios usuario = new Usuarios();
            UsuarioDao dao = new UsuarioDao();
            //Pega o id
            usuario.setIdUser((int) tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 0));
            //Atualização dos dados 
            usuario.setNomeUsuario(txtUsuarioNome.getText());
            usuario.setLogin(txtUsuarioLogin.getText());
            usuario.setSenha(txtUsuarioPass.getText());
            usuario.setTelefoneUsuario(txtUsuarioFone.getText());
            usuario.setPerfil(cboUsuarioPerfil.getSelectedItem().toString());
            dao.updateUser(usuario);
            //Retirando texto dos campos
            txtUsuarioNome.setText("");
            txtUsuarioLogin.setText("");
            txtUsuarioPass.setText("");
            txtUsuarioFone.setText("");
            
            //Atualiza a tabela
            readJtableUsuarios();
        }


    }//GEN-LAST:event_btnUsuarioUpActionPerformed

    private void btnUsuarioDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioDeleteActionPerformed
        if (tbUsuarios.getSelectedRow() != -1) {        //SE a tabela estiver selecionada
            Usuarios usuario = new Usuarios();
            UsuarioDao dao = new UsuarioDao();
            //Pega o id do Usuario para fazer a exclusão
            usuario.setIdUser((int) tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 0));
            //Chama o metodo e deleta Usuario
            dao.deleteUser(usuario);
            //Depois da logica realizada acima tira o texto dos campos.
            txtUsuarioNome.setText("");
            txtUsuarioLogin.setText("");
            txtUsuarioPass.setText("");
            txtUsuarioFone.setText("");
            

            //Atualiza a tabela
            readJtableUsuarios();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um outro usuario para excluir.");//Faz a exclusão de quantos usuarios precisar
        }
    }//GEN-LAST:event_btnUsuarioDeleteActionPerformed

    private void txtUsuarioFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioFoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioFoneActionPerformed

    private void txtUsuarioPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioPassActionPerformed

    private void txtUsuarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioLoginActionPerformed

    private void tbUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuariosMouseClicked
        //Quando clicar sobre a tabela
        if (tbUsuarios.getSelectedRow() != -1) {         //SE a tabela estiver selecionada
            //Preenche os campos de texto ao clicar dentro de um dado na tabela
            txtUsuarioNome.setText(tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 1).toString());
            txtUsuarioLogin.setText(tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 2).toString());
            txtUsuarioPass.setText(tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 3).toString());
            txtUsuarioFone.setText(tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 4).toString());
            cboUsuarioPerfil.setSelectedItem((tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 5).toString()));

            /*int linhaselcionada = tbUsuarios.getSelectedRow();
            String sta = (String) tbUsuarios.getValueAt(linhaselcionada, 5);
            cboUsuarioPerfil.setSelectedItem(sta);*/
        }
    }//GEN-LAST:event_tbUsuariosMouseClicked

    private void tbUsuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUsuariosKeyReleased
        if (tbUsuarios.getSelectedRow() != -1) {
            //Preenche os campos ao clicar dentro de um dado na tabela
            txtUsuarioNome.setText(tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 1).toString());
            txtUsuarioLogin.setText(tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 2).toString());
            txtUsuarioPass.setText(tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 3).toString());
            txtUsuarioFone.setText(tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 4).toString());
            cboUsuarioPerfil.setSelectedItem((tbUsuarios.getValueAt(tbUsuarios.getSelectedRow(), 5).toString()));
            //int linhaselcionada = tbUsuarios.getSelectedRow();
            /*String sta = (String) tbUsuarios.getValueAt(linhaselcionada, 5);
            cboUsuarioPerfil.setSelectedItem(sta);*/

        }
    }//GEN-LAST:event_tbUsuariosKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Limpa campos 
        txtUsuarioNome.setText("");
        txtUsuarioLogin.setText("");
        txtUsuarioPass.setText("");
        txtUsuarioFone.setText("");
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuarioCreate;
    private javax.swing.JButton btnUsuarioDelete;
    private javax.swing.JButton btnUsuarioUp;
    private javax.swing.JComboBox<String> cboUsuarioPerfil;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtFinder;
    private javax.swing.JFormattedTextField txtUsuarioFone;
    private javax.swing.JTextField txtUsuarioLogin;
    private javax.swing.JTextField txtUsuarioNome;
    private javax.swing.JTextField txtUsuarioPass;
    // End of variables declaration//GEN-END:variables
}
