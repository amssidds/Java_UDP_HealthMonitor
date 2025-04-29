# Java UDP Health Monitoring Load Balancer

A simple Java-based UDP health-monitoring system that demonstrates:
- **Client** sends synthetic health data (heart rate & temperature)  
- **Load Balancer** listens on port 8000 and forwards incoming packets in round-robin to three servers  
- **Servers** listen on different ports (9000, 9001, 9002) and print received data  

---

## Contributors
- Muhammad Hasan  
- Ameen Siddiqui  
- Ahmed Alsaleh  

---

## How to Run

When using VS Code, make sure you have three **Run Configurations** (or launch three terminals):

1. **Server Instances**  
   - Run `server_part1` with argument `9000`  
   - Run `server_part1` with argument `9001`  
   - Run `server_part1` with argument `9002`  

2. **Load Balancer**  
   - Run `load_balancer` (no arguments) — listens on port **8000**

3. **Client**  
   - Run `client_part1` (no arguments) — sends data to load balancer  

**Order**:  
1. Start all three **Server** configurations (ports **9000**, **9001**, **9002**)  
2. Start the **Load Balancer**  
3. Finally, start the **Client**  
