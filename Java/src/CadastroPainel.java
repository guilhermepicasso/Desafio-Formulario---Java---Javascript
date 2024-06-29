import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class CadastroPainel extends JPanel{
    private JTextField nomeField;
    private JTextField descricaoField;
    private JFormattedTextField valorField;
    private JRadioButton disponivelSim;
    private JRadioButton disponivelNao;
    private ButtonGroup disponivelGroup;
    private JButton cadastrarButton;
    private ProdutoPainel produtoPainel;

    public CadastroPainel(ProdutoPainel produtoPainel){
        this.produtoPainel = produtoPainel;
        setLayout(new GridLayout(5,2));

        add(new JLabel("Nome do Produto"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Descrição do Produto"));
        descricaoField = new JTextField();
        add(descricaoField);

        add(new JLabel("Valor do produto"));
        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.0);
        formatter.setAllowsInvalid(false);
        valorField= new JFormattedTextField(formatter);
        add(valorField);
        

        add(new JLabel("Disponível para venda:"));
        JPanel disponivelPanel = new JPanel(new FlowLayout());
        disponivelSim = new JRadioButton("Sim");
        disponivelNao = new JRadioButton("Não");
        disponivelGroup = new ButtonGroup();
        disponivelGroup.add(disponivelSim);
        disponivelGroup.add(disponivelNao);
        disponivelPanel.add(disponivelSim);
        disponivelPanel.add(disponivelNao);
        add(disponivelPanel);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                cadastrarProduto();
            }
        });
        add(cadastrarButton);
    }

    private void cadastrarProduto() {
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        double valor = ((Number) valorField.getValue()).doubleValue();
        boolean disponivel = disponivelSim.isSelected();

        Produto produto = new Produto(nome, descricao, valor, disponivel);
        produtoPainel.addProduto(produto);

        nomeField.setText("");
        descricaoField.setText("");
        valorField.setValue(null);
        disponivelGroup.clearSelection();

        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "listagem");
    }


    
}
