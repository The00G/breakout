import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    JPanel mainPanel;
    JButton rulesButton;
    JButton aboutButton;
    JButton playButton;
    JPanel aboutPanel;
    JButton returnButton;
    ImageIcon icon = new ImageIcon("breakout logo.png");

    int buttonWidth = 150;
    int buttonHeight = 50;
    int vertOffset = 175;

    public Menu() {
        super("Breakout!");
        this.setBounds(400, 100, 1200, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // Main panel -------------------------------------------

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.black);
        mainPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);
        this.add(mainPanel);

        rulesButton = new JButton("Règles du jeu");
        rulesButton.setBounds(mainPanel.getWidth() / 2 - buttonWidth - 10, 370 + vertOffset, buttonWidth, buttonHeight);
        rulesButton.addActionListener(this);
        mainPanel.add(rulesButton);

        aboutButton = new JButton("A propos");
        aboutButton.setBounds(mainPanel.getWidth() / 2 + 10, 370 + vertOffset, buttonWidth, buttonHeight);
        aboutButton.addActionListener(this);
        mainPanel.add(aboutButton);

        playButton = new JButton("Jouer");
        playButton.setBounds(mainPanel.getWidth() / 2 - buttonWidth / 2, 310 + vertOffset, buttonWidth, buttonHeight);
        playButton.addActionListener(this);
        mainPanel.add(playButton);

        // JLabel titre = new JLabel();
        // titre.setBounds(75,30,100,50);
        // titre.setText("Breakout !");

        JLabel image = new JLabel(icon, JLabel.CENTER);
        image.setBounds(mainPanel.getWidth() / 2 - icon.getIconWidth() / 2, 50, icon.getIconWidth(),
                icon.getIconHeight());
        mainPanel.add(image);

        // About panel ------------------------------------------

        aboutPanel = new JPanel();
        aboutPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
        aboutPanel.setVisible(false);
        aboutPanel.setLayout(null);
        this.add(aboutPanel);

        // JLabel aboutText = new JLabel();
        // aboutText.setBounds(0, 0, aboutPanel.getWidth(), 100);
        // aboutText.setBorder(BorderFactory.createTitledBorder("A propos"));
        // aboutText.setText(
        // "Ce jeu a été développé dans le cadre du projet de fin d'année du cours
        // Algorithmique et programmation 1 & 2 dispensé en première année de FIMI à
        // l'INSA Lyon."
        // + System.lineSeparator() + "\n"
        // + "Il utilise les notions d'algorithmique et de programmation en Java
        // découvertes tout au long de l'année mais également d'autres notions
        // découvertes dans un cadre extra-scolaire.");
        // aboutPanel.add(aboutText);

        String[] aboutText = {
                "Ce jeu a été développé dans le cadre du projet de fin d'année du cours Algorithmique et programmation 1 & 2 dispensé en première année de FIMI à l'INSA Lyon.",
                "Il utilise les notions d'algorithmique et de programmation en Java découvertes tout au long de l'année mais également d'autres notions découvertes dans un cadre extra-scolaire.",
                "", "------------------- Crédits ------------------", "",
                "2022 - Réutilisation et modification autorisée", "Dernière mise à jour le 02/06/2021",
                "@Jeremy Banks - Développeur", "@Margaux Nagel - Développeur", "@Theo Gaigé - Développeur" , "@Valentin Minot - Développeur"};

        returnButton = new JButton("Return to menu");
        aboutPanel.add(returnButton);
        returnButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            Game game = new Game();
            game.play();
            game.end();
        } else if (e.getSource() == aboutButton) {
            this.showAbout();
        } else if (e.getSource() == returnButton) {
            this.showMainMenu();
        }
    }

    public void showAbout() {
        mainPanel.setVisible(false);
        aboutPanel.setVisible(true);
    }

    public void showMainMenu() {
        mainPanel.setVisible(true);
        aboutPanel.setVisible(false);
    }
}