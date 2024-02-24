import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame
{
    private JButton rockButton, paperButton, scissorsButton, quitButton;
    private JTextArea resultTextArea;
    private JTextField playerWinsField, computerWinsField, tiesField;
    private int playerWins = 0, computerWins = 0, ties = 0;

    public RockPaperScissorsFrame()
    {
        setTitle("Rock Paper Scissors Game");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Choose Your Weapon"));
        rockButton = new JButton(resizeIcon(new ImageIcon("./resources/rock.jpg"), 100, 100));
        paperButton = new JButton(resizeIcon(new ImageIcon("./resources/paper.jpg"), 100, 100));
        scissorsButton = new JButton(resizeIcon(new ImageIcon("./resources/scissors.jpg"), 100, 100));
        quitButton = new JButton("Quit");
        buttonsPanel.add(rockButton);
        buttonsPanel.add(paperButton);
        buttonsPanel.add(scissorsButton);
        buttonsPanel.add(quitButton);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 2));
        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0", 5);
        playerWinsField.setEditable(false);
        statsPanel.add(playerWinsField);
        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0", 5);
        computerWinsField.setEditable(false);
        statsPanel.add(computerWinsField);
        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0", 5);
        tiesField.setEditable(false);
        statsPanel.add(tiesField);

        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        add(buttonsPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        rockButton.addActionListener(e -> playGame("Rock"));
        paperButton.addActionListener(e -> playGame("Paper"));
        scissorsButton.addActionListener(e -> playGame("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));
    }


    private ImageIcon resizeIcon(ImageIcon icon, int width, int height)
    {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private void playGame(String playerChoice)
    {
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[new Random().nextInt(choices.length)];
        String result;

        if (playerChoice.equals(computerChoice))
        {
            result = playerChoice + " equals " + computerChoice + " (Tie)";
            ties++;
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock"))) {
            result = playerChoice + " beats " + computerChoice + " (Player wins)";
            playerWins++;
        } else {
            result = computerChoice + " beats " + playerChoice + " (Computer Wins)";
            computerWins++;
        }

        updateStatsAndResult(result);
    }

    private void updateStatsAndResult(String result)
    {
        playerWinsField.setText(String.valueOf(playerWins));
        computerWinsField.setText(String.valueOf(computerWins));
        tiesField.setText(String.valueOf(ties));
        resultTextArea.append(result + "\n");
    }
}