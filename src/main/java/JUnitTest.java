//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.net.*;
//
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class JUnitTest {
//
//    private final PrintStream standardOut = System.out;
//    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//    WebServer server;
//    Socket socket;
//    GUI gui;
//
//    @Before
//    public void setUp(){
//        socket = new Socket();
//        server = new WebServer(socket);
//        gui = new GUI();
//        System.setOut(new PrintStream(outputStreamCaptor));
//    }
//
//    //GUI constructor state test
//    @Test
//    public void testGUIConstructor(){
//        assertEquals("Initial GUI state is not stopped", gui.getState(), 1);
//    }
//
//    //Testing if constructor sets the socket properly
//    @Test
//    public void testConstructor(){
//        assertEquals("Socket not set within constructor", server.clientSocket, socket);
//    }
//
//    //Testing if run() starts correctly
//    @Test
//    public void testRun1(){
//        server.run();
//        assertEquals("New Communication Thread Started", outputStreamCaptor.toString().trim());
//    }
//
//    //Testing IO exception due to no input stream data
//    @Test(expected=IOException.class)
//    public void testRun2(){
//        WebServer servertest = new WebServer(socket);
//        servertest.run();
//    }
//
//    @Test
//    public void testStart(){}
//
//    @After
//    public void cleanUp(){
//        server = null;
//        socket = null;
//        GUI = null;
//        System.setOut(standardOut);
//    }
//}