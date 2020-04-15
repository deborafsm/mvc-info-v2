/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.view;

import java.sql.*;
import br.com.InfoPie.connection.ConnectionFactory;
import br.com.InfoPie.model.beans.Equipamentos;
import br.com.InfoPie.modelDAO.OrdemDeServicoDao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author debs
 */
public class HelpDesk extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    //Armazena texto de acodo com o radio button Selecionado
    private String tipo;

    /**
     * Creates new form TelaOrdemServico
     */
    public HelpDesk() {
        initComponents();
        //chamando metodo conector / atribuir conexao ;
        conexao = ConnectionFactory.getConnection();
        //Cria o modelo da tabela 
        DefaultTableModel model = (DefaultTableModel) tblOsCliente.getModel();
        //Classificador de linha
        tblOsCliente.setRowSorter(new TableRowSorter(model));

        readJtable();//Já inicia com os dados do banco selecionado na tabela

    }

    public void readJtable() {
        DefaultTableModel model = (DefaultTableModel) tblOsCliente.getModel();
        model.setNumRows(0);
        OrdemDeServicoDao os = new OrdemDeServicoDao();

        os.findAll().forEach((oserv) -> {
            // for é usado para passar pelos objetos
            model.addRow(new Object[]{
                /* ordemdeservico.tecnico, ordemdeservico.servico,ordemdeservico.situacao,ordemdeservico.
                valor, equipamento.defeito,equipamento.marca,equipamento.tipo\n"
                + "from ordemdeservico \n"
                + "inner join equipamento on ordemdeservico.id_os = equipamento.id_os;";*/
                
                oserv.getTecnico(),
                oserv.getServico(),
                oserv.getSituacao(),
                oserv.getValor(),
                 
                oserv.getDefeito(),
                oserv.getMarca(),
                oserv.getTipo()
            });
        });

    }
       

    /*private void pesquisarCliente() {
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
            pst.setString(7, txtValorTotal.getText().replace(",", ".")); // Trocar vigula pelo ponto
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
                    txtValorTotal.setText(null);
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
                txtValorTotal.setText(rs.getString(9));
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
            pst.setString(7, txtValorTotal.getText().replace(",", ".")); // Trocar vigula pelo ponto
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
                    txtValorTotal.setText(null);

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
                    txtValorTotal.setText(null);
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOsCliente = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();

        jLabel7.setText("jLabel7");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Controle de Serviço");
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

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblOsCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tecnico", "Serviço", "Situação", "Valor", "Defeito", "Marca", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOsCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOsClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOsCliente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jToggleButton1.setText("Atualizar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton1.setText("Fechar chamado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1)
                .addGap(53, 53, 53)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jButton1))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(984, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void tblOsClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOsClienteMouseClicked
        //Chamando o metodo SetarCampos

    }//GEN-LAST:event_tblOsClienteMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // Ao abrir o form, marcar o RadioButton Orcamento
    }//GEN-LAST:event_formInternalFrameOpened

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        readJtable();
    }//GEN-LAST:event_jToggleButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tblOsCliente;
    // End of variables declaration//GEN-END:variables
}
