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
    JPanel rulesPanel;
    JButton returnAboutButton;
    JButton returnRulesButton;
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
                mainPanel.getHeight() - iconInsa.getIconHeight() - 45, iconInsa.getIconWidth(),
                iconInsa.getIconHeight());
        mainPanel.add(imageInsa);

        // About panel ------------------------------------------

        aboutPanel = new JPanel();
        aboutPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
        aboutPanel.setVisible(false);
        aboutPanel.setLayout(null);
        this.add(aboutPanel);
        int endOfAboutText = aboutPanel.getHeight() / 2;

        String[] aboutText = {
                "Ce jeu a été développé dans le cadre du projet de fin d'année du cours Algorithmique et programmation 1 & 2 dispensé en première année de FIMI à l'INSA Lyon.",
                "Il utilise les notions d'algorithmique et de programmation en Java découvertes tout au long de l'année mais également d'autres notions découvertes dans un cadre extra-scolaire.",
                "", "------------------- Crédits ------------------",
                "2022 - Réutilisation et modification autorisée", "Dernière mise à jour le 02/06/2021",
                "@Jeremy Banks - Développeur", "@Margaux Nagel - Développeur", "@Theo Gaigé - Développeur",
                "@Valentin Minot - Développeur" };

        JLabel[] aboutLabels = new JLabel[aboutText.length];
        for (int i = 0; i < aboutText.length; i++) {
            aboutLabels[i] = new JLabel();
            aboutLabels[i].setBounds(15, (int) mathHelper.lerp(15, endOfAboutText, i * 1 / ((double) aboutText.length)),
                    aboutPanel.getWidth(), 25);
            aboutLabels[i].setText(aboutText[i]);
            aboutPanel.add(aboutLabels[i]);
        }

        returnAboutButton = new JButton("Return to menu");
        aboutPanel.add(returnAboutButton);
        returnAboutButton.setBounds(15, endOfAboutText, buttonWidth, buttonHeight);
        returnAboutButton.setBackground(Color.WHITE);
        returnAboutButton.addActionListener(this);

        // Rules pannel -----------------------------------------

        rulesPanel = new JPanel();
        rulesPanel.setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
        rulesPanel.setVisible(false);
        rulesPanel.setLayout(null);
        this.add(rulesPanel);
        int endOfRulesText = rulesPanel.getHeight() / 2;

        String[] rulesText = { "J'aime les frites", "et le saucisson" };

        JLabel[] rulesLabels = new JLabel[rulesText.length];
        for (int i = 0; i < rulesText.length; i++) {
            rulesLabels[i] = new JLabel();
            rulesLabels[i].setBounds(15, (int) mathHelper.lerp(15, endOfRulesText, i * 1 / ((double) rulesText.length)),
                    rulesPanel.getWidth(), 25);
            rulesLabels[i].setText(rulesText[i]);
            rulesPanel.add(rulesLabels[i]);
        }

        returnRulesButton = new JButton("Retour au menu");
        rulesPanel.add(returnRulesButton);
        returnRulesButton.setBounds(15, endOfAboutText, buttonWidth, buttonHeight);
        returnRulesButton.setBackground(Color.WHITE);
        returnRulesButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            Game game = new Game();
        } else if (e.getSource() == aboutButton) {
            this.showAbout();
        } else if (e.getSource() == returnAboutButton) {
            this.showMainMenu();
        } else if (e.getSource() == returnRulesButton) {
            this.showMainMenu();
        } else if (e.getSource() == rulesButton) {
            this.showRules();
        }
    }

    public void showAbout() {
        mainPanel.setVisible(false);
        aboutPanel.setVisible(true);
    }

    public void showMainMenu() {
        mainPanel.setVisible(true);
        aboutPanel.setVisible(false);
        rulesPanel.setVisible(false);
    }

    public void showRules() {
        mainPanel.setVisible(false);
        rulesPanel.setVisible(true);
    }
}