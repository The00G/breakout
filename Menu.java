import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener{
    JPanel mainPanel;
    JButton rulesButton;
    JButton aboutButton;
    JButton playButton;
    
    JPanel aboutPanel;
    JButton returnButton;

    public Menu(){
        super("Breakout!");
        this.setBounds(200, 200, 1200, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        System.out.println(this.getSize().getWidth());

        //Main panel -------------------------------------------

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.green);
        mainPanel.setBounds(0,0,(int) this.getSize().getWidth(),(int) this.getSize().getHeight());
        mainPanel.setLayout(null);
        this.add(mainPanel);

        rulesButton = new JButton("Règles du jeu");
        rulesButton.setBounds(40,280,100,50);
        rulesButton.addActionListener(this);
        mainPanel.add(rulesButton);

        aboutButton = new JButton("A propos");
        aboutButton.setBounds(160,280,100,50);
        aboutButton.addActionListener(this);
        mainPanel.add(aboutButton);

        playButton = new JButton("Jouer");
        playButton.setBounds(75,200,100,50);
        playButton.addActionListener(this);
        mainPanel.add(playButton);

        JLabel titre = new JLabel();
        titre.setBounds(75,30,100,50);
        titre.setText("Breakout !");

        //About panel ------------------------------------------

        aboutPanel = new JPanel();
        aboutPanel.setBounds(0,0,250,350);
        aboutPanel.setVisible(false);
        this.add(aboutPanel);

        JLabel aboutText = new JLabel();
        aboutText.setBounds(10,10,230,330);
        aboutText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec metus odio, lacinia et ornare sit amet, imperdiet vitae turpis. Vestibulum in dui vitae est interdum suscipit. Nullam nec velit porttitor, egestas ligula quis, ultricies nisi. Pellentesque aliquet, quam eu finibus viverra, odio ex posuere leo, quis accumsan quam nibh sit amet ante. Phasellus tempus dapibus risus nec rhoncus. Praesent id ante sed nisi viverra dignissim. Aliquam aliquam maximus iaculis. In a accumsan ipsum, at lobortis dolor.");
        aboutPanel.add(aboutText);

        returnButton = new JButton("Return to menu");

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==playButton){
            Game game = new Game();
            //game.init(); //méthodes à coder
            //game.play();
            //game.end();
        }else if(e.getSource()==aboutButton){
            this.showAbout();
        }
    }

    public void showAbout(){
        mainPanel.setVisible(false);
        aboutPanel.setVisible(true);
    }
}