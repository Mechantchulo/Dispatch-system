# Ride Dispatch System - Friends Connection Guide

Connect from different laptops to use different menus (Customer, Driver, Admin)!

---

## 📍 Server Information

**Server IP:** Share this with your friends (check with `hostname -I`)  
**Port:** `8080`

---

## 🖥️ Setup Instructions

### **Your Laptop (Server - Admin Menu)**

1. **Start the server:**
   ```bash
   ./run-server.sh
   ```

2. **Note your IP address** (shown when server starts)
   - Share this IP with your friends
   - Example: `192.168.1.103`

---

### **Friend's Laptop (Customer or Driver Menu)**

#### Files Needed:
```
run-client.sh
src/
  main/
    network/
      Client.java
      Protocol.java
    model/
      *.java
```

#### Steps:

1. **Copy the client files** to their laptop

2. **Run the client:**
   ```bash
   ./run-client.sh
   ```

3. **Enter connection info:**
   ```
   Enter server IP address: 192.168.1.103  (your IP)
   Enter server port: 8080
   Choose role: CUSTOMER  (or DRIVER)
   ```

**Done!** 🚀

---

## 📦 Share Files with Friends

**Create a zip file:**

```bash
zip -r ride-client.zip run-client.sh src/main/network/ src/main/model/
```

**Send via:**
- Email
- Google Drive / Dropbox  
- USB drive
- AirDrop (Mac)

---

## 🧪 Test Locally First

Before friends join, test on your machine:

**Terminal 1 - Server:**
```bash
./run-server.sh
```

**Terminal 2 - Customer:**
```bash
./run-client.sh
# Enter: localhost, 8080, CUSTOMER
```

**Terminal 3 - Driver:**
```bash
./run-client.sh
# Enter: localhost, 8080, DRIVER
```

---

## 🎮 Available Roles & Menus

### **CUSTOMER Menu:**
- Request Ride
- View Pending Requests
- Exit

### **DRIVER Menu:**
- View All Drivers
- Set Available
- Set Busy
- View Available Drivers
- Exit

### **ADMIN Menu (you on server):**
- Dispatch Rides
- View Stats
- Add Driver
- View Drivers
- Exit

**Multiple people can use the same role!**

---

## 📱 Connection Requirements

✅ **All laptops on same WiFi network**  
✅ **Java 17+ installed on all machines**  
✅ **Server running** (`./run-server.sh`)  
✅ **Friends have your IP address**  
✅ **Port 8080 accessible**

---

## 🆘 Troubleshooting

**Connection Refused?**
```bash
# Check server is running
Server started on port 8080
Waiting for clients...

# Ping test from friend's laptop
ping 192.168.1.103
```

**Cannot find server IP?**
```bash
# Linux/Mac
hostname -I

# Show first IP only
hostname -I | awk '{print $1}'
```

**Firewall blocking?**
```bash
# Temporarily allow port 8080
sudo ufw allow 8080
```

**Compilation errors?**
```bash
# Check Java version (needs 17+)
java --version

# Recompile
javac -d bin src/main/**/*.java
```

---

## 🔥 Quick Reference

| Person | Command | Role |
|--------|---------|------|
| You (Server) | `./run-server.sh` | ADMIN |
| Friend 1 | `./run-client.sh` | CUSTOMER |
| Friend 2 | `./run-client.sh` | DRIVER |
| Friend 3 | `./run-client.sh` | CUSTOMER |

---

**Everyone connects to the same server, chooses their role, and uses their menu!** 🚗💨
