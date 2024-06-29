import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Cadastro Produtos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel cardPanel = new JPanel(new CardLayout());

        ProdutoPainel produtoPainel = new ProdutoPainel();
        CadastroPainel cadastroPainel = new CadastroPainel(produtoPainel);

        cardPanel.add(cadastroPainel, "cadastro");
        cardPanel.add(produtoPainel, "listagem");

        add(cardPanel);

        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "listagem");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}