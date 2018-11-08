/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricjo
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.*;

public class MovieBookingGUI extends JPanel implements ActionListener, ItemListener {

    private JButton Exit;
    private JButton New;
    private JButton Book;
    private JButton[][] seatingButtons;
    private JList sessionList;
    private JRadioButton Adult;
    private JRadioButton Elderly;
    private JRadioButton Child;
    private JCheckBox complementary;
    private DefaultListModel model;
    private JList currentReservation;
    private JScrollPane pane;
    private JLabel label;
    public static char rowPos;
    public static int colPos;
    public AdultReservation a;

    // constructor that creates the layout of the gui
    public MovieBookingGUI() {
        super(new BorderLayout());
        // center panel
        JPanel centerPanel = new JPanel(new GridLayout(3, 5));
        centerPanel.setPreferredSize(
                new Dimension(500, 400));
        seatingButtons = new JButton[3][5];
        for (int i = 0;
                i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                int rowIndex = i + 65;
                char row = (char) rowIndex;
                seatingButtons[i][j] = new JButton(row + "," + j);
                seatingButtons[i][j].addActionListener(this);
                centerPanel.add(seatingButtons[i][j]);
            }
        }

        add(centerPanel, BorderLayout.CENTER);

        // south panel
        JPanel southPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        Adult = new JRadioButton("Adult");
        Elderly = new JRadioButton("Elderly");
        Child = new JRadioButton("Child");
        group.add(Adult);
        group.add(Elderly);
        group.add(Child);
        complementary = new JCheckBox("Complementary");
        Exit = new JButton("Exit");
        New = new JButton("New");
        Book = new JButton("Book");
        Exit.addActionListener(this);
        New.addActionListener(this);
        Book.addActionListener(this);
        Adult.addItemListener(this);
        southPanel.add(Adult);
        southPanel.add(Elderly);
        southPanel.add(Child);
        southPanel.add(complementary);
        southPanel.add(Exit);
        southPanel.add(New);
        southPanel.add(Book);
        add(southPanel, BorderLayout.SOUTH);

        // east panel
        model = new DefaultListModel();
        currentReservation = new JList(model);
        currentReservation.setFixedCellWidth(150);
        pane = new JScrollPane(currentReservation);
        add(pane, BorderLayout.EAST);

        // north panel
        label = new JLabel("Seat bookings", SwingConstants.CENTER);
        label.setBackground(Color.GRAY);
        label.setLocation(200, 200);
        add(label, BorderLayout.NORTH);
    }

    // event handler methods which listens for any interaction with the user
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        char[] seatPos = new char[3];      
        for (int i = 0; i < seatingButtons.length; i++) {
            for (int j = 0; j < seatingButtons[i].length; j++) {
                if (seatingButtons[i][j] == e.getSource()) {
                    seatPos[0] = e.getActionCommand().toCharArray()[0];
                    seatPos[1] = e.getActionCommand().toCharArray()[2];
                    char x = seatPos[1];
                    colPos = Character.getNumericValue(x);
                    rowPos = seatPos[0];
                    seatingButtons[i][j].setEnabled(false);
                    System.out.println(colPos);
                }
            }
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        ArrayList<SeatReservation> seats = new ArrayList<>();
        while(e.getStateChange() == ItemEvent.SELECTED) {
                a = new AdultReservation(rowPos, colPos);
                seats.add(a);
                System.out.println(seats.get(0).toString());  
                break;
        }
    }

    // instantiates required objects 
    public static void main(String[] args) {
        Time t1 = new Time(14, 30);
        Time t2 = new Time(14, 25);
        Time t3 = new Time(11);
        MovieSession s1 = new MovieSession("Dr. What", 'M', t1);
        MovieSession s2 = new MovieSession("Bug-man", 'M', t2);
        MovieSession s3 = new MovieSession("Alivepool", 'R', t3);

        List<MovieSession> sessions = new ArrayList<>();
        sessions.add(s1);
        sessions.add(s2);
        sessions.add(s3);
        Collections.sort(sessions);
        System.out.println(sessions);

        MovieBookingGUI myPanel = new MovieBookingGUI();
        myPanel.model.addElement(sessions.get(0));
        myPanel.model.addElement(sessions.get(1));
        myPanel.model.addElement(sessions.get(2));
        JFrame frame = new JFrame("Scuffed Cinemas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myPanel);
        frame.pack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int sHeight = d.height;
        int sWidth = d.width;
        frame.setLocation(new Point((sWidth / 2) - (frame.getWidth() / 2),
                (sHeight / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
    }
}
