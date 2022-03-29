import javax.swing.*;

import helpers.mathHelper;

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
    ImageIcon iconInsa = new ImageIcon("Logo Insa.png");

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
        rulesButton.setBackground(Color.white);
        mainPanel.add(rulesButton);

        aboutButton = new JButton("A propos");
        aboutButton.setBounds(mainPanel.getWidth() / 2 + 10, 370 + vertOffset, buttonWidth, buttonHeight);
        aboutButton.addActionListener(this);
        aboutButton.setBackground(Color.white);
        mainPanel.add(aboutButton);

        playButton = new JButton("Jouer");
        playButton.setBounds(mainPanel.getWidth() / 2 - buttonWidth / 2, 310 + vertOffset, buttonWidth, buttonHeight);
        playButton.addActionListener(this);
        playButton.setBackground(Color.white);
        mainPanel.add(playButton);

        // JLabel titre = new JLabel();
        // titre.setBounds(75,30,100,50);
        // titre.setText("Breakout !");

        JLabel imageBreakout = new JLabel(icon, JLabel.CENTER);
        imageBreakout.setBounds(mainPanel.getWidth() / 2 - icon.getIconWidth() / 2, 50, icon.getIconWidth(),
                icon.getIconHeight());
        mainPanel.add(imageBreakout);

        JLabel imageInsa = new JLabel(iconInsa, JLabel.CENTER);
        imageInsa.setBounds(mainPanel.getWidth() - iconInsa.getIconWidth() - 25,
                mainPanel.getHeight() - iconInsa.getIconHeight() - 45, iconInsa.getIconWidth(), iconInsa.getIconHeight());
        mainPanel.add(imageInsa);

        // About panel ------------------------------------------

        aboutPanel = new JPanel();
        aboutPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
        aboutPanel.setVisible(false);
        aboutPanel.setLayout(null);
        this.add(aboutPanel);
        int endOfText = aboutPanel.getHeight() / 2;

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
                "", "------------------- Crédits ------------------",
                "2022 - Réutilisation et modification autorisée", "Dernière mise à jour le 02/06/2021",
                "@Jeremy Banks - Développeur", "@Margaux Nagel - Développeur", "@Theo Gaigé - Développeur",
                "@Valentin Minot - Développeur" };

        JLabel[] labels = new JLabel[aboutText.length];
        for (int i = 0; i < aboutText.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(15, (int) mathHelper.lerp(15, endOfText, i * 1 / ((double) aboutText.length)),
                    aboutPanel.getWidth(), 25);
            labels[i].setText(aboutText[i]);
            aboutPanel.add(labels[i]);
        }

        returnButton = new JButton("Return to menu");
        aboutPanel.add(returnButton);
        returnButton.setBounds(15, endOfText, buttonWidth, buttonHeight);
        returnButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            Game game = new Game();
            // game.play();
            // game.end();
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