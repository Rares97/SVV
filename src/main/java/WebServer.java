import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.Scanner;

class GUI implements ActionListener{

    private JFrame frame;
    private JButton start_button;
    private JButton stop_button;
    private JButton main_button;
    private JLabel label;
    private JPanel panel;
    private int state;

    public GUI() {
        state = 1;
        frame = new JFrame();
        start_button = new JButton("Start");
        start_button.addActionListener(this);
        stop_button = new JButton("Stop");
        stop_button.addActionListener(this);
        main_button = new JButton("Maintanance");
        main_button.addActionListener(this);
        panel = new JPanel();
        label = new JLabel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(start_button);
        panel.add(stop_button);
        panel.add(main_button);
        frame.add(panel, BorderLayout.CENTER);
        panel.add(label);
        ChangeLabel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(start_button)){
            state = 2;
            ChangeLabel();
        }
        else if(e.getSource().equals(stop_button)){
            state = 1;
            ChangeLabel();
        }
        else if(e.getSource().equals(main_button)){
            state = 3;
            ChangeLabel();
        }
    }

    private void ChangeLabel(){
        if(state==1){
            label.setText("Stopped");
        }
        else if(state==2){
            label.setText("Running");
        }
        else if(state==3){
            label.setText("Maintenance");
        }
    }

    public int getState(){
        return this.state;
    }
}

public class WebServer extends Thread {

    static final File home = new File("C:\\Users\\Sefu\\Desktop\\Temp\\Project\\a.html");
    static final File tab1 = new File("C:\\Users\\Sefu\\Desktop\\Temp\\Project\\b.html");
    static final File tab2 = new File("C:\\Users\\Sefu\\Desktop\\Temp\\Project\\c.html");
    static final File tab3 = new File("C:\\Users\\Sefu\\Desktop\\Temp\\Project\\d.html");
    static final File main = new File("C:\\Users\\Sefu\\Desktop\\Temp\\Project\\e.html");

    static GUI gui;

    protected Socket clientSocket;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        gui = new GUI();

        try {
            serverSocket = new ServerSocket(10008);
            System.out.println("Connection Socket Created");
            try {
                while (true) {
                    System.out.println("Waiting for Connection");
                    new WebServer(serverSocket.accept());
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10008.");
            System.exit(1);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close port: 10008.");
                System.exit(1);
            }
        }
    }

    private WebServer(Socket clientSoc) {
        clientSocket = clientSoc;
        start();
    }

    public void run(){
        while(gui.getState()==1){
            ;
        }
        System.out.println("New Communication Thread Started");

        if(gui.getState()==3){
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                        true);
                out.write(("HTTP/1.1 200 OK\r\n"));
                out.write(("\r\n"));
                Scanner file_in = new Scanner(main);
                while (file_in.hasNextLine()) {
                    String line = file_in.nextLine();
                    out.write(line);
                }
                file_in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Problem with Communication Server");
                System.exit(1);
            }
        }
        else if(gui.getState()==2){
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                        true);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        clientSocket.getInputStream()));
                String inputLine;

                //Process /tab
                StringBuilder request = new StringBuilder();
                String request_line = in.readLine();
                while (!request_line.isBlank()) {
                    request.append(request_line + "\r\n");
                    System.out.println(request);
                    request_line = in.readLine();
                }
                String first_line = request.toString().split("\n")[0];
                String tab = first_line.split(" ")[1];

                //HTML
                out.write(("HTTP/1.1 200 OK\r\n"));
                out.write(("\r\n"));
                if (tab.equals("/b")) {
                    Scanner file_in = new Scanner(tab1);
                    while (file_in.hasNextLine()) {
                        String line = file_in.nextLine();
                        out.write(line);
                    }
                    file_in.close();
                } else if (tab.equals("/c")) {
                    Scanner file_in = new Scanner(tab2);
                    while (file_in.hasNextLine()) {
                        String line = file_in.nextLine();
                        out.write(line);
                    }
                    file_in.close();
                } else if (tab.equals("/d")) {
                    Scanner file_in = new Scanner(tab3);
                    while (file_in.hasNextLine()) {
                        String line = file_in.nextLine();
                        out.write(line);
                    }
                    file_in.close();
                } else {
                    Scanner file_in = new Scanner(home);
                    while (file_in.hasNextLine()) {
                        String line = file_in.nextLine();
                        out.write(line);
                    }
                    file_in.close();
                }

                out.close();
                in.close();
                clientSocket.close();

            } catch (IOException e) {
                System.err.println("Problem with Communication Server");
                System.exit(1);
            }
        }
    }
}