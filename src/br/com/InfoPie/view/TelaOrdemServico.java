/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.view;

import java.sql.*;
import br.com.InfoPie.connection.ConnectionFactory;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author debs
 */
public class TelaOrdemServico extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    //Armazena texto de acodo com o radio button Selecionado
    private String tipo;

    /**
     * Creates new form TelaOrdemServico
     */
    public TelaOrdemServico() {
        initComponents();
        //chamando metodo conector / atribuir conexao ;
        conexao = ConnectionFactory.getConnection();

    }

    private void pesquisarCliente() {
        String sql = "select id_cliente as id, nome_cliente as nome, fone_cliente as fone from tb_clientes where nome_cliente like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtOsFinder.getText() + "%"); // "%" continuar instrução sql;
            rs = pst.executeQuery();
            tblOsCliente.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setaCampos() {
        int setar = tblOsCliente.getSelectedRow();
        txtOsId.setText(tblOsCliente.getModel().getValueAt(setar, 0).toString());

    }

    //Metodo para Cadastrar Uma ordem de serviço
    private void emitirOS() {
        String sql = "insert into tb_ordemservico(tipo,situacao,equipamento,defeito,servico,tecnico,valor,id_cliente) values (?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOdemServicoSituacao.getSelectedItem().toString());
            pst.setString(3, txtOsEquipamento.getText());
            pst.setString(4, txtOsDefeito.getText());
            pst.setString(5, txtOsServico.getText());
            pst.setString(6, txtOsTecnico.getText());
            pst.setString(7, txtOsValorTotal.getText().replace(",", ".")); // Trocar vigula pelo ponto
            pst.setString(8, txtOsId.getText());

            //Validação dos campos OBRIGATORIOS
            // id ,equipamento,def,
            if ((txtOsId.getText().isEmpty()) || (txtOsEquipamento.getText().isEmpty()) || (txtOsDefeito.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null,
                        "Preencha todos os campos obrigatórios");
            } else {
                //Confirmação dos dados preenchidos
                int add = pst.executeUpdate();
                if (add > 0) {
                    JOptionPane.showMessageDialog(null,
                            "Emitida com sucesso");
                    txtOsId.setText(null);
                    txtOsEquipamento.setText(null);
                    txtOsDefeito.setText(null);
                    txtOsServico.setText(null);
                    txtOsTecnico.setText(null);
                    txtOsValorTotal.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo Pesquisa OS
    private void pesquisarOs() {
        //Entrada JOptionPane
        String numOs = JOptionPane.showInputDialog("Número da OS ");
        String sql = "select * from tb_ordemservico where os = " + numOs;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtOsNumeroOs.setText(rs.getString(1));
                txtOsData.setText(rs.getString(2));
                String rbtTipo = rs.getString(3);
                if (rbtTipo.equals("Ordem de serviço")) {
                    rbOsOrcamento.setSelected(true);
                    tipo = "Ordem de serviço";
                } else {
                    rbOsOrdemServico.setSelected(true);
                    tipo = "Orçamento";
                }
                cboOdemServicoSituacao.setSelectedItem(rs.getString(4));
                txtOsEquipamento.setText(rs.getString(5));
                txtOsDefeito.setText(rs.getString(6));
                txtOsServico.setText(rs.getString(7));
                txtOsTecnico.setText(rs.getString(8));
                txtOsValorTotal.setText(rs.getString(9));
                txtOsId.setText(rs.getString(10));
                //Desabilitando oque não esta usando para não ter problemas dps
                btnOsAdd.setEnabled(false);
                txtOsFinder.setEditable(false);
                tblOsCliente.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Ordem de serviço não cadastrada!");
            }
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Os Invalida");
            System.out.println(e);
        } catch (HeadlessException | SQLException e2) {
            JOptionPane.showMessageDialog(null, e2);
        }
    }

    //Altera ordem de servico
    private void alterar_os() {
        String sql = "update tb_ordemservico set tipo = ?, situacao = ?,equipamento = ?,defeito = ? ,servico = ?,tecnico = ? , valor = ? where os = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOdemServicoSituacao.getSelectedItem().toString());
            pst.setString(3, txtOsEquipamento.getText());
            pst.setString(4, txtOsDefeito.getText());
            pst.setString(5, txtOsServico.getText());
            pst.setString(6, txtOsTecnico.getText());
            //.replace troca a virgula pelo ponto "."
            pst.setString(7, txtOsValorTotal.getText().replace(",", ".")); // Trocar vigula pelo ponto
            pst.setString(8, txtOsId.getText());

            //Validação dos campos OBRIGATORIOS
            // id ,equipamento,def,
            if ((txtOsId.getText().isEmpty()) || (txtOsEquipamento.getText().isEmpty()) || (txtOsDefeito.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null,
                        "Preencha todos os campos obrigatórios");
            } else {
                //Confirmação dos dados preenchidos e 
                //update no banco de dados
                int add = pst.executeUpdate();
                if (add > 0) {
                    JOptionPane.showMessageDialog(null,
                            "Ordem de servico alterada com sucesso");
                    txtOsNumeroOs.setText(null);
                    txtOsData.setText(null);
                    txtOsId.setText(sql);
                    txtOsEquipamento.setText(null);
                    txtOsDefeito.setText(null);
                    txtOsServico.setText(null);
                    txtOsTecnico.setText(null);
                    txtOsValorTotal.setText(null);

                    //Habilita os objetos
                    btnOsAdd.setEnabled(true);
                    txtOsFinder.setEnabled(true);
                    tblOsCliente.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void excluir_os() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir essa OS ? ", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tb_ordemservico where os = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtOsNumeroOs.getText());
                int apagar = pst.executeUpdate();
                if (apagar > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Ordem de serviço excluida com sucesso");
                    txtOsNumeroOs.setText(null);
                    txtOsData.setText(null);
                    txtOsId.setText(sql);
                    txtOsEquipamento.setText(null);
                    txtOsDefeito.setText(null);
                    txtOsServico.setText(null);
                    txtOsTecnico.setText(null);
                    txtOsValorTotal.setText(null);
                    //Habilita os objetos
                    btnOsAdd.setEnabled(true);
                    txtOsFinder.setEnabled(true);
                    tblOsCliente.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtOsNumeroOs = new javax.swing.JTextField();
        txtOsData = new javax.swing.JTextField();
        rbOsOrdemServico = new javax.swing.JRadioButton();
        rbOsOrcamento = new javax.swing.JRadioButton();
        cboOdemServicoSituacao = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtOsFinder = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOsCliente = new javax.swing.JTable();
        txtOsId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtOsEquipamento = new javax.swing.JTextField();
        txtOsDefeito = new javax.swing.JTextField();
        txtOsServico = new javax.swing.JTextField();
        txtOsTecnico = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtOsValorTotal = new javax.swing.JTextField();
        btnOsAdd = new javax.swing.JButton();
        btnOsRemove = new javax.swing.JButton();
        btnOsEdit = new javax.swing.JButton();
        btnOsFinder = new javax.swing.JButton();
        btnOsPrint = new javax.swing.JButton();

        jLabel7.setText("jLabel7");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        setPreferredSize(new java.awt.Dimension(929, 562));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Nº OS");

        jLabel3.setText("Data");

        txtOsNumeroOs.setEditable(false);

        txtOsData.setEditable(false);

        buttonGroup1.add(rbOsOrdemServico);
        rbOsOrdemServico.setText("Ordem de serviço");
        rbOsOrdemServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOsOrdemServicoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbOsOrcamento);
        rbOsOrcamento.setText("Orçamento");
        rbOsOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOsOrcamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOsNumeroOs, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(rbOsOrcamento))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(rbOsOrdemServico)
                    .addComponent(txtOsData, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOsNumeroOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOsData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbOsOrdemServico)
                    .addComponent(rbOsOrcamento))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        cboOdemServicoSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na bancada", "Entrega OK", "Orçamento REPROVADO", "Aguardando aprovação", "Aguardando Peças", "Abandonado pelo cliente", "Retornou" }));

        jLabel1.setText("Situação");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/InfoPie/icons/finderpq.png"))); // NOI18N

        txtOsFinder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsFinderActionPerformed(evt);
            }
        });
        txtOsFinder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOsFinderKeyReleased(evt);
            }
        });

        jLabel6.setText("*Id:");

        tblOsCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Telefone"
            }
        ));
        tblOsCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOsClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOsCliente);

        txtOsId.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOsFinder, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOsId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOsFinder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txtOsId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jLabel8.setText("* Equipamento");

        jLabel9.setText("* Defeito");

        jLabel10.setText("Serviço");

        jLabel11.setText("Técnico");

        jLabel12.setText("Valor Total");

        txtOsValorTotal.setText("0");

        btnOsAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/InfoPie/icons/add.png"))); // NOI18N
        btnOsAdd.setPreferredSize(new java.awt.Dimension(60, 60));
        btnOsAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAddActionPerformed(evt);
            }
        });

        btnOsRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/InfoPie/icons/delete.png"))); // NOI18N
        btnOsRemove.setPreferredSize(new java.awt.Dimension(60, 60));
        btnOsRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsRemoveActionPerformed(evt);
            }
        });

        btnOsEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/InfoPie/icons/edit.png"))); // NOI18N
        btnOsEdit.setPreferredSize(new java.awt.Dimension(60, 60));
        btnOsEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsEditActionPerformed(evt);
            }
        });

        btnOsFinder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/InfoPie/icons/finder.png"))); // NOI18N
        btnOsFinder.setFocusTraversalPolicyProvider(true);
        btnOsFinder.setPreferredSize(new java.awt.Dimension(60, 60));
        btnOsFinder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsFinderActionPerformed(evt);
            }
        });

        btnOsPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/InfoPie/icons/printer.png"))); // NOI18N
        btnOsPrint.setToolTipText("imprimir");
        btnOsPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOsPrint.setOpaque(false);
        btnOsPrint.setPreferredSize(new java.awt.Dimension(70, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(btnOsAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOsRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOsFinder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOsPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboOdemServicoSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtOsDefeito)
                                .addComponent(txtOsEquipamento)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtOsServico, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(67, 67, 67)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtOsTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOsValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboOdemServicoSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOsEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtOsDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtOsServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtOsTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtOsValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOsAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOsRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnOsFinder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(929, 535));
    }// </editor-fold>//GEN-END:initComponents

    private void txtOsFinderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsFinderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsFinderActionPerformed

    private void txtOsFinderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOsFinderKeyReleased
        // Chamando  o metodo PesquisaCliente
        pesquisarCliente();
    }//GEN-LAST:event_txtOsFinderKeyReleased

    private void tblOsClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOsClienteMouseClicked
        //Chamando o metodo SetarCampos
        setaCampos();

    }//GEN-LAST:event_tblOsClienteMouseClicked

    private void rbOsOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOsOrcamentoActionPerformed
        // Atribuindo um texto a variavel se for selecionado
        tipo = "Orçamento";
    }//GEN-LAST:event_rbOsOrcamentoActionPerformed

    private void rbOsOrdemServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOsOrdemServicoActionPerformed
        // Atribuindo um texto a variavel se for selecionado
        tipo = "Ordem de Serviço";
    }//GEN-LAST:event_rbOsOrdemServicoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // Ao abrir o form, marcar o RadioButton Orcamento
        rbOsOrcamento.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnOsAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAddActionPerformed
        // TODO add your handling code here:
        //Chama o metodo emitir OS 
        emitirOS();

    }//GEN-LAST:event_btnOsAddActionPerformed

    private void btnOsFinderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsFinderActionPerformed
        // TODO add your handling code here:
        pesquisarOs();
    }//GEN-LAST:event_btnOsFinderActionPerformed

    private void btnOsEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsEditActionPerformed
        //Chama metodo Alterar
        alterar_os();
    }//GEN-LAST:event_btnOsEditActionPerformed

    private void btnOsRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsRemoveActionPerformed
        //Metodo exclui OS
        excluir_os(); 
    }//GEN-LAST:event_btnOsRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsAdd;
    private javax.swing.JButton btnOsEdit;
    private javax.swing.JButton btnOsFinder;
    private javax.swing.JButton btnOsPrint;
    private javax.swing.JButton btnOsRemove;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboOdemServicoSituacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbOsOrcamento;
    private javax.swing.JRadioButton rbOsOrdemServico;
    private javax.swing.JTable tblOsCliente;
    private javax.swing.JTextField txtOsData;
    private javax.swing.JTextField txtOsDefeito;
    private javax.swing.JTextField txtOsEquipamento;
    private javax.swing.JTextField txtOsFinder;
    private javax.swing.JTextField txtOsId;
    private javax.swing.JTextField txtOsNumeroOs;
    private javax.swing.JTextField txtOsServico;
    private javax.swing.JTextField txtOsTecnico;
    private javax.swing.JTextField txtOsValorTotal;
    // End of variables declaration//GEN-END:variables
}
