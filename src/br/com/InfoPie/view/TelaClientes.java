/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.view;

import java.sql.*;
import br.com.InfoPie.connection.ConnectionFactory;
import br.com.InfoPie.model.beans.Cliente;
import br.com.InfoPie.modelDAO.ClienteDao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author debs
 */
public class TelaClientes extends javax.swing.JInternalFrame {

    //Inicia com as classes instanciadas 
    Cliente cliente = new Cliente();
    ClienteDao dao = new ClienteDao();
    //Conexoes 
    Connection conexao = null;

    /**
     * Creates new form TelaClientes
     */
    public TelaClientes() {
        initComponents();
        //Pega a conexao
        conexao = ConnectionFactory.getConnection();
        //Cria o modelo da tabela 
        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        //Classificador de linha
        tblClientes.setRowSorter(new TableRowSorter(model));

        readJtable();//Já inicia com os dados do banco selecionado na tabela

    }

    //Ele lista os dados dentro da tabela.
    public void readJtable() {
        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.setNumRows(0);
        ClienteDao cliente = new ClienteDao();

        // prod.findAll().stream().forEach((p)-> { //Operação funcional
        for (Cliente c : cliente.readCliente()) { // for é usado para passar pelos objetos
            model.addRow(new Object[]{
                //Chama os item 
                c.getId(),
                c.getNomeCliente(),
                c.getTelefoneCliente(),
                c.getEmailCliente(),
                c.getEnderecoCliente()

            });

        }

    }

//esse metdo lista dados para dentro da tabela atraves da chamada do metodo finderCliente
    public void readJtableForNameClient(String nome) {
        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.setNumRows(0);
        ClienteDao cliente = new ClienteDao();

        // prod.findAll().stream().forEach((p)-> { //Operação funcional
        for (Cliente c : cliente.findeClient(nome)) { // for é usado para passar pelos objetos
            model.addRow(new Object[]{
                //Chama os item 
                c.getId(),
                c.getNomeCliente(),
                c.getTelefoneCliente(),
                c.getEmailCliente(),
                c.getEnderecoCliente()

            });

        }

    }

    //Metodo SETA campos da tabela do banco de dados para o form
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jcbUF = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        btnAddCliente = new javax.swing.JButton();
        btnExcluirCliente = new javax.swing.JButton();
        btnAtualizarCliente = new javax.swing.JButton();
        txtFone = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        txtFinderName = new javax.swing.JTextField();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tela de clientes");
        setPreferredSize(new java.awt.Dimension(929, 562));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro"));

        jLabel1.setText("Nome:");

        jLabel2.setText("Endereço:");

        jLabel3.setText("Celular:");

        jLabel4.setText("E-mail:");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });

        jLabel5.setText("Cidade:");

        txtCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCityActionPerformed(evt);
            }
        });

        jcbUF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbUFActionPerformed(evt);
            }
        });

        jLabel6.setText("UF:");

        jLabel7.setText("Estado:");

        txtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoActionPerformed(evt);
            }
        });

        btnAddCliente.setText("Adcionar Cliente");
        btnAddCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddClienteActionPerformed(evt);
            }
        });

        btnExcluirCliente.setText("Excluir Cliente");
        btnExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirClienteActionPerformed(evt);
            }
        });

        btnAtualizarCliente.setText("Atualizar Cliente");
        btnAtualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarClienteActionPerformed(evt);
            }
        });

        try {
            txtFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCity)
                                    .addComponent(txtEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(jcbUF, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 198, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtMail))
                                .addContainerGap())
                            .addComponent(txtEndereco)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAddCliente)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirCliente)
                        .addGap(18, 18, 18)
                        .addComponent(btnAtualizarCliente)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirCliente)
                    .addComponent(btnAddCliente)
                    .addComponent(btnAtualizarCliente))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "TELEFONE", "E-MAIL", "ENDEREÇO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        tblClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblClientesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel8.setText("Nome Cliente: ");

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFinderName, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar)
                        .addGap(60, 60, 60))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtFinderName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 984, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCityActionPerformed

    private void jcbUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbUFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbUFActionPerformed

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnAddClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddClienteActionPerformed
        //Adicionando CLiente no banco de dados
        Cliente cliente = new Cliente();
        ClienteDao dao = new ClienteDao();
        //Adiciona o cliente com base dos dados que serão escritos pelo usuario
        cliente.setNomeCliente(txtNome.getText());
        cliente.setTelefoneCliente(txtFone.getText());
        cliente.setEmailCliente(txtMail.getText());
        cliente.setEnderecoCliente(txtEndereco.getText());

        //chama o metodo de inserir o cliente
        dao.insertClient(cliente);
        //Tira os textos dos campos
        txtNome.setText("");
        txtEndereco.setText("");
        txtFone.setText("");
        txtMail.setText("");
        //Faz a atualização na tabela 
        readJtable();
    }//GEN-LAST:event_btnAddClienteActionPerformed

    private void btnExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirClienteActionPerformed
        //Ecluindo do banco de dados
        if (tblClientes.getSelectedRow() != -1) {
            Cliente cliente = new Cliente();
            ClienteDao dao = new ClienteDao();
            //Pega o id do cliente para fazer a exclusão
            cliente.setId((int) tblClientes.getValueAt(tblClientes.getSelectedRow(), 0));
            //Chama o metodo e deleta cliente
            dao.deleteClient(cliente);
            //Depois da logica realizada acima tira o texto dos campos.
            txtNome.setText("");
            txtEndereco.setText("");
            txtFone.setText("");
            txtMail.setText("");
            //Atualiza a tabela
            readJtable();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um outro cliente para excluir.");//Faz a exclusão de quantos precisar
        }
    }//GEN-LAST:event_btnExcluirClienteActionPerformed

    private void btnAtualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarClienteActionPerformed
        //Atualizar Cliente
        //Seleciona da tabela
        if (tblClientes.getSelectedRow() != -1) {
            Cliente cliente = new Cliente();
            ClienteDao dao = new ClienteDao();
            //Seta todos os campos que podem ser atualizados
            cliente.setNomeCliente(txtNome.getText());
            cliente.setTelefoneCliente(txtFone.getText());
            cliente.setEmailCliente(txtMail.getText());
            cliente.setEnderecoCliente(txtEndereco.getText());
            
            
            //A atualização só vai ser possivel atraves do id
            cliente.setId((int) tblClientes.getValueAt(tblClientes.getSelectedRow(), 0));
            //Chama metodo UPDATE 
            dao.updateCliente(cliente);
            //Depois tira os texto dos campos
            txtNome.setText("");
            txtEndereco.setText("");
            txtFone.setText("");
            txtMail.setText("");
            //Atualiza os campos da tabela
            readJtable();
        }
    }//GEN-LAST:event_btnAtualizarClienteActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        if (tblClientes.getSelectedRow() != -1) {
            //Preenche os campos ao clicar dentro de um dado na tabela
            txtNome.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 1).toString());
            txtFone.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 2).toString());
            txtMail.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 3).toString());
            txtEndereco.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 4).toString());

        }
    }//GEN-LAST:event_tblClientesMouseClicked

    private void tblClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClientesKeyReleased
        if (tblClientes.getSelectedRow() != -1) {
            //Preenche os campos ao clicar dentro de um dado na tabela
            txtNome.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 1).toString());
            txtFone.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 2).toString());
            txtMail.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 3).toString());
            txtEndereco.setText(tblClientes.getValueAt(tblClientes.getSelectedRow(), 4).toString());

        }
    }//GEN-LAST:event_tblClientesKeyReleased

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        //Chama metodo list para procura por nome
        //Pega o nome da pessoa e busca no banco de dados
        readJtableForNameClient(txtFinderName.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCliente;
    private javax.swing.JButton btnAtualizarCliente;
    private javax.swing.JButton btnExcluirCliente;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbUF;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFinderName;
    private javax.swing.JFormattedTextField txtFone;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
