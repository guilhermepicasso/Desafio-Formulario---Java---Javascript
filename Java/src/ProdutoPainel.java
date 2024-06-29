import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class ProdutoPainel extends JPanel{
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton novoProdutoButton;
    private ArrayList<Produto> produtos;
    
    public ProdutoPainel() {
        setLayout(new BorderLayout());

        produtos = new ArrayList<>();
        tableModel = new DefaultTableModel(new Object[]{"Nome", "Valor"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        novoProdutoButton = new JButton("Novo Produto");
        novoProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCadastro();
            }
        });
        add(novoProdutoButton, BorderLayout.SOUTH);
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
        atualizarTabela();
    }

    private void atualizarTabela() {
        produtos.sort(Comparator.comparingDouble(Produto::getValor));
        tableModel.setRowCount(0); 

        for (Produto produto : produtos) {
            tableModel.addRow(new Object[]{produto.getNome(), produto.getValor()});
        }
    }

    private void mostrarCadastro() {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "cadastro");
    }
}
