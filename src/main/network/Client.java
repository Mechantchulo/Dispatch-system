package network;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner scanner;
    private String role;
    
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
    
    public void start() {
        scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("   RIDE DISPATCH SYSTEM - CLIENT");
        System.out.println("========================================\n");
        
        // Check environment variable first (for Docker compatibility)
        String serverHost = System.getenv("SERVER_HOST");
        if (serverHost == null) {
            System.out.print("Enter server IP address or hostname (default: localhost): ");
            serverHost = scanner.nextLine().trim();
            if (serverHost.isEmpty()) serverHost = "localhost";
        }
        
        int port = 8080;
        String portEnv = System.getenv("SERVER_PORT");
        if (portEnv == null) {
            System.out.print("Enter server port (default: 8080): ");
            String portInput = scanner.nextLine().trim();
            if (!portInput.isEmpty()) {
                try {
                    port = Integer.parseInt(portInput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid port, using default 8080");
                }
            }
        } else {
            port = Integer.parseInt(portEnv);
        }
        
        if (!connect(serverHost, port)) {
            System.out.println("Failed to connect. Exiting...");
            return;
        }
        
        chooseRole();
        runMenu();
        disconnect();
    }
    
    private boolean connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("\nConnected to server at " + host + ":" + port);
            return true;
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
            return false;
        }
    }
    
    private void chooseRole() {
        // Check if role is set via environment variable
        String envRole = System.getenv("CLIENT_ROLE");
        if (envRole != null) {
            role = envRole.toUpperCase();
            System.out.println("\nYou are: " + role);
            return;
        }
        
        System.out.println("\nSELECT ROLE:");
        System.out.println("  1. Customer");
        System.out.println("  2. Driver");
        System.out.println("  3. Admin");
        System.out.print("Choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        role = choice == 1 ? "CUSTOMER" : choice == 2 ? "DRIVER" : "ADMIN";
        System.out.println("\nYou are: " + role);
    }
    
    private void runMenu() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput();
            
            switch (role) {
                case "CUSTOMER":
                    running = handleCustomerChoice(choice);
                    break;
                case "DRIVER":
                    running = handleDriverChoice(choice);
                    break;
                case "ADMIN":
                    running = handleAdminChoice(choice);
                    break;
            }
        }
    }
    
    private void displayMenu() {
        System.out.println("\n========== " + role + " MENU ==========");
        switch (role) {
            case "CUSTOMER":
                System.out.println("1. Request Ride");
                System.out.println("2. View Pending");
                System.out.println("3. Exit");
                break;
            case "DRIVER":
                System.out.println("1. View All Drivers");
                System.out.println("2. Set Available");
                System.out.println("3. Set Busy");
                System.out.println("4. View Available");
                System.out.println("5. Exit");
                break;
            case "ADMIN":
                System.out.println("1. Dispatch Rides");
                System.out.println("2. View Stats");
                System.out.println("3. Add Driver");
                System.out.println("4. View Drivers");
                System.out.println("5. Exit");
                break;
        }
        System.out.print("Choice: ");
    }
    
    private boolean handleCustomerChoice(int choice) {
        switch (choice) {
            case 1: requestRide(); return true;
            case 2: sendCommand(Protocol.VIEW_PENDING_REQUESTS); return true;
            case 3: return false;
            default: return true;
        }
    }
    
    private boolean handleDriverChoice(int choice) {
        switch (choice) {
            case 1: sendCommand(Protocol.VIEW_ALL_DRIVERS); return true;
            case 2: setDriverStatus(true); return true;
            case 3: setDriverStatus(false); return true;
            case 4: sendCommand(Protocol.VIEW_AVAILABLE_DRIVERS); return true;
            case 5: return false;
            default: return true;
        }
    }
    
    private boolean handleAdminChoice(int choice) {
        switch (choice) {
            case 1: sendCommand(Protocol.DISPATCH_REQUESTS); return true;
            case 2: sendCommand(Protocol.VIEW_STATISTICS); return true;
            case 3: addDriver(); return true;
            case 4: sendCommand(Protocol.VIEW_ALL_DRIVERS); return true;
            case 5: return false;
            default: return true;
        }
    }
    
    private void requestRide() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Pickup (A-E): ");
        String pickup = scanner.nextLine().toUpperCase();
        System.out.print("Destination (A-E): ");
        String dest = scanner.nextLine().toUpperCase();
        sendCommand(Protocol.buildMessage(Protocol.REQUEST_RIDE, name, pickup, dest));
    }
    
    private void setDriverStatus(boolean available) {
        System.out.print("Driver ID: ");
        String id = scanner.nextLine();
        sendCommand(Protocol.buildMessage(Protocol.SET_DRIVER_STATUS, id, String.valueOf(available)));
    }
    
    private void addDriver() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Location (A-E): ");
        String loc = scanner.nextLine().toUpperCase();
        sendCommand(Protocol.buildMessage(Protocol.ADD_DRIVER, name, loc));
    }
    
    private void sendCommand(String command) {
        try {
            out.println(command);
            String response = in.readLine();
            String[] parts = Protocol.parseMessage(response);
            String message = parts.length > 1 ? parts[1] : "";
            
            // Replace literal \n with actual newlines for display
            message = message.replace("\\n", "\n");
            
            System.out.println("\n" + message);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private void disconnect() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            scanner.close();
            System.out.println("\nDisconnected. Goodbye!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter number: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}
