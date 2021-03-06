package main.java.View;

import main.java.Config.Config;
import main.java.Controller.Controller;
import main.java.Controller.MyKeyListener;
import main.java.Database.Database;
import main.java.Model.Apple;
import main.java.Model.Player;
import main.java.Model.RenderObject;
import main.java.Model.Snake;
import main.java.Util.AppState;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

/*
 * Main display for the game window
 * Display all other Panel such as Menu, Game panel, Score panel,...
 * Receive user input and send to Controller for handling
 *
 * @author Luu Pham Manh Ha - 1752001
 *         Nguyen Hoang Anh - 1652002
 *         Nguyen Thi Quynh Hoa - 1752017
 */

public class View extends JFrame{

    private final int width = Config.BOARD_COLUMNS * Config.SCALE;
    private final int height = Config.BOARD_COLUMNS * Config.SCALE;

    private final GamePanel gamePanel;
    private final ScorePanel scorePanel;
    private final MainMenuPanel mainMenuPanel;
    private final LoginPanel loginPanel;
    private final RegisterPanel registerPanel;
    private final RankingPanel rankingPanel;
    private final SettingPanel settingPanel;

    private final JDialog pauseDialog;

    private final Controller controller;

    private CardLayout card;
    private JPanel cardPanel;

    public View(Controller controller) {

        this.controller = controller;

        MyKeyListener keyListener = new MyKeyListener(controller);

        card = new CardLayout();
        cardPanel = new JPanel(card);

        setTitle("Snake");

        scorePanel = new ScorePanel(this,width / 2, height);
        gamePanel = new GamePanel(width, height);
        mainMenuPanel = new MainMenuPanel(this,width, height);
        loginPanel = new LoginPanel(this, width, height);
        registerPanel = new RegisterPanel(this, width, height);
        rankingPanel = new RankingPanel(this, width, height);
        settingPanel = new SettingPanel(this, width, height);

        cardPanel.add(loginPanel, "LOGIN");
        cardPanel.add(registerPanel, "REGISTER");
        cardPanel.add(mainMenuPanel, "MAINMENU");
        cardPanel.add(gamePanel, "GAMEBOARD");
        cardPanel.add(rankingPanel, "RANKING");
        cardPanel.add(settingPanel, "SETTING");

        add(cardPanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.EAST);
        addKeyListener(keyListener);

        pack();
        setResizable(false);
        setFocusable(true);
        setVisible(false);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                controller.saveData();
                Database.close();
                Config.saveConfig();
                System.exit(0);
            }
        });

        pauseDialog = new JDialog(this, "", Dialog.ModalityType.APPLICATION_MODAL);
        pauseDialog.getContentPane().add(new PausePanel(this));
        pauseDialog.setUndecorated(true);
        pauseDialog.pack();
        pauseDialog.setLocationRelativeTo(this);
        pauseDialog.setVisible(false);
        pauseDialog.setFocusable(false);
        pauseDialog.setFocusableWindowState(false);

        //setGlassPane(createGlassPane());
    }

    private JPanel createGlassPane() {

        JPanel glassPane = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {

                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 175));
                g.fillRect(0, 0, width, height);
            }
        };

        glassPane.setOpaque(false);
        glassPane.setBackground(null);
        glassPane.setVisible(false);
        return glassPane;
    }

    public void initScreen() {

        hideScorePanel();
        updateRankingBoard();
        display("LOGIN");
        setVisible(true);
    }

    public void display(String panel) {

        card.show(cardPanel, panel);
    }

    public void changeState(AppState state) {
        controller.changeState(state);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        gamePanel.repaint();
    }

    public void update(Snake snake, Apple apple, Player player) {
        LinkedList<RenderObject> list = new LinkedList<>();
        list.add(snake);
        list.add(apple);

        gamePanel.setObjectList(list);
        scorePanel.updateScore(player.getScore());
    }

    public void initTimer() {

        scorePanel.initTimer();
    }

    public void pauseTimer() {

        scorePanel.pauseTimer();
        //getGlassPane().setVisible(true);
        if (!pauseDialog.isVisible())
            pauseDialog.setVisible(true);
    }

    public void stopTimer() {

        scorePanel.stopTimer();
        //getGlassPane().setVisible(false);
        if (pauseDialog.isVisible())
            pauseDialog.setVisible(false);
    }

    public void resumeTimer() {

        scorePanel.resumeTimer();
        if (pauseDialog.isVisible())
            pauseDialog.setVisible(false);
    }

    public void gameOver() {

        controller.changeState(AppState.GameOver);
    }

    public void register(String username, String password, String playerName) {

        if (Database.addUser(username, password)) {

            controller.addNewPlayer(username, playerName);
        } else {

            JOptionPane.showMessageDialog(this, "Username already exist");
            System.out.println("Register fail");
        }
    }

    public void hideScorePanel() {
        if (scorePanel.isVisible())
            scorePanel.setVisible(false);
    }

    public void showScorePanel() {
       if (!scorePanel.isVisible())
            scorePanel.setVisible(true);
    }

    public void displayClock() {
        scorePanel.displayClock();
    }

    public void hideClock() {
        scorePanel.hideClock();
    }

    public void updatePlayerInfo(Player player) {

        scorePanel.updatePlayerInfo(player);
        scorePanel.updateScore(0);
        controller.setPlayer(player);
        settingPanel.setPlayerName(player.getName());
    }

    public void updateRankingBoard() {

        rankingPanel.updateRankingBoard();
    }

    public List<Document> getRankingBoard() {

        return controller.getRankingBoard();
    }

}
