import javax.swing.*;

import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.BorderLayout;

public class MainWindow extends JFrame {

    private JPanel panel1;
    private JPanel panel2;
    private JTabbedPane tabbedPane;
    private JTextArea txtrEnterUrlHere;
    private JButton btnNewButton;
    private JButton btnGetFile;
    private Choice choice;
    private JLabel lblFormat;
    private JButton btnView;
    private JMenuBar menuBar;
    private JMenu mnFile;
    private JMenuItem mntmSaveImage;
    private JMenu mnOptions;
    private JMenuItem mntmExit;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JLabel imageLabel;

    public MainWindow(int width, int height) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(640, 480);
        getContentPane().setLayout(null);

        panel1 = new JPanel(null);
        panel2 = new JPanel(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(12, 39, 614, 382);
        getContentPane().add(tabbedPane);
        setResizable(false);

        tabbedPane.addTab("Tab 1", panel1);
        panel1.setLayout(null);

        txtrEnterUrlHere = new JTextArea();
        txtrEnterUrlHere.setText("Enter URL here...");
        txtrEnterUrlHere.setBounds(12, 56, 585, 287);
        panel1.add(txtrEnterUrlHere);

        btnNewButton = new JButton("Get image");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setImage(new URL(txtrEnterUrlHere.getText()));
                } catch (MalformedURLException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid URL or no internet connection");
                }
            }
        });
        btnNewButton.setBounds(12, 12, 130, 25);
        panel1.add(btnNewButton);

        btnGetFile = new JButton("Get file");
        btnGetFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser();
                int replay = chooser.showOpenDialog(null);
                if(replay == JFileChooser.APPROVE_OPTION){
                    Main.setImage(chooser.getSelectedFile());
                }
            }
        });
        btnGetFile.setBounds(467, 12, 130, 25);
        panel1.add(btnGetFile);

        tabbedPane.addTab("Tab 2", panel2);
        panel2.setLayout(null);

        choice = new Choice();
        choice.setBounds(506, 11, 84, 20);
        panel2.add(choice);
        choice.add("png");
        choice.add("jpg");

        lblFormat = new JLabel("Format:");
        lblFormat.setBounds(446, 16, 55, 15);
        panel2.add(lblFormat);

        btnView = new JButton("View");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(Main.getImage() == null){ return; }
                imageLabel.setIcon(new ImageIcon(Main.getImage()));
                imageLabel.updateUI();
            }
        });
        btnView.setBounds(12, 12, 130, 25);
        panel2.add(btnView);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 56, 585, 287);
        panel2.add(scrollPane);

        panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setLayout(new BorderLayout(0, 0));

        imageLabel = new JLabel("");
        panel.add(imageLabel, BorderLayout.CENTER);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnFile = new JMenu("File");
        menuBar.add(mnFile);

        mntmSaveImage = new JMenuItem("Save image");
        mntmSaveImage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser();
                int replay = chooser.showSaveDialog(null);
                if(replay == JFileChooser.APPROVE_OPTION){
                    Main.saveImage(chooser.getSelectedFile(), choice.getSelectedItem());
                }
            }
        });
        mnFile.add(mntmSaveImage);

        mnOptions = new JMenu("Options");
        menuBar.add(mnOptions);

        mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        mnOptions.add(mntmExit);


        setVisible(true);

    }
}
