# -------------------------------------------------------------
# PROGRAM: Demonstrate Subnetting and Find Subnet Masks
# LANGUAGE: Python
# -------------------------------------------------------------

# Import necessary modules
# 'ipaddress' provides classes to handle and manipulate IPv4/IPv6 addresses and networks
import ipaddress

# -------------------------------------------------------------
# MAIN PROGRAM
# -------------------------------------------------------------
def main():
    print("=== SUBNETTING PROGRAM ===")

    # Take user input for IP address and prefix (CIDR notation)
    # Example: IP Address = 192.168.10.0 and CIDR = 28
    ip_address = input("Enter IP Address (e.g., 192.168.10.0): ")
    prefix_length = int(input("Enter CIDR Prefix (e.g., 28 for /28): "))

    # Combine IP and CIDR to form a full network address
    # Example: 192.168.10.0/28
    network = ipaddress.ip_network(f"{ip_address}/{prefix_length}", strict=False)

    # ---------------------------------------------------------
    # DISPLAY BASIC INFORMATION
    # ---------------------------------------------------------
    print("\n--- NETWORK INFORMATION ---")
    print(f"Network Address: {network.network_address}")
    print(f"Broadcast Address: {network.broadcast_address}")
    print(f"Subnet Mask: {network.netmask}")
    print(f"Wildcard Mask: {network.hostmask}")

    # ---------------------------------------------------------
    # CALCULATE HOST DETAILS
    # ---------------------------------------------------------
    # Total number of usable hosts in subnet = 2^(32 - prefix) - 2
    num_hosts = network.num_addresses - 2
    print(f"Number of Usable Hosts: {num_hosts}")

    # First and last usable IP in the subnet
    # Convert all host addresses to list and display first & last
    all_hosts = list(network.hosts())
    print(f"First Usable Host: {all_hosts[0]}")
    print(f"Last Usable Host: {all_hosts[-1]}")

    # ---------------------------------------------------------
    # DETERMINE IP CLASS
    # ---------------------------------------------------------
    first_octet = int(ip_address.split(".")[0])
    if 1 <= first_octet <= 126:
        ip_class = "Class A"
    elif 128 <= first_octet <= 191:
        ip_class = "Class B"
    elif 192 <= first_octet <= 223:
        ip_class = "Class C"
    elif 224 <= first_octet <= 239:
        ip_class = "Class D (Multicast)"
    elif 240 <= first_octet <= 255:
        ip_class = "Class E (Experimental)"
    else:
        ip_class = "Unknown"

    print(f"IP Address Class: {ip_class}")

    # ---------------------------------------------------------
    # SHOW ALL SUBNETS (optional demonstration)
    # ---------------------------------------------------------
    print("\n--- SUBNET BREAKDOWN ---")
    # Divide network further into smaller subnets (for example /30)
    # This shows how a bigger network can be divided into smaller networks
    for subnet in network.subnets(new_prefix=30):
        print(subnet)

# -------------------------------------------------------------
# ENTRY POINT
# -------------------------------------------------------------
if __name__ == "__main__":
    main()


# -------------------------------------------------------------
# HOW TO RUN THE PROGRAM
# -------------------------------------------------------------
# 1. Save this code as subnetting.py
# 2. Open terminal or command prompt.
# 3. Navigate to the folder containing the file.
# 4. Run the program using:
#       python subnetting.py
# 5. Example input:
#       Enter IP Address (e.g., 192.168.10.0): 192.168.10.0
#       Enter CIDR Prefix (e.g., 28 for /28): 28
# 6. Output will show:
#       - Network address
#       - Broadcast address
#       - Subnet mask
#       - Wildcard mask
#       - Usable host range
#       - IP class
#       - Optional subnet divisions


# -------------------------------------------------------------
# THEORY NOTES FOR ORAL / VIVA
# -------------------------------------------------------------

# 1️⃣ Subnetting:
#    - It is the process of dividing a large network (like Class A, B, or C)
#      into smaller, more manageable subnetworks.
#    - Helps in better IP utilization and improved security.

# 2️⃣ CIDR (Classless Inter-Domain Routing):
#    - CIDR notation (e.g., /28) tells how many bits are used for the network portion.
#    - Example:
#        /24 → 255.255.255.0 (256 total addresses)
#        /28 → 255.255.255.240 (16 total addresses)

# 3️⃣ Subnet Mask:
#    - Defines which part of the IP represents the network and which part represents the host.
#    - Example: For 192.168.10.0/28 → Subnet mask = 255.255.255.240

# 4️⃣ Wildcard Mask:
#    - It’s the inverse of the subnet mask.
#    - Used mainly in routers (like Cisco ACLs) for matching IP ranges.
#    - Example: Subnet mask 255.255.255.240 → Wildcard mask 0.0.0.15

# 5️⃣ IP Classes:
#    - Class A: 1–126 (Default mask /8)
#    - Class B: 128–191 (Default mask /16)
#    - Class C: 192–223 (Default mask /24)
#    - Class D: 224–239 (Multicast)
#    - Class E: 240–255 (Experimental)

# 6️⃣ Methods Used:
#    - ipaddress.ip_network(): Creates an IPv4/IPv6 network object.
#    - network.network_address → Gives network ID.
#    - network.broadcast_address → Gives broadcast ID.
#    - network.netmask → Returns subnet mask.
#    - network.hostmask → Returns wildcard mask.
#    - network.hosts() → Returns list of usable host IPs.
#    - network.subnets(new_prefix) → Divides into smaller subnets.

# 7️⃣ Why Subnetting:
#    - Reduces broadcast traffic.
#    - Efficiently manages IP addresses.
#    - Enhances network performance and security.

# -------------------------------------------------------------
# END OF PROGRAM
# -------------------------------------------------------------
