# ---------------------------------------------------------------
# PROGRAM: DNS Lookup
# LANGUAGE: Python
# CONCEPT: DNS resolution using socket library
# ---------------------------------------------------------------
# THEORY:
# DNS (Domain Name System) is a service that translates domain names
# (like www.google.com) into IP addresses (like 142.250.193.78) and vice versa.
# Two main types of lookups:
#   1. Forward DNS Lookup  → Hostname to IP Address
#   2. Reverse DNS Lookup  → IP Address to Hostname
# This program demonstrates both operations using Python's socket module.
# ---------------------------------------------------------------

import socket  # Importing socket library to perform DNS lookups

def dns_lookup():
    while True:
        # Ask the user for the type of lookup
        choice = input(
            "\nEnter '1' for IP to Hostname lookup or '2' for Hostname to IP lookup (or 'q' to quit): "
        ).strip()

        # -------------------------------
        # OPTION 1: IP to Hostname lookup
        # -------------------------------
        if choice == '1':
            ip_address = input("Enter IP address: ").strip()
            try:
                # Perform reverse DNS lookup
                hostname = socket.gethostbyaddr(ip_address)[0]
                print(f"Hostname for IP {ip_address} is: {hostname}")
            except socket.herror:
                # Raised if the hostname cannot be resolved
                print("Hostname not found for this IP address.")
            except Exception as e:
                print(f"An error occurred: {e}")

        # --------------------------------
        # OPTION 2: Hostname to IP lookup
        # --------------------------------
        elif choice == '2':
            hostname = input("Enter hostname (URL): ").strip()
            try:
                # Perform forward DNS lookup
                ip_address = socket.gethostbyname(hostname)
                print(f"IP address for hostname {hostname} is: {ip_address}")
            except socket.gaierror:
                # Raised if the IP cannot be found for the given hostname
                print("IP address not found for this hostname.")
            except Exception as e:
                print(f"An error occurred: {e}")

        # -------------------------------
        # EXIT CONDITION
        # -------------------------------
        elif choice.lower() == 'q':
            print("Exiting the program.")
            break
        else:
            print("Invalid choice. Please enter '1', '2', or 'q'.")

# Entry point of the program
if __name__ == "__main__":
    dns_lookup()

# ---------------------------------------------------------------
# HOW TO RUN:
# 1. Save this code as dns_lookup.py
# 2. Open terminal/command prompt and navigate to the file directory
# 3. Run: python dns_lookup.py
# 4. Enter '1' for IP → Hostname lookup
#    Enter '2' for Hostname → IP lookup
#    Enter 'q' to quit
# ---------------------------------------------------------------

# ---------------------------------------------------------------
# DETAILED THEORY NOTES:
# ---------------------------------------------------------------
# 1. DNS (Domain Name System):
#    - Internet's directory service, mapping domain names to IP addresses.
#    - Example: www.google.com → 142.250.193.78
#    - Makes internet navigation user-friendly.
#
# 2. Forward Lookup:
#    - Hostname → IP address
#    - Function: socket.gethostbyname(hostname)
#
# 3. Reverse Lookup:
#    - IP address → Hostname
#    - Function: socket.gethostbyaddr(ip_address)
#
# 4. Common DNS Record Types:
#    - A Record: Maps hostname to IPv4
#    - AAAA Record: Maps hostname to IPv6
#    - CNAME Record: Alias of another domain
#    - MX Record: Mail server routing
#
# 5. Error Handling:
#    - socket.gaierror: Hostname cannot be resolved
#    - socket.herror: IP cannot be resolved to hostname
#
# 6. Practical Uses:
#    - Network diagnostics, server testing, logging, and monitoring
# ---------------------------------------------------------------
